<?php

require_once(PATH_VIEWS . 'header.php');
require_once(PATH_ENTITY.'CRM.php'); 
require_once(PATH_MODELS.'CrmDAO.php');
require_once(PATH_VIEWS . 'alert.php'); 
?>

	<h3> Echanges et actions</h3>


<div>
	<form method="post" <?= 'action="index.php?page=echangeCRM2&id='.$crm->getCrmID().'"' ?> >
	<table id="tab">
		<tr>
			<th><img src="<?= PATH_MAIL?>" alt="" style="display: block;" width="90" height="100"></th>
			<th><div class="scroll">
				<?= $crm-> getCourrier()?>
			</div></th>
			
		</tr>
		<tr><th><img src="<?= PATH_APPEL?>" alt="" style="display: block;" width="70" height="90"></th>
		<th><div class="">
			<?= $crm-> getAppelTel()?>
		</div></th>
		</tr>

		<tr><th><img src="<?=PATH_FAX?>" alt="" style="display: block;" width="70" height="90"></th>
		<th><div class="scroll">
			<?= $crm-> getFax()?>
		</div></th>
		</tr>

		<tr><th><img src="<?= PATH_ACTION?>" alt="" style="display: block;" width="70" height="90"></th>
		<th>
			<?= $crm-> getAction()?>
		</th>
		</tr>

	</table>
		
	</form>
</div>


	<div>
	<form method="post" <?= 'action="index.php?page=ajoutCourrier&id='.$crm->getCrmID().'"'?> >
		<input type="submit" value="Modifier ou ajouter au CRM"/>
	</form>
	</div>



<? require_once(PATH_VIEWS.'footer.php') ?>

