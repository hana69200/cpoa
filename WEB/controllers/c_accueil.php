<?php
require_once(PATH_MODELS . 'PhotoDAO.php');
require_once(PATH_MODELS . 'CategorieDAO.php');


// Récuper de la catégorie

	if (isset($_GET['categorie'])) {
		$catid = htmlspecialchars($_GET['categorie']);
	}

//creation du tableau de categories
$categorieDAO= new CategorieDAO();

$categories= $categorieDAO -> getCategories();



// Récupération du nom de la catégorie
if (isset($catid)) {
	foreach ($categories as $categorie) {
		if ($categorie -> getCatid() == $catid) {
			$nomcat = $categorie -> getNomcat();
		}
	}
}

//erreurs de catégorie
if (isset($catid) && !isset($nomcat)) {
	$erreur = 'categorie';
}



// Appel du modèle pour charger les photos
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