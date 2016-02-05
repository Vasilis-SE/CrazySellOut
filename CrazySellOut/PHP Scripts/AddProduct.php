<?php
	
	require('Products.php');

	if(!empty($_POST)){
		
		$jsonObj = json_decode(stripslashes($_POST['addProductJSON']));

		$productName = $jsonObj->productName;
		$storeName = $jsonObj->storeName;
		$category = $jsonObj->category;
		$price = $jsonObj->productPrice;
		$description = $jsonObj->description;
		$expireDate = $jsonObj->expireDate;
		$longitude = $jsonObj->longitude;
		$latitude = $jsonObj->latitude;
		$city = $jsonObj->city;
		$username = md5($jsonObj->username);
	
		$productObj = new Product($productName, $storeName, $category, $price,
			$description, $expireDate, $longitude, $latitude, $city,$username);
		
		
		if($productObj->ProductAlreadyInsertedByProducer()){

			if($productObj->AddProductToDatabase()){
				
				$response["success"] = 1;
				$response["message"] = "Product inserted to database successfully!";
				die(json_encode($response));
			}
			else{
				$response["success"] = 0;
				$response["message"] = "Error occurred! Something went wrong while inserting the new product! Please try again later, or contact the support team.";
				die(json_encode($response));
			}
		}
		else{
			$response["success"] = 0;
			$response["message"] = "You have already inserted that product!";
			die(json_encode($response));
		}
	
	}

?>