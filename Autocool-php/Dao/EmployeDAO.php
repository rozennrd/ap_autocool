<?php
class EmployeDAO{
	public static function authentification($login , $mdp){
		try{
			
			$sql = "select id, login , statut from employe 
			where mail= :login and mdp = md5(:mdp) " ;
			$requetePrepa = DBConnex::getInstance()->prepare($sql);
			//$mdp =  md5($mdp);
			$requetePrepa->bindParam(":login", $login);
			$requetePrepa->bindParam(":mdp", $mdp);
			$requetePrepa->execute();
			$reponse = $requetePrepa->fetch(PDO::FETCH_ASSOC);
		}catch(Exception $e){
			$reponse = "";
		}
		return $reponse;
	}
}