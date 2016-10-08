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
				<div class="row">
					<div class="col-xs-12 col-sm-4 col-md-4 col-lg-4 col-xl-4">
						<div class="h4">Personal Details</div>
						<div class="form-group">
							<label for="username" class="control-label">User Name</label>
							<div id="username" name="username" class="form-control">${actionBean.username}</div>
						</div>
						<div class="form-group">
							<label for="firstname" class="control-label">First Name</label>
							<div id="firstname" name="firstname" class="form-control">${actionBean.firstname}</div>
						</div>
						<div class="form-group">
							<label for="lastname" class="control-label">Last Name</label>
							<div id="lastname" name="lastname" class="form-control">${actionBean.lastname}</div>
						</div>
						<div class="form-group">
							<label for="email" class="control-label">Email</label>
							<div id="email" name="email" class="form-control">${actionBean.email}</div>
						</div>
					</div>

					<div class="col-xs-12 col-sm-4 col-md-4 col-lg-4 col-xl-4">
						<div class="h4">Billing Details</div>
						<div class="form-group">
							<label for="address" class="control-label">Street Address</label>
							<div id="address" name="address" class="form-control">${actionBean.address}</div>
						</div>
						<div class="form-group">
							<label for="city" class="control-label">Suburb</label>
							<div id="city" name="city" class="form-control">${actionBean.city}</div>
						</div>
						<div class="form-group">
							<label for="state" class="control-label">State</label>
							<div id="state" name="state" class="form-control">${actionBean.state}</div>
						</div>
						<div class="form-group">
							<label for="postcode" class="control-label">Postcode</label>
							<div id="postcode" name="postcode" class="form-control">${actionBean.postcode}</div>
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
					<a href="${ctx}/secure/account/edit" id="edit" name="edit" value="edit" class="tm-btn text-uppercase">EDIT</a>
				</div>
            </div>
        </section>
        
    </s:layout-component>
</s:layout-render>