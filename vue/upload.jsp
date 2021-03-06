<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"
import="java.util.List"
import="model.*"
import="ejb.*"    
    
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
<title>FreeArt</title>
</head>
<body>

       <form action="UploadServlet" method="post" enctype="multipart/form-data">
            <fieldset>
                <legend>Envoi de fichier</legend>
                <select name="categorie">
                	<c:forEach var="categorie" items="${ListeCategorie}">
                		<option value =\"<c:out value="${categorie.id}"/>\"><c:out value="${categorie.nom}"/></option>
                	</c:forEach>
                </select>
                <br/>
                
                <label for="description">Nom du fichier</label>
                <input type="text" id="description" name="nom" value="" />
                <span class="succes"><c:out value="${nom}" /></span>
                <br />
                <label for="fichier">Emplacement du fichier <span class="requis"></span></label>
                <input type="file" id="fichier" name="fichier" />
                <span class="succes"><c:out value="${fichier}" /></span>
                <br />
                <input type="submit" value="Envoyer" class="sansLabel" />
                <br />                
            </fieldset>
        </form>

    </body>
</html>