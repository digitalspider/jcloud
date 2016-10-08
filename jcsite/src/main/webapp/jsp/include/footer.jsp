<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ include file="/jsp/include/taglibs.jsp"%>

<footer class="tm-footer">
    <div class="container-fluid">
        <div class="row">

            <div class="col-xs-12 col-sm-6 col-md-6 col-lg-3 col-xl-3">

                <div class="tm-footer-content-box">
                    <h3 class="tm-gold-text tm-title tm-footer-content-box-title">Quote</h3>
                    <div class="tm-gray-bg">
                        <p>Making web apps simple!</p>
                        <p><strong>The Java Cloud Team</strong></p>
                    </div>
                </div>
            </div>

            <div class="col-xs-12 col-sm-6 col-md-6 col-lg-3 col-xl-3">
                <div class="tm-footer-content-box tm-footer-links-container">

                    <h3 class="tm-gold-text tm-title tm-footer-content-box-title">Links</h3>
                    <nav>
                        <ul class="nav" id="footer-links">
                            <li><a href="${ctx}/blog.jsp" class="tm-footer-link">Our Blogs</a></li>
                            <li><a href="${ctx}/contact.jsp" class="tm-footer-link">Contact Us</a></li>
                            <li><a href="${ctx}/privacy.jsp" target="_blank" class="tm-footer-link">Privacy Policy</a></li>
                            <li><a href="${ctx}/terms.jsp" target="_blank" class="tm-footer-link">Terms and Conditions</a></li>
                            <li><a href="#" class="tm-footer-link">JIRA Support Portal</a></li>
                        </ul>
                    </nav>

                </div>

            </div>

            <!-- Add the extra clearfix for only the required viewport
                http://stackoverflow.com/questions/24590222/bootstrap-3-grid-with-different-height-in-each-item-is-it-solvable-using-only
            -->
            <div class="clearfix hidden-lg-up"></div>

            <div class="col-xs-12 col-sm-6 col-md-6 col-lg-3 col-xl-3">

                <div class="tm-footer-content-box">

                    <h3 class="tm-gold-text tm-title tm-footer-content-box-title">Support</h3>
                    <p class="tm-margin-b-30">If your container is not responding, please use the contact us page and submit a request. We will address this ASAP!</p><hr class="tm-margin-b-30">
                    <p class="tm-margin-b-30">If your container is working, but your application is failing, please look at our support contracts.</p>
                    <a href="${ctx}/service.jsp" class="tm-btn tm-btn-gray text-uppercase">Support</a>

                </div>

            </div>

            <div class="col-xs-12 col-sm-6 col-md-6 col-lg-3 col-xl-3">

                <div class="tm-footer-content-box">
                    <h3 class="tm-gold-text tm-title tm-footer-content-box-title">Partners</h3>
                    <p class="tm-margin-b-20">If you would like to partner with us, please contact us through the contact page</p>
                    <a href="${ctx}/contact.jsp" class="tm-btn tm-btn-gray text-uppercase">Contact</a>
                </div>

            </div>


        </div>

        <div class="row">
            <div class="col-xs-12 tm-copyright-col">
                <p class="tm-copyright-text">Copyright 2016 Java Cloud</p>
            </div>
        </div>
    </div>
</footer>