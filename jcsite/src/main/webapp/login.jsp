<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ include file="/jsp/include/taglibs.jsp"%>

<s:layout-render name="/jsp/layout/public.jsp" pageTitle="Login">
    <s:layout-component name="contents">

        <section class="tm-section">
            <div class="container-fluid">
                <div class="row">
                    <div class="col-xs-12 col-sm-6 col-md-6 col-lg-6 col-xl-6">

                        <s:form id="loginForm" method="post" action="/secure/login" class="tm-login-form">
                        	<input type="hidden" id="requestId" name="requestId" value="$request.getSession().getAttribute(Constants.SESSION_NONCE_UID_LOGIN)" />
                        	<s:errors globalErrorsOnly="true"/>
                            <div class="h4">Login</div>
                            <div class="form-group">
                            	<s:errors field="username"/>
                                <input type="text" id="username" name="username" class="form-control" placeholder="Username" required="true" value="${actionBean.username}"/>
                            </div>
                            <div class="form-group">
                            	<s:errors field="password"/>
                                <input type="password" id="password" name="password" class="form-control" placeholder="Password" required="true"/>
                            </div>
                            <s:submit id="login" name="login" value="login" class="tm-btn text-uppercase" />
                            <br/>
                            <s:link href="/reset.jsp">Forgot your password</s:link>
						</s:form>
                    </div>

                    <div class="col-xs-12 col-sm-6 col-md-6 col-lg-6 col-xl-6">
                        <s:form id="registerForm" method="post" action="/secure/login" class="tm-login-form">
                        	<input type="hidden" id="requestId" name="requestId" value="$request.getSession().getAttribute(Constants.SESSION_NONCE_UID_REGISTER)" />
                            <div class="h4">Register</div>
                            <div class="form-group">
                                <s:errors field="firstname"/>
                                <input type="text" id="firstname" name="firstname" class="form-control" placeholder="First Name" required="true" value="${actionBean.firstname}"/>
                            </div>
                            <div class="form-group">
                            	<s:errors field="lastname"/>
                                <input type="text" id="lastname" name="lastname" class="form-control" placeholder="Last Name" required="true" value="${actionBean.lastname}"/>
                            </div>
                            <div class="form-group">
                            	<s:errors field="email"/>
                                <input type="email" id="email" name="email" class="form-control" placeholder="Email" required="true" value="${actionBean.email}"/>
                            </div>
                            <div class="form-group">
                            	<s:errors field="newusername"/>
                                <input type="text" id="newusername" name="newusername" class="form-control" placeholder="Username" required="true" value="${actionBean.newusername}"/>
                            </div>
                            <div class="form-group">
                            	<s:errors field="newpassword"/>
                                <input type="password" id="newpassword" name="newpassword" class="form-control" placeholder="Password" required="true"/>
                            </div>
                            <s:submit id="register" name="register" value="register" class="tm-btn text-uppercase"/>
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
                if ($("#newpassword").val().length>0) {
                    $("#newpassword").val(md5($("#newpassword").val()))
                }
                this.name = $(".submit").attr("name");
                this.submit();
            }
           // $('#loginForm').submit(encodeAndSubmit);
           // $('#registerForm').submit(encodeAndSubmit);
        </script>

    </s:layout-component>
</s:layout-render>