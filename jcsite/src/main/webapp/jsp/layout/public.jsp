<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ include file="/jsp/include/taglibs.jsp"%>

<!DOCTYPE html>

<s:layout-definition>
<html>
    <head>
        <title>JCloud ${pageTitle}</title>
        <s:layout-component name="htmlhead">
        	<jsp:include page="/jsp/include/htmlhead.jsp"/>
        </s:layout-component>
        <s:layout-component name="customhead">
        </s:layout-component>
    </head>

    <body>
		<s:layout-component name="header">
            <jsp:include page="/jsp/include/header.jsp"/>
        </s:layout-component>

        <s:layout-component name="contents"/>

        <s:layout-component name="footer">
            <jsp:include page="/jsp/include/footer.jsp"/>
        </s:layout-component>
        <s:layout-component name="defaultjs">
        	<jsp:include page="/jsp/include/javascript.jsp"/>
        	<jsp:include page="/jsp/include/templates.jsp"/>
        </s:layout-component>
        <s:layout-component name="customjs">
        </s:layout-component>
    </body>
</html>
</s:layout-definition>