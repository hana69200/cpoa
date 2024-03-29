<?php
require_once(PATH_MODELS.'PhotoDAO.php');
require_once(PATH_ENTITY.'Photo.php'); 
require_once(PATH_MODELS.'CrmDAO.php');
require_once(PATH_ENTITY.'CRM.php'); 

if (!$_SESSION['logged']) {
    header('Refresh:0; url=index.php?page=connexion');
    exit();
}

if(isset($_GET['id'])){
	$crmDAO = new CrmDAO();
	$crm = $crmDAO -> getById(htmlspecialchars($_GET['id']));

	}
// si les champs courrier, appelTel, fax et action existent alors on les stockent dans une variable
if (isset($_POST['Courrier']) && isset($_POST['AppelTel']) && isset($_POST['Fax']) && isset($_POST['Action'])) {
	
    $courrier = htmlspecialchars($_POST['Courrier']);
	$appelTel = htmlspecialchars($_POST['AppelTel']);
	$fax = htmlspecialchars($_POST['Fax']);
	$action = htmlspecialchars($_POST['Action']);
	
	$crmDAO = new CrmDAO();
	//grace a la methode modifierCRM on peut interagir avec la bd et update la table CRM selon l'id recuperer
	$crmID = $crmDAO -> modifierCRM($courrier,$appelTel,$fax,$action);
	header('Refresh:0; url=index.php?page=echangeCRM2&id='.$crm->getCrmID());
}

// Gestion des erreurs
if (isset($erreur)) {
    $alert = choixAlert($erreur);
}
require_once(PATH_VIEWS.$page.'.php');
?>