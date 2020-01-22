<?php require_once(PATH_VIEWS.'header.php');
require_once(PATH_MODELS.'CrmDAO.php');
require_once(PATH_ENTITY.'CRM.php'); 
require_once(PATH_VIEWS.'alert.php');?>

<!--  Début de la page -->

<?php
	if(!isset($alert)){ //s'il n'y a pas eu d'erreurs
?>
	
	
	
	<!-- Affichage du formulaire -->
	<form method="post" <?= 'action="index.php?page=ajoutCourrier&id='.$crm->getCrmID().'"' ?> >
		
			<h4>Ajouter ou modifier au CRM</h4><br/>

			<h5> <strong>Courrier </strong></h5>
			<input type="textarea" size="60" name="Courrier"value="<?= $crm->getCourrier()?>"/>
		</br>
		<h5> <strong>Appel Téléphonique </strong></h5>
			<input type="textarea" size="60" name="AppelTel"value="<?= $crm->getAppelTel()?>"/>
		</br>
		<h5> <strong>Fax</strong></h5>
			<input type="textarea" size="60" name="Fax"value="<?= $crm->getFax()?>"/>
		</br>
		<h5> <strong>Action </strong> </h5>
			<input type="textarea" size="60" name="Action"value="<?= $crm->getAction()?>"/>
		<br/><br/>
			<input type="submit" value="Valider"/>
		
	</form>

	
	<?php 
	}
	?>
