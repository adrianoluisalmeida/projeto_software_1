<!-- DASHBOARD VOLUNTARY -->

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib tagdir="/WEB-INF/tags" prefix="custom"%>

<div class="col mt-5">
    <c:choose>
        <c:when test="${not empty solicitations}">
            <div class="col-md-12 mt-3">
                <h3>Últimas solicitações de Ajuda</h3>
            </div>
            <div class="clearfix"></div>
            <jsp:include page="../offers/solicitations_list.jsp" flush="true" />
            <div class="clearfix"></div>

            <div class="col-md-12 mt-3 pr-4">
                <a href="${pageContext.request.contextPath}/solicitations" class="float-right">Ver todas as solicitações</a>
            </div>
        </c:when>
        <c:otherwise>
            <div class="col-md-12 mt-3">
                <h4>Ainda não há nenhuma solicitação de ajuda</h4>
            </div>
            <div class="clearfix"></div>
        </c:otherwise>
    </c:choose>
</div>
