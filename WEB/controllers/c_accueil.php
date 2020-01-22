<?php
require_once(PATH_MODELS . 'PhotoDAO.php');
require_once(PATH_MODELS . 'CategorieDAO.php');


// R�cuper de la cat�gorie

	if (isset($_GET['categorie'])) {
		$catid = htmlspecialchars($_GET['categorie']);
	}

//creation du tableau de categories
$categorieDAO= new CategorieDAO();

$categories= $categorieDAO -> getCategories();



// R�cup�ration du nom de la cat�gorie
if (isset($catid)) {
	foreach ($categories as $categorie) {
		if ($categorie -> getCatid() == $catid) {
			$nomcat = $categorie -> getNomcat();
		}
	}
}

//erreurs de cat�gorie
if (isset($catid) && !isset($nomcat)) {
	$erreur = 'categorie';
}



// Appel du mod�le pour charger les photos
$photoDAO = new PhotoDAO();

$photos = $photoDAO -> getPhotos();

//traitement des erreurs
if(!is_null($photoDAO->getErreur()))
{
	$erreur='query';
}
// Gestion des erreurs
if (isset($erreur)) {
    $alert = choixAlert($erreur);
}
if (!isset($erreur)) {
    $nbP=count($photos);
}
elseif(isset($nbP)){
		$alert =choixAlert('selection_ok', $nbP);
}
// Appel de la vue
require_once(PATH_VIEWS.$page.'.php');
?>