<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta http-equiv="x-ua-compatible" content="ie=edge">
    <title>Pin My Help <c:if test="${not empty title}"> | ${title}</c:if></title>
    <!-- Font Awesome -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
    <!-- Bootstrap core CSS -->
    <link href="${pageContext.request.contextPath}/assets/css/bootstrap.min.css" rel="stylesheet">
    <!-- Material Design Bootstrap -->
    <link href="${pageContext.request.contextPath}/assets/css/mdb.min.css" rel="stylesheet">
    <!-- Your custom styles (optional) -->
    <link href="${pageContext.request.contextPath}/assets/css/custom.css" rel="stylesheet">
     <!-- JQuery -->
    <script type="text/javascript" src="${pageContext.request.contextPath}/assets/js/jquery-3.2.1.min.js"></script>
</head>
<body>

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
                    <li class="nav-item">
                        <a class="nav-link" href="${pageContext.request.contextPath}/requests">Solicitações</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="${pageContext.request.contextPath}/offers">Ofertas</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="${pageContext.request.contextPath}/requests/my">Meus Pedidos</a>
                    </li>
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
                            <a class="dropdown-item waves-effect waves-light" href="#">Meu Perfil</a>
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

    <div class="container">
        <c:if test = "${ fn:length(msg) gt 0 }">
            <br/>
            <div id="msg-returns" class="alert alert-success">${msg}</div> 
        </c:if>
        <jsp:include page="pages/${page}.jsp" flush="true" />
    </div>

    <!-- SCRIPTS -->
    <!-- Bootstrap tooltips -->
    <script type="text/javascript" src="${pageContext.request.contextPath}/assets/js/popper.min.js"></script>
    <!-- Bootstrap core JavaScript -->
    <script type="text/javascript" src="${pageContext.request.contextPath}/assets/js/bootstrap.min.js"></script>
    <!-- MDB core JavaScript -->
    <script type="text/javascript" src="${pageContext.request.contextPath}/assets/js/mdb.js"></script>
    <script type="text/javascript">

        $(document).ready(function () {
            setTimeout(function () {
                $('#msg-returns').hide();
            }, 5000);
        });


        //            $("#data").datepicker({
        //                minDate: 0,
        //                dateFormat: "dd/mm/yyyy"
        //            });
        //            $("#data").mask("00/00/0000");
        //            $('[name="hora"]').mask('00:00:00');

    </script>

</body>
</html>
