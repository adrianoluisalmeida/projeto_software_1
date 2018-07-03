<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib tagdir="/WEB-INF/tags" prefix="custom"%>

<!-- Página: Todas solicitações de ajuda. 
Página disponível somente para voluntário e entidade -->

<div class="col mt-5">
    <c:choose>
        <c:when test="${not empty solicitations}">
            <div class="col-md-12 mt-3">
                <h3>Todas solicitações de Ajuda</h3>
            </div>
            <div class="clearfix"></div>
            <jsp:include page="../offers/solicitations_list.jsp" flush="true" />                    
            <div class="clearfix"></div>
        </c:when>
        <c:otherwise>
            <div class="col-md-12 mt-3">
                <h4>Ainda não há nenhuma solicitação de ajuda</h4>
            </div>
            <div class="clearfix"></div>
        </div>
    </c:otherwise>
</c:choose>
