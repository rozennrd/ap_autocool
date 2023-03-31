<?php
/**
* Authentification habilité : 
*   login : savillepatenaude@teleworm.us 
*   mdp : test
*/

require_once '../Dao/Param.php';
require_once '../Dao/DBConnex.php';
require_once '../Dao/EmployeDAO.php';


print(json_encode(EmployeDAO::authentification($_POST['login'], $_POST['mdp'])));