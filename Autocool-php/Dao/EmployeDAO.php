<?php
class EmployeDAO{
	public static function authentification($login , $mdp){
		try{
			$sql = "select id, employe.login , statut from employe 
			where employe.login=:username and employe.password=MD5(:mdp)" ;
			$requetePrepa = DBConnex::getInstance()->prepare($sql);
			
			$requetePrepa->bindParam(":username", $login);
			$requetePrepa->bindParam(":mdp", $mdp);
			$requetePrepa->execute();
			
			$reponse = $requetePrepa->fetch(PDO::FETCH_ASSOC);
			
		}catch(Exception $e){
			var_dump($e);
			$reponse = "";
		}
		return $reponse;
	}
}