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
                            <option value="" disabled selected>Tipo de ajuda</option>
                            <c:forEach var="helpType" items="${HelpTypes}">
                                <option value="${helpType.id}">${helpType.type}</option>
                            </c:forEach>
                        </select>
                    </div>
                </div>
            </div>
            <hr>
            <h3>Meu endereço <a href="#" id="btn-get-address" class="btn btn-pink float-right waves-effect waves-light">Usar endereço atual</a></h3> 
            <div class="row">
                <div class="col">  
                    <div class="md-form">
                        <input type="text" id="Form-address" name="Form-address" class="form-control" value="${claimant.address.street}, ${claimant.address.number} - ${claimant.address.neighborhood}, ${claimant.address.city} - ${claimant.address.state}, ${claimant.address.postalCode}, Country"/>
                        <input type="hidden" id="Form-latitude" name="latitude" value="${claimant.address.location.latitude}"/>
                        <input type="hidden" id="Form-longitude" name="longitude" value="${claimant.address.location.longitude}"/>
                        <label for="Form-address">Endereço</label>
                    </div>
                </div>
            </div>   
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

            <h3>Requerente</h3>

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

<script>
    $(function () {
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
                    + position.coords.longitude + '&language=en';
            var $inputlatitude = $('#Form-latitude');
            var $inputlongitude = $('#Form-longitude');
            $($inputlatitude).val(position.coords.latitude);
            $($inputlongitude).val(position.coords.longitude);
            $.getJSON(GEOCODING).done(function (location) {
                showLocation(location);
            });
        }

        function showLocation(location) {
            if (location !== null) { // ok
                var $input = $('#Form-address');
                $($input).val(location.results[0].formatted_address);
                $($input).keyup(function (event) { // if user edit his address then active the button again
                    $($buttonAddress).prop("disabled", false);
                });
            } else {
                alert("Não foi possível recuperar a localização atual. Verifique sua conexão com a internet.");
                $($buttonAddress).prop("disabled", false); // let's user try again...
            }
        }
        
        
        $("#Form-start-date").mask("00/00/0000", {clearIfNotMatch: true});
        $("#Form-end-date").mask("00/00/0000", {clearIfNotMatch: true});
    });
</script>