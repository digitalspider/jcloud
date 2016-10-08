<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ include file="/jsp/include/taglibs.jsp"%>

<div class="tm-header">
    <div class="container-fluid">
        <div class="tm-header-inner">
            <a href="${ctx}/index.jsp" class="navbar-brand tm-site-name">JCloud</a>

            <!-- navbar -->
            <nav class="navbar tm-main-nav">

                <button class="navbar-toggler hidden-md-up" type="button" data-toggle="collapse" data-target="#tmNavbar">
                    &#9776;
                </button>

                <div class="collapse navbar-toggleable-sm" id="tmNavbar">
                    <ul class="nav navbar-nav">
                        <li class="nav-item">
                            <a href="${ctx}/service.jsp" class="nav-link">Services</a>
                        </li>
                        <li class="nav-item">
                            <a href="${ctx}/blog.jsp" class="nav-link">Blog</a>
                        </li>
                        <li class="nav-item">
                            <a href="${ctx}/contact.jsp" class="nav-link">Contact</a>
                        </li>
                        <li class="nav-item">
                        	<c:set var="user" scope="session" value='<%= request.getRemoteUser()%>'/>
                        	<c:choose>
                        		<c:when test="${user == null}">
                            		<a href="${ctx}/secure/login" class="nav-link">Login</a>
                            	</c:when>
                            	<c:otherwise>
                            		<a href="${ctx}/secure/index" class="nav-link">My Account</a>
                            	</li>
                            	<li class="nav-item">
                            		<a href="${ctx}/logout" class="nav-link">Logout</a>
                            	</c:otherwise>
                            </c:choose>
                        </li>
                    </ul>
                </div>

            </nav>

        </div>
    </div>
</div>
