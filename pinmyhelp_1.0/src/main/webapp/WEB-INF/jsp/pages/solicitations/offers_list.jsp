<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib tagdir="/WEB-INF/tags" prefix="custom"%> 
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<c:forEach var="offer" items="${offers}" varStatus="counter">
    <tr>
        <td colspan="5" style="padding: 8px 16px;">
            Ajuda com: <b>${offer.helpSolicitation.type.type}</b> - "<i>${offer.helpSolicitation.description}"</i>
            <c:if test="${not empty offer.observation}">
                <br/>
                Observações do voluntário: <i>${offer.observation}</i>
            </c:if>
        </td>
    </tr>
    <tr>
        <td></td>
        <td>
            <c:choose>
                <c:when test="${not empty offer.helpSolicitation.claimant}">${offer.helpSolicitation.claimant.name}</c:when>
                <c:when test="${not empty offer.helpSolicitation.entity}">${offer.helpSolicitation.entity.name}</c:when>
            </c:choose>
        </td>
        <td>
            <a data-toggle="modal" data-target="#voluntary-offer${counter.count}-more">
                <c:choose>
                    <c:when test="${not empty offer.voluntary}"><b>${offer.voluntary.name}</b></c:when>
                    <c:when test="${not empty offer.entity}"><b>${offer.entity.name}</b></c:when>
                </c:choose>
            </a>
        </td>
        <td>
            <b><custom:localDateFormat localDate="${offer.helpSolicitation.startDate}"/></b>
        </td>
        <td>
            <!-- SÓ APARECEM OS BOTÕES SE A SITUAÇÃO É OFERTADA -->
            <c:if test="${offer.status == 'OFERTADA'}">
                <a type="button" href="${pageContext.request.contextPath}/offers/approve/${offer.id}" class="btn btn-success btn-rounded btn-table btn-sm" data-toggle="tooltip"
                   data-placement="top" title="Aprovar Oferta">
                    <i class="fa fa-check"></i>
                </a> 
                <a data-target="#reject-offer" type="button" class="confirmation btn btn-danger btn-rounded btn-table btn-sm" data-toggle="modal"
                   data-placement="top" title="Rejeitar oferta" data-id="${offer.id}">
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
        </td>
    </tr>
    <!-- Modal -->
    <div class="modal fade" id="voluntary-offer${counter.count}-more" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
        <div class="modal-dialog cascading-modal modal-avatar modal-sm" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <c:choose>
                        <c:when test="${not empty offer.voluntary}">
                            <c:if test="${empty offer.voluntary.profilePicture}">
                                <img src="${pageContext.request.contextPath}/assets/img/profile-icon.png" class="rounded-circle img-responsive"/>
                            </c:if>
                            <c:if test="${not empty offer.voluntary.profilePicture}">
                                <img src="${pageContext.request.contextPath}/upload/${offer.voluntary.profilePicture}" class="rounded-circle img-responsive" />
                            </c:if>                        
                        </c:when>
                        <c:when test="${not empty offer.entity}">
                            <c:if test="${empty offer.entity.logo}">
                                <img src="${pageContext.request.contextPath}/assets/img/profile-icon.png" class="rounded-circle img-responsive" />
                            </c:if>
                            <c:if test="${not empty offer.entity.logo}">
                                <img src="${pageContext.request.contextPath}/upload/${offer.entity.logo}" class="rounded-circle img-responsive" />
                            </c:if>                     
                        </c:when>
                    </c:choose>  
                </div>
                <div class="modal-body text-center mb-1">
                    <h5 class="mt-1 mb-2">
                        <c:choose>
                            <c:when test="${not empty offer.voluntary}"><b>${offer.voluntary.name}</b></c:when>
                            <c:when test="${not empty offer.entity}"><b>${offer.entity.name}</b></c:when>
                        </c:choose>              
                    </h5>
                    <div class="md-form ml-0 mr-0">
                        <p>
                            <c:choose>
                                <c:when test="${not empty offer.voluntary}"><b>${offer.voluntary.biography}</b></c:when>
                                <c:when test="${not empty offer.entity}"><b>${offer.entity.description}</b></c:when>
                            </c:choose>  
                        </p>
                        <p><b>Telefone: </b>
                            <c:choose>
                                <c:when test="${not empty offer.voluntary}"><b>${offer.voluntary.firstPhone}</b></c:when>
                                <c:when test="${not empty offer.entity}"><b>${offer.entity.firstPhone}</b></c:when>
                            </c:choose>  
                        </p>
                        <p><b>E-mail: </b> 
                            <c:choose>
                                <c:when test="${not empty offer.voluntary}"><b>${offer.voluntary.email}</b></c:when>
                                <c:when test="${not empty offer.entity}"><b>${offer.entity.email}</b></c:when>
                            </c:choose>             
                        </p>
                    </div>
                </div>
            </div>
        </div>
    </div>
</c:forEach>
</div>

<div class="modal fade" id="reject-offer" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header text-center">
                <h4 class="modal-title w-100 font-weight-bold">Recusar Oferta</h4>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <form action="${pageContext.request.contextPath}/offers/reject" method="post">
                <div class="modal-body mx-3">
                    <div class="md-form mb-5">
                        <textarea type="text" type="text" name="" id="Form-reason" class="form-control md-textarea validate" required></textarea>
                        <label data-error="wrong" data-success="right" for="Form-reason">Descreva o motivo pelo qual você não aceita essa oferta</label>
                    </div>
                </div>
                <div class="modal-footer d-flex justify-content-center">
                    <input type="hidden" id="id_offer_reject" name="offer_id" value="${offer.id}"/>
                    <button type="submit" class="btn btn-danger"><i class="fa fa-times" aria-hidden="true"></i> Recusar</button>
                    <a class="btn btn-default" data-dismiss="modal"><i class="fa fa-check" aria-hidden="true"></i> Manter</a>
                </div>
            </form>
        </div>
    </div>

    <script>
        $(function () {
            $("#my-sol-offers").on("click", "a.confirmation", function () {
                var id_offer = $(this).data("id");
                $("input#id_offer_reject").val(id_offer);
            });
        });
    </script>