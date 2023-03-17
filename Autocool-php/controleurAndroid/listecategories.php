<?php

require_once '../Dao/Param.php';
require_once '../Dao/DBConnex.php';
require_once '../Dao/CategorieDAO.php';

print(json_encode(CategorieDAO::getCategorie()));
 ?>
