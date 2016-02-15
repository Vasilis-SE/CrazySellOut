<?php

	require('Products.php');

	if(!empty($_POST)){
		
		$jsonObj = json_decode(stripslashes($_POST['producerItemsJSON']));
		$productObj = new Product("", "", "", "", "", "", "", "", "","");

		$username = md5($jsonObj->username);
		
		$result = $productObj->RetrieveAllProductFromSpecificProducer($username);
		die(json_encode($result));	
			
	}


?>