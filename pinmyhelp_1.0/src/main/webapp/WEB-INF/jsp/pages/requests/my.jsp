<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="col mt-5">


    <div class="col-md-12 mt-3">
        <h3>Meus Pedidos de Ajuda <a href="${pageContext.request.contextPath}/requests/create" class="btn btn-pink btn-sm float-right">Novo pedido</a></h3>

    </div>
    <div class="clearfix"></div>
        <c:forEach var="request" items="${myRequests}">
            
               <div class=" col-md-4 float-left">
        <div class="card">

            <!-- Card image -->
            <!--<img class="card-img-top" src="https://mdbootstrap.com/img/Photos/Others/images/43.jpg"-->
            <!--alt="Card image cap">-->

            <!-- Card content -->
            <div class="card-body">

                <!-- Title -->
                <h4 class="card-title"><a>${request.requirementDescription}</a></h4>
                <!-- Text -->
                <p class="card-text">
                    <b>Requerente</b><br>
                    ${request.claimant.name}
                </p>
                <p class="card-text">
                    <b>Data</b><br>
                    ${request.startDate}
                </p>
                <p class="card-text">
                    <b>Situação</b><br>
                    ${request.status.status}
                </p>

                <!-- Button -->
                <div class="row justify-content-md-center">
                    <a href="#" class="btn btn-blue-grey float-left">Editar</a>
                    <a href="#" class="btn btn-danger float-left">Excluir</a>
                </div>
            </div>

        </div>
    </div>
    <!-- Card -->
        </c:forEach>
<div class="clearfix"></div>



