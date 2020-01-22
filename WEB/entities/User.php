<?php

class User {
	
	private $_userID;
	private $_username;
	private $_password;
	
	public function __construct($userID, $username, $password) {
		$this -> _userID = $userID;
		$this -> _username = $username;
		$this -> _password = $password;
	}
	
	public function getUserID() {
		return $this -> _userID;
	}
	
	public function getUsername() {
		return htmlspecialchars($this -> _username);
	}
    
    public function getPassword() {
		return htmlspecialchars($this -> _password);
	}
	
}

?>
