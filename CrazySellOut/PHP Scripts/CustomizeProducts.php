<?php

	require('Products.php');

	if(!empty($_POST)){
		
		$jsonObj = json_decode(stripslashes($_POST['productJSON']));
		
		$productName = $jsonObj->productName;
		$storeName = $jsonObj->storeName;
		$category = $jsonObj->category;
		$price = $jsonObj->productPrice;
		$description = $jsonObj->description;
		$expireDate = $jsonObj->expireDate;
		$longitude = $jsonObj->longitude;
		$latitude = $jsonObj->latitude;
		$city = $jsonObj->city;
		$username = $jsonObj->username;
		$id = $jsonObj->id;
		
		$productObj = new Product($productName, $storeName, $category, $price,
			$description, $expireDate, $longitude, $latitude, $city, $username);
		
		//If the product is to be updated (customizeType = 1)
		if($jsonObj->customizeType == 1){
			$jsonResponse = $productObj->UpdateProduct($id);
			die(json_encode($jsonResponse));
		}
		//If the product is to be deleted (customizeType = 2)
		else if($jsonObj->customizeType == 2){
			$jsonResponse = $productObj->DeleteProduct($id);
			die(json_encode($jsonResponse));
		}
		

	}
	
	
	
	
	
	
	
?>

