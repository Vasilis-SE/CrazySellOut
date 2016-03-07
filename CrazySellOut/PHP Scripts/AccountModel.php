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

		//Method that checks if the login account given exists.
		public function LoginAccountExists(){

			$query = "SELECT * FROM users WHERE (username='".$this->username."' AND password='".$this->password."')";
			$result = mysql_query($query);
			$row = mysql_fetch_array($result);

			$arrayResult = array(mysql_num_rows($result), $row['type']);
			
			return $arrayResult;
		}

		//Method that retrieves all the data from a specific user.
		public function RetrieveUserInfo(){
		
			$query = "SELECT * FROM users WHERE username='".$this->username."'";
			$result = mysql_query($query);
			
			if($result === false){
				$response["status"] = false;
				$response["message"] = "An error occurred while retrieving the data! ".
					"Please try again later or contact the support team.";
				return $response;
			}
			
			$account = mysql_fetch_array($result);
			$response["username"] = "********";
			$response["password"] = "********";
			$response["number"] = $account['number'];
			$response["email"] = $account['email'];
			$response["sex"] = $account['sex'];
			$response["status"] = true;
			
			return $response;
		}

		//Method that updates the account of a user.
		public function UpdateUserAccount($passwordChange){
					
			if($passwordChange == true) {
				$query = "UPDATE users SET password='".$this->password."', number='".$this->number."', 
					email='".$this->email."', sex='".$this->sex."' WHERE username='".$this->username."'";
			}
			else {
				$query = "UPDATE users SET number='".$this->number."', email='".$this->email."', sex='".$this->sex."' 
					WHERE username='".$this->username."'";
			}
			$result = mysql_query($query);
		
			if($result === false){
				$response["status"] = false;
				$response["message"] = "Something went wrong while trying to update the data to database!
					Please try again later, or contact the support stuff.";
				return $response;
			}
			
			$response["status"] = true;
			$response["message"] = "Account has been updated successfully!";
			return $response;
			
		}
	
	}




?>