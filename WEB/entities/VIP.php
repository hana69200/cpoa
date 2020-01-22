<?php
class VIP
{
	private $_VIPID;
	private $_VIPRENOM;
	private $_VIPNOM;
	private $_VIPAD;
	private $_VIPNAT;
	private $_CLASSATP;
	private $_NBTOURNOIS;
	private $_PRISECHARGE;
	private $_COMPAGNON;
	
	public function __construct ($VIPID,$VIPRENOM,$VIPNOM,$VIPAD,$VIPNAT,$CLASSATP,$NBTOURNOIS,$PRISECHARGE,$COMPAGNON)
	{
		//referenc à l'instance courante donc sans le $
		$this->_VIPID = $VIPID;
		$this->_VIPRENOM = $VIPRENOM;
		$this->_VIPNOM = $VIPNOM;
		$this->_VIPAD = $VIPAD;
		$this->_VIPNAT = $VIPNAT;
		$this->_CLASSATP = $CLASSATP;
		$this->_NBTOURNOIS = $NBTOURNOIS;
		$this->_PRISECHARGE = $PRISECHARGE;
		$this->_COMPAGNON = $COMPAGNON;
		
	}
	
	public function getVIPID()
  {
	  return $this->_VIPID;
  }
  
  public function getVIPRENOM()
  {
	  return $this->_VIPRENOM;
  }
  
  public function getVIPNOM()
  {
	  return $this->_VIPNOM;
  }
  public function getVIPAD()
  {
	  return $this->_VIPAD;
  }
  public function getVIPNAT()
  {
	  return $this->_VIPNAT;
  }
  public function getCLASSATP()
  {
	  return $this->_CLASSATP;
  }
  public function getNBTOURNOIS()
  {
	  return $this->_NBTOURNOIS;
  }
  public function getPRISECHARGE()
  {
	  return $this->_PRISECHARGE;
  }
  public function getCOMPAGNON()
  {
	  return $this->_COMPAGNON;
  }
  
  
}



?>