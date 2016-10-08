<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ include file="/jsp/include/taglibs.jsp"%>

<!-- load JS files -->
<script type="text/javascript" src="${ctx}/js/tether.min.js"></script> <!-- Tether for Bootstrap, https://www.atlasestateagents.co.uk/javascript/tether.min.js, http://stackoverflow.com/questions/34567939/how-to-fix-the-error-error-bootstrap-tooltips-require-tether-http-github-h -->
<script type="text/javascript" src="${ctx}/js/bootstrap.min.js"></script>       <!-- Bootstrap (http://v4-alpha.getbootstrap.com/) -->
<script type="text/javascript" src="${ctx}/js/mustache-2.2.1.min.js"></script>  <!-- Templates in Javascript (https://mustache.github.io/) -->
<script type="text/javascript" src="${ctx}/js/jcloud.js"></script>

<!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
<!--[if lt IE 9]>
<script type="text/javascript" src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
<script type="text/javascript" src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
<![endif]-->

<script>
	$(document).ready(function () {
		if ($("#footer-links").length) {
			getLinks("${ctx}", "footer", 5, "#link-footer-template", "#footer-links");
		}
	});
</script>