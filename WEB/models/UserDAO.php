<?php

require_once(PATH_MODELS . 'DAO.php');
require_once(PATH_ENTITY . 'User.php');

class UserDAO extends DAO {
	
    public function connexion($username, $password) {
		
		$ligne = $this -> queryRow("SELECT * FROM User WHERE username = ? AND password = ?", array($username, $password));
		if ($ligne) {
				return new User($ligne['userID'], $ligne['username'], $ligne['password']);
		}
		else {
			return false;
        }
    }
    
    public function getUserByUsername($username) {
		
		$ligne = $this -> queryRow("SELECT * FROM User WHERE username = ? ", array($username));
		if ($ligne) {
            return new User($ligne['userID'], $ligne['username'], $ligne['password']);
		}
		else {
			return false;
        }
    }
    public function getUsernameById($id) {
		
		$ligne = $this -> queryRow("SELECT username FROM User WHERE userID = ? ", array($id));
		if ($ligne) {
            return new User($ligne['userID'], $ligne['username'], $ligne['password']);
		}
		else {
			return false;
        }
    }
    
    public function getUserById($id) {
		
		$ligne = $this -> queryRow("SELECT * FROM User WHERE userID = ?", array($id));
		if ($ligne) {
				return new User($ligne['userID'], $ligne['username'], $ligne['password']);
		}
		else {
			return false;
        }
    }

    public function creerUser($username, $password)
    {
    	// Récupération d'un identifiant libre
        $res = $this -> queryRow('SELECT MAX(userID) FROM User');
		$userID = $res['MAX(userID)'] + 1;
		
		// Ajout du VIP en base
		$this -> _requete("INSERT INTO User (userID, username, password) VALUES (?, ?, ?)", array($userID,$username, $password));
		return $userID;
    }

}

?>
