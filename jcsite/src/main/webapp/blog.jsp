<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ include file="/jsp/include/taglibs.jsp"%>

<s:layout-render name="/jsp/layout/public.jsp" pageTitle="Blogs">

	<s:layout-component name="customjs">
		<script>
			$(document).ready(function () {
				getLinks("${ctx}", "category", 5, "#link-template", "#category-links");
				getLinks("${ctx}", "home", 5, "#link-template", "#useful-links");
				getBlogs("${ctx}", "", 4, "#blog-template", "#blogs");
			});
		</script>
	</s:layout-component>

    <s:layout-component name="contents">

        <section class="tm-section">
            <div class="container-fluid">
                <div class="row">

                    <div class="col-xs-12 col-sm-12 col-md-8 col-lg-9 col-xl-9">
                        <div class="tm-blog-post">
                            <h3 class="tm-gold-text">Blogs from JCloud</h3>
                            <p>This page contains some of our most popular blogs</p>
                            <p>To see all of them see:</p>
                            <ul>
                            	<li><a href="http://blog.jcloud.com.au">Blogs</a></li>
                            </ul>
                        </div>
                        
                        <div class="row tm-margin-t-big" id="blogs">
                            <div class="col-xs-12 col-sm-12 col-md-4 col-lg-4 col-xl-4">

                                <div class="tm-content-box">
                                    <img src="img/tm-img-310x180-1.jpg" alt="Image" class="tm-margin-b-30 img-fluid">
                                    <h4 class="tm-margin-b-20 tm-gold-text">Lorem ipsum dolor #1</h4>
                                    <p class="tm-margin-b-20">Aenean cursus tellus mauris, quis
                                    consequat mauris dapibus id. Donec
                                    scelerisque porttitor pharetra</p>
                                    <a href="#" class="tm-btn text-uppercase">Detail</a>    
                                </div>  

                            </div>

                            <div class="col-xs-12 col-sm-12 col-md-4 col-lg-4 col-xl-4">

                                <div class="tm-content-box">
                                    <img src="img/tm-img-310x180-2.jpg" alt="Image" class="tm-margin-b-30 img-fluid">
                                    <h4 class="tm-margin-b-20 tm-gold-text">Lorem ipsum dolor #2</h4>
                                    <p class="tm-margin-b-20">Aenean cursus tellus mauris, quis
                                    consequat mauris dapibus id. Donec
                                    scelerisque porttitor pharetra</p>
                                    <a href="#" class="tm-btn text-uppercase">Read More</a>    
                                </div>  

                            </div>

                            <div class="col-xs-12 col-sm-12 col-md-4 col-lg-4 col-xl-4">

                                <div class="tm-content-box">
                                    <img src="img/tm-img-310x180-3.jpg" alt="Image" class="tm-margin-b-30 img-fluid">
                                    <h4 class="tm-margin-b-20 tm-gold-text">Lorem ipsum dolor #3</h4>
                                    <p class="tm-margin-b-20">Aenean cursus tellus mauris, quis
                                    consequat mauris dapibus id. Donec
                                    scelerisque porttitor pharetra</p>
                                    <a href="#" class="tm-btn text-uppercase">Detail</a>    
                                </div>  

                            </div>    
                        </div>
                        
                    </div>

                    <aside class="col-xs-12 col-sm-12 col-md-4 col-lg-3 col-xl-3 tm-aside-r">

                        <div class="tm-aside-container">
                            <h3 class="tm-gold-text tm-title">
                                Categories
                            </h3>
                            <nav>
                                <ul class="nav" id="category-links">
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
                                <ul class="nav" id="useful-links">
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