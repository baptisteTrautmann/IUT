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
<title>FreeArt</title>
</head>
<body>
	<%
		FacadeImage facadeImage = new FacadeImage();
		List<Image> imgList = facadeImage.findAll();

		if (imgList != null) 
		{
			out.print("<ul>");
			for(Image img : imgList)
			{
				out.print("<li><img src=\"" + img.getSource() + "\" alt=\"" + img.getNom() + "\"</li>");
			}
			out.print("</ul>");
		}
		else 
		{
			out.print("<p>Il n'existe aucune image.</p>");
		}
	%>
</body>
</html>