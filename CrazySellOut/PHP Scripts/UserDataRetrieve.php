<?php

	require('AccountModel.php');

	if(!empty($_POST)){
		
		$jsonObj = json_decode(stripslashes($_POST['userInfoJSON']));
		$username = md5($jsonObj->username);
		
		$userObj = new Account($username, "", "", "", "", "");
		
		$result = $userObj->RetrieveUserInfo();
		die(json_encode($result));			
	}


?>