<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ include file="/jsp/include/taglibs.jsp"%>

<s:layout-render name="/jsp/layout/public.jsp" pageTitle="Service">

    <s:layout-component name="contents">
        <section class="tm-section">
            <div class="container-fluid">
                <div class="row tm-2-rows-sm-swap">

                    <div class="col-xs-12 col-sm-12 col-md-8 col-lg-9 col-xl-9 tm-2-rows-sm-down-1">
                        <h3 class="tm-gold-text">Services at JCloud</h3>
                        <p>
                        Interested in us developing a website solution
                        for you. We can host wordpress, integration with payments, build an ecommerce
                        solution, or help you fix your java applications. Contact us for a quote
                        </p>
						<p class="m-b-2">Try it out today, or contact us for more information. </p>
						<a href="login.jsp" class="tm-btn text-uppercase">Sign up</a>
						<a href="contact.jsp" class="tm-btn text-uppercase">Contact Us</a>
                    </div>

                    <jsp:include page="/jsp/include/sidebar.jsp"/>
                </div>
                
                <div class="row tm-margin-t-mid">
                    <div class="col-xs-12">
                        <h3 class="tm-gold-text">Hosting Pricing</h3>
                        <p>Below are the standard plans we support. You need to select both a plan and a support contract.</p>
                        <p>One you select a base plan, you can customise all the options by adding more RAM, more Disk Space,
                        changing application server, installing databases, etc. Let us know your requirements
                        and well adjust the service accordingly</p>

                        <div class="row">
                            <div class="col-md-4 col-sm-6">
                                <div data-wow-delay="0.3s" class="plan plan-one wow bounceIn animated" style="visibility: visible; animation-delay: 0.3s; animation-name: bounceIn;">
                                    <div class="plan_title">
                                        <i class="icon-mobile medium-icon"></i>
                                        <h3>SIMPLE</h3>
                                        <h2>$10 <span>per month</span></h2>
                                    </div>
                                    <ul>
                                        <li>1 GB Cloud Storage</li>
                                        <li>128 MB RAM</li>
                                        <li>AlpineLinux,Apache,Jetty Server</li>
                                        <li>SQLite Database</li>
                                        <li>Self Support only</li>
                                    </ul>
                                    <button class="btn btn-warning">Coming Soon</button>
                                </div>
                            </div>
                            <div class="col-md-4 col-sm-6">
                                <div data-wow-delay="0.3s" class="plan plan-two wow bounceIn animated" style="visibility: visible; animation-delay: 0.3s; animation-name: bounceIn;">
                                    <div class="plan_title">
                                        <i class="icon-desktop medium-icon"></i>
                                        <h3>STANDARD</h3>
                                        <h2>$30 <span>per month</span></h2>
                                    </div>
                                    <ul>
                                        <li>10 GB Cloud Storage</li>
                                        <li>512 MB RAM (+$10/m for 1GB)</li>
                                        <li>Ubuntu,Apache,Tomcat Server</li>
                                        <li>MySQL Database</li>
                                        <li>Self and Basic Support only</li>
                                    </ul>
                                    <button class="btn btn-warning">Coming Soon</button>
                                </div>
                            </div>
                            <div class="col-md-4 col-sm-6">
                                <div data-wow-delay="0.3s" class="plan plan-three wow bounceIn animated" style="visibility: visible; animation-delay: 0.3s; animation-name: bounceIn;">
                                    <div class="plan_title">
                                        <i class="icon-cloud medium-icon"></i>
                                        <h3>BUSINESS</h3>
                                        <h2>$150 <span>per month</span></h2>
                                    </div>
                                    <ul>
                                        <li>50 GB Cloud Storage</li>
                                        <li>4 GB RAM</li>
                                        <li>Any Linux, Any Web Server, JBoss, Wildfly or Glassfish</li>
                                        <li>Any of our support plans</li>
                                    </ul>
                                    <button class="btn btn-warning">Coming Soon</button>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>

                <div class="row tm-margin-t-mid">
                    <div class="col-xs-12">
                        <h3 class="tm-gold-text">Support Pricing</h3>

                        <table id="dataTables-example" class="table table-striped table-bordered table-hover">
                            <thead>
                                <tr>
                                    <th>Support Level</th>
                                    <th>Response Times</th>
                                    <th>Information</th>
                                    <th>Price</th>
                                </tr>
                            </thead>
                            <tbody>
                                <tr class="odd gradeX">
                                    <td>Self</td>
                                    <td>Self Managed</td>
                                    <td>SSH Access to your linux container</td>
                                    <td class="center">Free</td>
                                </tr>
                                <tr class="gradeA">
                                    <td>Basic</td>
                                    <td>24 hour response</td>
                                    <td>We will respond to your request within 24 hours</td>
                                    <td class="center">$50 / month</td>
                                </tr>
                                <tr class="gradeA">
                                    <td>Business</td>
                                    <td>9-to-5 Monday to Friday</td>
                                    <td>We will respond to your request during business hours</td>
                                    <td class="center">$300 / month</td>
                                </tr>
                                <tr class="gradeA">
                                    <td>Premium</td>
                                    <td>24x7 Support</td>
                                    <td>You will be provided with a support number to call us on</td>
                                    <td class="center">$1200 / month</td>
                                </tr>
                            </tbody>
                        </table>

                    </div>
                </div>

                <div class="row tm-margin-t-mid">
                    <div class="col-xs-12 col-sm-12 col-md-6 col-lg-6 col-xl-6 tm-sm-m-b">
                        <h3 class="tm-gold-text">Pellentesque fermentum mauris</h3>
                        <p>Vivamus vel leo vel nunc tincidunt mattis. Sed neque diam, semper suscipit dictum a, sodales ac metus. Class aptent taciti sociosqu ad litora torquent per conubia nostra, per inceptos himenaeos. Morbi vel pharetra massa, non iaculis tortor. Nulla porttitor tincidunt felis et feugiat.</p>
                        <p>Vivamus fermentum ligula justo, sit amet blandit nisl volutpat id. Fusce sagittis ultricies felis, non luctus mauris lacinia quis. Ut fringilla lacus ac tempor ullamcorper. Mauris iaculis placerat ex et mattis.</p>
                    </div>

                    <div class="col-xs-12 col-sm-12 col-md-6 col-lg-6 col-xl-6">
                        <h3 class="tm-gold-text">Pellentesque fermentum mauris</h3>
                        <p>
                            Fusce in dapibus quam, eget finibus velit. Nullam erat odio, vulputate id est ut, consequat rutrum justo. Vivamus vel leo vel nunc tincidunt mattis. Sed neque diam, semper suscipit dictum a, sodales ac metus. Quisque vel sem eu turpis ullamcorper euismod. Praesent quis nisi ac augue luctus viverra. Sed et dui nisi.
                        </p>
                        <p>
                            Fusce vitae dapibus justo. Pellentesque accumsan est ac posuere imperdiet. Curabitur eros mi, lacinia at euismod quis, dapibus vel ligula. Ut sodales erat vitae nunc tempor mollis.
                        </p>
                    </div>

                </div>

                <div class="row tm-margin-t-mid">
                    <div class="col-xs-12 col-sm-6 col-md-3 col-lg-3 col-xl-3">

                        <div class="tm-content-box">
                            <img src="img/tm-img-310x180-1.jpg" alt="Image" class="tm-margin-b-30 img-fluid">
                            <h4 class="tm-margin-b-20 tm-gold-text">Lorem ipsum dolor #1</h4>
                            <p class="tm-margin-b-20">Aenean cursus tellus mauris, quis
                            consequat mauris dapibus id. Donec
                            scelerisque porttitor pharetra</p>
                            <a href="#" class="tm-btn text-uppercase">Detail</a>    
                        </div>  

                    </div>

                    <div class="col-xs-12 col-sm-6 col-md-3 col-lg-3 col-xl-3">

                        <div class="tm-content-box">
                            <img src="img/tm-img-310x180-2.jpg" alt="Image" class="tm-margin-b-30 img-fluid">
                            <h4 class="tm-margin-b-20 tm-gold-text">Lorem ipsum dolor #2</h4>
                            <p class="tm-margin-b-20">Aenean cursus tellus mauris, quis
                            consequat mauris dapibus id. Donec
                            scelerisque porttitor pharetra</p>
                            <a href="#" class="tm-btn text-uppercase">Read More</a>    
                        </div>  

                    </div>

                    <div class="col-xs-12 col-sm-6 col-md-3 col-lg-3 col-xl-3">

                        <div class="tm-content-box">
                            <img src="img/tm-img-310x180-3.jpg" alt="Image" class="tm-margin-b-30 img-fluid">
                            <h4 class="tm-margin-b-20 tm-gold-text">Lorem ipsum dolor #3</h4>
                            <p class="tm-margin-b-20">Aenean cursus tellus mauris, quis
                            consequat mauris dapibus id. Donec
                            scelerisque porttitor pharetra</p>
                            <a href="#" class="tm-btn text-uppercase">Detail</a>    
                        </div>  

                    </div>

                    <div class="col-xs-12 col-sm-6 col-md-3 col-lg-3 col-xl-3">

                        <div class="tm-content-box tm-margin-b-30">
                            <a href="#" class="tm-text-link"><h4 class="tm-margin-b-20 tm-thin-font">Duis sit amet tristique #1</h4></a>
                            <p class="tm-margin-b-30">Vestibulum arcu erat, lobortis sit amet tellus ut, semper tristique nibh. Nunc in molestie elit.</p><hr>
                        </div>
                        <div class="tm-content-box">
                            <a href="#" class="tm-text-link"><h4 class="tm-margin-b-20 tm-thin-font">Duis sit amet tristique #2</h4></a>
                            <p>Vestibulum arcu erat, lobortis sit amet tellus ut, semper tristique nibh. Nunc in molestie elit.</p>
                        </div>  

                    </div>
                </div> <!-- row -->

            </div>
        </section>

    </s:layout-component>
</s:layout-render>