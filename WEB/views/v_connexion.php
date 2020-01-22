<?php require_once(PATH_VIEWS.'header.php');?>

<!--  Zone message d'alerte -->
<?php require_once(PATH_VIEWS.'alert.php');?>

<!--  Début de la page -->

<?php
	if(!isset($alert)){ //s'il n'y a pas eu d'erreurs
?>
	
	
	
	<!-- Affichage du formulaire -->
	<form method="post" action="index.php?page=connexion">
		<div class="contact-form">
			<img src="<?= PATH_LOGO2 ?>" alt="" class="avatar">
		<h2><?= TITRE_PAGE_CONNEXION ?></h1><br/><br/>


		<p><?= IDENTIFIANT ?></p>
		   <input type="username"name="identifiant" value="" placeholder="Enter Username">
		   <br/>
		<p><?= MOTDEPASSE ?></p>
		   <input type="password" placeholder="Enter Password"name="password"  value=""><br/>
			<br/>
		<input type="submit" value="Se connecter" /> 
		<br/>
		</div>	
	</form>
	<?php 
	}
	?>
