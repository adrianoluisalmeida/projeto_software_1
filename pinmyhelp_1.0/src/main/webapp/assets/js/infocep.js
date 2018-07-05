$(function () {

    var $inputs = $("div.md-form input.address-input");
    /* $('#btn-get-address').click(function () {
     if ("geolocation" in navigator) { //check if geolocation is available 
     navigator.geolocation.getCurrentPosition(function (position) {
     getPositionInfo(position);
     });
     } else
     alert("Geocalização não suportada pelo navegador atual.");
     });
     
     function getPositionInfo(position) {
     $inputs.prop("disabled", true);
     var GEOCODING = 'https://maps.googleapis.com/maps/api/geocode/json?latlng='
     + position.coords.latitude + '%2C'
     + position.coords.longitude + '&language=pt';
     var $inputlatitude = $('#Form-latitude');
     var $inputlongitude = $('#Form-longitude');
     $($inputlatitude).val(position.coords.latitude);
     $($inputlongitude).val(position.coords.longitude);
     $.getJSON(GEOCODING).done(function (location) {
     showLocation(location);
     });
     $inputs.prop("disabled", false);
     } */

    function showLocation(location) {
        if (location.status === "OK") {
            // var $input = $('#Form-address');                    
            // $($input).val(location.results[0].formatted_address);
            var address = location.results[0].address_components;
                   console.log(address);
            for (var i in address) {
        
                switch (String(address[i].types[0])) {
                   
                     
                    /* case "postal_code":
                     $inputs.filter("#Form-cep")
                     .val( (String(address[i].long_name).replace(/\D/g, '')) ) // only numbers
                     .trigger('input') // tigger jquery mask
                     .focus();
                     break; 
                     case "street_number":
                     $inputs.filter("#Form-number")
                     .val(String(address[i].long_name))
                     .focus();
                     break;  */
                    case "route":
                        //alert(String(address[i].long_name));
                        $("#Form-street").val(String(address[i].long_name));
//                        $inputs.filter("#Form-street")
//                                .val(String(address[i].long_name))
//                                .focus();
                        break;
                    case "political":
                        $("#Form-neighborhood").val(String(address[i].long_name));
//                        $inputs.filter("#Form-neighborhood")
//                                .val(String(address[i].long_name))
//                                .focus();
                        break;
                    case "administrative_area_level_2":
                          $("#Form-city").val(String(address[i].long_name));
//                        $inputs.filter("#Form-city")
//                                .val(String(address[i].long_name))
//                                .focus();
                        break;
                    case  "administrative_area_level_1":
                         $("#Form-uf").val(String(address[i].short_name));
//                        $inputs.filter("#Form-uf")
//                                .val(String(address[i].short_name))
//                                .focus();
                        break;
                }
                /*  $($input).keyup(function (event) { // if user edit his address then active the button again
                 $($buttonAddress).prop("disabled", false);
                 }); */
            }
            $("#Form-latitude").val(location.results[0].geometry['location'].lat);
            $("#Form-longitude").val(location.results[0].geometry['location'].lng);
        } else {
            alert("Não foi possível recuperar a localização atual. Verifique sua conexão com a internet ou se seu CEP está correto.");
            // $($buttonAddress).prop("disabled", false); // let's user try again...
            clear();
        }
    }

    function clear() {
        $inputs.filter("#Form-uf").val("").blur();
        $inputs.filter("#Form-city").val("").blur();
        $inputs.filter("#Form-cep").val("").focus();
    }

    // when focus out
    $("#Form-cep").blur(function () {
        var cep = $(this).val().replace(/\D/g, ''); // only numbers
        if (cep !== "") { // if not empty
            var $inputs = $("div.md-form input.address-input");
            $inputs.prop("disabled", true);

            var address = "https://viacep.com.br/ws/" + cep + "/json/";



            $.getJSON(address).done(function (result) {
              
                var GEOCODING = 'https://maps.googleapis.com/maps/api/geocode/json?key=AIzaSyBQfICn-6OaKmaohOOyHo7MMs12owuKS5w&address=' + result.logradouro + ", " + result.bairro + ", " + result.localidade + " - " + result.uf;
  
                $.getJSON(GEOCODING).done(function (location) {

                    showLocation(location);
                });
                $inputs.prop("disabled", false);
                //console.log(result);
                //showLocation(location);
            });


        } else
            clear();
    });
});