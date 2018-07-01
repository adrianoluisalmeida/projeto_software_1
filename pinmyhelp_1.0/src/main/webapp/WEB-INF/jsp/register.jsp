<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
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
    </head>
    <body>
        <a href="${pageContext.request.contextPath}/login"><i class="fa fa-chevron-left pull-left pink-text" style="margin-left:5px; font-size: 20px;"></i></a>
        <div class="container">
            <div class="col-md-6 center-block" style="margin: 0 auto; margin-top: 10px">
                <section class="form-elegant">
                    <!--Section: Live preview-->
                    <section class="form-light">
                        <!--Form without header-->
                        <div class="card">
                            <div class="card-body mx-4" style="padding: 0; padding-top: 20px; padding-bottom: 20px">
                                <!--Header-->
                                <div class="text-center">
                                    <h3 class="pink-text mb-5">
                                        <strong>Pin My Help</strong>
                                    </h3>
                                </div>
                                <!-- Nav tabs -->
                                <ul class="nav nav-tabs nav-justified">
                                    <li class="nav-item">
                                        <a class="nav-link active" id="tab-person" data-toggle="tab" href="#panel1" role="tab">Voluntário ou
                                            Requerente</a>
                                    </li>
                                    <li class="nav-item">
                                        <a class="nav-link" id="tab-entity" data-toggle="tab" href="#panel2" role="tab">Entidade</a>
                                    </li>
                                </ul>
                                <!-- Tab panels -->
                                <div class="tab-content">
                                    <!--Panel 1-->
                                    <div class="tab-pane fade in show active" id="panel1" role="tabpanel">
                                        <div class="col-md-12" style="padding: 0">
                                            <form method="POST" action="${pageContext.request.contextPath}/account/store/person">        
                                                <jsp:include page="pages/account/form_person.jsp" flush="true" />
                                            </form>
                                        </div>
                                    </div>
                                    <div class="tab-pane fade" id="panel2" role="tabpanel">
                                        <form action="${pageContext.request.contextPath}/account/store/entity" method="POST">
                                            <jsp:include page="pages/account/form_entity.jsp" flush="true" />
                                        </form>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </section>
                </section>
            </div>
        </div>

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
        <script type="text/javascript" src="${pageContext.request.contextPath}/assets/js/jquery.mask.min.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/assets/js/infocep.js"></script>

    <c:if test="${not empty tab}">
        <c:choose>
            <c:when test="${tab == 'tab-entity'}">
                <script>
                    $(function() {
                        $("#${tab}").tab("show");
                    });
                </script>
            </c:when>
        </c:choose>        
    </c:if>
    <script>
        // put mask into form fields
        $(function() {
            $("#Form-cpf").mask("000.000.000-00", {clearIfNotMatch: true});
            $("#Form-rg").mask("0000000000"); // could change between states
            $("#Form-phone").mask("(00) 0000-0000", {clearIfNotMatch: true, onKeyPress: function (phone, e, field, options) {
                    var masks = ['(00) 0000-00000', '(00) 0 0000-0000'];
                    mask = (phone.length > 14) ? masks[1] : masks[0];
                    $("#Form-phone").mask(mask, options);
                }});
            $("#Form-birth").mask("00/00/0000", {clearIfNotMatch: true});
            $("#Form-cnpj").mask("00.000.000/0000-00", {clearIfNotMatch: true});
            $("#Form-foundation").mask("00/00/0000", {clearIfNotMatch: true});
            $("#Form-phone-entity").mask("(00) 0000-0000", {clearIfNotMatch: true, onKeyPress: function (phone, e, field, options) {
                    var masks = ['(00) 0000-00000', '(00) 0 0000-0000'];
                    mask = (phone.length > 14) ? masks[1] : masks[0];
                    $("#Form-phone-entity").mask(mask, options);
                }});
            $("#Form-cep").mask("00.000-000", {clearIfNotMatch: true});
        });
    </script>
    </body>
</html>