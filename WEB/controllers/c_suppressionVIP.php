<?php
require_once(PATH_MODELS.'PhotoDAO.php');
require_once(PATH_MODELS.'VIPDAO.php');
require_once(PATH_ENTITY.'VIP.php');

$vip = "";
if (!$_SESSION['logged']) {
    header('Refresh:0; url=index.php?page=connexion');
    exit();
}

if(isset($_GET['id'])){
	$vipDAO = new VIPDAO();
	$vip = $vipDAO -> getById(htmlspecialchars($_GET['id']));
	$photoDAO = new PhotoDAO();
	$photo = $photoDAO->getPhoto(htmlspecialchars($_GET['id']));
	

	if (isset($_POST['Supp'])) {
		$vipDAO = new VIPDAO();
		$vip=$vipDAO -> supprimerVIP($_GET['id']);
		$photoDAO = new PhotoDAO();
		$photoDAO -> supprimerPhoto($photo -> getPhotoID());
		
		header('Refresh:0; url=index.php');
	}
}

// Appel de la vue
require_once(PATH_VIEWS.$page.'.php');
?>