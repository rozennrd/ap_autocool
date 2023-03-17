<?php
/**
* Authentification habilité : 
*   login : SavillePatenaude@teleworm.us 
*   mdp : aeYiey0oh
*/

require_once '../Param.php';
require_once '../Dao/DBConnex.php';
require_once '../Dao/EmployeDAO.php';


print(json_encode(EmployeDAO::authentification($_POST['login'], $_POST['mdp'])));
