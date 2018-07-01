<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>

<div class="col-md-6 col-md-offset-3 m-top-20" style="margin: 0 auto">
    <form action="${pageContext.request.contextPath}/account/update/${editPage}" commandName="person" accept-charset="iso-8859-1,utf-8" method="POST">
        <jsp:include page="form.jsp" flush="true" />
    </form>
</div>
    
    <!-- Mask JQuery -->
<script type="text/javascript" src="${pageContext.request.contextPath}/assets/js/jquery.mask.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/assets/js/infocep.js"></script>

<script>
    // put mask into form fields
    $(function() {
        $("#Form-cpf").mask("000.000.000-00", {clearIfNotMatch: true});
        $("#Form-rg").mask("0000000000"); // could change between states
        $("#Form-phone").mask("(00) 0000-0000", {clearIfNotMatch: true, onKeyPress: function (phone, e, field, options) {
                var masks = ['(00) 0000-00000', '(00) 0 0000-0000'];
                mask = (phone.length > 14) ? masks[1] : masks[0];
                $("#Form-phone").mask(mask, options);
            }});
        $("#Form-birth").mask("00/00/0000", {clearIfNotMatch: true});
        $("#Form-cnpj").mask("00.000.000/0000-00", {clearIfNotMatch: true});
        $("#Form-foundation").mask("00/00/0000", {clearIfNotMatch: true});
        $("#Form-phone-entity").mask("(00) 0000-0000", {clearIfNotMatch: true, onKeyPress: function (phone, e, field, options) {
                var masks = ['(00) 0000-00000', '(00) 0 0000-0000'];
                mask = (phone.length > 14) ? masks[1] : masks[0];
                $("#Form-phone-entity").mask(mask, options);
            }});
        $("#Form-cep").mask("00.000-000", {clearIfNotMatch: true});
    });
</script>