<?php

$firstNo = htmlspecialchars($_POST["firstNo"]);
$secondNo = htmlspecialchars($_POST["secondNo"]);

//Call RestfulAPI
$ch = curl_init();
curl_setopt($ch, CURLOPT_URL,"http://chrome.kaist.ac.kr:8199/restCalculatorResult");
curl_setopt($ch, CURLOPT_POST, 1);// set post data to true
curl_setopt($ch, CURLOPT_POSTFIELDS,"firstNo=".$firstNo."&secondNo=".$secondNo);   // post data
curl_setopt($ch, CURLOPT_RETURNTRANSFER, true);
$json = curl_exec($ch);
curl_close ($ch);

$result = json_decode($json);
?>
<html>
<title>bis332 first example</title>
<body>
Your input firstNo is <?php echo $firstNo; ?>. <br>
Your input secondNo is <?php echo $secondNo; ?>.<br>
Sum is <?php echo $result['sum']; ?>.<br>
</body>
</html>