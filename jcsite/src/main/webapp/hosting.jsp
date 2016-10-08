<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ include file="/jsp/include/taglibs.jsp"%>

<s:layout-render name="/jsp/layout/public.jsp" pageTitle="Hosting">

    <s:layout-component name="contents">
        <section class="tm-section">
            <div class="container-fluid">
                <div class="row tm-2-rows-sm-swap">

                    <div class="col-xs-12 col-sm-12 col-md-8 col-lg-9 col-xl-9 tm-2-rows-sm-down-1">
                        <h3 class="tm-gold-text">Hosting at JCloud</h3>
                        <p>
                        Host your business applications with us,
                        either exclusively, or as part of your load balanced solution. We focus on
                        Small to Medium business, and will work with you to find the right solution
                        for you.
                        </p>
						<p class="m-b-2">Try it out today, or contact us for more information. </p>
						<a href="login.jsp" class="tm-btn text-uppercase">Sign up</a>
						<a href="contact.jsp" class="tm-btn text-uppercase">Contact Us</a>
                    </div>

                    <jsp:include page="/jsp/include/sidebar.jsp"/>
                </div>
            </div>
        </section>

    </s:layout-component>
</s:layout-render>