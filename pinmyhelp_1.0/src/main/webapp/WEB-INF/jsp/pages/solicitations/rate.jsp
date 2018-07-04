<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="col-md-6 col-md-offset-3 m-top-20" style="margin: 0 auto">
    <form action="${pageContext.request.contextPath}${action}" accept-charset="iso-8859-1,utf-8" method="POST" enctype="multipart/form-data">
        <c:if test="${not empty solicitationId}">
            <input type="hidden" name="solicitationId" value="${solicitationId}" />
        </c:if>
        <c:if test="${not empty offerId}">
            <input type="hidden" name="offerId" value="${offerId}" />
        </c:if>
        <div class="mt-5">
            <h2>
                <c:if test="${not empty solicitationId}">
                    Avaliar ajuda
                </c:if>
                <c:if test="${not empty offerId}">
                    Avaliar requerente
                </c:if>
            </h2>
            <div class="md-form" style="text-align: center">
                <select required="true" name="rating" class="mdb-select form-control">
                    <option selected="true"></option>
                    <c:forEach var="rate" items="${ratings}">
                        <option value="${rate.value}">${rate.value} - ${rate.description}</option>
                    </c:forEach>
                </select>
            </div>
            <div class="md-form"> 
                <label for="Form-comments">Comentários</label>
                <!--<input type="text" name="description" >-->
                <textarea required="true" name="comments" id="Form-comments" class="md-textarea form-control"></textarea>
            </div>
            <div class="row d-flex align-items-center mb-4">
                <div class="col-md-3 col-md-12 text-center">
                    <button type="submit"
                            class="btn btn-pink btn-block btn-rounded z-depth-1 waves-effect waves-light">
                        Avaliar
                    </button>
                </div>
            </div>
        </div>
    </form>
</div>
