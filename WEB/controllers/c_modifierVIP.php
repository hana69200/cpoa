<?php
require_once(PATH_ENTITY.'VIP.php');
require_once(PATH_MODELS.'PhotoDAO.php');
require_once(PATH_MODELS.'VIPDAO.php');
require_once(PATH_ENTITY.'Photo.php');

if (!$_SESSION['logged']) {
    header('Refresh:0; url=index.php?page=connexion');
    exit();
}

if(isset($_GET['id'])){
	$vipDAO = new VIPDAO();
	$vip = $vipDAO -> getById(htmlspecialchars($_GET['id']));
	$photoDAO = new PhotoDAO();
	$photo = $photoDAO->getPhoto(htmlspecialchars($_GET['id']));
	
}

if(isset($_FILES['photo']))
{ 
     $dossier = PATH_IMAGES;
     $fichier = basename($_FILES['photo']['name']);
     move_uploaded_file($_FILES['photo']['tmp_name'], $dossier . $fichier);
}

if (isset($_POST['Prenom']) && isset($_POST['Nom']) && isset($_FILES['Photo']) &&  isset($_POST['Age']) && isset($_POST['Nationalite']) && isset($_POST['ClassATP']) &&  isset($_POST['NbTournois'])&&  isset($_POST['PriseCharge'])&& isset($_POST['Compagnon'])) {
	
    $prenom = htmlspecialchars($_POST['Prenom']);
	$nom = htmlspecialchars($_POST['Nom']);
	$age = htmlspecialchars($_POST['Age']);
	$nationalite = htmlspecialchars($_POST['Nationalite']);
	$classATP = htmlspecialchars($_POST['ClassATP']);
	$nbTournois = htmlspecialchars($_POST['NbTournois']);
	$priseCharge = htmlspecialchars($_POST['PriseCharge']);
	$compagnon = htmlspecialchars($_POST['Compagnon']);
	$nomFich = htmlspecialchars($_FILES['Photo']['name']);
	
	
	$VipDAO = new VIPDAO();
	$VipID = $VipDAO -> modifierVIP($prenom, $nom, $age, $nationalite,  $classATP, $nbTournois,$priseCharge, $compagnon, $nomFich);
	header('Refresh:0; url=index.php?page=vip&id='.$VipID);
}


// Gestion des erreurs
if (isset($erreur)) {
    $alert = choixAlert($erreur);
}


// Appel de la vue
require_once(PATH_VIEWS . $page . '.php');
?>