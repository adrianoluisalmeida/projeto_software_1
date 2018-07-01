<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@taglib tagdir="/WEB-INF/tags" prefix="custom"%>

<c:if test="${not empty entity}">
    <input type="hidden" name="id" value="${entity.id}"/>
</c:if>
<!--Body-->
<div class="md-form">
    <input type="text" name="name" id="Form-social-name" value="${entity.name}" class="form-control">
    <label for="Form-social-name">Razão Social</label>
    <form:errors path="entity.name" cssStyle="color:red"/>
</div>

<!--Body-->
<div class="md-form">
    <input name="cnpj" type="text" id="Form-cnpj" value="${entity.cnpj}"  class="form-control">
    <label for="Form-cnpj">CNPJ</label>
    <form:errors path="entity.cnpj" cssStyle="color:red"/>
</div>


<!--Body-->
<div class="md-form">
    <input name="foundationDate" id="Form-foundation" value="<custom:localDateFormat localDate="${entity.foundationDate}"/>" type="text" class="form-control">
    <label for="Form-foundation">Data de Fundação</label>
    <c:if test="${not empty errorFoundationDate}"><span class="error">${errorFoundationDate}</span></c:if>
</div>


<div class="md-form">
    <input name="firstPhone" id="Form-phone-entity" value="${entity.firstPhone}"  type="tel" class="form-control">
    <label for="Form-phone-entity">Telefone</label>
    <form:errors path="entity.firstPhone" cssStyle="color:red"/>
</div>

<div class="md-form">
    <input type="email" name="email" id="Form-email-entity" value="${entity.email}"  class="form-control">
    <label for="Form-email-entity">E-mail para contato e login</label>
    <form:errors path="entity.email" cssStyle="color:red"/>
    <c:if test="${not empty email_error_entity}">
        <span class="error">${email_error_entity}</span>
        <a href="#" class="font-weight-light ml-1 pull-right"> Esqueceu a senha?</a>    
    </c:if>
</div>

<div class="md-form">
    <input type="password" id="Form-pass-entity" name="password" class="form-control">
    <label for="Form-pass-entity">Senha</label>
    <form:errors path="entity.password" cssStyle="color:red"/>
    <c:if test="${not empty password_error_entity}"><span class="error">${password_error_entity}</span></c:if>
</div>

<h4>Endereço</h4>
<div class="row height-60">
    <div class="col-8">
        <div class="md-form">
            <input type="text" name="address.postalCode" value="${entity.address.postalCode}" id="Form-cep" class="form-control address-input" >
            <label for="Form-cep">CEP</label>
            <form:errors path="entity.address.postalCode" cssStyle="color:red"/>
        </div>
    </div>

    <div class="col-4">
        <div class="md-form">
            <input type="text" name="address.state" value="${entity.address.state}" id="Form-uf" class="form-control address-input" readonly="readonly">
            <label for="Form-uf">UF</label>
            <form:errors path="entity.address.state" cssStyle="color:red"/>
        </div>
    </div>
</div>

<div class="md-form">
    <input type="text" name="address.city" value="${entity.address.city}" id="Form-city" class="form-control address-input" readonly="readonly">
    <label for="Form-city">Cidade</label>
    <form:errors path="entity.address.city" cssStyle="color:red"/>
</div>
    
<div class="row height-60">
    <div class="col-8">
        <div class="md-form">
            <input type="text" name="address.street" value="${entity.address.street}" id="Form-street" class="form-control" >
            <label for="Form-street">Rua</label>
            <form:errors path="entity.address.street" cssStyle="color:red"/>
        </div>
    </div>

    <div class="col-4">
        <div class="md-form">
            <input type="number" name="address.number" value="${entity.address.number}" id="Form-number" class="form-control" min="0">
            <label for="Form-number">Nº</label>
            <form:errors path="entity.address.number" cssStyle="color:red"/>
        </div>
    </div>
</div>

<div class="md-form">
    <input type="text" name="address.neighborhood" value="${entity.address.neighborhood}" id="Form-neighborhood" class="form-control address-input" >
    <label for="Form-neighborhood">Bairro</label>
    <form:errors path="entity.address.neighborhood" cssStyle="color:red"/>
</div>

<div class="md-form">
    <input type="text" name="address.complement" value="${entity.address.complement}" id="Form-complement" class="form-control">
    <label for="Form-complement">Complemento</label>
</div>
    
<input type="hidden" id="Form-latitude" name="address.location.latitude" value="${entity.address.location.latitude}"/>
<input type="hidden" id="Form-longitude" name="address.location.longitude" value="${entity.address.location.longitude}"/>
    
<div class="md-form"> 
    <textarea name="description" id="Form-description" class="md-textarea form-control" rows="2">${entity.description}</textarea>
    <label for="Form-description">Descrição da entidade</label>
</div>


<div class="row d-flex align-items-center mb-4">
    <div class="col-md-3 col-md-12 text-center">
        <button type="submit"
                class="btn btn-pink btn-block btn-rounded z-depth-1 waves-effect waves-light">
            Cadastrar
        </button>
    </div>
</div>