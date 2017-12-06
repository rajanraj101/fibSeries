<%@page import="me.xdrop.fuzzywuzzy.FuzzySearch"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>ratio</title>
</head>
<body> 
<%= FuzzySearch.ratio("this is","this to know") %>
<%= FuzzySearch.ratio("hy","what") %>
<%= FuzzySearch.ratio("know","i know you ") %>
<%= FuzzySearch.ratio("   a  ","apple") %>
<%= FuzzySearch.ratio("t","ttttttt") %>
<%= FuzzySearch.ratio("t","ttttttt") %>

</body>
</html>