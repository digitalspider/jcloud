package au.com.jcloud.actionbean;

import static au.com.jcloud.WebConstants.BILLING_JSP;
import static au.com.jcloud.WebConstants.CART_JSP;
import static au.com.jcloud.WebConstants.PATH_SECURE;
import static au.com.jcloud.WebConstants.PATH_SECURE_JSP;
import static au.com.jcloud.WebConstants.THANKYOU_JSP;
import static au.com.jcloud.actionbean.CheckoutActionBean.JSP_BINDING;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.security.PermitAll;

import com.avaje.ebean.Ebean;

import au.com.jcloud.enums.Status;
import au.com.jcloud.model.Address;
import au.com.jcloud.model.Cart;
import au.com.jcloud.model.CartItem;
import au.com.jcloud.model.CreditCard;
import au.com.jcloud.model.Product;
import au.com.jcloud.model.User;
import au.com.jcloud.service.UserService;
import au.com.jcloud.util.PathParts;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.DontBind;
import net.sourceforge.stripes.action.DontValidate;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.HandlesEvent;
import net.sourceforge.stripes.action.RedirectResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.integration.spring.SpringBean;

/**
 * Created by david.vittor on 3/08/16.
 */
@PermitAll
@UrlBinding(PATH_SECURE + JSP_BINDING)
public class CheckoutActionBean extends JCSecureActionBean {

	public static final String JSP_BINDING = "/checkout";

	public enum PageAction {
		CART,
		BILLING,
		PURCHASE;

		public static PageAction parse(String data) {
			for (PageAction action : PageAction.values()) {
				if (action.name().equalsIgnoreCase(data)) {
					return action;
				}
			}
			return null;
		}
	}

	public enum CartAction {
		ADD;

		public static CartAction parse(String data) {
			for (CartAction action : CartAction.values()) {
				if (action.name().equalsIgnoreCase(data)) {
					return action;
				}
			}
			return null;
		}
	}

	private Cart cart;
	private Address address;
	private CreditCard creditCard;
	private String name;

	@SpringBean
	private UserService userService;

	public String getCartPageJsp() {
		return PATH_SECURE_JSP + getJSPBinding() + "/" + CART_JSP;
	}

	public String getBillingPageJsp() {
		return PATH_SECURE_JSP + getJSPBinding() + "/" + BILLING_JSP;
	}

	public String getThankYouPageJsp() {
		return PATH_SECURE_JSP + getJSPBinding() + "/" + THANKYOU_JSP;
	}

	public Resolution getCartResolution() {
		return new ForwardResolution(getCartPageJsp());
	}

	public Resolution getBillingResolution() {
		return new ForwardResolution(getBillingPageJsp());
	}

	public Resolution getThankYouResolution() {
		return new ForwardResolution(getThankYouPageJsp());
	}

	@Override
	public Resolution getDefaultResolution() {
		return getCartResolution();
	}

	@PermitAll
	@DontValidate
	@DontBind
	@DefaultHandler
	@Override
	public Resolution action() {
		User user = getUser();
		List<Cart> carts = Ebean.find(Cart.class).setDisableLazyLoading(true).fetch("cartItems").fetch("user").where().eq("user", user).findList();
		if (carts.isEmpty()) {
			cart = new Cart();
			cart.setCartItems(new ArrayList<CartItem>());
			cart.setStatus(Status.ENABLED.value());
			cart.setUser(user);
			Ebean.save(cart);
			carts = Ebean.find(Cart.class).setDisableLazyLoading(true).fetch("cartItems").fetch("user").where().eq("user", user).findList();
		}
		if (carts.isEmpty()) {
			return getDefaultResolution();
		}
		cart = carts.get(0);
		if (cart.getCartItems() == null) {
			cart.setCartItems(new ArrayList<CartItem>());
		}

		PathParts pathParts = getPathParts(); // 0=checkout, 1=add, 2=123 (productId), 3=description
		LOG.info("pathParts=" + pathParts);
		if (pathParts.size() > 1) {
			String action = pathParts.get(1);
			LOG.info("action=" + action);
			switch (PageAction.parse(action)) {
			case CART:
				if (pathParts.size() > 2) {
					String cartAction = pathParts.get(2);
					LOG.info("cartAction=" + cartAction);
					switch (CartAction.parse(cartAction)) {
					case ADD:
						int productId = pathParts.getInt(3);
						LOG.info("adding productId=" + productId);
						if (productId > 0) {
							Product product = Ebean.find(Product.class).setDisableLazyLoading(true).where().idEq(Long.valueOf(productId)).findUnique();
							LOG.info("adding product=" + product);
							if (product != null) {
								boolean added = false;
								for (CartItem cartItem : cart.getCartItems()) {
									if (cartItem.getProduct().getId().equals(product.getId())) {
										cartItem.setQuantity(cartItem.getQuantity() + 1);
										Ebean.update(cartItem);
										added = true;
										break;
									}
								}
								if (!added) {
									CartItem cartItem = new CartItem();
									cartItem.setProduct(product);
									cartItem.setCart(cart);
									cartItem.setQuantity(1);
									Ebean.save(cartItem);
									cart.getCartItems().add(cartItem);
								}
							}
						}
					}
				}
				return getCartResolution();
			case BILLING:
				if (cart.getCartItems().size() < 1) {
					LOG.error("No items in the cart()");
					addGlobalValidationError("cart.no.items", user);
					return getCartResolution();
				}
				name = user.getFullName();
				address = userService.getDefaultAddress(user);
				if (address == null) {
					address = new Address();
				}
				creditCard = userService.getDefaultCreditCard(user);
				if (creditCard == null) {
					creditCard = new CreditCard();
				}
				return getBillingResolution();
			case PURCHASE:
				LOG.info("Need to validate cart()");
				return getThankYouResolution();
			}
		}
		return getDefaultResolution();
	}

	@HandlesEvent("checkout")
	public Resolution checkout() throws Exception {
		User user = getUser();
		LOG.info(user + " has checked out!");
		return new RedirectResolution(PATH_SECURE + JSP_BINDING + "/billing");
	}

	@HandlesEvent("purchase")
	public Resolution purchase() throws Exception {
		User user = getUser();
		LOG.info(user + " has bought!");
		return getThankYouResolution();
	}

	@Override
	public String getJSPBinding() {
		return JSP_BINDING;
	}

	public Cart getCart() {
		return cart;
	}

	public void setCart(Cart cart) {
		this.cart = cart;
	}

	@Override
	public User getUser() {
		return super.getUser();
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public CreditCard getCreditCard() {
		return creditCard;
	}

	public void setCreditCard(CreditCard creditCard) {
		this.creditCard = creditCard;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

}
