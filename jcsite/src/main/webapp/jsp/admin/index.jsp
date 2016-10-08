<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ include file="/jsp/include/taglibs.jsp"%>

<!DOCTYPE html>

<s:layout-render name="/jsp/layout/public.jsp" pageTitle="Login">
    <s:layout-component name="contents">
<%
String username = request.getRemoteUser();
%>
<h1>Welcome Administrator <%= username %>.</h1>

    </s:layout-component>
</s:layout-render>