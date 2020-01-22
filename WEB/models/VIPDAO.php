<?php
require_once(PATH_MODELS.'DAO.php');
require_once(PATH_ENTITY.'VIP.php');
require_once(PATH_MODELS .'PhotoDAO.php');
class VipDAO extends DAO {
	

	// Retourne un tableau d'images ou null
   public function getVIP()
		//retourne un tableau d'image
	{
		$i=0;
		$res = $this->queryAll('select * from VIP');
		if($res == false)
			$photos=array();
		else
		{
			foreach($res as $v)
			{
				$vips[$i]= new VIP($v['VIPID'],$v['VIPRENOM'],$v['VIPNOM'],$v['VIPAD'],$v['VIPNAT'],$v['CLASSATP'],$v['NBTOURNOIS'],$v['PRISECHARGE'],$v['COMPAGNON']);
				$i++;
				
			}
			return $vips;
		}
		return null;
	}
	
	public function getById($VIPID)
	
	{
		$res= $this->queryRow('SELECT * FROM VIP WHERE VIPID=?', array($VIPID));
		if($res ===false)
		{
			$vip=null;
		
		}
		else{
			$vip=new VIP($res['VIPID'],$res['VIPRENOM'],$res['VIPNOM'],$res['VIPAD'],$res['VIPNAT'],$res['CLASSATP'],$res['NBTOURNOIS'],$res['PRISECHARGE'],$res['COMPAGNON']);
			
		}
		return $vip;
	}

	public function ajouterVIP($VIPRENOM,$VIPNOM,$VIPAD,$VIPNAT,$CLASSATP,$NBTOURNOIS,$PRISECHARGE,$COMPAGNON, $nomFich) {
		
		$photoDAO = new PhotoDAO();
		// Ajout d'un vip dans la base
		$photoID = $photoDAO -> ajouterPhoto($nomFich);

		// Récupération d'un identifiant libre
        $res = $this -> queryRow('SELECT MAX(VIPID) FROM VIP');
		$VIPID = $res['MAX(VIPID)'] + 1;
		
		// Ajout du VIP en base
		$this -> _requete("INSERT INTO VIP (VIPID, VIPRENOM,VIPNOM,VIPAD,VIPNAT,CLASSATP,NBTOURNOIS,PRISECHARGE,COMPAGNON) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)", array($VIPID,$VIPRENOM,$VIPNOM,$VIPAD,$VIPNAT,$CLASSATP,$NBTOURNOIS,$PRISECHARGE,$COMPAGNON));
		return $VIPID;
	}


	// Retourne l'identifiant du vip si elle se trouve en base
    public function existe($prenom, $nom) 
    {
        $res = $this -> queryRow('SELECT * FROM VIP WHERE VIPNOM = ? AND VIPRENOM = ?', array($prenom, $nom));
        if ($res) {
            return $res['VIPID'];
        }
        return false;
    }
	
	public function supprimerVIP($id) 
	{
			// Suppression d'un vip dans la base
			$req = $this->_requete('DELETE FROM VIP WHERE VIPID='.$id);
		
	}

	public function modifierVIP($VIPRENOM,$VIPNOM,$VIPAD,$VIPNAT,$CLASSATP,$NBTOURNOIS,$PRISECHARGE,$COMPAGNON, $nomFich)
	{
		$photoDAO = new PhotoDAO();
		$photoID = $photoDAO -> modifierPhoto($nomFich);

		$VIPID = $_GET['id'];


		// mettre à jour un vip
        $res = $this -> _requete('UPDATE VIP 
 									set VIPRENOM =?,VIPNOM=?,VIPAD=?,VIPNAT=?,CLASSATP=?,NBTOURNOIS=?,PRISECHARGE=?, COMPAGNON=? WHERE VIPID=?', array($VIPRENOM,$VIPNOM,$VIPAD,$VIPNAT,$CLASSATP,$NBTOURNOIS,$PRISECHARGE,$COMPAGNON,$VIPID));

	}


}

?>