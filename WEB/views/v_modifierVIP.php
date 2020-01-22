<?php
require_once(PATH_VIEWS . 'header.php');
require_once(PATH_ENTITY.'VIP.php'); 
require_once(PATH_MODELS.'VIPDAO.php');
require_once(PATH_VIEWS . 'alert.php'); 
?>

<div>
	
	<h3>Vous modifiez le VIP: <?= $vip->getVIPRENOM().' '.$vip->getVIPNOM() ?> </h3>
</div>

<div>
	<form action="index.php?page=modifierVIP&id=<?= $vip->getVIPID()?>" method="post" enctype="multipart/form-data">
		
		<h4>Prénom</h4>
		<input type="text" name="Prenom"value="<?= $vip->getVIPRENOM()?>"></br>
		<h4>Nom</h4>
		<input type="text" name="Nom"value="<?= $vip->getVIPNOM()?>"></br>
		<h4>Photo</h4>
		<input type="file" name="Photo" ></br>
		<h4>Âge</h4>
		<input type="number" name="Age" value="<?= $vip->getVIPAD()?>"></br>
		<h4>Nationalité</h4>
		<input type="text" name="Nationalite" value="<?= $vip->getVIPNAT()?>"></br></br>
		<h4>Classement ATP</h4>
		<input type="text" name="ClassATP" value="<?= $vip->getCLASSATP()?>"></br></br>
		<h4>Nb Tournois Grands Chelems</h4>
		<input type="text" name="NbTournois" value="<?= $vip->getNBTOURNOIS() ?>"></br></br>
		<h4>Prise en charge</h4>
		<input type="text" name="PriseCharge" value="<?= $vip->getPRISECHARGE()?>"></br></br>
		<h4>Compagnon</h4>
		<input type="text" name="Compagnon" value="<?= $vip->getCOMPAGNON()?>"></br></br>
		<button class="btn btn-outline-secondary my-2 my-sm-0" type="submit">Modifier</button>
	</form>
</div>

<!--  Pied de page -->
<?php require_once(PATH_VIEWS . 'footer.php'); ?>