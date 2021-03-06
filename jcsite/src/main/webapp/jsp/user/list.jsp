<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page import="org.apache.commons.lang3.StringEscapeUtils" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
        <title>Show All Users</title>
    </head>
    <body>
        <a href="${baseUrl}">Home</a>&nbsp;|&nbsp;
        <% if (request.getUserPrincipal()==null) { %>
            <strong><a href="<%=request.getContextPath()%>/login.jsp">Login</a></strong>
        <% } else { %>
            <strong><a href="<%=request.getContextPath()%>/logout.jsp">Logout</a></strong>
        <% } %>&nbsp;|&nbsp;
        <a href="${beanUrl}/list">List</a>&nbsp;|&nbsp;
        <a href="${beanUrl}/list/1">Page1</a>&nbsp;|&nbsp;
        <a href="${beanUrl}/list/2">Page2</a>&nbsp;|&nbsp;
        <a href="${beanUrl}/find/id/1">Find ID 1</a>&nbsp;|&nbsp;
        <a href="${beanUrl}/config/order/id/desc">ID Desc</a>&nbsp;|&nbsp;
        <a href="${beanUrl}/config/order/id/asc">ID ASC</a>&nbsp;|&nbsp;
        <a href="${beanUrl}/config/order/id">ID Toggle</a>&nbsp;|&nbsp;
        <a href="${beanUrl}/config/limit/2">Limit 2</a>&nbsp;|&nbsp;
        <a href="${beanUrl}/config/limit">Limit Reset</a>&nbsp;|&nbsp;
        <a href="${beanUrl}.json">JSON</a>&nbsp;|&nbsp;
        <br/>

        <p>Showing ${beans.size()}/${beancount}</p>
        <table>
        <thead>
          <tr>
    <th><a href="${beanUrl}/config/order/id">User ID</a></th>
    <th><a href="${beanUrl}/config/order/cdate">Cdate</a></th>
    <th><a href="${beanUrl}/config/order/cuser">Cuser</a></th>
    <th><a href="${beanUrl}/config/order/description">Description</a></th>
    <th><a href="${beanUrl}/config/order/email">Email</a></th>
    <th><a href="${beanUrl}/config/order/firstname">Firstname</a></th>
    <th><a href="${beanUrl}/config/order/image">Image</a></th>
    <th><a href="${beanUrl}/config/order/lastname">Lastname</a></th>
    <th><a href="${beanUrl}/config/order/mdate">Mdate</a></th>
    <th><a href="${beanUrl}/config/order/mobile">Mobile</a></th>
    <th><a href="${beanUrl}/config/order/muser">Muser</a></th>
    <th><a href="${beanUrl}/config/order/password">Password</a></th>
    <th><a href="${beanUrl}/config/order/status">Status</a></th>
    <th><a href="${beanUrl}/config/order/tags">Tags</a></th>
    <th><a href="${beanUrl}/config/order/token">Token</a></th>
    <th><a href="${beanUrl}/config/order/type">Type</a></th>
    <th><a href="${beanUrl}/config/order/url">Url</a></th>
    <th><a href="${beanUrl}/config/order/username">Username</a></th>

            <th colspan="2">Action</th>
          </tr>
        </thead>
        <tbody>
          <c:forEach items="${beans}" var="bean">
            <tr>
              <td><a href="${beanUrl}/show/<c:out value='${bean.id}'/>"><c:out value="${bean.id}" /></a></td>
              <td><c:out value="${bean.cdate}" /></td>
              <td><a href="${baseUrl}/user/show/<c:out value='${bean.cuser.id}'/>"><c:out value="${bean.cuser.displayValue}" /></a></td>
              <td><c:out value="${bean.description}" /></td>
              <td><c:out value="${bean.email}" /></td>
              <td><c:out value="${bean.firstname}" /></td>
              <td><c:out value="${bean.image}" /></td>
              <td><c:out value="${bean.lastname}" /></td>
              <td><c:out value="${bean.mdate}" /></td>
              <td><c:out value="${bean.mobile}" /></td>
              <td><a href="${baseUrl}/user/show/<c:out value='${bean.muser.id}'/>"><c:out value="${bean.muser.displayValue}" /></a></td>
              <td><c:out value="${bean.password}" /></td>
              <td><c:out value="${bean.status}" /></td>
              <td><c:out value="${bean.tags}" /></td>
              <td><c:out value="${bean.token}" /></td>
              <td><c:out value="${bean.type}" /></td>
              <td><c:out value="${bean.url}" /></td>
              <td><c:out value="${bean.username}" /></td>

              <td><a href="${beanUrl}/edit/<c:out value='${bean.id}'/>">Update</a></td>
              <td><a href="${beanUrl}/delete/<c:out value='${bean.id}'/>">Delete</a></td>
            </tr>
          </c:forEach>
        </tbody>
        </table>

        <p>
            <a href="${beanUrl}/insert">Add User</a>
        </p>
    </body>
</html>