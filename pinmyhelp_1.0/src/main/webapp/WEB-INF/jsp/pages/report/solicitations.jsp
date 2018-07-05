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
        <h5>Total de solicitações: ${fn:length(solicitations)}</h5>
        <h5>Solicitadas: ${solicitadas}</h5>
        <h5>Encerradas: ${encerradas}</h5>
        <h5>Com interessados: ${comInteressados}</h5>
        <h5>Canceladas: ${canceladas}</h5>
        <h5>Concluidas: ${concluidas}</h5>
        <h5>Avaliadas: ${avaliadas}</h5>
        <table class="table table-striped">
            <thead>
              <tr>
                <th scope="col">Requerente</th>
                <th scope="col">Tipo</th>
                <th scope="col">Data inicial</th>
                <th scope="col">Data final</th>
                <th scope="col">Status</th>
              </tr>
            </thead>
            <tbody>
              <tr>
                <c:forEach var="solicitation" items="${solicitations}">
                    <td>${solicitation.claimant.name}</td>
                    <td>${solicitation.type.type}</td>
                    <td><custom:localDateFormat localDate="${solicitation.startDate}" /></td>
                    <td><custom:localDateFormat localDate="${solicitation.endDate}" /></td>
                    <td>${solicitation.status.status}</td>
                </c:forEach>
              </tr>
            </tbody>
          </table>
    </body>
</html>
