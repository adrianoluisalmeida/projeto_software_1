<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib tagdir="/WEB-INF/tags" prefix="custom"%> 

<div class="row mt-5">
    <div class="col-md-4 mt-3">
        <h3>Solicitação de ${solicitation.type.type}</h3>
        <hr>
        <h4>Descrição da Solicitação</h4> 
        <p>${solicitation.description}</p>
        <hr>
        <h4>Período</h4>
        <p>Data de início:  <custom:localDateFormat localDate="${solicitation.startDate}"/></p>
        <p>Data de fim:  <custom:localDateFormat localDate="${solicitation.endDate}"/></p>
    </div>
    <div class="col-md-4 mt-3">
        <h3>Requerente</h3>
        <table class="table table-striped mt-3">
            <c:if test="${not empty solicitation.claimant}">
                <tr>
                    <td><b>Nome</b></td>
                    <td>${solicitation.claimant.name}</td>
                </tr>
                <tr>
                    <td><b>Data de nascimento</b></td>
                    <td><custom:localDateFormat localDate="${solicitation.claimant.bornDate}"/></td>
                </tr>
                <tr>
                    <td><b>E-mail</b></td>
                    <td>${solicitation.claimant.email}</td>
                </tr>
                <tr>
                    <td><b>Telefone</b></td>
                    <td>${solicitation.claimant.firstPhone}</td>
                </tr>
            </c:if>
            <c:if test="${not empty solicitation.entity}">
                <tr>
                    <td><b>Nome</b></td>
                    <td>${solicitation.entity.name}</td>
                </tr>
                <tr>
                    <td><b>CPF</b></td>
                    <td>${solicitation.entity.cnpj}</td>
                </tr>
                <tr>
                    <td><b>E-mail</b></td>
                    <td>${solicitation.entity.email}</td>
                </tr>
                <tr>
                    <td><b>Telefone</b></td>
                    <td>${solicitation.entity.firstPhone}</td>
                </tr>
            </c:if>
        </table>
    </div>
    <div class="col-md-4 mt-3">
        <h3>Voluntário</h3>
        <jsp:include page="../user_info.jsp" flush="true" />
    </div>
</div>

<div class="row mt-5">
    <form action="${pageContext.request.contextPath}/offers/store/${solicitation.id}" method="POST" enctype="multipart/form-data" accept-charset="iso-8859-1,utf-8" class="col-md">
        <div class="md-form">
            <textarea type="text" id="Form-observation" name="observation" class="md-textarea form-control" rows="3"></textarea>
            <label for="Form-observation">Gostaria de fazer alguma observação sobre sua oferta?</label>
        </div>
        <button type="submit" class="btn btn-pink float-right">Realizar oferta</button>
    </form>
</div>
