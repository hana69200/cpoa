<?php
require_once(PATH_MODELS.'CrmDAO.php');
require_once(PATH_ENTITY.'CRM.php');

$vip = "";
if (!$_SESSION['logged']) {
    header('Refresh:0; url=index.php?page=connexion');
    exit();
}

if(isset($_GET['id'])){
	$crmDAO = new CrmDAO();
	$crm = $crmDAO -> getById(htmlspecialchars($_GET['id']));
	

	if (isset($_POST['ajoutCou'])) {
		$crmDAO = new crmDAO();
		$crm=$crmDAO -> ajoutCourrier($_GET['id']);
		
		header('Refresh:0; url=index.php');
	}
}

// Appel de la vue
require_once(PATH_VIEWS.$page.'.php');
?>