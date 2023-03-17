<?php



class CategorieDAO{
	public static function getCategorie(){
		try{
			$sql = "select * from catvehicule";
			$requetePrepa = DBConnex::getInstance()->prepare($sql);
			$requetePrepa->execute();
			$reponse = $requetePrepa->fetchAll(PDO::FETCH_ASSOC);
		}catch(Exception $e){
			$reponse = "";
		}
		return $reponse;
	}


}


 ?>
