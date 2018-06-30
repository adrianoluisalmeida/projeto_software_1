<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<div class="row mt-5">
    <div class="col-md-4 mt-3">
        <h3>Descrição da Solicitação</h3> 
        <p>${solicitation.description}</p>
        <hr>
        <h3>Meu endereço</h3>
        <p>RST 287, Camobi, Santa Maria RS, 2180, apto 300</p>
        <hr>
        <h3>Data/Hora</h3>
        <p>${solicitation.startDate}</p>
    </div>
    <div class="col-md-4 mt-3">
        <h3>Requerente</h3>
        <table class="table table-striped">
            <c:if test="${not empty solicitation.claimant}">

                <tr>
                    <td><b>Nome</b></td>
                    <td>${solicitation.claimant.name}</td>
                </tr>
                <tr>
                    <td><b>CPF</b></td>
                    <td>${solicitation.claimant.cpf}</td>
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
    <form action="offers/store/${solicitation.id}" class="col-md">



        <div class="md-form">
            <textarea type="text" id="form7" class="md-textarea form-control" rows="3"></textarea>
            <label for="form7">Gostaria de fazer alguma observação sobre sua oferta?</label>
        </div>


        <button type="submit" class="btn btn-pink float-right">Realizar oferta</button>
    </form>
</div>