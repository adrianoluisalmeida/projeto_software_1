<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<div class="form-group mb-0 mt-4">
    <select name="type" value="Entity" class="mdb-select form-control">
        <option value="" disabled <c:if test="${empty person.type}">selected</c:if>>Seu objetivo no Pin My Help</option>

        <option value="Voluntary" <c:if test="${person.type == 'Voluntary'}">selected</c:if>>Quero Ajudar</option>
        <option value="Claimant" <c:if test="${person.type == 'Claimant'}">selected</c:if>">Preciso de Ajuda</option>
    </select>
    <form:errors path="person.type" cssStyle="color:red"/>
</div>

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
    <input name="rg" type="text" id="Form-rg" <c:if test="${not empty person.rg}">value="${person.rg}"</c:if> class="form-control">
    <label for="Form-rg">RG</label>
    <form:errors path="person.rg" cssStyle="color:red"/>
</div>


<!--Body-->
<div class="md-form">
    <input name="bornDate" id="Form-birth"  value="${person.bornDate}" type="text" class="form-control" required>
    <label for="Form-birth">Data Nascimento</label>
</div>


<div class="md-form">
    <input name="firstPhone" id="Form-phone" type="text" <c:if test="${not empty person.firstPhone}">value="${person.firstPhone}"</c:if> class="form-control">
    <label for="Form-phone">Telefone</label>
    <form:errors path="person.firstPhone" cssStyle="color:red"/>
</div>


<div class="md-form">
    <input type="email" name="email" id="Form-email" <c:if test="${not empty person.email}">value="${person.email}"</c:if> class="form-control">
    <label for="Form-email">Seu e-mail</label>
    <form:errors path="person.email" cssStyle="color:red"/>
</div>

<div class="md-form pb-3">
    <input type="password" id="Form-pass" name="password" class="form-control">
    <label for="Form-pass">Senha</label>
    <form:errors path="person.password" cssStyle="color:red"/>
</div>

<div class="row d-flex align-items-center mb-4">
    <div class="col-md-3 col-md-12 text-center">
        <button type="submit"
                class="btn btn-pink btn-block btn-rounded z-depth-1 waves-effect waves-light">
            Cadastrar
        </button>
    </div>
</div>