<?php

require_once(PATH_MODELS . 'DAO.php');
require_once(PATH_ENTITY . 'User.php');

class CrmDAO extends DAO {
	
    // Retourne un tableau d'images ou null
   public function getCRM()
		//retourne un tableau d'image
	{
		$i=0;
		$res = $this->queryAll('select * from CRM');
		if($res == false)
			$crm=array();
		else
		{
			foreach($res as $v)
			{
				$crm[$i]= new CRM($v['crmID'],$v['Courrier'],$v['AppelTel'],$v['Fax'],$v['Action']);
				$i++;
				
			}
			return $crm;
		}
		return null;
	}
	
	public function getById($crmID)
	
	{
		$res= $this->queryRow('SELECT * FROM CRM WHERE crmID=?', array($crmID));
		if($res ===false)
		{
			$crm=null;
		
		}
		else{
			$crm=new CRM($res['crmID'],$res['Courrier'],$res['AppelTel'],$res['Fax'],$res['Action']);
			
		}
		return $crm;
	}

	public function modifierCRM($Courrier,$AppelTel,$Fax,$Action)
	{
		$CrmID = $_GET['id'];
		// mettre à jour un vip
        $res = $this -> _requete('UPDATE CRM set Courrier =?, AppelTel=?,Fax=?,Action=? WHERE CrmID=?', array($Courrier,$AppelTel,$Fax,$Action,$CrmID));

	}

}

?>
