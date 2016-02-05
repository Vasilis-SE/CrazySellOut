<?php
	require_once 'conf.inc.php';

	class Product {
		
		private $productName;
		private $storeName;
		private $category;
		private $price;
		private $description;
		private $expireDate;
		private $longitude;
		private $latitude;
		private $city;
		private $username;
		
		//Constructor
		public function __construct($pName, $sName, $cat, $price, $desc, 
			$date, $lon, $lat, $city, $usname){
			
			$this->productName = $pName;
			$this->storeName = $sName;
			$this->category = $cat;
			$this->price = $price;
			$this->description = $desc;
			$this->expireDate = $date;
			$this->longitude = $lon;
			$this->latitude = $lat;
			$this->city = $city;
			$this->username = $usname;
		}	
		
		//Method that checks if the product to be added is already inserted by the
		//same producer (username, product name and store name must be the same).
		public function ProductAlreadyInsertedByProducer(){

			$response = true;
			
			$query = "SELECT * FROM products WHERE (username='".$this->username."' && productName='".$this->productName."' && storeName='".$this->storeName."')";
			$result = mysql_query($query);
			
			if(mysql_num_rows($result) >= 1){
				$response = false;
			}
			
			return $response;
		}

		//Method that inserts the new product into the products table of  the database.
		public function AddProductToDatabase(){
			
			$response = true;
			$query = "INSERT INTO products (username, productName, storeName, category, 
				price, description, expireDate, longitude, latitude, city) 
				VALUES ('$this->username', '$this->productName', '$this->storeName', 
				'$this->category', '$this->price', '$this->description', '$this->expireDate', 
				'$this->longitude', '$this->latitude', '$this->city')";
			$result = mysql_query($query);	
			
			if($result === false){
				$response = false;
			}

			return $response;	
		}
		
		
		
	}

?>