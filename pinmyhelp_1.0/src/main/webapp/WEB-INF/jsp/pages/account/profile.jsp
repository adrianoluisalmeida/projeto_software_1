<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="col-md-6 col-md-offset-3 m-top-20" style="margin: 0 auto">
    <form action="${pageContext.request.contextPath}/account/update/profile" accept-charset="iso-8859-1,utf-8" method="POST" enctype="multipart/form-data">
        <c:if test="${not empty person}">
            <input type="hidden" name="id" value="${person.id}" />
        </c:if>
        <c:if test="${not empty entity}">
            <input type="hidden" name="id" value="${entity.id}" />
        </c:if>
        <div class="mt-5">
            <h2>Editar perfil</h2>
            <div class="md-form" style="text-align: center">
                <c:if test="${empty sessionScope.profilePicture}">
                    <img style="width: 200px; border-radius: 50%;" src="${pageContext.request.contextPath}/assets/img/profile-icon.png" rel="stylesheet" />
                </c:if>
                <c:if test="${not empty sessionScope.profilePicture}">
                    <img style="width: 200px; border-radius: 50%;" src="${pageContext.request.contextPath}/upload/${sessionScope.profilePicture}" rel="stylesheet" />
                </c:if>
            </div>
            <div class="md-form">
                Foto de perfil
            <div class="md-form">
                <input id="Form-picture" type="file" name="picture" />
            </div>
            <div class="md-form"> 
                <textarea name="bio" id="Form-description" class="md-textarea form-control"><c:if test="${not empty person && not empty person.biography}">${person.biography}</c:if><c:if test="${not empty entity && not empty entity.notes}">${entity.notes}</c:if></textarea>
                <!--<input type="text" name="description" >-->
                <label for="Form-description">Biografia</label>
            </div>
            <div class="row d-flex align-items-center mb-4">
                <div class="col-md-3 col-md-12 text-center">
                    <button type="submit"
                            class="btn btn-pink btn-block btn-rounded z-depth-1 waves-effect waves-light">
                        Salvar
                    </button>
                </div>
            </div>
        </div>
    </form>
</div>
