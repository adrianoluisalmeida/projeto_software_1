<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>

<div class="col-md-6 col-md-offset-3 m-top-20" style="margin: 0 auto">
    <form action="${pageContext.request.contextPath}/person/update/${id}" commandName="person" accept-charset="iso-8859-1,utf-8" method="POST">
        <jsp:include page="form.jsp" flush="true" />
    </form>
</div>