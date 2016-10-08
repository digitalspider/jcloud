<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ include file="/jsp/include/taglibs.jsp"%>

<s:layout-render name="/jsp/layout/secure.jsp" pageTitle="Invoice">
    <s:layout-component name="contents">

<%
String username = request.getRemoteUser();
%>
<span>Hello <%= username %>. This is a secure resource</span>


        <section class="tm-section">
            <div class="container-fluid">
                <div class="row">

                    <div class="col-xs-12 col-sm-12 col-md-8 col-lg-9 col-xl-9">
                        <div class="tm-blog-post">
                            <h3 class="tm-gold-text">Your Servers</h3>
                            Your server list
                            <table>
                                <thead>
                                    <tr>
                                        <th>Server</th>
                                        <th>Status</th>
                                        <th>Processes</th>
                                        <th>Mem (cur)</th>
                                        <th>Mem (peak)</th>
                                        <th>Disk</th>
                                        <th>Bytes recieved</th>
                                        <th>Bytes sent</th>
                                        <th>Apps</th>
                                        <th>Price</th>
                                        <th>Actions</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <tr>
                                        <td><a href="#">alpine1</a></td>
                                        <td>Running</td>
                                        <td>235</td>
                                        <td>1.82GB</td>
                                        <td>1.86GB</td>
                                        <td>1.92GB</td>
                                        <td>8.93MB</td>
                                        <td>6.44MB</td>
                                        <td>JIRA</td>
                                        <td>$12.95</td>
                                        <td>
                                            <a href="#">Stop</a>
                                            <a href="#">Start</a>
                                            <a href="#">Restart</a>
                                        </td>
                                    </tr>
                                </tbody>

                            </table>
                        </div>

                        <a href="server.html" class="tm-btn text-uppercase">Add New Server</a>

                        <div class="row tm-margin-t-small">
                            <div class="col-xs-12 col-sm-12 col-md-12 col-lg-12 col-xl-12">
                                <div class="tm-content-box">
                                    <h4 class="tm-margin-b-20 tm-gold-text">Services</h4>
                                    <p class="tm-margin-b-20">
                                        <table>
                                            <thead>
                                                <tr>
                                                    <th>PID</th>
                                                    <th>Service</th>
                                                    <th>Status</th>
                                                    <th>Actions</th>
                                                </tr>
                                            </thead>
                                            <tbody>
                                                <tr>
                                                    <td>123</td>
                                                    <td>Tomcat</td>
                                                    <td>Running</td>
                                                    <td>
                                                        <a href="#">Stop</a>
                                                        <a href="#">Start</a>
                                                        <a href="#">Restart</a>
                                                    </td>
                                                </tr>
                                                <tr>
                                                    <td>456</td>
                                                    <td>MySQL</td>
                                                    <td>Running</td>
                                                    <td>
                                                        <a href="#">Stop</a>
                                                        <a href="#">Start</a>
                                                        <a href="#">Restart</a>
                                                    </td>
                                                </tr>
                                            </tbody>

                                        </table>
                                    </p>
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
                        </div>
                        
                        
                    </aside>

                </div>
                
            </div>
        </section>
        
    </s:layout-component>
</s:layout-render>