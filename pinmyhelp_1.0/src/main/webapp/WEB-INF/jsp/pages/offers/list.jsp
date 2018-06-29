<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib tagdir="/WEB-INF/tags" prefix="custom"%> 
<c:forEach var="solicitation" items="${solicitations}">
        <div class=" col-md-4 float-left">
            <div class="card">
                <!-- Card image -->
                <!--<img class="card-img-top" src="https://mdbootstrap.com/img/Photos/Others/images/43.jpg"-->
                <!--alt="Card image cap">-->
                <!-- Card content -->
                <div class="card-body">
                    <!-- Title -->
                    <h4 class="card-title"><a>${solicitation.type.type}</a></h4>
                    <!-- Text -->
                    <p class="card-text">
                        <b>Requerente</b><br>
                        <c:choose>
                            <c:when test="${type == 'Claimant'}">${person.name}</c:when>
                            <c:when test="${type == 'Entity'}">${entity.name}</c:when>
                        </c:choose>
                    </p>
                    <p class="card-text">
                        <b>Data</b><br>
                        <custom:localDateFormat localDate="${solicitation.startDate}"/>
                    </p>
                    <p class="card-text">
                        <b>Situa��o</b><br>
                        ${solicitation.status.status}
                    </p>
                    <!-- Button -->
                    <div class="row justify-content-center">
                        <a href="${pageContext.request.contextPath}/offers/help/${solicitation.id}" class="btn btn-blue-grey float-left">Ajudar</a>
                    </div>
                </div>
            </div>
        </div>
    <!-- Card -->
    </c:forEach>