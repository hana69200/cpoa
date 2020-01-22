<?php
require_once(PATH_MODELS . 'UserDAO.php');
require_once(PATH_MODELS . 'DAO.php');
require_once(PATH_ENTITY . 'User.php');

/*if (isset($_POST['identifiant']))
{
	if($_POST['identifiant']=LOGADMIN)
	{
		if (password_verify($_POST['password'],MDPADMIN))
		{
			$alert=choixAlert('ok_connexion');
			$_SESSION['logged']=true;
		}
		else
		{
			$alert=choixAlert('erreur_mdp');
		}
	}
	else
	{
		$alert=choixAlert('erreur_id');
	}
}*/

// si champ identifiant existe
if (isset($_POST['identifiant']))
{
    //on stocke
	$identifiant = htmlspecialchars($_POST['identifiant']);
	

		 $uDao = new UserDAO();
		$userBD = $uDao->getUserByUsername($identifiant);
		// si l'identifiant recuperer n'existe pas dans la bd 
		if (!$userBD) $alert=choixAlert('erreur_id');
		if (!isset($_POST['password']) || $_POST['password']!=$userBD->getPassword()) $alert = choixAlert('erreur_mdp');
		else {
			$alert = choixAlert('ok_connexion');
			$_SESSION['logged'] = true;
		}

		$identite=$userBD->getUsername();
  
}

if(isset($erreur))
{
	$alert=choixAlert($erreur);
}

	
require_once(PATH_VIEWS.$page.'.php');
?>