<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ include file="/jsp/include/taglibs.jsp"%>

<s:layout-render name="/jsp/layout/secure.jsp" pageTitle="Server">

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

                    <div class="col-xs-12 col-sm-12 col-md-12 col-lg-12 col-xl-12">
                        <div class="tm-blog-post">
                            <h3 class="tm-gold-text">Your Servers</h3>
                            Your server list
                            <table class="table table-striped table-condensed">
                                <thead style="background-color: #4CAF50; color: white; padding: 6px;">
                                    <tr>
                                        <th style="padding: 6px;">Server</th>
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
                                        <td><a href="${ctx}/secure/server/123/view">alpine1</a></td>
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
                                            <a href="${ctx}/secure/server/123/stop">Stop</a>
                                            <a href="${ctx}/secure/server/123/start">Start</a>
                                            <a href="${ctx}/secure/server/123/restart">Restart</a>
                                        </td>
                                    </tr>
									<c:forEach items="${actionBean.servers}" var="server">
										<tr>
											<td><a href="${ctx}/secure/server/${server.id}/view">${server.name}</a></td>
									   		<td>${server.status}</td>
											<td></td>
											<td></td>
											<td></td>
											<td></td>
											<td></td>
											<td></td>
											<td></td>
											<td></td>
											<td>
												<a href="${ctx}/secure/server/${server.id}/stop">Stop</a>
												<a href="${ctx}/secure/server/${server.id}/start">Start</a>
												<a href="${ctx}/secure/server/${server.id}/restart">Restart</a>
											</td>
									   	</tr>
									</c:forEach>
                                </tbody>

                            </table>
                        </div>

                        <a href="${ctx}/secure/server/add" class="tm-btn text-uppercase">Add New Server</a>

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
                                                        <a href="${ctx}/secure/server/123/service/1/stop">Stop</a>
                                                        <a href="${ctx}/secure/server/123/service/1/start">Start</a>
                                                        <a href="${ctx}/secure/server/123/service/1/restart">Restart</a>
                                                    </td>
                                                </tr>
                                                <tr>
                                                    <td>456</td>
                                                    <td>MySQL</td>
                                                    <td>Running</td>
                                                    <td>
                                                        <a href="${ctx}/secure/server/123/service/2/stop">Stop</a>
                                                        <a href="${ctx}/secure/server/123/service/2/start">Start</a>
                                                        <a href="${ctx}/secure/server/123/service/2/restart">Restart</a>
                                                    </td>
                                                </tr>
                                            </tbody>

                                        </table>
                                    </p>
                                </div>
                            </div>
                        </div>
                    </div>

                </div>
                
            </div>
        </section>
        
    </s:layout-component>
</s:layout-render>