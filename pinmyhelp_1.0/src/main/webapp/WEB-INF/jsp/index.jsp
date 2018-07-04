<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <meta http-equiv="x-ua-compatible" content="ie=edge">
    
        <title>Pin My Help</title>
        
        <!-- Font Awesome -->
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
        <!-- Bootstrap core CSS -->
        <link href="${pageContext.request.contextPath}/assets/css/bootstrap.min.css" rel="stylesheet">
        <!-- Material Design Bootstrap -->
        <link href="${pageContext.request.contextPath}/assets/css/mdb.min.css" rel="stylesheet">
        <!-- Your custom styles (optional) -->
        <link href="${pageContext.request.contextPath}/assets/css/custom.css" rel="stylesheet">
        <!-- Toast -->
        <link href="${pageContext.request.contextPath}/assets/css/jquery.toast.min.css" rel="stylesheet">
        <!-- Template -->
        <link href="${pageContext.request.contextPath}/assets/css/carousel.css" rel="stylesheet">
        
        <style>
            h2 { 
                font-weight: 300;
                line-height: 1;
                letter-spacing: -.05rem;
                font-size: 50px;
                min-width: 62em
            }
        </style>
    </head>
    
    <body>
        <header
            <!--Navbar-->
            <nav class="navbar navbar-expand-md navbar-dark fixed-top pink-color">
                <div class="container">
                    <!-- Navbar brand -->
                    <a class="navbar-brand" href="${pageContext.request.contextPath}/">Pin My Help</a>

                    <!-- Collapse button -->
                    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#basicExampleNav"
                            aria-controls="basicExampleNav"
                            aria-expanded="false" aria-label="Toggle navigation">
                        <span class="navbar-toggler-icon"></span>
                    </button>

                    <!-- Collapsible content -->
                    <div class="collapse navbar-collapse" id="basicExampleNav">
                        <!-- Links -->
                        <ul class="navbar-nav mr-auto">
                            <li id="nav-home" class="nav-item active">
                                <a class="nav-link" href="${pageContext.request.contextPath}/">Home
                                    <span class="sr-only">(current)</span>
                                </a>
                            </li>
                            <!--
                            <li id="nav-about" class="nav-item">
                                <a class="nav-link" href="${pageContext.request.contextPath}/">Sobre</a>
                            </li> 
                            -->
                        </ul>
                        <!-- Icons -->
                        <ul class="navbar-nav ml-auto nav-flex-icons justify-content-center">
                            <li id="login" class="nav-item">
                                <i class="fa fa-sign-in nav-link"></i>
                                <li id="nav-login" class="nav-item">
                                    <a class="nav-link" href="${pageContext.request.contextPath}/login">Entrar no sistema (Login)</a>
                                </li>
                            </li>
                        </ul>
                        <!-- /Icons -->
                    </div>
                    <!-- /Collapsible content -->
                </div>
            </nav>
            <!-- /Navbar -->
        </header>
        <!-- Slides -->  
        <main role="main">
            <div id="myCarousel" class="carousel slide" data-ride="carousel">
                <ol class="carousel-indicators">
                    <li data-target="#myCarousel" data-slide-to="0" class="active"></li>
                    <li data-target="#myCarousel" data-slide-to="1"></li>
                    <li data-target="#myCarousel" data-slide-to="2"></li>
                </ol>
                <div class="carousel-inner">
                    <div class="carousel-item active">
                        <img class="first-slide" src="${pageContext.request.contextPath}/assets/img/slide.jpg">
                        <div class="container">
                            <div class="carousel-caption">
                                <h2>Tá esperando o quê?</h2>
                                <p>Junte-se a gente! Cadastre-se agora e entre junto nesta corrente do bem (:</p>
                                <p><a class="btn btn-lg btn-pink waves-effect waves-light" href="${pageContext.request.contextPath}/account/create" role="button">Cadastre-se</a></p>
                            </div>
                        </div>
                    </div>
                    <div class="carousel-item">
                        <img class="second-slide" src="${pageContext.request.contextPath}/assets/img/slide1.jpg">
                        <div class="container">
                            <div class="carousel-caption">
                                <h1>Escolha entre ajudar ou receber ajuda</h1>
                                <p>Ou quem sabe ambos? Você que necessita de qualquer tipo de ajuda ou você que deseja ajudar são muito bem-vindos! :D</p>
                                <p><a class="btn btn-lg btn-pink waves-effect waves-light" href="${pageContext.request.contextPath}/login" role="button">Entre agora</a></p>
                            </div>
                        </div>
                    </div>
                    <div class="carousel-item">
                        <img class="third-slide" src="${pageContext.request.contextPath}/assets/img/slide2.jpg">
                        <div class="container">
                            <div class="carousel-caption">
                                <h1>Entidades também podem entrar</h1>
                                <p>Você também pode cadastrar sua entidade e através dela solicitar ou receber ajudas o/</p>
                                 <p><a class="btn btn-lg btn-pink waves-effect waves-light" href="${pageContext.request.contextPath}/account/create#panel2" role="button">Cadastre sua Entidade</a></p>
                            </div>
                        </div>
                    </div>
                </div>
                <a class="carousel-control-prev" href="#myCarousel" role="button" data-slide="prev">
                    <span class="carousel-control-prev-icon" aria-hidden="true"></span>
                    <span class="sr-only">Voltar</span>
                </a>
                <a class="carousel-control-next" href="#myCarousel" role="button" data-slide="next">
                    <span class="carousel-control-next-icon" aria-hidden="true"></span>
                    <span class="sr-only">Avançar</span>
                </a>
            </div>
            <!-- /Slides -->
            <!-- Textos abaixo dos slides -->
            <div class="container marketing">
                <hr class="featurette-divider">

                <div class="row featurette">
                    <div class="col-md-7">
                        <h2>Primeiramente: <span class="text-muted">O que é o<br>Pyn My Help?</span></b></h2>
                          <p class="lead" align="justify">Pin My Help é um site desenvolvido com a finalidade de proporcionar a colaboração entre pessoas
                          que precisam de ajuda com aquelas que tem interesse em ajudar. O intuito é criar um ambiente que facilite encontrar
                          voluntários dispostos a estender a mão para qualquer tipo de necessidade que o requerente tenha, desde ajuda com limpeza
                          e organização até ajuda com compras ou locomoção. Permite que cada um faça sua parte para tornar a vida de quem precisa
                          de apoio muito mais fácil e mais satisfatória! 😃❤
                          </p>
                    </div>
                    <div class="col-md-5">
                         <img src="${pageContext.request.contextPath}/assets/img/handshake.png" width="300px" height="300px" algn="middle">
                    </div>
                </div>

                <hr class="featurette-divider">

                <div class="row featurette">
                    <div class="col-md-7 order-md-2">
                          <h2>E... <span class="text-muted">Como funciona?</span></h2>
                          <p class="lead" align="justify">Para iniciar é muito fácil: basta criar um cadastro no nosso site, clique em "Entrar no sistema" na parte
                          superior direita ou clique nos botões das imagens que passam logo acima. Você pode se cadastrar como requerente (se precisa
                          de algum tipo de ajuda) ou voluntário (se quiser oferecer sua ajuda) ou então como entidade (podendo solicitar ou oferecer
                          ajuda através dela). Feito isso, basta o requerente fazer seu pedido de solicitação de ajuda e aguardar até o usuário mais
                          próximo se voluntariar e ir ajudá-lo. Viu como é fácil e prático?! 😍
                          </p>
                    </div>
                    <div class="col-md-5 order-md-1">
                         <img src="${pageContext.request.contextPath}/assets/img/handshake1.png" width="300px" height="300px" align="middle">
                    </div>
                </div>

                <hr class="featurette-divider">

                <div class="row featurette">
                    <div class="col-md-7">
                          <h2>Por fim... <span class="text-muted">Quem pode entrar?</span></h2>
                          <p class="lead" align="justify">Todas as pessoas que necessitem de qualquer tipo ajuda ou as que queiram ajudar são bem-vindas aqui.
                          Aos requerentes, basta terem alguma necessidade e aos voluntários, basta ter disponibilidade e um pouco de amor para
                          doar ao próximo. Você pode fazer parte desta corrente do bem que só tende a crescer e se multiplicar!
                          Vem com a gente! 😘
                          </p>
                    </div>
                    <div class="col-md-5">
                          <img src="${pageContext.request.contextPath}/assets/img/handshake2.jpg" width="300px" height="350px" align="middle">
                    </div>
                </div>

                <hr class="featurette-divider">
            </div>
            <!-- Textos -->
            <!-- FOOTER -->
            <footer class="container">
                <p class="float-right"><i align="right" class="fa fa-arrow-up pink-text"><a href="#" style="color:#FF3E96"> Voltar ao topo</a></i></p>
            </footer>
        </main>
        <!-- SCRIPTS -->
        <!-- JQuery -->
        <script type="text/javascript" src="${pageContext.request.contextPath}/assets/js/jquery-3.2.1.min.js"></script>
        <!-- Bootstrap tooltips -->
        <script type="text/javascript" src="${pageContext.request.contextPath}/assets/js/popper.min.js"></script>
        <!-- Bootstrap core JavaScript -->
        <script type="text/javascript" src="${pageContext.request.contextPath}/assets/js/bootstrap.min.js"></script>
        <!-- MDB core JavaScript -->
        <script type="text/javascript" src="${pageContext.request.contextPath}/assets/js/mdb.js"></script>
        <!-- Mask JQuery -->                                              
        <script type="text/javascript" src="${pageContext.request.contextPath}/assets/js/jquery.toast.min.js"></script>
    </body>
</html>