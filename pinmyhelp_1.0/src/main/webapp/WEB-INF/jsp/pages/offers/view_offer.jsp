<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib tagdir="/WEB-INF/tags" prefix="custom"%> 

<div class="row mt-5">
    <div class="col-md-4 mt-3">
        <h3>Solicitação de ${offer.helpSolicitation.type.type}</h3>
        <hr>
        <h4>Descrição</h4> 
        <p>${offer.helpSolicitation.description}</p>
        <hr>
        <h4>Período</h4>
        <p>Data de início:  <custom:localDateFormat localDate="${offer.helpSolicitation.startDate}"/></p>
        <p>Data de fim:  <custom:localDateFormat localDate="${offer.helpSolicitation.endDate}"/></p>
        <hr>
        <h4>Situação</h4> 
        <p>${offer.helpSolicitation.status.status}</p>
        <hr>
    </div>
    <div class="col-md-4 mt-3">
        <h3>Oferta</h3>
        <hr>        
        <h4>Observação</h4> 
        <p>${offer.observation}</p>
        <hr>
        <h4>Situação</h4> 
        <p>${offer.status.status}
            <c:if test="${offer.status == 'CANCELADA' &&  
                         (offer.helpSolicitation.status == 'SOLICITADA' || 
                          offer.helpSolicitation.status == 'INTERESSE')}"> <%-- se oferta cancelada e a sol. continua em aberto --%>
                <c:choose>
                    <c:when test="${not empty offer.voluntary && 
                                  offer.voluntary.id == user.id}"> <%-- se eh o voluntario --%>
                        <a href="${pageContext.request.contextPath}/offers/open/${offer.id}" class="btn btn-pink btn-rounded btn-sm">Reabrir</a>
                    </c:when>
                    <c:when test="${not empty offer.entity && 
                                  offer.entity.id == user.id}">  <%-- ou se eh a entidade --%>
                        <a href="${pageContext.request.contextPath}/offers/open/${offer.id}" class="btn btn-pink btn-rounded btn-sm">Reabrir</a>
                    </c:when>
                </c:choose>
            </c:if>
        </p>
        <hr>
        <c:if test="${type != 'Claimant'}">
            <a href="${pageContext.request.contextPath}/offers/my" class="float-right">Ver todas minhas ofertas</a>        
        </c:if>

    </div>
    
    <div class="col-md-4 mt-3">
        <h3>Requerente</h3>
        <hr>
        <table class="table table-striped">
            <c:if test="${not empty offer.helpSolicitation.claimant}">
                <tr>
                    <td><b>Nome</b></td>
                    <td>${offer.helpSolicitation.claimant.name}</td>
                </tr>
                <tr>
                    <td><b>Data de nascimento</b></td>
                    <td><custom:localDateFormat localDate="${offer.helpSolicitation.claimant.bornDate}"/></td>
                </tr>
                <tr>
                    <td><b>E-mail</b></td>
                    <td>${offer.helpSolicitation.claimant.email}</td>
                </tr>
                <tr>
                    <td><b>Telefone</b></td>
                    <td>${offer.helpSolicitation.claimant.firstPhone}</td>
                </tr>
            </c:if>
            <c:if test="${not empty offer.helpSolicitation.entity}">
                <tr>
                    <td><b>Nome</b></td>
                    <td>${offer.helpSolicitation.entity.name}</td>
                </tr>
                <tr>
                    <td><b>CPF</b></td>
                    <td>${offer.helpSolicitation.entity.cnpj}</td>
                </tr>
                <tr>
                    <td><b>E-mail</b></td>
                    <td>${offer.helpSolicitation.entity.email}</td>
                </tr>
                <tr>
                    <td><b>Telefone</b></td>
                    <td>${offer.helpSolicitation.entity.firstPhone}</td>
                </tr>
            </c:if>
        </table>
        <h3>Voluntário</h3>
        <hr>
        <jsp:include page="../user_info.jsp" flush="true" />
    </div>
</div>



