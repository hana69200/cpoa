<?php
require_once(PATH_MODELS.'Connexion.php');
abstract class DAO 
{

  private $_erreur; //stocke les messages d'erreurs associées au PDOException
  private $_debug;
  
  public function __construct($debug= false)
  {
    $this-> _debug = $debug;
    $this -> _erreur = null;
  }

  public function getErreur()
  {
   return $this->_erreur;
  }
  protected function beginTransaction() {
        Connexion::getInstance() -> getBdd() -> beginTransaction(); 
    }

  protected function endTransaction() {
        if (is_null($this -> _erreur))
        Connexion::getInstance() -> getBdd() -> commit();
    }

  public function _requete($sql, $args = null)  
  {
    if ($args == null) 
    {
	$pdos = Connexion::getInstance()->getBdd()->query($sql);// exécution directe
    }
    else 
    {
	$pdos = Connexion::getInstance()->getBdd()->prepare($sql);// requête préparée
	$pdos->execute($args);
    }
    return $pdos;
  }
 
  // retourne un tableau 1D avec les données d'un seul enregistrement
  // ou false 
  public function queryRow($sql, $args = null)
  {
	try
	{
		$pdos = $this->_requete($sql, $args);
		$res = $pdos->fetch();
                $pdos->closeCursor();
	}
	catch(PDOException $e)
	{ 
	  //if($this->_debug)
          die($e->getMessage());
          $this->_erreur = 'query';
	  $res = false;
	} 
    return $res;
  }
  
  //retourne un tableau 2D avec éventuellement plusieurs enregistrements
  public function queryAll($sql, $args = null)
  {
 	try
	{
		$pdos = $this->_requete($sql, $args);
		$res = $pdos->fetchAll();
                $pdos->closeCursor();
	}
	catch(PDOException $e)
	{ 
	  /*if($this-> _debug)*/
            die($e->getMessage());
          $this->_erreur = 'query';
	  $res = false;
	} 
    return $res;
  }


   // Retourne true ou false pour update et delete et insert
    public function queryBdd($sql, $args = null) {
        $res = true;
        try {
            $pdos = $this -> _requete($sql, $args);
            $pdos -> closeCursor();
        }
        catch(PDOException $e) { 
            if ($this -> _debug) {
                die($e -> getMessage());
            }
            $this -> _erreur = 'query';
            $res = false;
        }
        return $res;
    }

    // Retourne l'identifiant de la ligne insérée ou false
    protected function insertId() {
        try {
            $res = Connexion::getInstance() -> getBdd() -> lastInsertId();
        }
        catch(PDOException $e) {
            if ($this -> _debug) {
                die($e -> getMessage);
            }
            $this -> _erreur = 'query';
            $res = false;
        }
        return $res;
    }

}
