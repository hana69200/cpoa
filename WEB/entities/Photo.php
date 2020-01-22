<?php
class Photo
{
	private $_photoId;
	private $_nomFich;
	
	public function __construct ($photoId,$nomFich)
	{
		//referenc à l'instance courante donc sans le $
		$this->_photoId = $photoId;
		$this->_nomFich = $nomFich;
	}
	
	public function getPhotoId()
    {
	   return $this->_photoId;
    }
  
    public function getNomFich()
	{
		return $this->_nomFich;
	}
	  
	public function setPhotoId($photoId) 
	{
	    $this -> _photoId = $photoId;
	}

    public function setNomPhoto($nomFich) 
    {
        $this -> _nomFich = $nFichmPhoto;
    }
  
}



?>