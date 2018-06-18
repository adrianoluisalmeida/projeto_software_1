<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@taglib tagdir="/WEB-INF/tags" prefix="custom" %>
<input type="hidden" name="id" value="${person.id}"/>
<input type="hidden" name="type" value="${person.type}" />
<!--Body-->
<div class="md-form">
    <input type="text" name="name" id="Form-name" <c:if test="${not empty person.name}">value="${person.name}"</c:if>  class="form-control">
    <label for="Form-name">Nome</label>
    <form:errors path="person.name" cssStyle="color:red"/>
</div>

<!--Body-->
<div class="md-form">
    <input name="cpf" type="text" id="Form-cpf" <c:if test="${not empty person.cpf}">value="${person.cpf}"</c:if> class="form-control">
    <label for="Form-cpf">CPF</label>
    <form:errors path="person.cpf" cssStyle="color:red"/>
</div>

<!--Body-->
<div class="md-form">
    <input name="rg" type="text" id="Form-rg" <c:if test="${not empty person.rg}">value="${person.rg}"</c:if> class="form-control" maxlength="10">
    <label for="Form-rg">RG</label>
    <form:errors path="person.rg" cssStyle="color:red"/>
</div>


<!--Body-->
<div class="md-form">
    <input name="bornDate" id="Form-birth"  value="<custom:localDateFormat localDate="${person.bornDate}" />" type="text" class="form-control" required>
    <label for="Form-birth">Data Nascimento</label>
</div>


<div class="md-form">
    <input name="firstPhone" id="Form-phone" type="text" <c:if test="${not empty person.firstPhone}">value="${person.firstPhone}"</c:if> class="form-control">
    <label for="Form-phone">Telefone</label>
    <form:errors path="person.firstPhone" cssStyle="color:red"/>
</div>

<div class="md-form">
    <input type="email" name="email" id="Form-email" value="${person.email}" class="form-control">
    <label for="Form-email">Seu e-mail</label>
    <form:errors path="person.email" cssStyle="color:red"/>
</div>

<div class="md-form pb-3">
    <input type="password" id="Form-pass" name="password" value="${person.password}" class="form-control">
    <label for="Form-pass">Senha</label>
    <form:errors path="person.password" cssStyle="color:red"/>
</div>

<h4>Endereço</h4>
<div class="row height-60">
    <div class="col-8">
        <div class="md-form">
            <input type="text" name="postalCode" value="${person.address.postalCode}" id="Form-cep" class="form-control" >
            <label for="Form-cep">CEP</label>
        </div>
    </div>

    <div class="col-4">
        <div class="md-form">
            <input disabled="trye" type="text" name="state" value="${person.address.state}" id="Form-uf" class="form-control" >
            <label for="Form-uf">UF</label>
        </div>
    </div>
</div>

<div class="md-form">
    <input disabled="true" type="text" name="city" value="${person.address.city}" id="Form-city" class="form-control" >
    <label for="Form-city">Cidade</label>
</div>
    
<div class="row height-60">
    <div class="col-8">
        <div class="md-form">
            <input type="text" name="street" value="${person.address.street}" id="Form-street" class="form-control" >
            <label for="Form-street">Rua</label>
        </div>
    </div>

    <div class="col-4">
        <div class="md-form">
            <input type="text" name="number" value="${person.address.number}" id="Form-number" class="form-control" >
            <label for="Form-number">Nº</label>
        </div>
    </div>
</div>

<div class="md-form">
    <input type="text" name="neighborhood" value="${person.address.neighborhood}" id="Form-distric" class="form-control" >
    <label for="Form-distric">Bairro</label>
</div>


<div class="md-form">
    <input type="text" name="complement" value="${person.address.complement}" id="Form-complement" class="form-control">
    <label for="Form-complement">Complemento</label>
</div>

<div class="row d-flex align-items-center mb-4">
    <div class="col-md-3 col-md-12 text-center">
        <button type="submit"
                class="btn btn-pink btn-block btn-rounded z-depth-1 waves-effect waves-light">
            Cadastrar
        </button>
    </div>
</div>