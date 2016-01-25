<?php 
	
	require_once 'conf.inc.php';

	class Account {
		
		private $username;
		private $password;
		private $number;
		private $email;
		private $sex;
		private $type;
	
		//Constructor
		public function __construct($usname, $pass, $num, $mail, $sex, $type){
			$this->username = $usname;
			$this->password = $pass;
			$this->number = $num;
			$this->email = $mail;
			$this->sex = $sex;
			$this->type = $type;
		}	

		//Method that checks if the new account already exists on database.
		public function NewAccountAlreadyExists(){

			$response = true;
			$query = "SELECT * FROM users WHERE (username='".$this->username."' OR password='".$this->password."')";
			$result = mysql_query($query);
			
			if(mysql_num_rows($result) >= 1){
				$response = false;
			}
			
			return $response;
		}
		
		//Method that inserts the new account into the database.
		public function InsertNewAccountToDatabase(){
			
			$response = true;
			$query = "INSERT INTO users (username, password, number, email, sex, type) 
				VALUES ('$this->username', '$this->password', '$this->number', 
				'$this->email', '$this->sex', '$this->type')";
			$result = mysql_query($query);	
			
			if($result === false){
				$response = false;
			}

			return $response;
		}
	
	}




?>