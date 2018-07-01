<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib tagdir="/WEB-INF/tags" prefix="custom"%>

<div class="col mt-5">
    <c:choose>
        <c:when test="${not empty solicitations}">
            <div class="col-md-12 mt-3">
                <h3>Meus Pedidos de Ajuda <a href="${pageContext.request.contextPath}/solicitations/create" class="btn btn-pink btn-sm float-right">Novo pedido</a></h3>
            </div>
            <div class="clearfix"></div>
            <jsp:include page="../solicitations/list.jsp" flush="true" />
        </c:when>
        <c:otherwise>
            <div class="col-md-12 mt-3">
                <h4>Você ainda não realizou nenhuma solicitação de ajuda<a href="${pageContext.request.contextPath}/solicitations/create" class="btn btn-pink btn-sm float-right">Novo pedido</a></h4>
            </div>
            <div class="clearfix"></div>
    </c:otherwise>
</c:choose>
</div>
<div class="clearfix"></div>