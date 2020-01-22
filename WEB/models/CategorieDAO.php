<?php
require_once(PATH_MODELS.'DAO.php');
require_once(PATH_ENTITY.'Categorie.php');
//retourne un tableau de catégories ou null

class CategorieDAO extends DAO {
    public function getCategories() {
        $res = $this -> queryAll('SELECT * FROM categorie');
       
        if ($res) {
            foreach ($res as $ligne) {
                $categorie[] = new Categorie($ligne['catId'], $ligne['nomCat']);
                
            }
            return $categorie;
        }
        return null;
    }
	
	public function getCatById($catid) 
	{
		$res = $this->queryRow('SELECT * FROM Categorie WHERE catId =?', array($catid));
		if($res ===false)
		{
			$categories=null;
		}
		else{
			$categories= new Categorie($res['catId'], $res['nomCat']);
		
		}
		
		return $categories;
		
	}
		
}
?>