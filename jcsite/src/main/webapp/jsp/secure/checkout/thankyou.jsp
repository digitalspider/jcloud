<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ include file="/jsp/include/taglibs.jsp"%>

<s:layout-render name="/jsp/layout/secure.jsp" pageTitle="Thank You">

	<s:layout-component name="customjs">
    </s:layout-component>

    <s:layout-component name="contents">

        <section class="tm-section">
            <div class="container-fluid">
                <div class="row">

					<s:form id="paymentForm" method="post" action="/secure/checkout/purchase" class="tm-payment-form">
                    	<div class="col-xs-12 col-sm-6 col-md-6 col-lg-6 col-xl-6">
                        	<s:errors globalErrorsOnly="true"/>
                            <div class="h4">Thank You Details</div>
                            <div class="form-group">
                            	<s:errors field="name"/>
                                <input type="text" id="name" name="name" class="form-control" placeholder="Name" required="true"/>
                            </div>
                            <div class="form-group">
                            	<s:errors field="address"/>
                                <input type="text" id="address" name="address" class="form-control" placeholder="Street Address" required="true"/>
                            </div>
                            <div class="form-group">
                            	<s:errors field="city"/>
                                <input type="text" id="city" name="city" class="form-control" placeholder="Suburb" required="true"/>
                            </div>
                            <div class="form-group">
                            	<s:errors field="state"/>
                                <input type="text" id="state" name="state" class="form-control" placeholder="State" required="true"/>
                            </div>
                            <div class="form-group">
                            	<s:errors field="postcode"/>
                                <input type="text" id="postcode" name="postcode" class="form-control" placeholder="Postcode" required="true"/>
                            </div>
                            <a href="${ctx}/secure/server" class="tm-btn text-uppercase">View Servers</a>
                    	</div>
                    </s:form>

                </div>
                
            </div>
        </section>

    </s:layout-component>
</s:layout-render>