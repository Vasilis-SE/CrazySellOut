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
		
		//Method that creates a json object with all the product to be displayed in the app.
		public function RetrieveAllProductsFromDatabase(){
			
			$query = "SELECT * FROM products";
			$result = mysql_query($query);
			
			$response["status"] = true;
			
			if($result === false){
				$response["status"] = false;
				$response["message"] = "Something went wrong while trying to retrieve the data from database!
					Please try again later, or contact with the support stuff.";
				return $response;
			}

			if(mysql_num_rows($result) == 0){
				$response["status"] = false;
				$response["message"] = "No results found with the selected sorting method!";
				return $response;
			}
			
			$rows = array();
			while($r = mysql_fetch_assoc($result)) {
				$rows[] = $r;
			}
			$response["message"] = $rows;
			
			return $response;
		}
		
		//Method that creates a json object with the products that have the same product name as the user typed.
		public function RetrieveProductsSortedByProductName($prodName){
			
			$query = "SELECT * FROM products WHERE productName='".$prodName."'";
			$result = mysql_query($query);
			
			$response["status"] = true;
			
			if($result === false){
				$response["status"] = false;
				$response["message"] = "Something went wrong while trying to retrieve the data from database!
					Please try again later, or contact with the support stuff.";
				return $response;
			}

			if(mysql_num_rows($result) == 0){
				$response["status"] = false;
				$response["message"] = "No results found with the selected sorting method!";
				return $response;
			}
			
			$rows = array();
			while($r = mysql_fetch_assoc($result)) {
				$rows[] = $r;
			}
			$response["message"] = $rows;
			
			return $response;
		}
		
		//Method that creates a json object with the products that have the same product category as the user typed.
		public function RetrieveProductsSortedByProductCategory($category){
			
			$query = "SELECT * FROM products WHERE category='".$category."'";
			$result = mysql_query($query);
			
			$response["status"] = true;
			
			if($result === false){
				$response["status"] = false;
				$response["message"] = "Something went wrong while trying to retrieve the data from database!
					Please try again later, or contact with the support stuff.";
				return $response;
			}

			if(mysql_num_rows($result) == 0){
				$response["status"] = false;
				$response["message"] = "No results found with the selected sorting method!";
				return $response;
			}
			
			$rows = array();
			while($r = mysql_fetch_assoc($result)) {
				$rows[] = $r;
			}
			$response["message"] = $rows;
			
			return $response;
			
		}
		
		//Method that creates a json object with the products that have the same store name as the user typed.
		public function RetrieveProductsSortedByStoreName($stName){
			
			$query = "SELECT * FROM products WHERE storeName='".$stName."'";
			$result = mysql_query($query);
			
			$response["status"] = true;
			
			if($result === false){
				$response["status"] = false;
				$response["message"] = "Something went wrong while trying to retrieve the data from database!
					Please try again later, or contact with the support stuff.";
				return $response;
			}

			if(mysql_num_rows($result) == 0){
				$response["status"] = false;
				$response["message"] = "No results found with the selected sorting method!";
				return $response;
			}
			
			$rows = array();
			while($r = mysql_fetch_assoc($result)) {
				$rows[] = $r;
			}
			$response["message"] = $rows;
			
			return $response;
			
		}
		
		//Method that creates a json object with the products that have the same city as the user typed.
		public function RetrieveProductsSortedByCity($city){
			
			$query = "SELECT * FROM products WHERE city='".$city."'";
			$result = mysql_query($query);
			
			$response["status"] = true;
			
			if($result === false){
				$response["status"] = false;
				$response["message"] = "Something went wrong while trying to retrieve the data from database!
					Please try again later, or contact with the support stuff.";
				return $response;
			}

			if(mysql_num_rows($result) == 0){
				$response["status"] = false;
				$response["message"] = "No results found with the selected sorting method!";
				return $response;
			}
			
			$rows = array();
			while($r = mysql_fetch_assoc($result)) {
				$rows[] = $r;
			}
			$response["message"] = $rows;
			
			return $response;
		}
		
		//Method that retrieves all the products that the specific producer has submitted.
		public function RetrieveAllProductFromSpecificProducer($username){
			
			$query = "SELECT * FROM products WHERE username='".$username."'";
			$result = mysql_query($query);
			
			$response["status"] = true;
			
			if($result === false){
				$response["status"] = false;
				$response["message"] = "Something went wrong while trying to retrieve the data from database!
					Please try again later, or contact with the support stuff.";
				return $response;
			}

			if(mysql_num_rows($result) == 0){
				$response["status"] = false;
				$response["message"] = "There are no product submitted by your account!";
				return $response;
			}
			
			$rows = array();
			while($r = mysql_fetch_assoc($result)) {
				$rows[] = $r;
			}
			$response["message"] = $rows;
			
			return $response;
		}
		
		//Method that handles the update of products into the database.
		public function UpdateProduct($id){
		
			$query = "UPDATE products SET productName='".$productName."', storeName='".$storeName."', 
				category='".$category."', price='".$price."', description='".$description."', 
				expireDate='".$expireDate."', longitude='".$longitude."', latitude='".$latitude."', city='".$city."' 
				WHERE id='".$id."' AND username='".$username."' ";
			$result = mysql_query($query);
			
			if($result === false){
				$response["status"] = false;
				$response["message"] = "Something went wrong while trying to update the data to database!
					Please try again later, or contact the support stuff.";
				return $response;
			}
			
			$response["status"] = true;
			$response["message"] = "Data have been updated successfully!";
			return $response;
			
		}
		
		//Method that handles the deletion of a product.
		public function DeleteProduct($id){
			
			$query = "DELETE FROM products WHERE id='".$id."' AND username='".$username."' 
				AND productName='".$productName."' AND storeName='".$storeName."' AND category='".$category."'";
			$result = mysql_query($query);
			
			if($result === false){
				$response["status"] = false;
				$response["message"] = "Something went wrong while trying to delete the product from database!
					Please try again later, or contact the support stuff.";
				return $response;
			}
			
			$response["status"] = true;
			$response["message"] = "Data have been deleted successfully!";
			return $response;
			
		}
		
		//Method that adds the product into the favorites table in database.
		public function AddProductToFavorites(){
			
			$query = "SELECT * FROM products WHERE productName='$this->productName' ";
			$product = mysql_query($query);
				
			if(mysql_num_rows($product) == 0){
				$response["status"] = false;
				$response["message"] = "Error while adding product to favorites! Please try again later.";
				return $response;
			}

			if($this->FavoriteProductAlreadyExistsCheck() == false){
				$response["status"] = false;
				$response["message"] = "You have already added this product into your favorite list!";
				return $response;
			}
			
			if(mysql_num_rows($product) > 1)
				$product = mysql_fetch_array($product[0]);
			else
				$product = mysql_fetch_array($product);

			$query = "INSERT INTO favorites (username, productName, storeName, category, 
				price, description, expireDate, longitude, latitude, city)
				VALUES ('".$this->username."', '".$product['productName']."', '".$product['storeName']."', 
				'".$product['category']."', '".$product['price']."', '".$product['description']."', '".$product['expireDate']."', 
				'".$product['longitude']."', '".$product['latitude']."', '".$product['city']."')";
			$result2 = mysql_query($query);	
			
			if($result2 === false){
				$response["status"] = false;
				$response["message"] = "Something went wrong while adding the product into the database! Please try again later or contact the support team.";
				return $response;
			}
			
			$response["status"] = true;
			$response["message"] = "Product has been added to favorites!";
			return $response;
		}
		
		//Method that checks if the product to be added into the favorites has already been
		//added in the past.
		public function FavoriteProductAlreadyExistsCheck(){
			
			$query = "SELECT * FROM favorites WHERE productName='$this->productName' AND username='$this->username' ";
			$result = mysql_query($query);	
			
			if(mysql_num_rows($result) > 0)
				return false;
			else
				return true;	
		}
		
		//Method that draws all the favorite products from the database to be displayed to user.
		public function GetFavoritesFromDatabase(){
		
			$query = "SELECT * FROM favorites WHERE username='$this->username' ";
			$result = mysql_query($result);
			
			if($result === false){
				$response["status"] = false;
				$response["message"] = "Something went wrong while trying to retrieve favorites from database!";
				return $response;
			}
			
			if(mysql_num_rows($result) == 0){
				$response["status"] = false;
				$response["message"] = "You don't have any favorite product!";
				return $response;
			}
			
			$response["status"] = true;
			
			$rows = array();
			while($r = mysql_fetch_assoc($result)) {
				$rows[] = $r;
			}
			$response["message"] = $rows;
			
			return $response;
		}

		
		
	}

?>