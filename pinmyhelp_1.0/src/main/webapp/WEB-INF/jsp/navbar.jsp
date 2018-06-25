<!--NAVBAR CLAIMANT-->

<%@page contentType="text/html" pageEncoding="UTF-8" session="true"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!--Navbar-->
<nav class="navbar navbar-expand-lg navbar-dark pink-color">
    <div class="container">
        <!-- Navbar brand -->
        <a class="navbar-brand" href="${pageContext.request.contextPath}/dashboard">Pin My Help</a>

        <!-- Collapse button -->
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#basicExampleNav"
                aria-controls="basicExampleNav"
                aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>

        <!-- Collapsible content -->
        <div class="collapse navbar-collapse" id="basicExampleNav">
            <!-- Links -->
            <ul class="navbar-nav mr-auto">
                <li class="nav-item active">
                    <a class="nav-link" href="${pageContext.request.contextPath}/dashboard">Dashboard
                        <span class="sr-only">(current)</span>
                    </a>
                </li>
                <c:if test="${type != 'Voluntary'}"> 
                    <li class="nav-item">
                        <a class="nav-link" href="${pageContext.request.contextPath}/requests/my">Meus Pedidos</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="${pageContext.request.contextPath}/offers">Todas ofertas</a>
                    </li>
                </c:if>
                <c:if test="${type != 'Claimant'}"> 
                    <li class="nav-item">
                        <a class="nav-link" href="${pageContext.request.contextPath}/requests">Todas solicitações</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="${pageContext.request.contextPath}/offers/my">Minhas Ofertas</a>
                    </li>
                </c:if>

            </ul>
            <!-- /Links -->
            <!-- Icons -->
            <ul class="navbar-nav ml-auto nav-flex-icons justify-content-center">
                <li class="nav-item">
                    <a class="nav-link waves-effect waves-light"> <c:if test="${not empty messages}">messages.quantity</c:if>
                            <i class="fa fa-envelope"></i>
                        </a>
                    </li>
                    <li class="nav-item avatar dropdown">
                        <a class="nav-link dropdown-toggle waves-effect waves-light" id="navbarDropdownMenuLink-5"
                           data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                        <c:choose>
                            <c:when test="${not empty profile.imageUrl}">
                                <img src="${profile.imageUrl}" width="30px"
                                     class="rounded-circle z-depth-0" alt="avatar image">
                            </c:when>
                            <c:otherwise>
                                <i class="fa fa-user-circle" style="font-size: 20pt;"></i>
                            </c:otherwise>
                        </c:choose>
                        <c:if test="${not empty profile.image}">

                        </c:if>
                    </a>
                    <div class="dropdown-menu dropdown-menu-right dropdown-secondary"
                         aria-labelledby="navbarDropdownMenuLink-5">
                        <a class="dropdown-item waves-effect waves-light" href="${pageContext.request.contextPath}/account/edit/profile/${sessionScope.user.id}">Meu Perfil</a>
                        <a class="dropdown-item waves-effect waves-light" href="${pageContext.request.contextPath}/account/edit/${sessionScope.type == "Entity" ? "entity" : "person"}/${sessionScope.user.id}">Cadastro</a>
                        <a class="dropdown-item waves-effect waves-light" href="${pageContext.request.contextPath}/sign-out">Sair</a>
                    </div>
                </li>
            </ul>
            <!-- /Icons -->
        </div>
        <!-- /Collapsible content -->
    </div>
</nav>
<!-- /Navbar -->

