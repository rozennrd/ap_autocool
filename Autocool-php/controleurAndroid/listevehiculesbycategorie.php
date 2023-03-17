<?php

require_once '../Dao/Param.php';
require_once '../Dao/DBConnex.php';
require_once '../Dao/VoitureDAO.php';

//$_POST['categ'] = 'S';
print(json_encode(VoitureDAO::getVoituresByCategorie($_POST['categorie'])));
 ?>
