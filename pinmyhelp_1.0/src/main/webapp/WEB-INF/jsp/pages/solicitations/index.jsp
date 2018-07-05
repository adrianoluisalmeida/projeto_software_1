<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib tagdir="/WEB-INF/tags" prefix="custom"%>

<!-- P�gina: Todas solicita��es de ajuda. 
P�gina dispon�vel somente para volunt�rio e entidade -->

<div class="col mt-5">
    <h3>Todas solicita��es de ajuda</h3>
    <c:choose>
        <c:when test="${not empty solicitations}">

            <div class="clearfix"></div>
            <jsp:include page="../offers/solicitations_list.jsp" flush="true" />                    
            <div class="clearfix"></div>
        </c:when>
        <c:otherwise>
            <p>Ainda n�o h� nenhuma solicita��o de ajuda.</p>
        </c:otherwise>
    </c:choose>
</div>
