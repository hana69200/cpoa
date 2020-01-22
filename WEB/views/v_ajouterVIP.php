<?php
require_once(PATH_VIEWS . 'header.php');
require_once(PATH_ENTITY.'VIP.php'); 
require_once(PATH_MODELS.'VIPDAO.php');
require_once(PATH_VIEWS . 'alert.php'); 
?>

<div>
	<h3><?= TITRE_AJOUT_VIP ?></h3>
	</br>

</div>

<div>
	<form action="index.php?page=ajouterVIP" method="post" enctype="multipart/form-data">
		<h4>Prénom</h4>
		<input type="text" name="Prenom"></br>
		<h4>Nom</h4>
		<input type="text" name="Nom"></br>
		<h4>Photo</h4>
		<input type="file" name="Photo">
		<h4>Âge</h4>
		<input type="number" name="Age"></br>
		<h4>Nationalité</h4>
		<input type="text" name="Nationalite"></br></br>
		<h4>Classement ATP</h4>
		<input type="text" name="ClassATP"></br></br>
		<h4>Nb Tournois Grands Chelems</h4>
		<input type="text" name="NbTournois"></br></br>
		<h4>Prise en charge</h4>
		<input type="text" name="PriseCharge"></br></br>
		<h4>Compagnon</h4>
		<input type="text" name="Compagnon"></br>
		<button class="btn btn-outline-secondary my-2 my-sm-0" type="submit">Ajouter</button>
	</form>
</div>

<?php require_once(PATH_VIEWS.'footer.php'); ?>