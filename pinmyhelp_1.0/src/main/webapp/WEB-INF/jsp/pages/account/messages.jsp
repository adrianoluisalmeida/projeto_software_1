<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib tagdir="/WEB-INF/tags" prefix="custom"%> 
<%@page contentType="text/html" pageEncoding="UTF-8"%>


<!--Página: minhas ofertas realizadas. 
Página disponível somente para voluntário ou entidade -->

<div class="col-md-12 mt-5">
    <h3>Novas mensagens <a href="${pageContext.request.contextPath}/account/messages/my/read" class="btn btn-rounded btn-link btn-readall btn-sm pull-right">Marcar todas como lidas</a></h3>
    <c:choose>
    <c:when test="${not empty new_messages}">
        <table class="table table-striped table-responsive-md table-messages">
            <tbody>
            <c:forEach var="message" items="${new_messages}">
                <tr>
                    <td class="text-center">
                       <i class="fa fa-envelope message-icon"></i>
                    </td>
                    <td>
                    <div>
                        <div class="message">
                            <span class="message-date pull-right"><custom:dateMessageFormat localDate="${message.createdDate}"/></span>
                            <h4 class="message-title">${message.title}</h4>
                            <p class="message-content">${message.content}</p>
                        </div>
                    </div>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </c:when>
    <c:otherwise>
        <p>Você não possui nenhuma nova mensagem.</p>
    </c:otherwise>
    </c:choose>
</div>

<div class="col-md-12 mt-5">
    <h3>Histórico</h3>
    <c:choose>
    <c:when test="${not empty old_messages}">
        <table class="table table-striped table-responsive-md table-messages">
            <tbody>
            <c:forEach var="message" items="${old_messages}">
                <tr>
                    <td class="text-center">
                       <i class="fa fa-envelope-open message-icon"></i>
                    </td>
                    <td>
                    <div>
                        <div class="message">
                            <span class="message-date pull-right"><custom:dateMessageFormat localDate="${message.createdDate}"/></span>
                            <h4 class="message-title">${message.title}</h4>
                            <p class="message-content">${message.content}</p>
                        </div>
                    </div>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </c:when>
    <c:otherwise>
        <p>Você ainda não possui histórico de mensagens.</p>
    </c:otherwise>
    </c:choose>
</div>


<style>
    a.btn-link.btn-readall {
        text-decoration: none;
    }
    
    a.btn-link.btn-readall:focus,
    a.btn-link.btn-readall:hover {
        color: #ec407a !important; 
    }

    .table-messages td {
        vertical-align: middle;
    }

    .table-messages .message-icon {
        font-size: 18px;                                                            
        color: #ec407a !important;
    }

    .table-messages .message-date {
        font-size: 12px;
        color: /*#999*/ #ec407a;
    }

    .table-messages .message .message-title {
        color: #000;
        font-size: 14px;
        font-weight: 400;
        line-height: normal;
        margin: 0;
    }
    
    .table-messages .message .message-content {
        font-size: 14px;
    }  
</style>