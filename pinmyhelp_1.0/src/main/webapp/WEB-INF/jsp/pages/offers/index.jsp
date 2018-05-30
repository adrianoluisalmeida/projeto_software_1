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
            <tr>
                <td colspan="5" style="padding: 8px 16px;">
                    <b>Ir ao supermercado</b> - <i>Este é um exemplo de observação sobre a oferta, essa observação é escrita pelo voluntário</i>
                </td>
            </tr>
            <tr>
                <td></td>
                <td>
                    <b>Pedro Soares</b>
                </td>
                <td>
                    <b>Joaquim Ferreira</b>
                </td>
                <td>
                    <b>00/00/0000 às 19:00</b>
                </td>

                <td>

                    <a data-target="#modalLoginForm" type="button" class="btn btn-danger btn-rounded btn-table btn-sm" data-toggle="modal"
                       data-placement="top" title="Recusar Oferta">
                        <i class="fa fa-times"></i>
                    </a>
                    <a type="button" class="btn btn-success btn-rounded btn-table btn-sm" data-toggle="tooltip"
                       data-placement="top" title="Aprovar Oferta">
                        <i class="fa fa-check"></i>
                    </a>
                </td>
            </tr>
            <tr>
                <td colspan="5" style="padding: 8px 16px;">
                    <b>Ir na Padaria</b> -  <i>Sem observação</i>
                </td>
            </tr>
            <tr>
                <td></td>
                <td>
                    <b>Maria</b>
                </td>
                <td>
                    <b>Pedrão Ferreira</b>
                </td>
                <td>
                    <b>00/00/0000 às 20:00</b>
                </td>

                <td>
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

                    <!--<div class="text-center">-->
                    <!--<a href="" class="btn btn-default btn-rounded mb-4" >Launch Modal Login Form</a>-->
                    <!--</div>-->
                    <!---->

                    <a data-toggle="modal" dasta-target="#modalLoginForm" type="button" class="btn btn-danger btn-rounded btn-table btn-sm" data-toggle="tooltip"
                       data-placement="top" title="Recusar Oferta">
                        <i class="fa fa-times"></i>
                    </a>
                    <a type="button" class="btn btn-success btn-rounded btn-table btn-sm" data-toggle="tooltip"
                       data-placement="top" title="Aprovar Oferta">
                        <i class="fa fa-check"></i>
                    </a>
                </td>
            </tr>
        </tbody>
        <!--Table body-->

    </table>
    <!--Table-->
</div>