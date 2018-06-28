<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@taglib tagdir="/WEB-INF/tags" prefix="custom"%>

<form action="${pageContext.request.contextPath}/solicitations/store" enctype="multipart/form-data" accept-charset="iso-8859-1,utf-8" method="POST">    
    
    <jsp:include page="form.jsp" flush="true" />
   
</form>
