<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib tagdir="/WEB-INF/tags" prefix="custom"%>

<div class="col mt-5">
    <c:choose>
        <c:when test="${not empty mySolicitations}">
            <div class="col-md-12 mt-3">
                <h3>Meus Pedidos de Ajuda <a href="${pageContext.request.contextPath}/solicitations/create" class="btn btn-pink btn-sm float-right">Novo pedido</a></h3>
            </div>
            <div class="clearfix"></div>
            <jsp:include page="../solicitations/list.jsp" flush="true" />
        </c:when>
        <c:otherwise>
            <div class="col-md-12 mt-3">
                <h4>Voc� ainda n�o realizou nenhuma solicita��o de ajuda<a href="${pageContext.request.contextPath}/solicitations/create" class="btn btn-pink btn-sm float-right">Novo pedido</a></h4>
            </div>
            <div class="clearfix"></div>
        </div>
    </c:otherwise>
</c:choose>
<div class="clearfix"></div>