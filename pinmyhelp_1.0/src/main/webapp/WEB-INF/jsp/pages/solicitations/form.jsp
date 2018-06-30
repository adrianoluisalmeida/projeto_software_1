<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@taglib tagdir="/WEB-INF/tags" prefix="custom"%>

<div class="row mt-5">
    <div class="col-md-8 mt-3">
        <h3>Descrição da Solicitação</h3>
        <div class="md-form">
            <textarea type="text" id="Form-requirement" name="description" class="md-textarea form-control" rows="2">${helpSolicitation.description}</textarea>
            <label for="Form-requirement">Descreva a sua necessidade</label>
            <form:errors path="helpSolicitation.description" cssStyle="color:red"/>
        </div>
        <hr>
        <h3>Tipo de ajuda</h3>
        <div class="row">
            <div class="col">
                <div class="form-group">
                    <select name="type_id" id="type_id" class="mdb-select form-control">
                        <option value="" disabled selected>Selecione um tipo</option>
                        <c:forEach var="helpType" items="${helpTypes}">
                            <option value="${helpType.id}" <c:if test="${helpSolicitation.type.id == helpType.id}">selected</c:if>>${helpType.type}</option>
                        </c:forEach>
                    </select>
                </div>
                <c:if test="${not empty errorHelpType}"><span class="error">${errorHelpType}</span></c:if>
                </div>
            </div>
            <hr>
            <h3>Meu endereço <!-- <a id="btn-get-address" class="btn btn-pink float-right waves-effect waves-light">Usar endereço atual</a> --></h3> 
            <c:if test="${empty person.address.postalCode}">
                <p><i class="fa fa-info-circle"></i> Você ainda não possui nenhum endereço cadastrado em seu perfil, com isso o endereço abaixo será considerado seu endereço atual até atualização
                    de seu <a href="${pageContext.request.contextPath}/account/edit/claimant/${sessionScope.user.id}" class="font-weight-light ml-1">Cadastro</a>.
                </p>
            </c:if>
            <div class="row height-60">
                <div class="col-8">
                    <div class="md-form">
                        <input type="text" name="address.postalCode" value="${address.postalCode}"  id="Form-cep" class="form-control address-input">
                    <label for="Form-cep">CEP</label>
                    <form:errors path="helpSolicitation.address.postalCode" cssStyle="color:red"/>
                </div>
            </div>

            <div class="col-4">
                <div class="md-form">
                    <input type="text" name="address.state" value="${address.state}" id="Form-uf" class="form-control address-input" readonly="readonly">
                    <label for="Form-uf">UF</label>
                    <form:errors path="helpSolicitation.address.state" cssStyle="color:red"/>
                </div>
            </div>
        </div>

        <div class="md-form">
            <input type="text" name="address.city" value="${address.city}" id="Form-city" class="form-control address-input" readonly="readonly">
            <label for="Form-city">Cidade</label>
            <form:errors path="helpSolicitation.address.city" cssStyle="color:red"/>
        </div>

        <div class="row height-60">
            <div class="col-8">
                <div class="md-form">
                    <input type="text" name="address.street" value="${address.street}" id="Form-street" class="form-control address-input">
                    <label for="Form-street">Rua</label>
                    <form:errors path="helpSolicitation.address.street" cssStyle="color:red"/>
                </div>
            </div>

            <div class="col-4">
                <div class="md-form">
                    <input type="number" name="address.number" value="${address.number}" id="Form-number" class="form-control address-input" min="0">
                    <label for="Form-number">Nº</label>
                    <form:errors path="helpSolicitation.address.number" cssStyle="color:red"/>
                </div>
            </div>
        </div>

        <div class="md-form">
            <input type="text" name="address.neighborhood" value="${address.neighborhood}" id="Form-neighborhood" class="form-control address-input">
            <label for="Form-neighborhood">Bairro</label>
            <form:errors path="helpSolicitation.address.neighborhood" cssStyle="color:red"/>
        </div>

        <input type="hidden" id="Form-latitude" name="latitude" value="${address.location.latitude}"/>
        <input type="hidden" id="Form-longitude" name="longitude" value="${address.location.longitude}"/>

        <hr>
        <h3>Data</h3>
        <div class="row">
            <div class="col">
                <div class="md-form">
                    <input type="text" name="startDate" id="Form-start-date" value="<custom:localDateFormat localDate="${helpSolicitation.startDate}"/>" class="form-control">
                    <label for="Form-date">Data inicial</label>
                    <c:if test="${not empty errorStartDate}"><span class="error">${errorStartDate}</span></c:if>
                    </div>
                </div>
                <div class="col">
                    <div class="md-form">
                        <input type="text" name="endDate" id="Form-end-date" value="<custom:localDateFormat localDate="${helpSolicitation.endDate}"/>" class="form-control">
                    <label for="Form-time">Data final</label>
                    <c:if test="${not empty errorEndDate}"><span class="error">${errorStartDate}</span></c:if>
                    </div>
                </div>
            </div>

        <c:if test="${page == 'solicitations/edit'}">
            <input type="hidden" name="id" value="${id}"/>
        </c:if>

        <hr>          
        <button type="submit" class="btn btn-pink float-right waves-effect waves-light">Salvar</button></h3> 
    </div>
    <div class="col-md-4 mt-3">

        <h3>Minhas informações</h3>
            <jsp:include page="../user_info.jsp" flush="true" />
            <p class="font-small grey-text d-flex pull-right">Não está correto?
                <a href="${pageContext.request.contextPath}/account/edit/${fn:toLowerCase(type)}/${sessionScope.user.id}" class="font-weight-light ml-1"> Atualize</a>
            </p>
    </div>
</div>


<c:if test="${helpSolicitation.type != null}">
    <script>
        $(function () {
            $('#type_id').val('${helpSolicitation.type.getId()}');
        });
    </script>
</c:if>                    

<!-- Mask JQuery -->
<script type="text/javascript" src="${pageContext.request.contextPath}/assets/js/jquery.mask.min.js"></script>                                          
<script>
    $(function () {
        var $inputs = $("div.md-form input.address-input");

       /* $('#btn-get-address').click(function () {
            if ("geolocation" in navigator) { //check if geolocation is available 
                navigator.geolocation.getCurrentPosition(function (position) {
                    getPositionInfo(position);
                });
            } else
                alert("Geocalização não suportada pelo navegador atual.");
        });

        function getPositionInfo(position) {
            $inputs.prop("disabled", true);
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
            $inputs.prop("disabled", false);
        } */

        function showLocation(location) {
            if (location.status === "OK") {
                // var $input = $('#Form-address');                    
                // $($input).val(location.results[0].formatted_address);
                var address = location.results[0].address_components;
                for (i in address) {
                    switch (String(address[i].types[0])) {
                        /* case "postal_code":
                                $inputs.filter("#Form-cep")
                                    .val( (String(address[i].long_name).replace(/\D/g, '')) ) // only numbers
                                    .trigger('input') // tigger jquery mask
                                    .focus();
                            break; */
                        case "street_number":
                            $inputs.filter("#Form-number")
                                    .val(String(address[i].long_name))
                                    .focus();
                            break;
                        case "route":
                            $inputs.filter("#Form-street")
                                    .val(String(address[i].long_name))
                                    .focus();
                            break;
                        case "administrative_area_level_2":
                            $inputs.filter("#Form-city")
                                    .val(String(address[i].long_name))
                                    .focus();
                            break;
                        case  "administrative_area_level_1":
                            $inputs.filter("#Form-uf")
                                    .val(String(address[i].short_name))
                                    .focus();
                            break;
                    }
                        /*  $($input).keyup(function (event) { // if user edit his address then active the button again
                         $($buttonAddress).prop("disabled", false);
                         }); */
                }
                $("Form-latitude").val(location.results[0].geometry['location'].lat);
                $("Form-longitude").val(location.results[0].geometry['location'].long);
            } else {
                alert("Não foi possível recuperar a localização atual. Verifique sua conexão com a internet ou se seu CEP está correto.");
                // $($buttonAddress).prop("disabled", false); // let's user try again...
                clear();
            }
        }
            
        function clear() {
            $inputs.filter("#Form-uf").val("").blur();
            $inputs.filter("#Form-city").val("").blur();
            $inputs.filter("#Form-cep").val("").focus();
        }

        // when focus out
        $("#Form-cep").blur(function() {
            $('#btn-get-address').prop("disabled", true);
            var cep = $( this ).val().replace(/\D/g, ''); // only numbers
            if (cep !== "") { // if not empty
                var $inputs = $("div.md-form input.address-input");
                $inputs.prop("disabled", true);
                var GEOCODING = 'https://maps.googleapis.com/maps/api/geocode/json?address=' + cep;
                $.getJSON(GEOCODING).done(function (location) {
                    showLocation(location);
                });
                $inputs.prop("disabled", false);
            } else
                clear();
        });

        $("#Form-cep").mask("00.000-000", {clearIfNotMatch: true});
        $("#Form-start-date").mask("00/00/0000", {clearIfNotMatch: true});
        $("#Form-end-date").mask("00/00/0000", {clearIfNotMatch: true});
    });
</script>