<!--Body-->
<div class="md-form">
    <input type="text" name="name" id="Form-social-name" value="${entity.name}" class="form-control">
    <label for="Form-social-name">Raz�o Social</label>
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

    <input name="foundationDate" id="Form-foundation" value="${entity.foundationDate}" type="text" class="form-control" required>
    <label for="Form-foundation">Data Funda��o</label>
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
    <input type="password" id="Form-pass-entity" name="password" class="form-control">
    <label for="Form-pass-entity">Senha</label>
    <form:errors path="entity.password" cssStyle="color:red"/>
</div>
<h4>Endere�o</h4>
<div class="row height-60">
    <div class="col-8">
        <div class="md-form">
            <input type="text" name="cep" value="${entity.address.cep}" id="Form-cep" class="form-control" >
            <label for="Form-cep">CEP</label>
        </div>
    </div>

    <div class="col-4">
        <div class="md-form">
            <input type="text" name="uf" value="${entity.address.uf}" id="Form-uf" class="form-control" >
            <label for="Form-uf">UF</label>
        </div>
    </div>
</div>
<div class="row height-60">
    <div class="col-6">
        <div class="md-form">
            <input type="text" name="distric" value="${entity.address.distric}" id="Form-distric" class="form-control" >
            <label for="Form-distric">Bairro</label>
        </div>
    </div>

    <div class="col-6">
        <div class="md-form">
            <input type="text" name="city" value="${entity.address.city}" id="Form-city" class="form-control" >
            <label for="Form-city">Cidade</label>
        </div>
    </div>
</div>

<div class="md-form">
    <input type="text" name="complement" value="${entity.address.complement}" id="Form-complement" class="form-control">
    <label for="Form-complement">Complemento</label>
</div>
<div class="md-form"> 
    <textarea name="description" id="Form-description"   value="${entity.description}" class="form-control"></textarea>
    <!--<input type="text" name="description" >-->
    <label for="Form-description">Descri��o da entidade</label>
</div>


<div class="row d-flex align-items-center mb-4">
    <div class="col-md-3 col-md-12 text-center">
        <button type="submit"
                class="btn btn-pink btn-block btn-rounded z-depth-1 waves-effect waves-light">
            Cadastrar
        </button>
    </div>
</div>