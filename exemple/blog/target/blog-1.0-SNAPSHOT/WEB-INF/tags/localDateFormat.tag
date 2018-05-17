<%-- 
    Document   : localDateFormat
    Created on : 01/04/2018, 18:53:26
    Author     : roger
--%>
<%@tag description="put the tag description here" pageEncoding="UTF-8"%>
<%-- The list of normal or fragment attributes can be specified here: --%>
<%@attribute name="localDate"%>
<%-- any content can be specified here e.g.: --%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:parseDate value="${localDate}" pattern="yyyy-MM-dd" var="parsedDate" type="date" />
<fmt:formatDate value="${parsedDate}" pattern="dd/MM/yyyy" />