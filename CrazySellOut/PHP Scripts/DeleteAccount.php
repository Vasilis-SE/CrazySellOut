<?php

	require('AccountModel.php');

	if(!empty($_POST)){
		
		$jsonObj = json_decode(stripslashes($_POST['accountJSON']));
		$username = md5($jsonObj->username);
		$accountObj = new Account($username, "", "", "", "", "");

		$response = $accountObj->DeleteUsersAccount();	
		die(json_encode($response));

	}

?>