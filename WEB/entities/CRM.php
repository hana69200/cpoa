<?php
class CRM
{
	private $_crmID;
	private $_Courrier;
	private $_AppelTel;
	private $_Fax;
	private $_Action;
	
	public function __construct ($crmID,$Courrier,$AppelTel,$Fax,$Action)
	{
		//referenc à l'instance courante donc sans le $
		$this->_crmID = $crmID;
		$this->_Courrier = $Courrier;
		$this->_AppelTel = $AppelTel;
		$this->_Fax = $Fax;
		$this->_Action = $Action;
		
	}
	
	public function getCrmID()
  {
	  return $this->_crmID;
  }
  
  public function getCourrier()
  {
	  return $this->_Courrier;
  }
  
  public function getAppelTel()
  {
	  return $this->_AppelTel;
  }
  public function getFax()
  {
	  return $this->_Fax;
  }
  public function getAction()
  {
	  return $this->_Action;
  }
  
  
}



?>