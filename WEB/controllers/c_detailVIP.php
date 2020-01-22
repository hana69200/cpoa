<?php
require_once(PATH_MODELS.'VIPDAO.php');
require_once(PATH_MODELS.'PhotoDAO.php');

if (!$_SESSION['logged']) {
    header('Refresh:0; url=index.php?page=connexion');
    exit();
}

if(isset($_GET['id'])){
	$vipDAO = new VIPDAO();
	$vip = $vipDAO -> getById(htmlspecialchars($_GET['id']));
	$photoDAO= new PhotoDAO();
	$photo= $photoDAO ->getPhoto(htmlspecialchars($_GET['id']));

}

// Gestion des erreurs
if (isset($erreur)) {
    $alert = choixAlert($erreur);
}



// Appel de la vue
require_once(PATH_VIEWS.$page.'.php');
?>