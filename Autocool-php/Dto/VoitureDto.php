<?php

class VoitureDto{

  use Hydrate;

  private $IDVOITURE;
  private $IDSTATION;
  private $IDTYPE;
  private $LIBELLE;
  private $KM;
  private $NIVEAUESSENCE;
  private $NBPLACE;
  private $AUTO_ON;

  public function getIDVOITURE(){
    return $this->IDVOITURE;
  }
  public function setIDVOITURE($newid){
    $this->IDVOITURE = $newid;
  }

  public function getIDSTATION(){
    return $this->IDSTATION;
  }
  public function setIDSTATION($newidsta){
    $this->IDSTATION = $newidsta;
  }

  public function getIDTYPE(){
    return $this->IDTYPE;
  }
  public function setIDTYPE($newidtyp){
    $this->IDTYPE = $newidtyp;
  }

  public function getLIBELLE(){
    return $this->LIBELLE;
  }
  public function setLIBELLE($newlib){
    $this->LIBELLE = $newlib;
  }

  public function getKM(){
    return $this->KM;
  }
  public function setKM($newkm){
    $this->KM = $newkm;
  }

  public function getNIVEAUESSENCE(){
    return $this->NIVEAUESSENCE;
  }
  public function setNIVEAUESSENCE($newnivess){
    $this->NIVEAUESSENCE = $newnivess;
  }

  public function getNBPLACE(){
    return $this->NBPLACE;
  }
  public function setNBPLACE($newnbpla){
    $this->NBPLACE = $newnbpla;
  }

  public function getAUTO_ON(){
    return $this->AUTO_ON;
  }
  public function setAUTO_ON($newautoon){
    $this->AUTO_ON = $newautoon;
  }


}






 ?>
