<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@taglib tagdir="/WEB-INF/tags" prefix="custom" %>

<c:if test="${not empty person.id}">
    <input type="hidden" name="id" value="${person.id}"/> 
    <input type="hidden" name="type" value="${person.type}"/>
</c:if>
<c:if test="${empty person.id}">
<!-- Type -->
<div class="form-group mb-0 mt-4">
    <select name="type" class="mdb-select form-control">
        <option value="" disabled <c:if test="${(person.type != 'Voluntary') && (person.type != 'Claimant')}">selected</c:if>>Seu objetivo no Pin My Help</option>
        <option value="Voluntary" <c:if test="${person.type == 'Voluntary'}">selected</c:if>>Quero Ajudar</option>
        <option value="Claimant" <c:if test="${person.type == 'Claimant'}">selected</c:if>>Preciso de Ajuda</option>
    </select>
    <form:errors path="person.type" cssStyle="color:red"/>
</div>   
</c:if>
<div class="md-form">
    <input type="text" name="name" id="Form-name" <c:if test="${not empty person.name}">value="${person.name}"</c:if>  class="form-control">
    <label for="Form-name">Nome</label>
    <form:errors path="person.name" cssStyle="color:red"/>
</div>

<div class="md-form">
    <input name="cpf" type="text" id="Form-cpf" <c:if test="${not empty person.cpf}">value="${person.cpf}"</c:if> class="form-control">
    <label for="Form-cpf">CPF</label>
    <form:errors path="person.cpf" cssStyle="color:red"/>
</div>

<div class="md-form">
    <input name="rg" type="text" id="Form-rg" <c:if test="${not empty person.rg}">value="${person.rg}"</c:if> class="form-control" maxlength="10">
    <label for="Form-rg">RG</label>
    <form:errors path="person.rg" cssStyle="color:red"/>
</div>

<div class="md-form">
    <input name="bornDate" id="Form-birth" value="<custom:localDateFormat localDate="${person.bornDate}"/>" type="text" class="form-control">
    <label for="Form-birth">Data de Nascimento</label>
    <c:if test="${not empty errorBornDate}"><span class="error">${errorBornDate}</span></c:if>
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
    <c:if test="${not empty email_error_person}">
        <span class="error">${email_error_person}</span> 
        <a href="#" class="font-weight-light ml-1 pull-right"> Esqueceu a senha?</a>
    </c:if>
</div>

<div class="md-form pb-3">
    <input type="password" id="Form-pass" name="password" class="form-control">
    <label for="Form-pass">Senha</label>
    <form:errors path="person.password" cssStyle="color:red"/>
    <c:if test="${not empty password_error_person}"><span class="error">${password_error_person}</span></c:if>
</div>
<c:if test="${not empty person.id}">
    <h4>Endereço</h4>
    <div class="row height-60">
        <div class="col-8">
            <div class="md-form">
                <input type="text" name="address.postalCode" value="${person.address.postalCode}" id="Form-cep" class="form-control address-input">
                <label for="Form-cep">CEP</label>
                <form:errors path="person.address.postalCode" cssStyle="color:red"/>
            </div>
        </div>

        <div class="col-4">
            <div class="md-form">
                <input type="text" name="address.state" value="${person.address.state}" id="Form-uf" class="form-control address-input" readonly="readonly">
                <label for="Form-uf">UF</label>
                <form:errors path="person.address.state" cssStyle="color:red"/>
            </div>
        </div>
    </div>

    <div class="md-form">
        <input type="text" name="address.city" value="${person.address.city}" id="Form-city" class="form-control address-input" readonly="readonly">
        <label for="Form-city">Cidade</label>
        <form:errors path="person.address.city" cssStyle="color:red"/>
    </div>

    <div class="row height-60">
        <div class="col-8">
            <div class="md-form">
                <input type="text" name="address.street" value="${person.address.street}" id="Form-street" class="form-control" >
                <label for="Form-street">Rua</label>
                <form:errors path="person.address.street" cssStyle="color:red"/>
            </div>
        </div>

        <div class="col-4">
            <div class="md-form">
                <input type="text" name="address.number" value="${person.address.number}" id="Form-number" class="form-control" >
                <label for="Form-number">Nº</label>
                <form:errors path="person.address.number" cssStyle="color:red"/>
            </div>
        </div>
    </div>

    <div class="md-form">
        <input type="text" name="address.neighborhood" value="${person.address.neighborhood}" id="Form-neighborhood" class="form-control address-input" >
        <label for="Form-neighborhood">Bairro</label>
        <form:errors path="person.address.neighborhood" cssStyle="color:red"/>
    </div>

    <div class="md-form">
        <input type="text" name="address.complement" value="${person.address.complement}" id="Form-complement" class="form-control">
        <label for="Form-complement">Complemento</label>
    </div>
        
    <input type="hidden" id="Form-latitude" name="address.location.latitude" value="${person.address.location.latitude}"/>
    <input type="hidden" id="Form-longitude" name="address.location.longitude" value="${person.address.location.longitude}"/>
</c:if>
<div class="row d-flex align-items-center mb-4">
    <div class="col-md-3 col-md-12 text-center">
        <button type="submit"
                class="btn btn-pink btn-block btn-rounded z-depth-1 waves-effect waves-light">
            ${empty person.id ? "Cadastrar" : "Atualizar" }
        </button>
    </div>
</div>