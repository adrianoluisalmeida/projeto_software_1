<div class="col-md-12 mt-5 mb-5" >
    <h2>Solicitações mais próximas</h2>
    <div class="map-container">
        <div class="g_map" style="min-height: 500px"  data-longitude="44.958309" data-latitude="34.109925"
             data-marker="http://simbiose-agro.com.br/images/frontend/marker.png">
        </div>
        <!-- Google Map Javascript Codes -->
    </div>
</div>

<input type="hidden" id="baseUrl" value="${pageContext.request.contextPath}">

<script type="text/javascript" src="http://maps.google.com/maps/api/js?key=AIzaSyBQfICn-6OaKmaohOOyHo7MMs12owuKS5w"></script>
<script type="text/javascript">


    /**
     * 
     * @param {object} solicitations
     * @param {float} mapLat
     * @param {float} mapLng
     * @param {int} zoomEdit
     * @param {bool} search
     * @returns {undefined}
     */
    function mapGenerator(solicitations, mapLat, mapLng, zoomEdit, search) {

        if (!zoomEdit)
            zoomEdit = 4;
   console.log(solicitations);
        var maps = $('.g_map');

        if (maps.length > 0) {
            // $.getScript('', function (data, textStatus, jqxhr) {

            maps.each(function () {
                var current_map = $(this);
                var latlng = new google.maps.LatLng(mapLat, mapLng);
                var point = current_map.attr('data-marker');

                var myOptions = {
                    zoom: zoomEdit,
                    center: latlng,
                    mapTypeId: "terrain",
//                        mapTypeId: google.maps.MapTypeId.ROADMAP,
                    mapTypeControl: true,
                    scrollwheel: true,
                    draggable: true,
                    panControl: true,
                    zoomControl: true,
                    disableDefaultUI: true
                };

                var stylez = [
                    {
                        featureType: "all",
                        elementType: "all",
                        stylers: [
                            {saturation: -100} // <-- THIS
                        ]
                    }
                ];

                var map = new google.maps.Map(current_map[0], myOptions);

                var mapType = new google.maps.StyledMapType(stylez, {name: "terrain"});
                map.mapTypes.set('terrain', mapType);
                map.setMapTypeId('terrain');

                $(solicitations).each(function (index, data) {

                 

                    var long = parseFloat(data.location.latitude);


                    var marker = new google.maps.Marker({
                        map: map,
                        icon: {
                            size: new google.maps.Size(40, 56),
                            origin: new google.maps.Point(0, 0),
                            anchor: new google.maps.Point(20, 56),
                            url: point
                        },
                        position: {lat: data.location.latitude, lng: data.location.longitude}
                    });


                    if (data.entity) {
                        var name = data.entity.name;
                    }else if(data.claimant){
                        var name = data.claimant.name;
                    }
                    var map_descr = "<p><b>Requerente </b><br/>" + name + "</p><p><b>Descrição</b> <br/>" + data.description + "</p><a href=" + $('#baseUrl').val() + "/offers/help/" + data.id + " class='btn btn-sm btn-pink'>Ajudar</a><br/>";

                    var infowindow = new google.maps.InfoWindow({
                        content: map_descr,

                    });

                    if (search) {
                        infowindow.open(map, marker);
                    }

                    google.maps.event.addListener(marker, "click", function () {
                        infowindow.open(map, marker);
                    });
                });
                //});
            });
        }
    }
    function showPosition(position) {
        mapGenerator(${gson}, position.coords.latitude, position.coords.longitude, 13);
        console.log(position.coords.latitude, position.coords.longitude);
    }

    jQuery(document).ready(function () {
        //var location = null;
        if (navigator.geolocation) {
            navigator.geolocation.getCurrentPosition(showPosition);
        } else {
            alert("Geolocation is not supported by this browser.");
        }

        mapGenerator(${gson}, '-29.705222899999995', '-53.70974280000001', 10);
    });
</script>