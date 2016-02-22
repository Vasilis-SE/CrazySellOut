<?php

	require('Products.php');

	if(!empty($_POST)){
		
		$jsonObj = json_decode(stripslashes($_POST['favoriteProdJSON']));
		
		$username = md5($jsonObj->username);
		
		$productObj = new Product("", "", "", "", "", "", 
			"", "", "", $username);

		$response = $productObj->GetFavoritesFromDatabase();	
		die(json_encode($response));

	}


?>