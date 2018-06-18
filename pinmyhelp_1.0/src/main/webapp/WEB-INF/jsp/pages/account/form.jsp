<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="mt-5">
    <h2>Atualizar cadastro</h2>
    <c:if test="${not empty person}">
        <jsp:include page="form_person.jsp" flush="true" />
    </c:if>    
    <c:if test="${not empty entity}">
        <jsp:include page="form_entity.jsp" flush="true" />
    </c:if>    
</div>