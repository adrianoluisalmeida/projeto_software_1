<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib tagdir="/WEB-INF/tags" prefix="custom"%>

<div class="col mt-5">
    <div class="col-md-12 mt-3">
        <h3>Meus Pedidos de Ajuda <a href="${pageContext.request.contextPath}/solicitations/create" class="btn btn-pink btn-sm float-right">Novo pedido</a></h3>
    </div>
    <div class="clearfix"></div>
    
     <jsp:include page="list.jsp" flush="true" />
    
       
</div>
<div class="clearfix"></div>