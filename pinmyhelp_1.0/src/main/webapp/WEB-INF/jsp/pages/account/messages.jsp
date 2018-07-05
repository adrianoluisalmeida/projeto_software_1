<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib tagdir="/WEB-INF/tags" prefix="custom"%>
<c:if test="${not empty msg_success}">
    
    <input type="hidden" id="flash_msg_success" value="${msg_success}"/>
    
    <script type="text/javascript">
        $.toast({
            heading: 'Sucesso',
            text: $('#flash_msg_success').val(),
            showHideTransition: 'slide',
            icon: 'success'
        })
    </script>
 
</c:if>

<c:if test="${not empty msg_error}">
    
    <input type="hidden" id="flash_msg_error" value="${msg_success}"/>
    
    <script type="text/javascript">
        $.toast({
            heading: 'Erro',
            text: $('#flash_msg_error').val(),
            showHideTransition: 'slide',
            icon: 'error'
        })
    </script>
 
</c:if>