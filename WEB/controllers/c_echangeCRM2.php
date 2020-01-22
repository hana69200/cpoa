<?php
require_once(PATH_MODELS.'PhotoDAO.php');
require_once(PATH_ENTITY.'Photo.php'); 
require_once(PATH_MODELS.'CrmDAO.php');
require_once(PATH_ENTITY.'CRM.php'); 

	//creer photoDAO
	$photoDAO=new PhotoDAO(DEBUG);

	$photo=$photoDAO->getPhotos();

if(isset($_GET['id'])){
	$crmDAO = new CrmDAO();
	$crm = $crmDAO -> getById(htmlspecialchars($_GET['id']));

	}


if (!isset($photo)) {
	$alert = choixAlert('photo');
	
}

require_once(PATH_VIEWS.$page.'.php');
?>