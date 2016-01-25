<?php

	require('AccountModel.php');

	if(!empty($_POST)){
		
		$jsonObj = json_decode(stripslashes($_POST['newAccountJSON']));

		$username = md5($jsonObj->username);
		$password = md5($jsonObj->password);
		$number = $jsonObj->number;
		$emailAddress = $jsonObj->emailAddress;
		$sex = $jsonObj->sex;
		$accountType = $jsonObj->accountType;

		$accountObj = new Account($username, $password, $number, $emailAddress, $sex, $accountType);
		
		//Account exists check.
		if($accountObj->NewAccountAlreadyExists()){

			//Insert account.
			if($accountObj->InsertNewAccountToDatabase()){
				
				$response["success"] = 1;
				$response["message"] = "Account successfully created!";
				die(json_encode($response));
			}
			else{
				$response["success"] = 0;
				$response["message"] = "Error occurred! Something went wrong while inserting the new account! Please try again later, or contact the support team.";
				die(json_encode($response));
			}
		}
		else{
			$response["success"] = 0;
			$response["message"] = "The account already exists! Please try another combination.";
			die(json_encode($response));
		}
	
	}
?>