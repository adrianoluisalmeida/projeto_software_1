<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib tagdir="/WEB-INF/tags" prefix="custom" %>

<form action="${pageContext.request.contextPath}/requests/store" enctype="multipart/form-data" accept-charset="iso-8859-1,utf-8" method="POST">
    <div class="row mt-5">
        <div class="col-md-8 mt-3">
            <h3>Descrição da Solicitação</h3>
            <div class="md-form">
                <textarea type="text" id="Form-requirement" name="requirementDescription" class="md-textarea form-control" rows="3"></textarea>
                <label for="Form-requirement">Descreva a sua necessidade</label>
            </div>
            <hr>
            <h3>Tipo de ajuda</h3>
            <div class="row">
                <div class="col">
                    <div class="md-form">
                        <select name="idType" class="mdb-select form-control">
                            <option value="" disabled selected>Selecione um tipo</option>
                            <c:forEach var="helpType" items="${HelpTypes}">
                                <option value="${helpType.id}">${helpType.type}</option>
                            </c:forEach>
                        </select>
                    </div>
                </div>
            </div>
            <hr>
            <h3>Meu endereço <button id="btn-get-address" class="btn btn-pink float-right waves-effect waves-light">Usar endereço atual</button></h3> 
            <div class="row height-60">
                <div class="col-8">
                    <div class="md-form">
                        <input type="text" name="address.postalCode" value="${claimant.address.postalCode}" id="Form-cep" class="form-control" >
                        <label for="Form-cep">CEP</label>
                        <form:errors path="entity.address.postalCode" cssStyle="color:red"/>
                    </div>
                </div>

                <div class="col-4">
                    <div class="md-form">
                        <input type="text" name="address.state" value="${claimant.address.state}" id="Form-uf" class="form-control" >
                        <label for="Form-uf">UF</label>
                        <form:errors path="entity.address.state" cssStyle="color:red"/>
                    </div>
                </div>
            </div>

            <div class="md-form">
                <input type="text" name="address.city" value="${claimant.address.city}" id="Form-city" class="form-control" >
                <label for="Form-city">Cidade</label>
                <form:errors path="entity.address.city" cssStyle="color:red"/>
            </div>

            <div class="row height-60">
                <div class="col-8">
                    <div class="md-form">
                        <input type="text" name="address.street" value="${claimant.address.street}" id="Form-street" class="form-control" >
                        <label for="Form-street">Rua</label>
                        <form:errors path="entity.address.street" cssStyle="color:red"/>
                    </div>
                </div>

                <div class="col-4">
                    <div class="md-form">
                        <input type="number" name="address.number" value="${claimant.address.number}" id="Form-number" class="form-control" min="0">
                        <label for="Form-number">Nº</label>
                        <form:errors path="entity.address.number" cssStyle="color:red"/>
                    </div>
                </div>
            </div>

            <div class="md-form">
                <input type="text" name="address.neighborhood" value="${claimant.address.neighborhood}" id="Form-neighborhood" class="form-control" >
                <label for="Form-neighborhood">Bairro</label>
                <form:errors path="entity.address.neighborhood" cssStyle="color:red"/>
            </div>
            
       <%--     <div class="row">
                <div class="col">  
                    <div class="md-form">
                        <input type="text" id="Form-address" name="Form-address" class="form-control" value="${claimant.address.street}, ${claimant.address.number} - ${claimant.address.neighborhood}, ${claimant.address.city} - ${claimant.address.state}, ${claimant.address.postalCode}, Brasil"/>
                        <input type="hidden" id="Form-latitude" name="latitude" value="${claimant.address.location.latitude}"/>
                        <input type="hidden" id="Form-longitude" name="longitude" value="${claimant.address.location.longitude}"/>
                        <label for="Form-address">Endereço</label>
                    </div>
                </div>
            </div>    --%>
            <hr>
           <h3>Data</h3>
            <div class="row">
                <div class="col">
                    <div class="md-form">
                        <input type="text" name="start-date" id="Form-start-date" class="form-control">
                        <label for="Form-date">Data inicial</label>
                    </div>
                </div>
                <div class="col">
                    <div class="md-form">
                        <input type="text" name="end-date" id="Form-end-date" class="form-control">
                        <label for="Form-time">Data final</label>
                    </div>
                </div>
            </div>
            <hr>
            <button type="submit" id="btn-store" class="btn btn-pink float-right waves-effect waves-light">Salvar</button></h3> 
        </div>
        <div class="col-md-4 mt-3">

            <h3>Minhas informações</h3>

            <!--Caso for uma entedidade, pode selecionar requerente
            <p><i>Caso for entidade</i></p>
            <div class="form-group mb-0 mt-4">
                <select name="objective" class="mdb-select form-control">
                    <option value="" disabled selected>Selecione um requerente</option>
                    <option value="1" selected>Pedro Soares</option>
                    <option value="2">Maria Luísa</option>
                </select>
            </div>
            -->
            <c:if test="${not empty claimant}">
                <table class="table table-striped mt-3">
                    <tr>
                        <td><b>Nome</b></td>
                        <td>${claimant.name}</td>
                    </tr>
                    <tr>
                        <c:if test="${type == 'Claimant'}"> 
                        <td><b>CPF</b></td>
                            <td>${claimant.cpf}</td>
                        </c:if>
                        <c:if test="${type == 'Entity'}"> 
                        <td><b>CNPJ</b></td>
                            <td>${claimant.cnpj}</td>
                        </c:if>
                    </tr>
                    <tr>
                        <td><b>E-mail</b></td>
                        <td>${claimant.email}</td>
                    </tr>
                    <tr>
                        <td><b>Telefone</b></td>
                        <td>${claimant.firstPhone}</td>
                    </tr>
                </table>
            </c:if>
        </div>

    </div>
</form>

<!-- Mask JQuery -->
<script type="text/javascript" src="${pageContext.request.contextPath}/assets/js/jquery.mask.min.js"></script>
        
<script>
    $(function() {
        var $buttonAddress = $('#btn-get-address');
        $($buttonAddress).on('click', function () {
            $buttonAddress.prop("disabled", true); // disable button
            if ("geolocation" in navigator) { //check if geolocation is available 
                navigator.geolocation.getCurrentPosition(function (position) {
                    getPositionInfo(position);
                });
            } else
                alert("Geocalização não suportada pelo navegador atual.");
        });

        function getPositionInfo(position) {
            var GEOCODING = 'https://maps.googleapis.com/maps/api/geocode/json?latlng='
                    + position.coords.latitude + '%2C'
                    + position.coords.longitude + '&language=pt';
            var $inputlatitude = $('#Form-latitude');
            var $inputlongitude = $('#Form-longitude');
            $($inputlatitude).val(position.coords.latitude);
            $($inputlongitude).val(position.coords.longitude);
            $.getJSON(GEOCODING).done(function (location) {
                showLocation(location);
            });
        }

        function showLocation(location) {
            if (location === null || location.results[0] === null || location.results[0].address_components === null) { // ok
                alert("Não foi possível recuperar a localização atual. Verifique sua conexão com a internet.");
                $($buttonAddress).prop("disabled", false); // let's user try again...
            }    
            else {
                // var $input = $('#Form-address');
                var address = location.results[0].address_components;
                // $($input).val(location.results[0].formatted_address);
                for (i in address) {
                    switch ( String(address[i].types[0]) ) {
                        case "street_number":
                            $("#Form-number").focus();
                            $("#Form-number").val(String(address[i].long_name));
                            break;
                        case "route":
                            $("#Form-street").focus();
                            $("#Form-street").val(String(address[i].long_name));
                            break;
                        case "administrative_area_level_2":
                            $("#Form-city").focus();
                            $("#Form-city").val(String(address[i].long_name));
                            break;            
                        case  "administrative_area_level_1":
                            $("#Form-uf").focus();
                            $("#Form-uf").val(String(address[i].short_name));
                            break;          
                        case "postal_code":
                            $("#Form-cep").focus();
                            $("#Form-cep").val(String(address[i].long_name));
                            break;                       
                    }
                    /*  $($input).keyup(function (event) { // if user edit his address then active the button again
                        $($buttonAddress).prop("disabled", false);
                    }); */
                }
            }
        }
        
        
        $("#Form-start-date").mask("00/00/0000", {clearIfNotMatch: true});
        $("#Form-end-date").mask("00/00/0000", {clearIfNotMatch: true});
    });
</script>