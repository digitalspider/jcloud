<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ include file="/jsp/include/taglibs.jsp"%>

<script>
	$(document).ready(function () {
		getLinks("${ctx}", "sidebar", 5, "#link-template", "#sidebar-links");
	});
</script>

<div class="col-xs-12 col-sm-12 col-md-4 col-lg-3 col-xl-3 tm-2-rows-sm-down-2">

	<h3 class="tm-gold-text">Sidebar Links</h3>

	<nav>
		<ul class="nav" id="links" id="sidebar-links">
			<li><a href="${ctx}/login.jsp" class="tm-text-link">Register</a></li>
			<li><a href="${ctx}/service.jsp" class="tm-text-link">Services</a></li>
			<li><a href="${ctx}/hosting.jsp" class="tm-text-link">Business Hosting</a></li>
			<li><a href="${ctx}/develop.jsp" class="tm-text-link">Software Developers</a></li>
			<li><a href="${ctx}/learn.jsp" class="tm-text-link">Educators and Students</a></li>
			<li><a href="${ctx}/about.jsp" class="tm-text-link">About Us</a></li>
			<li><a href="${ctx}/billing.jsp" class="tm-text-link">Billing</a></li>
		</ul>
	</nav>

</div>