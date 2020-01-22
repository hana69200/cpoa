<?php
require_once(PATH_MODELS.'PhotoDAO.php');
require_once(PATH_ENTITY.'Photo.php'); 


	//creer photoDAO
	$photoDAO=new PhotoDAO(DEBUG);

	$photo=$photoDAO->getPhotos();
	


if (!isset($photo)) {
	$alert = choixAlert('photo');
	
}

require_once(PATH_VIEWS.$page.'.php');
?>