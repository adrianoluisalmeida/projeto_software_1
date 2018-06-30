<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
        <c:if test="${not empty person}">
            <table class="table table-striped mt-3">
                <tr>
                    <td><b>Nome</b></td>
                    <td>${person.name}</td>
                </tr>
                <tr>
                    <td><b>CPF</b></td>
                    <td>${person.cpf}</td>         
                </tr>
                <tr>
                    <td><b>E-mail</b></td>
                    <td>${person.email}</td>
                </tr>
                <tr>
                    <td><b>Telefone</b></td>
                    <td>${person.firstPhone}</td>
                </tr>
            </table>
        </c:if>
        <c:if test="${not empty entity}">
            <table class="table table-striped mt-3">
                <tr>
                    <td><b>Nome</b></td>
                    <td>${entity.name}</td>
                </tr>
                <tr>
                    <td><b>CNPJ</b></td>
                    <td>${entity.cnpj}</td>
                </tr>
                <tr>
                    <td><b>E-mail</b></td>
                    <td>${entity.email}</td>
                </tr>
                <tr>
                    <td><b>Telefone</b></td>
                    <td>${entity.firstPhone}</td>
                </tr>
            </table>
        </c:if>