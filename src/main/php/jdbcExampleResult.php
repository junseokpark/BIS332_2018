<?php

$geneid = htmlspecialchars($_POST["geneid"]);

//Call RestfulAPI
$ch = curl_init();
curl_setopt($ch, CURLOPT_URL,"http://chrome.kaist.ac.kr:8199/restjdbcexample");
curl_setopt($ch, CURLOPT_POST, 1);// set post data to true
curl_setopt($ch, CURLOPT_POSTFIELDS,"geneid=".$geneid);   // post data
curl_setopt($ch, CURLOPT_RETURNTRANSFER, true);
$json = curl_exec($ch);
curl_close ($ch);

$result = json_decode($json);
?>
<html>
<title>bis332 first example</title>
<body>
Your input geneid : <?php echo $geneid; ?> <br>
<table>
    <tr><td>geneid</td><td>symbol</td><td>synonyms</td></tr>
    <?php foreach($result as $value) {
        echo "<tr>";
        echo "<td>".$value->geneid."</td>";
        echo "<td>".$value->symbol."</td>";
        echo "<td>".$value->synonyms."</td>";
        echo "</tr>";
    }
    ?>
</table>

</body>
</html>