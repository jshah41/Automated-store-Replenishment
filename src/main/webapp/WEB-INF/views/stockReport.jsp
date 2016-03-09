<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Stock Available in Stores</title>
</head>
<body>
	<div id='ReplenishTable'>
		<table border='1'>
		<tr>
		<th>Product id</th>
		<th>Quantity in Stock</th>
		<th>Store</th>
		</tr>
			<c:forEach var="product" items="${productCatalog}">
			 <tr>
			 <td class = "catalogName">${product.getPID()}</td>
			 <td>${product.getQuant()}</td>
			 <td>${product.getSID()}</td>
			 </tr>
			</c:forEach>
		</table>
		</div>
</body>
</html>