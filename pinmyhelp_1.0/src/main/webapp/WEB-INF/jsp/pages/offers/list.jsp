<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib tagdir="/WEB-INF/tags" prefix="custom"%> 
<%@page contentType="text/html" pageEncoding="UTF-8"%>


<c:forEach var="offer" items="${offers}" varStatus="counter">
    <tr>
        <td colspan="5" style="padding: 8px 16px;">
            Ajuda com: <b>${offer.helpSolicitation.type.type}</b> - "<i>${offer.helpSolicitation.description}"</i>
            <c:if test="${not empty offer.observation}">
                <br/>
                Minhas observações: <i>${offer.observation}</i>
            </c:if>
        </td>
    </tr>
    <tr>
        <td></td>
        <td>
            <a data-toggle="modal" data-target="#claimant-sol${counter.count}-more">
                <c:choose>
                    <c:when test="${not empty offer.helpSolicitation.claimant}"><b>${offer.helpSolicitation.claimant.name}</b></c:when>
                    <c:when test="${not empty offer.helpSolicitation.entity}"><b>${offer.helpSolicitation.entity.name}</b></c:when>
                </c:choose>
            </a>
        </td>
        <td>
            <c:choose>
                <c:when test="${not empty offer.voluntary}">${offer.voluntary.name}</c:when>
                <c:when test="${not empty offer.entity}">${offer.entity.name}</c:when>
            </c:choose>
        </td>
        <td>
            <b><custom:localDateFormat localDate="${offer.helpSolicitation.startDate}"/></b>
        </td>
        <td>
            <a type="button" href="${pageContext.request.contextPath}/offers/offer/${offer.id}" class="btn btn-blue-grey btn-rounded btn-table btn-sm" data-toggle="tooltip"
               data-placement="top" title="Visualizar Oferta">
                <i class="fa fa-info-circle"></i>
            </a> 

            <!-- POR ENQUANTO SÓ APARECE O BOTÃO CANCELAR SE A SITUAÇÃO É OFERTADA -->
            <c:if test="${offer.status == 'OFERTADA'}">
                <a data-target="#cancel-offer" type="button" class="confirmation btn btn-danger btn-rounded btn-table btn-sm" data-toggle="modal"
                   data-placement="top" title="Cancelar oferta" data-id="${offer.id}">
                    <i class="fa fa-times"></i>
                </a>
            </c:if>
            <c:if test="${offer.status == 'APROVADA'}">
                <p class="btn btn-success btn-rounded btn-table btn-sm"
                   data-placement="top">Oferta aprovada
                    <i class="fa fa-check"></i>
                </p> 
            </c:if>
            <c:if test="${offer.status == 'REJEITADA'}">
                <p class="btn btn-danger btn-rounded btn-table btn-sm"
                   data-placement="top">Oferta Rejeitada
                    <i class="fa fa-times"></i>
                </p> 
            </c:if>
            <c:if test="${offer.status == 'CANCELADA'}">
                <p class="btn btn-pink btn-rounded btn-table btn-sm"
                   data-placement="top">Oferta Cancelada
                    <i class="fa fa-times"></i>
                </p> 
            </c:if>
        </td>
    </tr>
    <!-- Modal -->
    <div class="modal fade" id="claimant-sol${counter.count}-more" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
        <div class="modal-dialog cascading-modal modal-avatar modal-sm" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <c:choose>
                        <c:when test="${not empty offer.helpSolicitation.claimant}">
                            <c:if test="${empty offer.helpSolicitation.claimant.profilePicture}">
                                <img src="${pageContext.request.contextPath}/assets/img/profile-icon.png" class="rounded-circle img-responsive"/>
                            </c:if>
                            <c:if test="${not empty offer.helpSolicitation.claimant.profilePicture}">
                                <img src="${pageContext.request.contextPath}/upload/${offer.helpSolicitation.claimant.profilePicture}" class="rounded-circle img-responsive" />
                            </c:if>                        
                        </c:when>
                        <c:when test="${not empty offer.helpSolicitation.entity}">
                            <c:if test="${empty offer.helpSolicitation.entity.logo}">
                                <img src="${pageContext.request.contextPath}/assets/img/profile-icon.png" class="rounded-circle img-responsive" />
                            </c:if>
                            <c:if test="${not empty offer.helpSolicitation.entity.logo}">
                                <img src="${pageContext.request.contextPath}/upload/${offer.helpSolicitation.entity.logo}" class="rounded-circle img-responsive" />
                            </c:if>                     
                        </c:when>
                    </c:choose>  
                </div>
                <div class="modal-body text-center mb-1">
                    <h5 class="mt-1 mb-2">
                        <c:choose>
                            <c:when test="${not empty offer.helpSolicitation.claimant}"><b>${offer.helpSolicitation.claimant.name}</b></c:when>
                            <c:when test="${not empty offer.helpSolicitation.entity}"><b>${offer.helpSolicitation.entity.name}</b></c:when>
                        </c:choose>              
                    </h5>
                    <div class="md-form ml-0 mr-0">
                        <p>
                            <c:choose>
                                <c:when test="${not empty offer.helpSolicitation.claimant}"><b>${offer.helpSolicitation.claimant.biography}</b></c:when>
                                <c:when test="${not empty offer.helpSolicitation.entity}"><b>${offer.helpSolicitation.entity.description}</b></c:when>
                            </c:choose>  
                        </p>
                        <p><b>Telefone: </b>
                            <c:choose>
                                <c:when test="${not empty offer.helpSolicitation.claimant}"><b>${offer.helpSolicitation.claimant.firstPhone}</b></c:when>
                                <c:when test="${not empty offer.helpSolicitation.entity}"><b>${offer.helpSolicitation.entity.firstPhone}</b></c:when>
                            </c:choose>  
                        </p>
                        <p><b>E-mail: </b> 
                            <c:choose>
                                <c:when test="${not empty offer.helpSolicitation.claimant}"><b>${offer.helpSolicitation.claimant.email}</b></c:when>
                                <c:when test="${not empty offer.helpSolicitation.entity}"><b>${offer.helpSolicitation.entity.email}</b></c:when>
                            </c:choose>             
                        </p>
                    </div>
                </div>
            </div>
        </div>
    </div>
</c:forEach>

<div class="modal fade" id="cancel-offer" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
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
                    Você tem certeza que deseja cancelar sua oferta para esta ajuda?
                </div>
            </div>
            <div class="modal-footer d-flex justify-content-center">
                <a class="btn btn-danger" id="delete-offer-anchor" href="${pageContext.request.contextPath}/offers/cancel/${offer.id}"><i class="fa fa-times" aria-hidden="true"></i> Excluir</a>
                <a class="btn btn-default" data-dismiss="modal"><i class="fa fa-check" aria-hidden="true"></i> Manter</a>
            </div>
        </div>
    </div>
</div>
                
<script>
    $(function () {
        var urlDeleteBase = "${pageContext.request.contextPath}/offers/cancel/";
        $("#my-offers").on("click", "a.confirmation", function () {
            var id_offer = $(this).data("id");
            $("a#delete-offer-anchor").attr("href", urlDeleteBase + id_offer);
        });
    });
</script>