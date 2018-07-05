<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@taglib tagdir="/WEB-INF/tags" prefix="custom" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>PinMyHelp - Relatório</title>
        <!-- Font Awesome -->
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
        <!-- Bootstrap core CSS -->
        <link href="${pageContext.request.contextPath}/assets/css/bootstrap.min.css" rel="stylesheet">
        <!-- Material Design Bootstrap -->
        <link href="${pageContext.request.contextPath}/assets/css/mdb.min.css" rel="stylesheet">
        <!-- Your custom styles (optional) -->
        <link href="${pageContext.request.contextPath}/assets/css/custom.css" rel="stylesheet">
        <!-- Toast -->
        <link href="${pageContext.request.contextPath}/assets/css/jquery.toast.min.css" rel="stylesheet">
        <!-- JQuery -->
        <script type="text/javascript" src="${pageContext.request.contextPath}/assets/js/jquery-3.2.1.min.js"></script>
    </head>
    <body style="margin: 15px">
        <h1>${title}</h1>
        <c:if test="${not empty name}">
            <h2>Pesquisando por: "${name}"</h2>
        </c:if>
        <hr />
        <h5>Total de feedbacks ${fn:length(feedbacks)}</h5>
        <table class="table table-striped">
            <thead>
              <tr>
                <th scope="col">Remetente</th>
                <th scope="col">Nota</th>
                <th scope="col">Comentários</th>
                <th scope="col">Destinatário</th>
              </tr>
            </thead>
            <tbody>
              <c:forEach var="feedback" items="${feedbacks}">
                <tr>
                      <td>${feedback.sender.email}</td>
                      <td>${feedback.rating.value} - ${feedback.rating.description}</td>
                      <td>${feedback.comments}</td>
                      <td>${feedback.offer.voluntary.email}</td>
                </tr>
              </c:forEach>
            </tbody>
          </table>
    </body>
</html>
