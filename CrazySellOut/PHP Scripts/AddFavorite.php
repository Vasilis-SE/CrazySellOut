<?php

	require('Products.php');

	if(!empty($_POST)){
		
		$jsonObj = json_decode(stripslashes($_POST['favoritesJSON']));
		
		$productName = $jsonObj->productName;
		$username = md5($jsonObj->username);
		
		$productObj = new Product($productName, "", "", "", "", "", 
			"", "", "", $username);

		$response = $productObj->AddProductToFavorites();	
		die(json_encode($response));

	}

?>