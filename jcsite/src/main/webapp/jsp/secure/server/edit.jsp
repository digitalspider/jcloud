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
		<s:form id="serverForm" method="post" action="/secure/server" class="tm-server-form">
			<div class="row">

				<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12 col-xl-12">
					<div class="tm-blog-post">
						<h3 class="tm-gold-text">Server Page</h3>
						<p>This is the page for creating or editing a server</p>

						<s:errors globalErrorsOnly="true"/>

						<div class="form-group">
							<s:errors field="hostname"/>
							<label for="hostname" class="control-label">Server Name:</label>
							<input type="text" id="hostname" name="hostname" class="form-control" placeholder="server" required="true" style="display: inline; width: 200px;"/>
							<span>.jcloud.com.au</span>
						</div>

					</div>

					<div class="row tm-margin-t-small">
						<div class="col-xs-12 col-sm-12 col-md-3 col-lg-3 col-xl-3">
								<h4 class="tm-margin-b-20 tm-gold-text">Hardware</h4>
								<table class="table table-striped table-condensed">
									<tr >
										<td><label for="memory">Memory</label></td>
										<td>
											<select class="form-control form-control-bootstrap" id="memory" name="memory">
												<option value="128">128 MB</option>
												<option value="512">0.5 GB</option>
												<option value="1024">1 GB</option>
												<option value="2048">2 GB</option>
												<option value="4096">4 GB</option>
											</select>
										</td>
									</tr>
									<tr>
										<td><label for="disk">Hard Disk</label></td>
										<td>
											<select class="form-control form-control-bootstrap" id="disk" name="disk">
												<option value="1000">1 GB</option>
												<option value="10000">10 GB</option>
												<option value="20000">20 GB</option>
												<option value="40000">40 GB</option>
												<option value="80000">80 GB</option>
											</select>
										</td>
									</tr>
									<tr>
										<td><label for="backups">Backups</label></td>
										<td>
											<select class="form-control form-control-bootstrap" id="backups" name="backups">
												<option>None</option>
												<option>Weekly</option>
												<option>Daily</option>
											</select>
										</td>
									</tr>
									<tr>
										<td><label for="cores">Cores</label></td>
										<td>
											<select class="form-control form-control-bootstrap" id="cores" name="cores">
												<option>1</option>
												<option>2</option>
												<option>3</option>
												<option>4</option>
											</select>
										</td>
									</tr>
								</table>
						</div>

						<div class="col-xs-12 col-sm-12 col-md-5 col-lg-5 col-xl-5">
								<h4 class="tm-margin-b-20 tm-gold-text">System Setup</h4>
								<table class="table table-striped table-condensed">
									<tr >
										<td><label for="os">Operating System</label></td>
										<td>
											<select class="form-control form-control-bootstrap" id="os" name="os">
												<option>Alpine edge</option>
												<option>Alpine 3.3</option>
												<option>Alpine 3.4</option>
												<option>Ubuntu 16.04 - xenial</option>
												<option>Ubuntu 14.04 - wily</option>
												<option>Ubuntu 12.04 - precise</option>
												<option>Centos 6</option>
												<option>Centos 7</option>
												<option>Debian wheezy</option>
												<option>OpenSUSE 13.2</option>
												<option>Fedora 22</option>
												<option>Fedora 23</option>
												<option>Fedora 24</option>
											</select>
										</td>
									</tr>
									<tr>
										<td><label for="dbserver">Database Server</label></td>
										<td>
											<select class="form-control form-control-bootstrap" id="dbserver" name="dbserver">
												<option>None</option>
												<option>MySQL 5.7</option>
												<option>Postgres</option>
											</select>
										</td>
									</tr>
									<tr>
										<td><label for="appserver">Application Server</label></td>
										<td>
											<select class="form-control form-control-bootstrap" id="appserver" name="appserver">
												<option>None</option>
												<option>Jetty</option>
												<option>Tomcat 6.x</option>
												<option>Tomcat 7.x</option>
												<option>Tomcat 8.x</option>
												<option>Tomcat 9.x</option>
												<option>JBossAS 5.x</option>
												<option>JBossAS 7.x</option>
												<option>Wildfly 8.x</option>
												<option>Wildfly 9.x</option>
												<option>Glassfish 3.x</option>
												<option>Glassfish 4.x</option>
											</select>
										</td>
									</tr>
									<tr>
										<td><label for="webserver">Web Server</label></td>
										<td>
											<select class="form-control form-control-bootstrap" id="webserver" name="webserver">
												<option>None</option>
												<option>Apache 2.x</option>
												<option>Nginx</option>
											</select>
										</td>
									</tr>
								</table>
						</div>

						<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4 col-xl-4">
								<h4 class="tm-margin-b-20 tm-gold-text">Application & Support</h4>
								<table class="table table-striped table-condensed">
									<tr>
										<td><label for="application">Application</label></td>
										<td>
											<select class="form-control form-control-bootstrap" id="application" name="application">
												<option>None / Custom</option>
												<option>JSPWiki</option>
												<option>Wordpress</option>
												<option>JIRA</option>
												<option>Confluence</option>
												<option>Jenkins</option>
												<option>JForum</option>
												<option>exoPlatform</option>
												<option>SugarCRM</option>
												<option>Broadleaf Commerce</option>
												<option>Wikipedia</option>
												<option>Drupal</option>
												<option>Prestashop</option>
												<option>Magento</option>
												<option>OneHippo</option>
												<option>OwnCloud</option>
											</select>
										</td>
									</tr>
									<tr>
										<td><label for="support">Support</label></td>
										<td>
											<select class="form-control form-control-bootstrap" id="support" name="support">
												<option>None / Self managed</option>
												<option>Basic Email</option>
												<option>Business Hours</option>
												<option>Enterprise 24x7</option>
											</select>
										</td>
									</tr>
								</table>
						</div>
					</div>
				</div>
			</div>
			<div class="row">
				Click to agree with our <a href="${ctx}/terms.jsp" target="_blank">Terms and Conditions</a>&nbsp;
				<input type="checkbox" id="terms" name="terms" class="terms" required="true"/>
				<br/>
				<s:submit id="addserver" name="addserver" value="Add Server" class="tm-btn text-uppercase"/>
				<a href="${ctx}/secure/checkout/add/1" class="tm-btn text-uppercase">Add To Cart</a>
				<a href="${ctx}/secure/server" class="tm-btn text-uppercase">Cancel</a>
			</div>
		</s:form>

	</div>
</section>
        
    </s:layout-component>
</s:layout-render>