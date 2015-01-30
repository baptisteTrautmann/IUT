<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"
	import="java.util.List"
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
	<h3>Création de compte:</h3>
	<%
			String erreur = (String)request.getAttribute("erreur");
			if(erreur != null && erreur != "")
			{
				out.print(erreur);
			}
			request.setAttribute("erreur", null);
	%>
			<form method="POST" action="creationCompte">
				<input type="hidden" name="action" value="creerCompte"/>
				<input type="text" name="loginCreer" placeholder="Login"/>
				<input type="password" name="passwordCreer" placeholder="Mot de passe"/>
				<input type="submit" name="boutonValid" value="Créer le compte"/>
			</form><br/>
</body>
</html>