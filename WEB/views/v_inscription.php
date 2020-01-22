<?php require_once(PATH_VIEWS.'header.php');?>

<!--  Zone message d'alerte -->
<?php require_once(PATH_VIEWS.'alert.php');?>

<!--  Début de la page -->

<?php
	if(!isset($alert)){ //s'il n'y a pas eu d'erreurs
?>
	
	
	<!-- Affichage du formulaire -->
	<form method="post" action="index.php?page=inscription">
	
	<div class="contact-form">
		<img src="<?= PATH_LOGO2 ?>" alt="" class="avatar">
		<h2>S'inscrire </h2><br/>
		<p> Username </p>
		<input type="username" placeholder="Enter Username" name="id">
		<p> Password </p>
		<input type="password" placeholder="Enter Password" name="mdp">
		<input type="submit" value="S'inscrire">
    </div>
	</form>
	<?php 
	}
	?>
