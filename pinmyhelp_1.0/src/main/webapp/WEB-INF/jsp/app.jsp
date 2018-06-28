<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@taglib tagdir="/WEB-INF/tags" prefix="custom"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <meta http-equiv="x-ua-compatible" content="ie=edge">
        <title>Pin My Help <c:if test="${not empty title}"> | ${title}</c:if></title>
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
    <body>
        <jsp:include page="navbar.jsp" flush="true" />
        
        <div class="container">
            <c:if test = "${ fn:length(msg) gt 0 }">
                <br/>
                <div id="msg-returns" class="alert alert-success">${msg}</div> 
            </c:if>
                
           
                
            <jsp:include page="pages/${page}.jsp" flush="true" />
        </div>

        <!-- SCRIPTS -->
        <!-- Bootstrap tooltips -->
        <script type="text/javascript" src="${pageContext.request.contextPath}/assets/js/popper.min.js"></script>
        <!-- Bootstrap core JavaScript -->
        <script type="text/javascript" src="${pageContext.request.contextPath}/assets/js/bootstrap.min.js"></script>
        <!-- MDB core JavaScript -->
        <script type="text/javascript" src="${pageContext.request.contextPath}/assets/js/mdb.js"></script>
        <!-- Toast -->
        <script type="text/javascript" src="${pageContext.request.contextPath}/assets/js/jquery.toast.min.js"></script>
        <script type="text/javascript">

            $(document).ready(function () {
                setTimeout(function () {
                    $('#msg-returns').hide();
                }, 5000);
            });


            //            $("#data").datepicker({
            //                minDate: 0,
            //                dateFormat: "dd/mm/yyyy"
            //            });
            //            $("#data").mask("00/00/0000");
            //            $('[name="hora"]').mask('00:00:00');

        </script>
        <jsp:include page="messages.jsp" flush="true" />
    </body>
</html>
