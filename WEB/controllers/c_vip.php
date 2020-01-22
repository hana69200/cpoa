<?php
require_once(PATH_MODELS.'VIPDAO.php');



/*else{
		header('location:index.php');
		exit;
	}*/

//creer VIPDAO
	$vipDAO=new VIPDAO(DEBUG);

	$vip=$vipDAO->getVIP();


if (!isset($vip)) {
	$alert = choixAlert('photo');
	
}

if (!$_SESSION['logged']) {
    header('Refresh:0; url=index.php?page=connexion');
    exit();
}

// Gestion des erreurs
if (isset($erreur)) {
    $alert = choixAlert($erreur);
}




require_once(PATH_VIEWS.$page.'.php');
?>