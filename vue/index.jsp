<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
<title>FreeArt</title>
</head>
<body>
	<h2>Page d'accueil</h2>
	<p>Pour déposer une image:</p>
	<form method="POST" action="accueil">
		<input type="hidden" name="action" value="upload"/>
		<input type="submit" name="boutonUpload" value="Déposer une image"/>
	</form><br/>
	
	<p>Pour afficher toutes les images:</p>
	<form method="POST" action="accueil">
		<input type="hidden" name="action" value="tout"/>
		<input type="submit" name="boutonTout" value="Tout afficher"/>
	</form><br/>
	
	<p>Pour rechercher des images par catégorie ou par nom:</p>
	<form method="POST" action="search">
		<input type="text" name="recherche" placeholder="Recherche..."/>
		<input type="radio" name="type" value="image" checked>Image
		<input type="radio" name="type" value="categorie">Catégorie
		<input type="submit" name="boutonParCategorie" value="Recherche"/>
	</form>
</body>
</html>