<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>



<form action="${pageContext.request.contextPath}/solicitations/update/${id}" enctype="multipart/form-data" accept-charset="iso-8859-1,utf-8" method="POST">    
    
    <jsp:include page="form.jsp" flush="true" />
   
</form>
