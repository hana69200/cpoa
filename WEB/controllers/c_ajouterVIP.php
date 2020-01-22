<?php
require_once(PATH_MODELS.'VIPDAO.php');

if (!$_SESSION['logged']) {
    header('Refresh:0; url=index.php?page=connexion');
    exit();
}

if(isset($_FILES['Photo']))
{ 
     $dossier = PATH_IMAGES;
     $fichier = basename($_FILES['Photo']['name']);
     move_uploaded_file($_FILES['Photo']['tmp_name'], $dossier . $fichier);
}


if (isset($_POST['Prenom']) && isset($_POST['Nom']) && isset($_FILES['Photo']) && isset($_POST['Age']) && isset($_POST['Nationalite']) && isset($_POST['ClassATP']) && isset($_POST['NbTournois']) && isset($_POST['PriseCharge']) && isset($_POST['Compagnon'])) {
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
	$VipID = $VipDAO -> ajouterVIP($prenom, $nom, $age, $nationalite,  $classATP, $nbTournois,$priseCharge, $compagnon, $nomFich);
	header('Refresh:0; url=index.php?page=vip&id='.$VipID);
}

// Gestion des erreurs
if (isset($erreur)) {
    $alert = choixAlert($erreur);
}

require_once(PATH_VIEWS.$page.'.php');
?>