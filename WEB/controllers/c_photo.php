<?php
require_once(PATH_MODELS.'PhotoDAO.php');
require_once(PATH_MODELS.'CategorieDAO.php');


if (isset($_GET['id'])|| is_numeric($_GET['id'] ) ) {
	$id =(int)$_GET['id'];	
}
elseif (isset($_GET['cat'])|| is_numeric($_GET['cat'] ) ) {
	$cat =(int)$_GET['cat'];	
}
else{
		header('location:index.php');
		exit;
	}
	


	//creer photoDAO
	$photoDAO=new PhotoDAO(DEBUG);

	$photo=$photoDAO->getById($id);
	
	$categorieDAO=new CategorieDAO();
	
	$cat=$categorieDAO ->getCatById($photo -> getCatId());


if (!isset($photo)) {
	$alert = choixAlert('photo');
	
}

require_once(PATH_VIEWS.$page.'.php');
?>