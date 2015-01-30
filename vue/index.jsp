<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"
    import="model.*"
	import="ejb.*"
	%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
<title>FreeArt</title>
</head>
<body>
	<header>
		<%
			String erreur = (String)request.getAttribute("erreur");
			if(erreur != null && erreur != "")
			{
				out.print(erreur);
			}
			request.setAttribute("erreur", null);
			
			Utilisateur u = (Utilisateur)request.getAttribute("utilisateur");
			if(u != null)
			{
				out.print("Vous etes connecté sous " + u.getLogin() + "!");	
				%>
				<form method="POST" action="connexion">
					<input type="hidden" name="action" value="deconnexion"/>
					<input type="submit" name="boutonDeco" value="Se déconnecter"/>
				</form>
				<form method="POST" action="Panier">
					<input type="hidden" name="action" value="panier"/>
					<input type="submit" name="boutonPanier" value="Mon panier"/>
				</form>
				<br/>
				<%
			}
			else
			{
		%>
				<form method="POST" action="connexion">
					<input type="hidden" name="action" value="connexion"/>
					<input type="text" name="login" placeholder="Login"/>
					<input type="password" name="password" placeholder="Mot de passe"/>
					<input type="submit" name="boutonConnec" value="Se connecter"/>
				</form><br/>
				<form method="POST" action="creationCompte">
					<input type="hidden" name="action" value="creation"/>
					<input type="submit" name="boutonCreation" value="Créer un compte"/>
				</form><br/>
		<%
			}
		%>
	</header>
	
	<h2>Page d'accueil</h2>
	<%
	if(u != null)
	{
	%>
	<p>Pour déposer une image:</p>
	<form method="POST" action="accueil">
		<input type="hidden" name="action" value="upload"/>
		<input type="submit" name="boutonUpload" value="Déposer une image"/>
	</form><br/>
	<%
	}
	%>
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