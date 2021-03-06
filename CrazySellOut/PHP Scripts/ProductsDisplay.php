<?php

	require('Products.php');

	if(!empty($_POST)){
		
		$jsonObj = json_decode(stripslashes($_POST['displayProdJSON']));
		$productObj = new Product("", "", "", "", "", "", "", "", "","");

		if($jsonObj->sortcategory == 'All'){
			$result = $productObj->RetrieveAllProductsFromDatabase();
			die(json_encode($result));
		}
		else if($jsonObj->sortcategory == 'Product Name Sort'){
			$result = $productObj->RetrieveProductsSortedByProductName($jsonObj->sortingText);
			die(json_encode($result));			
		}
		else if($jsonObj->sortcategory == 'Product Category Sort'){
			$result = $productObj->RetrieveProductsSortedByProductCategory($jsonObj->sortingText);
			die(json_encode($result));
		}
		else if($jsonObj->sortcategory == 'Store Name Sort'){
			$result = $productObj->RetrieveProductsSortedByStoreName($jsonObj->sortingText);
			die(json_encode($result));
		}
		else if($jsonObj->sortcategory == 'City Sort'){
			$result = $productObj->RetrieveProductsSortedByCity($jsonObj->sortingText);
			die(json_encode($result));
		}

		$response["status"] = false;
		$response["message"] = "Invalid selection! Cannot sort the products with this method.";
		die(json_encode($response));
			
	}









?>