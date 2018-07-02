<%-- 
    Document   : test.jsp
    Created on : 01/07/2018, 11:31:07
    Author     : roger
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Pinmyhelp - Report Test</title>
    </head>
    <body>
        <h1>PinMyHelp - Teste de relatório</h1>
        <p><a target="_blank" href="${pageContext.request.contextPath}/report/claimants">Requerentes</a></p>
        <p><a target="_blank" href="${pageContext.request.contextPath}/report/voluntaries">Voluntários</a></p>
        <p><a target="_blank" href="${pageContext.request.contextPath}/report/entities">Entidades</a></p>
        <p>
            Solicitações:
            <form method="POST" target="_blank" action="${pageContext.request.contextPath}/report/solicitations">
                De 
                <input type="date" name="startDate" /> até <input type="date" name="endDate" />
                <input value="Mostrar" type="submit">
            </form>
        </p>
    </body>
</html>
