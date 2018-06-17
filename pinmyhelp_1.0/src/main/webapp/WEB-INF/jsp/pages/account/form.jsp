<div class="mt-5">
    <h2>Alteração usuário</h2>
    <!-- TESTAR SE É VOLUNTÁRIO OU REQUERENTE <c:if test="$ { }">-->         
    <jsp:include page="form_person.jsp" flush="true" />
    <!--</c:if>-->
    <!--<c:if TESTAR SE É ENTEIDADE test="$ { }">-->         
    <jsp:include page="form_entity.jsp" flush="true" />
    <!--</c:if>-->
</div>