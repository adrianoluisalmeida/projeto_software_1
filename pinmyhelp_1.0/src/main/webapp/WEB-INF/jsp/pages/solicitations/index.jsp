<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib tagdir="/WEB-INF/tags" prefix="custom"%>

<div class="col mt-5">
    <c:choose>
        <c:when test="${not empty solicitations}">
            <div class="col-md-12 mt-3">
                <h3>Todas solicitações de Ajuda</h3>
            </div>
            <div class="clearfix"></div>
            <c:choose>
                <c:when test="${type == 'Voluntary'}">
                    <jsp:include page="../solicitations/list.jsp" flush="true" />
                </c:when>
                <c:otherwise>
                    <jsp:include page="../offers/solicitations_list.jsp" flush="true" />                    
                </c:otherwise>
            </c:choose>
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
        </div>
    </c:otherwise>
</c:choose>
