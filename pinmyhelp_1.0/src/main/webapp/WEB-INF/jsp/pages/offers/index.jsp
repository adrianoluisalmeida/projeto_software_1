<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!-- P�gina: Todas ofertas recebidas para minhas solicita��es de ajuda. 
Dispon�vel somente para requerente e entidade -->

<div class="col-md-12 mt-5">
    <h3>Lista de Ofertas recebidas</h3>
    <c:choose>
        <c:when test="${not empty offers}">
            <!--Table-->
            <table class="table table-striped table-responsive-md btn-table" id="my-sol-offers">

                <!--Table head-->
                <thead>
                    <tr>
                        <th>#</th>
                        <th>Requerente</th>
                        <th>Volunt�rio</th>
                        <th>Data</th>
                        <th>A��es</th>
                    </tr>
                </thead>

                <!--Table head-->

                <!--Table body-->
                <tbody>
                    <c:choose>
                        <c:when test="${type != 'Voluntary'}">
                            <jsp:include page="../solicitations/offers_list.jsp" flush="true" />
                        </c:when>
                        <c:otherwise>
                            <jsp:include page="../offers/list.jsp" flush="true" />                    
                        </c:otherwise>
                    </c:choose>
                    </tr>
                </tbody>
                <!--Table body-->

            </table>
            <!--Table-->
        </c:when>
        <c:otherwise>
            <p>Voc� ainda n�o recebeu nenhuma oferta de ajuda.</p>
        </c:otherwise>
    </c:choose>
</div>