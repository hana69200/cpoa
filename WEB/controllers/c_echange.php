<?php

require_once(PATH_ENTITY.'CRM.php'); 
require_once(PATH_MODELS.'CrmDAO.php');
// Gestion des erreurs
if (isset($erreur)) {
    $alert = choixAlert($erreur);
}

if (!$_SESSION['logged']) {
    header('Refresh:0; url=index.php?page=connexion');
    exit();
}


try{
if(isset($_GET['id'])){
	$crmDAO = new CrmDAO();
	$crm = $crmDAO -> getById(htmlspecialchars($_GET['id']));

	}
}
catch(PDOException $e){
if($this->_debug)
            die($e->getMessage());
          $this->_erreur = 'query';
	  
}

//Appel de la vue 
require_once(PATH_VIEWS.$page.'.php');
?>