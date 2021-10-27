<?php
	//Variables de conexion
	$dbServerName = "localhost";
	$dbUserName= "user_wordpress";
	$dbPassword= "pass_wordpress";
	$dbName= "wordpress";
	
	//Creacion de Conexion
	//$conn = mysql_connect($dbServerName,$dbUserName,$dbPassword,$dbName);
	$conn= new mysqli($dbServerName,$dbUserName,$dbPassword,$dbName);
	
	//Creacion de Conexion
/*	Forma1
	if (!$conn) {
		die("Connection failed: " . mysqli_connect_error());
	}
*/	

/*	Forma2	*/
	if ($conn->connect_error) {
		die("Conexion Fallida:" . $conexion->connect_error);
	} else {
		echo "Conexion Establecida con bdd";
		
		/*
		Deberá de incluir una sección que muestre información relativa al usuario.
			Nombre y apellidos
			DNI
			Nombre de usuario
			Última conexión
		*/
		
		$query ="SELECT e.vchNombre, e.vchApellido1, e.vchApellido2, e.vchDni, w.user_login, u.dateUltimaConexion  
		FROM sdfsfwkefbjweifb_users w INNER JOIN usuarios u ON w.ID = ;";
		
		$result= $conn->query($userL);

		
		if ($result->num_rows > 0)
			while
				echo "Nombre:". 
		
	}
	
	/* cerrar conexión */
	mysqli_close($conn);
	https://www.gpsos.es/2019/02/mysqli_multi_query-php-mysql/
	https://www.youtube.com/watch?v=ToEeaQlFPXY
?>