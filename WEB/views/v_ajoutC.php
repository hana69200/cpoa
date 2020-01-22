<?php
require_once(PATH_VIEWS . 'header.php');
require_once(PATH_ENTITY.'CRM.php'); 
require_once(PATH_MODELS.'CrmDAO.php');
require_once(PATH_VIEWS . 'alert.php'); 
?>

<div>
	<h3> Ajouter un Courrier</h3>
</div>

<div>
	<form method="post" <?='action="index.php?page=ajoutC&id='.$crm->getCrmID().'"' ?> >
		<input type='submit' value='Ajouter'>
	</form>
</div>

<?require_once(PATH_VIEWS.'footer.php') ?>