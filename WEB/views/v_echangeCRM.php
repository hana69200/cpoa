<?php
require_once(PATH_VIEWS . 'header.php');
require_once(PATH_ENTITY.'CRM.php'); 
require_once(PATH_MODELS.'CrmDAO.php');
require_once(PATH_MODELS.'PhotoDAO.php');
require_once(PATH_ENTITY.'Photo.php'); 
require_once(PATH_VIEWS . 'alert.php');
?>

<!--  Affichage du tableau de photos -->
<h3> Choisissez le vip pour voir les échanges effectués avec:</h3>
	<?php

	if(!isset($alert)){ //s'il n'y a pas d'erreurs
	if (isset($photo)) {
		foreach ($photo as $photos) {
			echo '<a href="index.php?page=echangeCRM2&id=' . $photos -> getPhotoId() . '" title="' . INFOS_PHOTO . '">';
			echo '<img src="' .PATH_IMAGES. $photos -> getNomFich(). '" style="border-radius: 30px;"/>';
			echo '</a>' . "\n";
		}
	}
}
?>