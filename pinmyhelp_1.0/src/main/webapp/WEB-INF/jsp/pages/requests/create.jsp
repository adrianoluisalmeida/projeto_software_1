<div class="row mt-5">
    <div class="col-md-8 mt-3">
        <h3>Descrição da Solicitação</h3>
        <div class="md-form">
            <textarea type="text" id="Form-requirement" class="md-textarea form-control" rows="3"></textarea>
            <label for="Form-requirement">Descreve a sua necessidade ?</label>
        </div>

        <h3>Meu endereço <button id="btn-get-address" class="btn btn-pink float-right waves-effect waves-light">Usar endereço atual</button></h3> 
        <div class="row">
            <div class="col">  
                <div class="md-form">
                    <input type="text" id="Form-address" name="Form-address" class="form-control" value="RST 287, Camobi, Santa Maria RS, 2180, apto 300"/>
                                    <!-- aqui no lugar do value vai ir o \${user.address}, ta assim so pra exemplo -->
                    <label for="Form-address">Endereço</label>
                </div>
            </div>
        </div>   
        <hr>
        <h3>Data/Hora</h3>
        <div class="row">
            <div class="col">
                <div class="md-form">
                    <input type="text" name="date" id="Form-date" class="form-control">
                    <label for="Form-date">Data</label>
                </div>
            </div>
            <div class="col">
                <div class="md-form">
                    <input type="text" name="time" id="Form-time" class="form-control">
                    <label for="Form-time">Hora</label>
                </div>
            </div>
        </div>
    </div>
    <div class="col-md-4 mt-3">


        <!--Caso for uma entedidade, pode selecionar requerente-->
        <h3>Requerente</h3>
        <p><i>Caso for entidade</i></p>
        <div class="form-group mb-0 mt-4">
            <select name="objective" class="mdb-select form-control">
                <option value="" disabled selected>Selecione um requerente</option>
                <option value="1" selected>Pedro Soares</option>
                <option value="2">Maria Luísa</option>
            </select>
        </div>

        <table class="table table-striped mt-3">
            <tr>
                <td><b>Nome</b></td>
                <td>Pedro Soares</td>
            </tr>
            <tr>
                <td><b>CPF</b></td>
                <td>000.000.000-00</td>
            </tr>
            <tr>
                <td><b>E-mail</b></td>
                <td>pedros@gmail.com.br</td>
            </tr>
            <tr>
                <td><b>Telefone</b></td>
                <td>00 0 0000-0000</td>
            </tr>
        </table>
    </div>

</div>

<script>
    $(function() {
        var $buttonAddress = $('#btn-get-address');
        $( $buttonAddress ).on('click', function() {
            $buttonAddress.prop("disabled", true); // disable button
            if ("geolocation" in navigator) { //check if geolocation is available 
                navigator.geolocation.getCurrentPosition(function(position) { 
                    getPositionInfo(position);
               });
            } else
                alert("Geocalização não suportada pelo navegador atual.");
        });        
    
        function getPositionInfo(position) {
            var GEOCODING = 'https://maps.googleapis.com/maps/api/geocode/json?latlng=' 
                    + position.coords.latitude  + '%2C' 
                    + position.coords.longitude + '&language=en';
            $.getJSON(GEOCODING).done(function(location) {
                showLocation(location);
            });
        }
        
        function showLocation(location) {
            if ( location !== null ) { // ok
                var $input = $('#Form-address');
                $( $input ).val( location.results[0].formatted_address );
                $( $input ).keyup(function(event) { // if user edit his address then active the button again
                    $( $buttonAddress).prop("disabled", false);  
                });
            } else {
                alert("Não foi possível recuperar a localização atual. Verifique sua conexão com a internet.");
                $( $buttonAddress).prop("disabled", false); // let's user try again...
            }
        }
    });
</script>