<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ include file="/jsp/include/taglibs.jsp"%>

<s:layout-render name="/jsp/layout/secure.jsp" pageTitle="Cart">

	<s:layout-component name="customjs">
    </s:layout-component>

    <s:layout-component name="contents">

        <section class="tm-section">
            <div class="container-fluid">
                <div class="row">

					<s:form id="cartForm" method="post" action="/secure/checkout/billing" class="tm-cart-form">
                    	<div class="col-xs-12 col-sm-6 col-md-6 col-lg-6 col-xl-6">
                        	<s:errors globalErrorsOnly="true"/>
                            <div class="h4">Cart Details</div>
                            <table id="table" class="table">
                            <c:forEach var="cartItem" items="${actionBean.cart.cartItems}">
                            	<tr class="cart-item" id="${cartItem.product.id}">
                            		<td class="cart-item-name">${cartItem.product.name}</td>
                            		<td class="cart-item-desc">${cartItem.product.description}</td>
                            		<td class="cart-item-qty">${cartItem.quantity}</td>
                            		<td class="cart-item-price">\$${cartItem.product.listPrice}</td>
		                        </tr>
                            </c:forEach>
                            </table>
                            <s:submit id="checkout" name="checkout" value="checkout" class="tm-btn text-uppercase" />
                            <a href="${ctx}/secure/server/add" class="tm-btn text-uppercase">Add New Server</a>
                    	</div>
                    </s:form>

                </div>
                
            </div>
        </section>

    </s:layout-component>
</s:layout-render>