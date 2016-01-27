<?php

	require('AccountModel.php');

	if(!empty($_POST)){
		
		$jsonObj = json_decode(stripslashes($_POST['loginJSON']));
	
		$username = md5($jsonObj->username);
		$password = md5($jsonObj->password);
		
		$accountObj = new Account($username, $password, "", "", "", "");
		if($accountObj->LoginAccountExists()){
			
			$response["success"] = 1;
			$response["message"] = "Successful login!";
			die(json_encode($response));
		}
		else{
		
			$response["success"] = 0;
			$response["message"] = "The account does not exist! ";
			die(json_encode($response));
		}
	
	}

?>