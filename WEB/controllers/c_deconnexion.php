<?php


// Delete the session cookie
	if ( ini_get("session.use_cookies")) {
		$params = session_get_cookie_params();
		setcookie(session_name() , '', -1,
		$params["path"], $params["domain"],
		$params["secure"], $params["httponly"]
	);
}
	// Détruit toutes les variables de session
	$_SESSION = array();
	
	header('location:index.php');
require_once(PATH_VIEWS.$page.'.php');
?>