<!--Index ofertas recebidas -->
<div class="col-md-12 mt-5">
    <h3>Lista de Ofertas recebidas</h3>
<!--Table-->
    <table class="table table-striped table-responsive-md btn-table">

        <!--Table head-->
        <thead>
            <tr>
                <th>#</th>
                <th>Requerente</th>
                <th>Voluntário</th>
                <th>Data/Hora</th>
                <th>Ações</th>
            </tr>
        </thead>

        <!--Table head-->

        <!--Table body-->
        <tbody>
            <jsp:include page="../offers/list.jsp" flush="true" />                    

            </tr>
        </tbody>
        <!--Table body-->

    </table>
    <!--Table-->


    <div class="modal fade" id="modalLoginAvatarDemo" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" style=" padding-right: 15px;">
        <div class="modal-dialog cascading-modal modal-avatar modal-sm" role="document">
            <!--Content-->
            <div class="modal-content">

                <!--Header-->
                <div class="modal-header">
                    <img src="https://mdbootstrap.com/img/Photos/Avatars/img%20%281%29.jpg" class="rounded-circle img-responsive" alt="Avatar photo">
                </div>
                <!--Body-->
                <div class="modal-body text-center mb-1">

                    <h5 class="mt-1 mb-2">Maria Doe</h5>

                    <div class="md-form ml-0 mr-0">
                        <p>
                            <b>Nome: </b> Deborah
                        </p>
                        <p>
                            Estudante .... estudante e outras coisas para a biografia aqui.
                        </p>
                        <p>
                            <b>Telefone: </b> (51) 9 9999-9999
                        </p>
                        <p>
                            <b>E-mail: </b> deb@gmail.com
                        </p>

                    </div>
                </div>

            </div>
            <!--/.Content-->
        </div>
    </div>

    <div class="modal fade" id="modalLoginForm" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header text-center">
                    <h4 class="modal-title w-100 font-weight-bold">Recusar Oferta</h4>
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                </div>
                <div class="modal-body mx-3">
                    <div class="md-form mb-5">
                        <input type="text" id="defaultForm-reason" class="form-control validate">
                        <label data-error="wrong" data-success="right" for="defaultForm-reason">Descreva o motivo pelo qual você não aceita essa oferta</label>
                    </div>

                </div>
                <div class="modal-footer d-flex justify-content-center">
                    <button class="btn btn-pink">Confirmar</button>
                </div>
            </div>
        </div>
    </div>

</div>