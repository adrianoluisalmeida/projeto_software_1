<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib tagdir="/WEB-INF/tags" prefix="custom"%> 


<!--Página: minhas ofertas realizadas. 
Página disponível somente para voluntário ou entidade -->

<div class="col-md-12 mt-5">
    <h3>Lista de Ofertas realizadas</h3>
    <c:choose>
        <c:when test="${not empty offers}">
            <!--Table-->
            <table class="table table-striped table-responsive-md btn-table" id="my-offers">

                <!--Table head-->
                <thead>
                    <tr>
                        <th>#</th>
                        <th>Requerente</th>
                        <th>Voluntário</th>
                        <th>Data/Hora</th>
                        <th>Ações</th>
                    </tr>
                </thead>

                <!--Table head-->

                <!--Table body-->
                <tbody>
                    <jsp:include page="../offers/list.jsp" flush="true" />                    
                    </tr>
                </tbody>
                <!--Table body-->

            </table>
            <!--Table-->
        </c:when>
        <c:otherwise>
            <p>Você ainda não realizou nenhuma oferta de ajuda.</p>
        </c:otherwise>
    </c:choose>
    <div class="modal fade" id="modalLoginForm" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header text-center">
                    <h4 class="modal-title w-100 font-weight-bold">Recusar Oferta</h4>
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                </div>
                <div class="modal-body mx-3">
                    <div class="md-form mb-5">
                        <input type="text" id="defaultForm-reason" class="form-control validate">
                        <label data-error="wrong" data-success="right" for="defaultForm-reason">Descreva o motivo pelo qual você não aceita essa oferta</label>
                    </div>

                </div>
                <div class="modal-footer d-flex justify-content-center">
                    <button class="btn btn-pink">Confirmar</button>
                </div>
            </div>
        </div>
    </div>

</div>