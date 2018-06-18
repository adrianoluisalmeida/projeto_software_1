<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@taglib tagdir="/WEB-INF/tags" prefix="custom" %>
<input type="hidden" name="id" value="${entity.id}"/>
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
    <input name="foundationDate" id="Form-foundation" value="<custom:localDateFormat localDate="${entity.foundationDate}" />" type="text" class="form-control" required>
    <label for="Form-foundation">Data Fundação</label>
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
</div>

<div class="md-form">
    <input type="password" id="Form-pass-entity" name="password" value="${entity.password}" class="form-control">
    <label for="Form-pass-entity">Senha</label>
    <form:errors path="entity.password" cssStyle="color:red"/>
</div>

<h4>Endereço</h4>
<div class="row height-60">
    <div class="col-8">
        <div class="md-form">
            <input type="text" name="postalCode" value="${entity.address.postalCode}" id="Form-cep" class="form-control" >
            <label for="Form-cep">CEP</label>
        </div>
    </div>

    <div class="col-4">
        <div class="md-form">
            <input disabled="trye" type="text" name="state" value="${entity.address.state}" id="Form-uf" class="form-control" >
            <label for="Form-uf">UF</label>
        </div>
    </div>
</div>

<div class="md-form">
    <input disabled="true" type="text" name="city" value="${entity.address.city}" id="Form-city" class="form-control" >
    <label for="Form-city">Cidade</label>
</div>
    
<div class="row height-60">
    <div class="col-8">
        <div class="md-form">
            <input type="text" name="street" value="${entity.address.street}" id="Form-street" class="form-control" >
            <label for="Form-street">Rua</label>
        </div>
    </div>

    <div class="col-4">
        <div class="md-form">
            <input type="text" name="number" value="${entity.address.number}" id="Form-number" class="form-control" >
            <label for="Form-number">Nº</label>
        </div>
    </div>
</div>

<div class="md-form">
    <input type="text" name="neighborhood" value="${entity.address.neighborhood}" id="Form-distric" class="form-control" >
    <label for="Form-distric">Bairro</label>
</div>


<div class="md-form">
    <input type="text" name="complement" value="${entity.address.complement}" id="Form-complement" class="form-control">
    <label for="Form-complement">Complemento</label>
</div>
    
<div class="md-form"> 
    <textarea name="description" id="Form-description"   value="${entity.description}" class="form-control"></textarea>
    <!--<input type="text" name="description" >-->
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