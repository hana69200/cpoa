<?php
require_once(PATH_VIEWS . 'header.php');
require_once(PATH_ENTITY.'CRM.php'); 
require_once(PATH_MODELS.'CrmDAO.php');
require_once(PATH_VIEWS . 'alert.php'); 
?>

	<h3> Echanges et actions</h3>


<div>
	<form method="post" action="index.php?page=echange">
	<table>
		<tr>
			<td><img src="<?= PATH_MAIL?>" alt="" style="display: block;" width="90" height="100"></td>
			<td>
				<?= $crm-> getCourrier()?>
			</td>
		</tr>
		<tr><td><img src="<?= PATH_APPEL?>" alt="" style="display: block;" width="70" height="90"></td>
		<td>
			<?= $crm-> getAppelTel()?>
		</td></tr>

		<tr><td><img src="<?=PATH_FAX?>" alt="" style="display: block;" width="70" height="90"></td>
		<td>
			<?= $crm-> getFax()?>
		</td></tr>
		<tr><td><img src="<?= PATH_ACTION?>" alt="" style="display: block;" width="70" height="90"></td>
		<td>
			<?= $crm-> getAction()?>
		</td></tr>
	</table>
		
	</form>
</div>


<? require_once(PATH_VIEWS.'footer.php') ?>