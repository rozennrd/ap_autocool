<?php

require_once '../Dao/Param.php';
require_once '../Dao/DBConnex.php';
require_once '../Dao/VoitureDAO.php';

//$_POST['idStation'] =3;
//$_POST['idType']=3;
//$_POST['libelle']="La peugot 306";
//$_POST['km']=280000;
//$_POST['niveauEssence']=50;
//$_POST['nbPlace']=2;
//$_POST['auto_on']=0;
print(json_encode(VoitureDAO::ajouterVoiture($_POST['idStation'], $_POST['idType'], $_POST['libelle'], $_POST['km'], $_POST['niveauEssence'], $_POST['nbPlace'], $_POST['auto_on'])));
 ?>
