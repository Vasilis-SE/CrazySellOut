<?php

	require('Products.php');

	if(!empty($_POST)){
		
		$jsonObj = json_decode(stripslashes($_POST['selectedFavJSON']));
		
		$username = md5($jsonObj->username);
		$productName = $jsonObj->productName;
		$storeName = $jsonObj->storeName;
		$price = $jsonObj->price;
		$expireDate = $jsonObj->expireDate;

		$productObj = new Product($productName, $storeName, "", $price, "", $expireDate, 
			"", "", "", $username);

		$response = $productObj->DeleteFavoriteFromDatabase();	
		die(json_encode($response));

	}


?>