<?php

	require('AccountModel.php');

	if(!empty($_POST)){
		
		$jsonObj = json_decode(stripslashes($_POST['accountJSON']));
		$username = md5($jsonObj->username);
		$type = $jsonObj->type;
		$accountObj = new Account($username, "", "", "", "", "");

		$response = $accountObj->DeleteUsersAccount($type);	
		die(json_encode($response));

	}

?>