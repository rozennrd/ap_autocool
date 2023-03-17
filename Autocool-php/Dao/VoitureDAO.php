<?php



class VoitureDAO{
	public static function getVoituresByCategorie($lacateg){
		try{
			$sql = "select * from voiture, catvehicule, type
			where voiture.IDTYPE = type.IDTYPE
			and type.IDCAT = catvehicule.IDCAT
			and catvehicule.LIBELLECAT = :categ" ; //and type.IDCAT = :idcateg
			$requetePrepa = DBConnex::getInstance()->prepare($sql);
			$requetePrepa->bindParam(":categ", $lacateg);

			$requetePrepa->execute();
			$reponse = $requetePrepa->fetchAll(PDO::FETCH_ASSOC);
		}catch(Exception $e){
			$reponse = "";
		}
		return $reponse;
	}

	public static function getVoiture($leidvoiture){
		try{
			$sql = "select voiture.IDVOITURE, station.LIBELLESTATION, ville.LIBELLEVILLE, type.LIBELLETYPE, voiture.NBPLACE, voiture.AUTO_ON, voiture.KM, voiture.NIVEAUESSENCE
							from voiture, type, station, ville
							where type.IDTYPE = voiture.IDTYPE
							and ville.IDVILLE = station.IDVILLE
							and station.IDSTATION = voiture.IDSTATION
							and IDVOITURE = :idvoiture" ;
			$requetePrepa = DBConnex::getInstance()->prepare($sql);
			$requetePrepa->bindParam(":idvoiture", $leidvoiture);
			$requetePrepa->execute();
			$reponse = $requetePrepa->fetch(PDO::FETCH_ASSOC);
		}catch(Exception $e){
			$reponse = "";
		}
		return $reponse;
	}

	public static function ajouterVoiture($idStation, $idType, $libelle, $km, $niveauEssence, $nbPlace, $auto_on){

			$sql =  "INSERT INTO `voiture` ( IDSTATION, IDTYPE, LIBELLE, KM, NIVEAUESSENCE, NBPLACE, AUTO_ON)
				VALUES (:idStation, :idType, :libelle, :km, :niveauEssence, :nbPlace, :auto_on)";
			$requetePrepa = DBConnex::getInstance()->prepare($sql);
			$requetePrepa->bindParam(":idStation", $idStation);
			$requetePrepa->bindParam(":idType", $idType);
			$requetePrepa->bindParam(":libelle", $libelle);
			$requetePrepa->bindParam(":km", $km);
			$requetePrepa->bindParam(":niveauEssence", $niveauEssence);
			$requetePrepa->bindParam(":nbPlace", $nbPlace);
			$requetePrepa->bindParam(":auto_on", $auto_on);
			$requetePrepa->execute();



}











}




 ?>
