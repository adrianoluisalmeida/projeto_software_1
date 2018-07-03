<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="col-md-6 col-md-offset-3 m-top-20" style="margin: 0 auto">
    <form target="_blank" action="${pageContext.request.contextPath}/report/generate" accept-charset="iso-8859-1,utf-8" method="POST" enctype="multipart/form-data">
        <div class="mt-5">
            <h2>Gerar relatório</h2>
            <div class="md-form"> 
                <select id="options" name="type" onchange="typeCheck()">
                    <option value="1">Requerentes</option>
                    <option value="2">Voluntários</option>
                    <option value="3">Entidades</option>
                    <option value="4">Solicitações de ajuda</option>
                    <option value="5">Feedbacks</option>
                </select>
            </div>
            <div class="md-form">
                Pesquisa:<br />
                <input type="text" name="name" style="width: 100%;"/>
            </div>
            <div id="dateDiv" class="md-form" style="visibility:hidden;">
                Intervalo de relatório gerado<br /> 
                <input id="startDate" type="date" name="startDate" required="false" value="2018-01-01"/> até 
                <input id="endDate" type="date" name="endDate" required="false" value="2018-12-31"/>
            </div>
            <div class="row d-flex align-items-center mb-4">
                <div class="col-md-3 col-md-12 text-center">
                    <button type="submit"
                            class="btn btn-pink btn-block btn-rounded z-depth-1 waves-effect waves-light">
                        Gerar
                    </button>
                </div>
            </div>
        </div>
    </form>
</div>       
<script type="text/javascript">
    function typeCheck(){
        var type = document.getElementById("options").value;
        if (type === "4" || type === "5"){
            document.getElementById("dateDiv").style.visibility = "visible";
            document.getElementById("startDate").required = true;
            document.getElementById("endDate").required = true;
        }else{
            document.getElementById("dateDiv").style.visibility = "hidden";
            document.getElementById("startDate").required = false;
            document.getElementById("endDate").required = false;
        }
    }
</script>