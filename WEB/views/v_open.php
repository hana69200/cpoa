<?php require_once(PATH_VIEWS.'header.php');?>

<!--  Zone message d'alerte -->
<?php require_once(PATH_VIEWS.'alert.php');?>

<!--  Affichage du formulaire -->
<link href="<?= PATH_CSS ?>css.css" rel="stylesheet">
	<form method="get" action="index.php?page=accueil">
	

	<div id="myCarousel" class="carousel slide" data-ride="carousel"  style="width: 75%; float: center;">
  <!-- Indicators -->
  <ol class="carousel-indicators">
    <li data-target="#myCarousel" data-slide-to="0" class="active"></li>
    <li data-target="#myCarousel" data-slide-to="1"></li>
    <li data-target="#myCarousel" data-slide-to="2"> </li>
  </ol>

  <!-- Wrapper for slides -->
  <div class="carousel-inner">
    <div class="item active">
      <img src="assets/images/tennis2.jpg" alt="Tennis1">
      <div class="carousel-caption">
        <h3>Open Parc Aral</h3>
        <p>Fondé en 1987, l'Open Tennis de Lyon fait partie des tournois internationaux comptant pour le classement ATP 250.</p>
      </div>
    </div>

    <div class="item">
      <img src="assets/images/tennis1.jpg" alt="Tennis2">
      <div class="carousel-caption">
        <h3>Open Parc Aral</h3>
        <p>En 2017, il devient l'Open Parc Auvergne-Rhône-Alpes Lyon (ou Open par 
        	ARAL) et est organisé sur terre batuue au Parc de la Tête d'Or, durant la semaien précéfdent 
        	Roland-Garros.</p>
      </div>
    </div>

    <div class="item">
      <img src="assets/images/tennis3.jpg" alt="Tennis3">
      <div class="carousel-caption">
        <h3>Open Parc Aral</h3>
        <p>Le gagnant remporte un prix d'un peu plus de 500 000€ (tournois de Master 1000 :
        	plus de 3M€</p>
      </div>
    </div>
  </div>

  <!-- Left and right controls -->
  <a class="left carousel-control" href="#myCarousel" data-slide="prev">
    <span class="glyphicon glyphicon-chevron-left"></span>
    <span class="sr-only">Previous</span>
  </a>
  <a class="right carousel-control" href="#myCarousel" data-slide="next">
    <span class="glyphicon glyphicon-chevron-right"></span>
    <span class="sr-only">Next</span>
  </a>
</div>
	<br/>
	<h1 class="nomPage" style="sont-size:36px; outline: 0 !important;" > Plan accès du site</h1>
	<p>
	<img src="<?=PATH_PLAN?>" style="width: 38%;">
	<span class="droite" style="text-align:justify; float:right;font-size: 20px;"> 
	<br/><br/><br/>
	Le parc ne disposant pas de courts de tennis, un court central<br> de 4500 places est construit sur le Vélofrome Georges-Préveral, <br/> tandis que 3 courts annexes sont installés sur les parkings <br/>situés à proximité. Il existe différents types de billets: grand public,<br/> licenciés, promotions (associations...), invitations, et billets pour la Finale.
	
	</span></p> 
	</form>



<img src="assets/images/logoTennis.png" style="width:95px;float:right;">

<!--  Pied de page -->
<?php require_once(PATH_VIEWS.'footer.php'); ?>