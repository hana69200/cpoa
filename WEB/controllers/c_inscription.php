<?php
require_once(PATH_MODELS.'UserDAO.php');
// Gestion des erreurs
if (isset($erreur)) {
    $alert = choixAlert($erreur);
}

	
if(isset($_POST['id']) && isset($_POST['mdp']))
{
	$username = htmlspecialchars($_POST['id']);
	$password = htmlspecialchars($_POST['mdp']);

	$userDAO= new UserDAO();
	$UserID= $userDAO -> creerUser($username, $password);
	header('Refresh:0; url=index.php?page=connexion');
}
	
	
require_once(PATH_VIEWS.$page.'.php');
?>