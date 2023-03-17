<?php

require_once '../Dao/Param.php';
require_once '../Dao/DBConnex.php';
require_once '../Dao/VoitureDAO.php';

//$_POST['idVoiture'] = '1';
print(json_encode(VoitureDAO::getVoiture($_POST['idVoiture'])));
 ?>
