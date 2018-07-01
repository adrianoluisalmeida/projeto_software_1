<!--DASHBOARD ENTITY -->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="col mt-5">
    <c:choose>
        <c:when test="${not empty solicitations}">
            <div class="col-md-12 mt-3">
                <h3>Meus Últimos Pedidos de Ajuda <a href="${pageContext.request.contextPath}/solicitations/create" class="btn btn-pink btn-sm float-right">Novo pedido</a></h3>
            </div>
            <div class="clearfix"></div>
            <jsp:include page="../solicitations/list.jsp" flush="true" />
             <div class="clearfix"></div>
        <div class="col-md-12 mt-3 pr-4">
            <a href="${pageContext.request.contextPath}/solicitations/my" class="float-right">Ver todas as minhas solicitações</a>
        </div>
        </c:when>
        <c:otherwise>
            <div class="col-md-12 mt-3">
                <h4>Você ainda não realizou nenhuma solicitação de ajuda<a href="${pageContext.request.contextPath}/solicitations/create" class="btn btn-pink btn-sm float-right">Novo pedido</a></h4>
            </div>
            <div class="clearfix"></div>
        </div>
    </c:otherwise>
</c:choose>
<div class="clearfix"></div>
