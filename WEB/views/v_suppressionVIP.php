<?php
require_once(PATH_VIEWS . 'header.php');
require_once(PATH_ENTITY.'VIP.php'); 
require_once(PATH_MODELS.'VIPDAO.php');
require_once(PATH_VIEWS . 'alert.php'); 
?>
<div class="contact-form2">
<div>
	<h3> Vous supprimez le VIP : <?= $vip->getVIPRENOM().' '.$vip->getVIPNOM() ?> </h3>
</div>

<div>
	<form method="post" <?= 'action="index.php?page=suppressionVIP&id='.$vip->getVIPID().'"' ?> >
	Vous allez supprimer ce VIP, êtes vous sûrs de vouloir faire cela ?
		<input type='hidden' name='Supp'>
		<button class="btn btn-danger" style="float: center;"type="submit">Supprimer</button></input>
	</form>
</div>
</div>

<? require_once(PATH_VIEWS.'footer.php') ?>