<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
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
                                        <a class="nav-link active" data-toggle="tab" href="#panel1" role="tab">Voluntário ou
                                            Requerente</a>
                                    </li>
                                    <li class="nav-item">
                                        <a class="nav-link" data-toggle="tab" href="#panel2" role="tab">Entidade</a>
                                    </li>

                                </ul>
                                <!-- Tab panels -->
                                <div class="tab-content">
                                    <!--Panel 1-->
                                    <div class="tab-pane fade in show active" id="panel1" role="tabpanel">
                                        <div class="col-md-12" style="padding: 0">

                                            <form method="POST" action="${pageContext.request.contextPath}/account/store/person">        

                                                <div class="form-group mb-0 mt-4">
                                                    <select name="type" value="Entity" class="mdb-select form-control" required>
                                                        <option value="" disabled selected>Seu objetivo no Pin My Help</option>

                                                        <option value="Voluntary" ${person.type == 'Voluntary' ? 'selected' : ''}">Quero Ajudar</option>
                                                        <option value="Claimant" ${person.type == 'Claimant' ? 'selected' : ''}">Preciso de Ajuda</option>
                                                    </select>
                                                    <form:errors path="person.type" cssStyle="color:red"/>
                                                </div>

                                                <!--Body-->
                                                <div class="md-form">
                                                    <input type="text" name="name" id="Form-name" value="${person.name}"  class="form-control">
                                                    <label for="Form-name">Nome</label>
                                                    <form:errors path="person.name" cssStyle="color:red"/>
                                                </div>

                                                <!--Body-->
                                                <div class="md-form">
                                                    <input name="cpf" type="text" id="Form-cpf" value="${person.cpf}" class="form-control">
                                                    <label for="Form-cpf">CPF</label>
                                                    <form:errors path="person.cpf" cssStyle="color:red"/>
                                                </div>

                                                <!--Body-->
                                                <div class="md-form">
                                                    <input name="rg" type="text" id="Form-rg" value="${person.rg}" class="form-control">
                                                    <label for="Form-rg">RG</label>
                                                    <form:errors path="person.rg" cssStyle="color:red"/>
                                                </div>


                                                <!--Body-->
                                                <div class="md-form">
                                                    <input name="bornDate" id="Form-birth"  value="${person.bornDate}" type="text" class="form-control" required>
                                                    <label for="Form-birth">Data Nascimento</label>
                                                </div>


                                                <div class="md-form">
                                                    <input name="firstPhone" id="Form-phone" type="tel" value="${person.firstPhone}" class="form-control">
                                                    <label for="Form-phone">Telefone</label>
                                                    <form:errors path="person.firstPhone" cssStyle="color:red"/>
                                                </div>


                                                <div class="md-form">
                                                    <input type="email" name="email" id="Form-email" value="${person.email}" class="form-control">
                                                    <label for="Form-email">Seu e-mail</label>
                                                    <form:errors path="person.email" cssStyle="color:red"/>
                                                </div>

                                                <div class="md-form pb-3">
                                                    <input type="password" id="Form-pass" name="password"  class="form-control">
                                                    <label for="Form-pass">Senha</label>
                                                    <form:errors path="person.password" cssStyle="color:red"/>
                                                </div>

                                                <div class="row d-flex align-items-center mb-4">
                                                    <div class="col-md-3 col-md-12 text-center">
                                                        <button type="submit"
                                                                class="btn btn-pink btn-block btn-rounded z-depth-1 waves-effect waves-light">
                                                            Cadastrar
                                                        </button>
                                                    </div>
                                                </div>
                                            </form>
                                        </div>

                                    </div>

                                    <div class="tab-pane fade" id="panel2" role="tabpanel">

                                        <form action="${pageContext.request.contextPath}/account/store/entity" method="POST">
                                            <!--Body-->
                                            <div class="md-form">
                                                <input type="text" name="name" id="Form-social-name" value="${entity.name}" class="form-control">
                                                <label for="Form-social-name">Razão Social</label>
                                                <form:errors path="entity.name" cssStyle="color:red"/>
                                            </div>

                                            <!--Body-->
                                            <div class="md-form">
                                                <input name="cnpj" type="text" id="Form-cnpj" value="${entity.cnpj}"  class="form-control">
                                                <label for="Form-cnpj">CNPJ</label>
                                                <form:errors path="entity.cnpj" cssStyle="color:red"/>
                                            </div>


                                            <!--Body-->
                                            <div class="md-form">

                                                <input name="foundationDate" id="Form-foundation" value="${entity.foundationDate}" type="text" class="form-control" required>
                                                <label for="Form-foundation">Data Fundação</label>
                                            </div>


                                            <div class="md-form">

                                                <input name="firstPhone" id="Form-phone-entity" value="${entity.firstPhone}"  type="tel" class="form-control">
                                                <label for="Form-phone-entity">Telefone</label>
                                                <form:errors path="entity.firstPhone" cssStyle="color:red"/>
                                            </div>


                                            <div class="md-form">
                                                <input type="email" name="email" id="Form-email-entity" value="${entity.email}"  class="form-control">
                                                <label for="Form-email-entity">E-mail para contato e login</label>
                                                <form:errors path="entity.email" cssStyle="color:red"/>
                                            </div>

                                            <div class="md-form">
                                                <input type="password" id="Form-pass-entity" name="password" class="form-control">
                                                <label for="Form-pass-entity">Senha</label>
                                                <form:errors path="entity.password" cssStyle="color:red"/>
                                            </div>
                                            <h4>Endereço</h4>
                                            <div class="row height-60">
                                                <div class="col-8">
                                                    <div class="md-form">
                                                        <input type="text" name="cep" value="${entity.address.cep}" id="Form-cep" class="form-control" >
                                                        <label for="Form-cep">CEP</label>
                                                    </div>
                                                </div>

                                                <div class="col-4">
                                                    <div class="md-form">
                                                        <input type="text" name="uf" value="${entity.address.uf}" id="Form-uf" class="form-control" >
                                                        <label for="Form-uf">UF</label>
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="row height-60">
                                                <div class="col-6">
                                                    <div class="md-form">
                                                        <input type="text" name="distric" value="${entity.address.distric}" id="Form-distric" class="form-control" >
                                                        <label for="Form-distric">Bairro</label>
                                                    </div>
                                                </div>

                                                <div class="col-6">
                                                    <div class="md-form">
                                                        <input type="text" name="city" value="${entity.address.city}" id="Form-city" class="form-control" >
                                                        <label for="Form-city">Cidade</label>
                                                    </div>
                                                </div>
                                            </div>

                                            <div class="md-form">
                                                <input type="text" name="complement" value="${entity.address.complement}" id="Form-complement" class="form-control">
                                                <label for="Form-complement">Complemento</label>
                                            </div>
                                            <div class="md-form"> 
                                                <textarea name="description" id="Form-description"   value="${entity.description}" class="form-control"></textarea>
                                                <!--<input type="text" name="description" >-->
                                                <label for="Form-description">Descrição da entidade</label>
                                            </div>


                                            <div class="row d-flex align-items-center mb-4">
                                                <div class="col-md-3 col-md-12 text-center">
                                                    <button type="submit"
                                                            class="btn btn-pink btn-block btn-rounded z-depth-1 waves-effect waves-light">
                                                        Cadastrar
                                                    </button>
                                                </div>
                                            </div>

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

        <script>
            // put mask into form fields
            $(function () {
                $("#Form-cpf").mask("000.000.000-00", {clearIfNotMatch: true});
                $("#Form-rg").mask("0000000000"); // could change between states
                $("#Form-phone").mask("(00) 0000-0000", {clearIfNotMatch: true, onKeyPress: function (phone, e, field, options) {
                        var masks = ['(00) 0000-00000', '(00) 0 0000-0000'];
                        mask = (phone.length > 14) ? masks[1] : masks[0];
                        console.log(phone.length);
                        $("#Form-phone").mask(mask, options);
                    }});
                $("#Form-birth").mask("00/00/0000", {clearIfNotMatch: true});
                $("#Form-cnpj").mask("00.000.000/0000-00", {clearIfNotMatch: true});
                $("#Form-foundation").mask("00/00/0000", {clearIfNotMatch: true});
                $("#Form-phone-entity").mask("(00) 0000-0000", {clearIfNotMatch: true, onKeyPress: function (phone, e, field, options) {
                        var masks = ['(00) 0000-00000', '(00) 0 0000-0000'];
                        mask = (phone.length > 14) ? masks[1] : masks[0];
                        console.log(phone.length);
                        $("#Form-phone-entity").mask(mask, options);
                    }});
                $("#Form-cep").mask("00.000-000", {clearIfNotMatch: true});
            });
        </script>

    </body>
</html>