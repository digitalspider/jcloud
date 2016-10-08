<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ include file="/jsp/include/taglibs.jsp"%>

<!DOCTYPE html>

<s:layout-render name="/jsp/layout/secure.jsp" pageTitle="Login">
    <s:layout-component name="contents">
<%
String username = request.getRemoteUser();
%>
<span>Hello <%= username %>. This is a secure resource</span>
	
    </s:layout-component>
</s:layout-render>