<?php

	require('AccountModel.php');

	if(!empty($_POST)){
		
		$jsonObj = json_decode(stripslashes($_POST['accountJSON']));
		$username = md5($jsonObj->username);
		$password = md5($jsonObj->password);
		$number = $jsonObj->number;
		$email = $jsonObj->email;
		$sex = $jsonObj->sex;

		$passwordChange = $jsonObj->passwordChange;
		
		$accountObj = new Account($username, $password, $number, $email, $sex, "");
		$response = $accountObj->UpdateUserAccount($passwordChange);
		die(json_encode($response));
	
	}

?>