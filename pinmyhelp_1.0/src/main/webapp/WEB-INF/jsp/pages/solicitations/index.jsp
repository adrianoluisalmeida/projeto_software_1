<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib tagdir="/WEB-INF/tags" prefix="custom"%>

<!-- P�gina: Todas solicita��es de ajuda. 
P�gina dispon�vel somente para volunt�rio e entidade -->

<div class="col mt-5">
    <h3>Todas solicita��es de ajuda</h3>
    <div class="col-md-12 mt-3">
    </div>
    <div class="clearfix"></div>
    <jsp:include page="../offers/solicitations_list.jsp" flush="true" />                    
    <div class="clearfix"></div>
</div>
