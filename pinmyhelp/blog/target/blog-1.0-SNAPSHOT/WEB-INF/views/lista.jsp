<%-- 
    Document   : lista
    Created on : 01/04/2018, 15:14:21
    Author     : roger
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@taglib tagdir="/WEB-INF/tags" prefix="custom" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Posts</title>
    </head>
    <body>
          <br /> <br />                
          <table border="1px">
              <tr>
                  <th>Id</th>
                  <th>Título</th>
                  <th>Conteúdo</th>
                  <th>Data de publicação</th>
              </tr>
              <c:forEach items="${posts}" var="post">
                  <tr>
                      <td>${post.id}</td>
                      <td>${post.titulo}</td>
                      <td>${post.conteudo}</td>
                      <td>
                          <custom:localDateFormat localDate="${post.dataPub}" />
                      </td>
                  </tr>
              </c:forEach>
          </table>
    </body>
</html>
