<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ include file="/jsp/include/taglibs.jsp"%>

<s:layout-render name="/jsp/layout/secure.jsp" pageTitle="Account">

	<s:layout-component name="customjs">
		<script>
			$(document).ready(function () {
				getLinks("${ctx}", "category", 5, "#link-template", "#category-links");
				getLinks("${ctx}", "home", 5, "#link-template", "#useful-links");
			});
		</script>
	</s:layout-component>

    <s:layout-component name="contents">

<%
String username = request.getRemoteUser();
%>
<span>Hello <%= username %>. This is a secure resource</span>

        <section class="tm-section">
            <div class="container-fluid">
            	<s:form id="accountDetailsForm" method="post" action="/secure/account" class="tm-account-form">
                	<div class="row">
                    	<div class="col-xs-12 col-sm-4 col-md-4 col-lg-4 col-xl-4">
                        	<input type="hidden" id="requestId" name="requestId" value="$request.getSession().getAttribute(Constants.SESSION_NONCE_UID_REGISTER)" />
                            <div class="h4">Register</div>
                            <div class="form-group">
                            	<s:errors field="username"/>
                            	<label for="username" class="control-label">User Name</label>
                                <input type="text" id="username" name="username" class="form-control" placeholder="Username" required="true" value="${actionBean.username}"/>
                            </div>
                            <div class="form-group">
                                <s:errors field="firstname"/>
                                <label for="firstname" class="control-label">First Name</label>
                                <input type="text" id="firstname" name="firstname" class="form-control" placeholder="First Name" required="true" value="${actionBean.firstname}"/>
                            </div>
                            <div class="form-group">
                            	<s:errors field="lastname"/>
                            	<label for="lastname" class="control-label">Last Name</label>
                                <input type="text" id="lastname" name="lastname" class="form-control" placeholder="Last Name" required="true" value="${actionBean.lastname}"/>
                            </div>
                            <div class="form-group">
                            	<s:errors field="email"/>
                            	<label for="email" class="control-label">Email</label>
                                <input type="email" id="email" name="email" class="form-control" placeholder="Email" required="true" value="${actionBean.email}"/>
                            </div>
                        </div>

                    	<div class="col-xs-12 col-sm-4 col-md-4 col-lg-4 col-xl-4">
                        	<s:errors globalErrorsOnly="true"/>
                            <div class="h4">Billing Details</div>
                            <div class="form-group">
                            	<s:errors field="address"/>
                            	<label for="address" class="control-label">Street Address</label>
                                <input type="text" id="address" name="address" class="form-control" placeholder="Street Address" value="${actionBean.address}"/>
                            </div>
                            <div class="form-group">
                            	<s:errors field="city"/>
                            	<label for="city" class="control-label">City</label>
                                <input type="text" id="city" name="city" class="form-control" placeholder="Suburb" value="${actionBean.city}"/>
                            </div>
                            <div class="form-group">
                            	<s:errors field="state"/>
                            	<label for="state" class="control-label">State</label>
                                <input type="text" id="state" name="state" class="form-control" placeholder="State" value="${actionBean.state}"/>
                            </div>
                            <div class="form-group">
                            	<s:errors field="postcode"/>
                            	<label for="postcode" class="control-label">Postcode</label>
                                <input type="text" id="postcode" name="postcode" class="form-control" placeholder="Postcode" value="${actionBean.postcode}"/>
                            </div>
						</div>

						<div class="col-xs-12 col-sm-4 col-md-4 col-lg-4 col-xl-4">
							<div class="h4">Payment Details</div>
							<div class="form-group">
								<label for="cc-type" class="control-label">Card type</label>
								<div id="cc-type" class="form-control cc-type"><small class="text-muted">[<span class="cc-brand">MasterCard</span>]</small></div>
							</div>
							<div class="form-group">
								<label for="cc-exp" class="control-label">Card expiry </label>
								<div id="cc-exp" class="form-control cc-exp">01 / 16</div>
							</div>
						</div>
                    </div>
                    <div class="row">
                        <s:submit id="save" name="save" value="save" class="tm-btn text-uppercase"/>
                        <a href="${ctx}/secure/account" class="tm-btn text-uppercase">Cancel</a>
                    </div>
                </s:form>
            </div>
        </section>
        
    </s:layout-component>
</s:layout-render>