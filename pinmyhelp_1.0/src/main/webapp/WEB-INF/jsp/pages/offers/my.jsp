<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib tagdir="/WEB-INF/tags" prefix="custom"%> 


<!--P�gina: minhas ofertas realizadas. 
P�gina dispon�vel somente para volunt�rio ou entidade -->

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
                        <th>Volunt�rio</th>
                        <th>Data/Hora</th>
                        <th>A��es</th>
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
            <p>Voc� ainda n�o realizou nenhuma oferta de ajuda.</p>
        </c:otherwise>
    </c:choose>

</div>