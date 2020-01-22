<?php
require_once(PATH_VIEWS . 'header.php');
require_once(PATH_ENTITY.'VIP.php'); 
require_once(PATH_MODELS.'VIPDAO.php');

?>
<?php
if (!isset($alert)) {  //tant qu'il n'a a pas d'erreurs
?>
<div>
	<form method="get" <?= 'action="index.php?page=detailVIP&id='.$vip->getVIPID().'"' ?> >
	<h3><?= $vip->getVIPRENOM().' '.$vip->getVIPNOM() ?> </h3>
	</br>
	<p>
	<?php
		echo '<div class="row"><div class="col-md-4 order-md-1 mb-4"><img alt="Photo" class="circular-square" style=border-radius:60%; src="'.PATH_IMAGES.$photo -> getNomFich().'" style=width:70%;></div>';

	?>

	<div class = "col-md-6 col-sm-6 col-xs-12">
	<table class="table table-bordered">
		<tr>
			<th>Age </th>
			<td><?=$vip -> getVIPAD() ?></td>
		</tr>
		<tr>
			<th>Nationalité</th>
			<td><?= $vip -> getVIPNAT() ?></td>
		</tr>
		<tr>
			<th>Classement ATP </th>
			<td><?= $vip -> getCLASSATP() ?></td>
		</tr>
		<tr>
			<th>Nb Tournois Grands Chelem </th>
			<td><?= $vip -> getNBTOURNOIS()?></td>
		</tr>
		<tr>
			<th>Prise en charge </th>
			<td><?= $vip -> getPRISECHARGE() ?></td>
		</tr>
		<tr>
			<th>Compagnon </th>
			<td><?=$vip -> getCOMPAGNON() ?></td>
		</tr>
	</table>
	</div>


	</p>
</form>                   
</div>

<div>
	<form method="post" <?= 'action="index.php?page=modifierVIP&id='.$vip->getVIPID().'"'?> >
		<button class="btn btn-outline-secondary my-2 my-sm-0" type="submit">Modifier</button>
	</form>
	<form method="post" <?= 'action="index.php?page=suppressionVIP&id='.$vip->getVIPID().'"'?> >
		<button class="btn btn-outline-secondary my-2 my-sm-0" type="submit">Supprimer</button>
	</form>
</div> 

<?php
}
?>

<!--  Pied de page -->
<?php require_once(PATH_VIEWS . 'footer.php'); ?>