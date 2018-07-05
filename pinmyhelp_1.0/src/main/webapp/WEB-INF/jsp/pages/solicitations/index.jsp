<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib tagdir="/WEB-INF/tags" prefix="custom"%>

<!-- Página: Todas solicitações de ajuda. 
Página disponível somente para voluntário e entidade -->

<div class="col mt-5">
    <h3>Todas solicitações de ajuda</h3>
    <c:choose>
        <c:when test="${not empty solicitations}">

            <div class="clearfix"></div>
            <jsp:include page="../offers/solicitations_list.jsp" flush="true" />                    
            <div class="clearfix"></div>
        </c:when>
        <c:otherwise>
            <p>Ainda não há nenhuma solicitação de ajuda.</p>
        </c:otherwise>
    </c:choose>
</div>
