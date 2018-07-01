<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib tagdir="/WEB-INF/tags" prefix="custom"%> 
<%@page contentType="text/html" pageEncoding="UTF-8"%>


<c:forEach var="offer" items="${offers}">

    <tr>
        <td colspan="5" style="padding: 8px 16px;">
            <b>${offer.helpSolicitation.type.type}</b> - <i>Este é um exemplo de observação sobre a oferta, essa observação é escrita pelo voluntário</i>
        </td>
    </tr>
    <tr>

        <td></td>
        <td>

            <c:choose>
                <c:when test="${not empty offer.helpSolicitation.claimant}"><b>${offer.helpSolicitation.claimant.name}</b></c:when>
                <c:when test="${not empty offer.helpSolicitation.entity}"><b>${offer.helpSolicitation.entity.name}</b></c:when>
            </c:choose>
        </td>
        <td>
            <a data-toggle="modal" data-target="#modalLoginAvatarDemo">
                <c:choose>
                    <c:when test="${not empty offer.voluntary}">${offer.voluntary.name}</c:when>
                    <c:when test="${not empty offer.entity}">${offer.entity.name}</c:when>
                </c:choose>
            </a>
        </td>
        <td>
            <b><custom:localDateFormat localDate="${offer.helpSolicitation.startDate}"/></b>
        </td>

        <td>

            <a data-target="#modalLoginForm" type="button" class="btn btn-danger btn-rounded btn-table btn-sm" data-toggle="modal"
               data-placement="top" title="Recusar Oferta">
                <i class="fa fa-times"></i>
            </a>
            <a type="button" class="btn btn-success btn-rounded btn-table btn-sm" data-toggle="tooltip"
               data-placement="top" title="Aprovar Oferta">
                <i class="fa fa-check"></i>
            </a>
        </td>
    </c:forEach>