<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib tagdir="/WEB-INF/tags" prefix="custom"%> 
<div id="sol-list">
<c:forEach var="solicitation" items="${solicitations}">
    <div class=" col-md-4 float-left">
        <div class="card">
            <!-- Card image -->
            <!--<img class="card-img-top" src="https://mdbootstrap.com/img/Photos/Others/images/43.jpg"-->
            <!--alt="Card image cap">-->
            <!-- Card content -->
            <div class="card-body">
                <!-- Title -->
                <h4 class="card-title">${solicitation.type.type}</h4>
                <!-- Text -->
                <p class="card-text">
                    <b>Requerente</b><br>
                    <c:choose>
                        <c:when test="${not empty solicitation.claimant}">${solicitation.claimant.name}</c:when>
                        <c:when test="${not empty solicitation.entity}">${solicitation.entity.name}</c:when>
                    </c:choose>
                </p>
                <p class="card-text">
                    <b>Data</b><br>
                    <custom:localDateFormat localDate="${solicitation.startDate}"/>
                </p>
                <p class="card-text">
                    <b>Situação</b><br>
                    ${solicitation.status.status}
                </p>          
                <!-- Button -->
                <div class="row justify-content-center">
                    
                    <c:if test="${empty solicitation.helpOffer}">
                        <a href="${pageContext.request.contextPath}/solicitations/edit/${solicitation.id}" class="btn btn-blue-grey btn-sm float-left">Editar</a>
                        <a data-target="#cancel-sol" data-toggle="modal" data-id="${solicitation.id}" class="confirmation btn btn-danger btn-sm float-left">Excluir</a>
                    </c:if>
                    <c:if test="${(not empty solicitation.helpOffer) && (solicitation.status.status == 'Encerrada')}">
                        <a href="${pageContext.request.contextPath}/solicitations/rate/${solicitation.id}" class="btn btn-blue-grey btn-sm float-left">Avaliar</a>
                    </c:if>    
                    <a href="${pageContext.request.contextPath}/offers/${solicitation.id}" class="btn btn-pink btn-sm float-left">Ofertas</a>
                </div>
            </div>
        </div>
    </div>
<!-- Card -->
</c:forEach>
</div> 

<div class="modal fade" id="cancel-sol" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header text-center">
                <h4 class="modal-title w-100 font-weight-bold">Confirmar exclusão</h4>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body mx-3">
                <div class="md-form mb-5 text-center">
                    Você tem certeza que deseja excluir sua solicitação de ajuda?
                </div>
            </div>
            <div class="modal-footer d-flex justify-content-center">
                <a id="delete-sol-anchor" class="btn btn-danger"><i class="fa fa-times" aria-hidden="true"></i> Excluir</a>
                <a class="btn btn-default" data-dismiss="modal"><i class="fa fa-check" aria-hidden="true"></i> Manter</a>
            </div>
        </div>
    </div>
</div>

<script>
    $(function() {
        var urlDeleteBase = "${pageContext.request.contextPath}/solicitations/delete/";
        $("#sol-list").on("click", "a.confirmation", function() {
           var id_sol = $( this ).data("id");
           $("a#delete-sol-anchor").attr("href", urlDeleteBase + id_sol);
        });
    });
</script>