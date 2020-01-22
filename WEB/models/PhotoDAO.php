<?php
require_once(PATH_MODELS.'DAO.php');
require_once(PATH_ENTITY.'Photo.php');

class PhotoDAO extends DAO {

	// Retourne un tableau d'images ou null
   public function getPhotos()
		//retourne un tableau d'image
	{
		$i=0;
		$res = $this->queryAll('select * from Photo');
		if($res == false)
			$photos=array();
		else
		{
			foreach($res as $p)
			{
				$photos[$i]= new Photo($p['photoId'], $p['nomFich']);
				$i++;
				
			}
			return $photos;
		}
		return null;
	}


	// Retourne un tableau d'images ou null
   public function getPhoto($id)
		//retourne un tableau d'image
	{
		$i=0;
		$res = $this->queryAll('select * from Photo where photoId=?', array ($id));
		if($res ===false)
		{
			$photo=null;
		
		}
		else{
			$photo=new Photo($res[0]['photoId'], $res[0]['nomFich']);
			
		}
		return $photo;
	}

	// Ajoute la photo en base et retourne son ID
	public function ajouterPhoto($nomFich) {

        // Récupération d'un identifiant libre
        $res = $this -> queryRow('SELECT MAX(photoId) FROM Photo');
        $photoId = $res['MAX(photoId)'] + 1;

        // Ajoute la photo
        $this -> queryBdd("INSERT INTO Photo(photoId, nomFich) VALUES (?, ?)", array($photoId, $nomFich));
        return $photoId;

    }
	
	public function supprimerPhoto($id) {
		return $this -> _requete("DELETE FROM Photo WHERE photoId = ?", array($id));
	}

	public function modifierPhoto($nomFich)
	{
        // Modifier la photo
        $this -> queryBdd("UPDATE from Photo set nomFich=?", array($nomFich));

	}
	
	
}
?>