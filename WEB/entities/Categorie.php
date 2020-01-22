<?php
class Categorie {
    private $_catid;
    private $_nomcat;
    
	
    public function __construct($catid, $nomcat) {
        $this -> _catid = $catid;
        $this -> _nomcat = $nomcat;
    }
	
    
    public function setCatId($catid) {
        $this -> _catid = $catid;
    }
    
    public function getCatId() {
        return $this -> _catid;
    }
	
	public function setNomCat($nomcat) {
        $this -> _nomcat = $nomcat;
    }
    
    public function getNomCat() {
        return $this -> _nomcat;
    }
}
?>