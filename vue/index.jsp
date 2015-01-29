<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>FreeArt</title>
</head>
<body>
	<form method="POST" action="JeeServlet">
		<input type="hidden" name="upload" value="upload"/>
		<input type="submit" name="boutonUpload" value="Déposer une image"/>
	</form>
	
	<form method="POST" action="JeeServlet">
		<input type="hidden" name="action" value="tout"/>
		<input type="submit" name="boutonTout" value="Tout afficher"/>
	</form>
	
	<form method=POST>
		<input type=text name="rechercheCategorie" placeholder="Saisir une catégorie d'image ici"></input>
		<button name="boutonRecherche">Recherche catégorie</button>
	</form><br/>
	<!--  <form method=POST>
		<input type=text name="rechercheNom" placeholder="Saisir un nom d'image ici"></input>
		<button name="boutonRecherche">Recherche image</button>
	</form><br/>  -->
</body>
</html>