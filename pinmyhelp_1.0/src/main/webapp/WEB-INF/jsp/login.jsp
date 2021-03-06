<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta http-equiv="x-ua-compatible" content="ie=edge">
    <title>Pin My Help | Login</title>
    <!-- Font Awesome -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
    <!-- Bootstrap core CSS -->
    <link href="${pageContext.request.contextPath}/assets/css/bootstrap.min.css" rel="stylesheet">
    <!-- Material Design Bootstrap -->
    <link href="${pageContext.request.contextPath}/assets/css/mdb.min.css" rel="stylesheet">
    <!-- Custom styles -->
    <link href="${pageContext.request.contextPath}/assets/css/custom.css" rel="stylesheet">
    
    <link href="${pageContext.request.contextPath}/assets/css/jquery.toast.min.css" rel="stylesheet">
</head>
<body>
    <div class="fluid-container">
        <div class="col-md-12">
            <a href="${pageContext.request.contextPath}/"><i class="fa fa-chevron-left pull-left pink-text" style="margin-top: 8px; font-size: 20px;"></i></a>
        </div>
    </div>
    <div class="container">
    <div class="col-md-6 center-block" style="margin: 0 auto; margin-top: 10%">
        <section class="form-elegant">
        <section class="form-light">
            <div class="card">
            <div class="card-body mx-4">
                <form method="POST" action="${pageContext.request.contextPath}/sign-in" name="send_login">
                    <div class="text-center">
                        <h3 class="pink-text mb-5">
                            <strong>Pin My Help</strong>
                        </h3>
                    </div>
                    <div class="md-form">
                        <input type="email" id="Form-email" class="form-control" name="email" value="${registered_email}">
                        <label for="Form-email">Seu e-mail</label>
                        <form:errors path="user.email" cssStyle="color:red"/>
                    </div>
                    <div class="md-form pb-3">
                        <input type="password" id="Form-pass" class="form-control" name="password">
                        <label for="Form-pass">Senha</label>
                        <form:errors path="user.password" cssStyle="color:red"/>
                        <c:if test="${not empty login_error}"><span class="error">${login_error}</span></c:if>                            
                        <div class="form-check"></div>
                        <p class="font-small grey-text d-flex justify-content-end">
                            <a href="#" class="font-weight-light ml-1">
                                Esqueceu a senha?
                            </a>
                        </p>
                    </div>
                    <div class="row d-flex align-items-center mb-4">
                        <div class="col-md-3 col-md-12 text-center">
                            <button type=submit"
                                    class="btn btn-pink btn-block btn-rounded z-depth-1 waves-effect waves-light">
                                Entrar
                            </button>
                        </div>
                        <div class="clearfix"></div>
                        <br/>
                        <div class="col-md-12 mt-4">
                            <p class="font-small grey-text d-flex justify-content-center">Ainda não tem conta?
                                <a href="${pageContext.request.contextPath}/account/create" class="font-weight-light ml-1"> Fazer Cadastro</a>
                            </p>
                        </div>
                    </div>
                </form>
            </div> <!-- ./card-body -->
            </div> <!-- ./card -->
        </section> <!-- ./form-light -->
        </section> <!-- ./form-elegant -->
    </div> <!-- ./col -->
    </div> <!-- ./container -->
    
    <!-- SCRIPTS -->
    <!-- JQuery -->
    <script type="text/javascript" src="${pageContext.request.contextPath}/assets/js/jquery-3.2.1.min.js"></script>
    <!-- Bootstrap tooltips -->
    <script type="text/javascript" src="${pageContext.request.contextPath}/assets/js/popper.min.js"></script>
    <!-- Bootstrap core JavaScript -->
    <script type="text/javascript" src="${pageContext.request.contextPath}/assets/js/bootstrap.min.js"></script>
    <!-- MDB core JavaScript -->
    <script type="text/javascript" src="${pageContext.request.contextPath}/assets/js/mdb.js"></script>
    <!-- Mask JQuery -->                                              
    <script type="text/javascript" src="${pageContext.request.contextPath}/assets/js/jquery.toast.min.js"></script>
    <jsp:include page="messages.jsp" flush="true" />
    
</body>
</html>