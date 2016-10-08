<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ include file="/jsp/include/taglibs.jsp"%>

<s:layout-render name="/jsp/layout/public.jsp" pageTitle="Cart">
    <s:layout-component name="contents">

        <section class="tm-section">
            <div class="container-fluid">
                <div class="row">

                    <div class="col-xs-12 col-sm-12 col-md-8 col-lg-9 col-xl-9">
                    	<form action="" method="POST">
	                        <div class="tm-blog-post">
	                            <h3 class="tm-gold-text">Shopping Cart</h3>
	                            <p>You have the following items in your cart</p>
								<div id="cart-item-header" class="cart-item-header">
									<div>Item Description</div>
									<div>Item Quantity</div>
									<div>Item Price</div>
									<div>Item Price Total</div>
									<div>Operations</div>
								</div>
								<c:forEach var="item" items="$shoppingCart.items">
									<div id="cart-item" class="cart-item">
										<div>${item.description}</div>
										<div>${item.quantity}</div>
										<div>${item.price}</div>
										<div>${item.total}</div>
										<div><a href="/public/cart/update/${item.id}">update</a></div>
										<div><a href="/public/cart/remove/${item.id}">remove</a></div>
									</div>
								</c:forEach>
								<div><a href="/public/cart/add">add</a></div>
	                        </div>
	                	</form>
                    </div>

                    <aside class="col-xs-12 col-sm-12 col-md-4 col-lg-3 col-xl-3 tm-aside-r">

                        <div class="tm-aside-container">
                            <h3 class="tm-gold-text tm-title">
                                Categories
                            </h3>
                            <nav>
                                <ul class="nav">
                                    <li><a href="#" class="tm-text-link">Java development</a></li>
                                    <li><a href="#" class="tm-text-link">Hosting</a></li>
                                    <li><a href="#" class="tm-text-link">Technology news</a></li>
                                    <li><a href="#" class="tm-text-link">Business news</a></li>
                                    <li><a href="#" class="tm-text-link">Other</a></li>
                                </ul>
                            </nav>
                            <hr class="tm-margin-t-small">   
                            <h3 class="tm-gold-text tm-title tm-margin-t-small">
                                Useful Links
                            </h3>
                            <nav>   
                                <ul class="nav">
                                    <li><a href="http://www.java.com" class="tm-text-link">Oracle Java</a></li>
                                    <li><a href="http://www.se-radio.net" class="tm-text-link">SE Radio</a></li>
                                    <li><a href="http://www.mkyong.com/" class="tm-text-link">Mykong</a></li>
                                    <li><a href="http://build-podcast.com" class="tm-text-link">Build Podcast</a></li>
                                    <li><a href="http://www.theserverside.com" class="tm-text-link">The Server Side</a></li>
                                    <li><a href="#" class="tm-text-link">Other..</a></li>
                                </ul>
                            </nav>   
                            <hr class="tm-margin-t-small"> 

                            <div class="tm-content-box tm-margin-t-small">
                                <a href="#" class="tm-text-link"><h4 class="tm-margin-b-20 tm-thin-font">Duis sit amet tristique #1</h4></a>
                                <p class="tm-margin-b-30">Vestibulum arcu erat, lobortis sit amet tellus ut, semper tristique nibh. Nunc in molestie elit.</p>
                            </div>
                            <hr class="tm-margin-t-small">
                            <div class="tm-content-box tm-margin-t-small">
                                <a href="#" class="tm-text-link"><h4 class="tm-margin-b-20 tm-thin-font">Duis sit amet tristique #2</h4></a>
                                <p>Vestibulum arcu erat, lobortis sit amet tellus ut, semper tristique nibh. Nunc in molestie elit.</p>
                            </div>  
                            <hr class="tm-margin-t-small">
                            <div class="tm-content-box tm-margin-t-small">
                                <a href="#" class="tm-text-link"><h4 class="tm-margin-b-20 tm-thin-font">Duis sit amet tristique #3</h4></a>
                                <p>Vestibulum arcu erat, lobortis sit amet tellus ut, semper tristique nibh. Nunc in molestie elit.</p>
                            </div>      
                        </div>
                        
                        
                    </aside>

                </div>
                
            </div>
        </section>

    </s:layout-component>
</s:layout-render>