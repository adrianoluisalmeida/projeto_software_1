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
            <c:if test="${empty address.postalCode}">
                <p><i class="fa fa-info-circle"></i> Você ainda não possui nenhum endereço cadastrado em seu perfil, com isso o endereço abaixo será considerado seu endereço atual até atualização
                    de seu <a href="${pageContext.request.contextPath}/account/edit/person/${sessionScope.user.id}" class="font-weight-light ml-1">Cadastro</a>.
                </p>
            </c:if>
            <div class="row height-60">
                <div class="col-8">
                    <div class="md-form">
                        <input type="text" name="address.postalCode" value="${address.postalCode}" id="Form-cep" class="form-control address-input">
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
                    <input type="text" name="address.street" value="${address.street}" id="Form-street" class="form-control">
                    <label for="Form-street">Rua</label>
                    <form:errors path="helpSolicitation.address.street" cssStyle="color:red"/>
                </div>
            </div>

            <div class="col-4">
                <div class="md-form">
                    <input type="number" name="address.number" value="${address.number}" id="Form-number" class="form-control" min="0">
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

        <input type="hidden" id="Form-latitude" name="address.location.latitude" value="${address.location.latitude}"/>
        <input type="hidden" id="Form-longitude" name="address.location.longitude" value="${address.location.longitude}"/>

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
            <p class="font-small grey-text d-flex pull-right">Não está correto?&nbsp;
                <a href="${pageContext.request.contextPath}/account/edit/${sessionScope.type == "Entity" ? "entity" : "person"}/${sessionScope.user.id}">Atualize</a>
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
<script type="text/javascript" src="${pageContext.request.contextPath}/assets/js/infocep.js"></script>    

<script>
    $(function () {
        $("#Form-cep").mask("00.000-000", {clearIfNotMatch: true});
        $("#Form-start-date").mask("00/00/0000", {clearIfNotMatch: true});
        $("#Form-end-date").mask("00/00/0000", {clearIfNotMatch: true});
    });
</script>