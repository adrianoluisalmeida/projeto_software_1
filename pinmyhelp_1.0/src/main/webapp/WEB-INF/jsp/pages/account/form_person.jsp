<div class="form-group mb-0 mt-4">
    <select name="type" value="Entity" class="mdb-select form-control" required>
        <option value="" disabled selected>Seu objetivo no Pin My Help</option>

        <option value="Voluntary" ${person.type == 'Voluntary' ? 'selected' : ''}">Quero Ajudar</option>
        <option value="Claimant" ${person.type == 'Claimant' ? 'selected' : ''}">Preciso de Ajuda</option>
    </select>
    <form:errors path="person.type" cssStyle="color:red"/>
</div>

<!--Body-->
<div class="md-form">
    <input type="text" name="name" id="Form-name" value="${person.name}"  class="form-control">
    <label for="Form-name">Nome</label>
    <form:errors path="person.name" cssStyle="color:red"/>
</div>

<!--Body-->
<div class="md-form">
    <input name="cpf" type="text" id="Form-cpf" value="${person.cpf}" class="form-control">
    <label for="Form-cpf">CPF</label>
    <form:errors path="person.cpf" cssStyle="color:red"/>
</div>

<!--Body-->
<div class="md-form">
    <input name="rg" type="text" id="Form-rg" value="${person.rg}" class="form-control">
    <label for="Form-rg">RG</label>
    <form:errors path="person.rg" cssStyle="color:red"/>
</div>


<!--Body-->
<div class="md-form">
    <input name="bornDate" id="Form-birth"  value="${person.bornDate}" type="text" class="form-control" required>
    <label for="Form-birth">Data Nascimento</label>
</div>


<div class="md-form">
    <input name="firstPhone" id="Form-phone" type="tel" value="${person.firstPhone}" class="form-control">
    <label for="Form-phone">Telefone</label>
    <form:errors path="person.firstPhone" cssStyle="color:red"/>
</div>


<div class="md-form">
    <input type="email" name="email" id="Form-email" value="${person.email}" class="form-control">
    <label for="Form-email">Seu e-mail</label>
    <form:errors path="person.email" cssStyle="color:red"/>
</div>

<div class="md-form pb-3">
    <input type="password" id="Form-pass" name="password"  class="form-control">
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