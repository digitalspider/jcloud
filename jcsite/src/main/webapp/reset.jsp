<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ include file="/jsp/include/taglibs.jsp"%>
<%@ page import="au.com.jcloud.actionbean.LoginActionBean" %>

<% boolean resetReady = LoginActionBean.isResetReady(request); %>

<s:layout-render name="/jsp/layout/public.jsp" pageTitle="Reset">
    <s:layout-component name="contents">

        <section class="tm-section">
            <div class="container-fluid">
                <div class="row">
                    <div class="col-xs-12 col-sm-6 col-md-6 col-lg-6 col-xl-6">
						<s:form id="resetForm" method="post" action="/secure/login"  class="tm-login-form">
							<s:errors/>
							<% if (resetReady) { %>
								<div class="h4">New Password</div>
								<div class="form-group">
									<input type="password" id="password" name="password" class="form-control" placeholder="Password" required="true"/>
								</div>
								<s:submit id="submit" name="reset" value="Reset" class="tm-btn text-uppercase" />
							<% } else { %>
								<div class="h4">Username or Email</div>
								<div class="form-group">
									<input type="username" id="username" name="username" class="form-control" placeholder="Username or Email" required="true"/>
								</div>
								<s:submit id="forgot" name="forgot" value="Email me" class="tm-btn text-uppercase" />
							<% } %>
						</s:form>
                    </div>
                </div> <!-- row -->

            </div>
        </section>

    </s:layout-component>
    <s:layout-component name="footer">

        <!-- load JS files -->
        <script src="${ctx}/js/jquery-1.11.3.min.js"></script>             <!-- jQuery (https://jquery.com/download/) -->
        <script src="https://www.atlasestateagents.co.uk/javascript/tether.min.js"></script> <!-- Tether for Bootstrap, http://stackoverflow.com/questions/34567939/how-to-fix-the-error-error-bootstrap-tooltips-require-tether-http-github-h -->
        <script src="${ctx}/js/bootstrap.min.js"></script>                 <!-- Bootstrap (http://v4-alpha.getbootstrap.com/) -->

        <script src="${ctx}/js/md5.js"></script> <%-- http://www.myersdaily.org/joseph/javascript/md5-text.html --%>
        <script type="text/javascript">
            function encodeAndSubmit(event) {
                event.preventDefault();
                if ($("#password").val().length>0) {
                    $("#password").val(md5($("#password").val()))
                }
                this.name = $(".submit").attr("name");
                this.submit();
            }
           // $('#resetForm').submit(encodeAndSubmit);
        </script>

    </s:layout-component>
</s:layout-render>