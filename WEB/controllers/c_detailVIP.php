<?php
require_once(PATH_MODELS.'VIPDAO.php');
require_once(PATH_MODELS.'PhotoDAO.php');

if (!$_SESSION['logged']) {
    header('Refresh:0; url=index.php?page=connexion');
    exit();
}

if(isset($_GET['id'])){
    //on creer un objet vip dao pour pouvoir appeler les methodes presente dans ce model
	$vipDAO = new VIPDAO();
	$vip = $vipDAO -> getById(htmlspecialchars($_GET['id']));
	//on creer un objet photo dao afin que avec l'id recupere on peut recuperer la photo associe au VIPID
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