<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>  
<%@ page session="false" %> 
<html>
<head>
	<title>User Transaction Logs</title>
	<style>
ul {
    
    margin: 0;
    padding: 0;
    
}

	li {
	    display: inline;
	    
	}
.header{
	text-align:center;
	}
	ul li a {
    border-radius:4px;
    width: 60px;
    background-color: #66CCFF;
    padding:2px 2px 2px 2px;
}
	ul li a:link{
	text-decoration: none;
	
	}
	ul li a:hover {
    text-decoration: underline;
}

#utogstable{
	padding-left:25%;
	padding-right:25%;
}


	</style>
	
	<script type="text/javascript" src="http://code.jquery.com/jquery-2.1.0.min.js">
		
</script>
</head>
<body>

<ul><li> <a href="dashboard"> Inventory</a></li>

<li><a href="replenish">Replenishment</a></li>
<li><a href="utlogs">Transaction Logs</a></li>
<li><a href="wtlogs">Warehouse Logs</a></li>
<li><a href="stockInventory">Stock Report</a></li>
</ul>
<h1 class="header" >
	Transaction Logs 
</h1>

<p>Please select store from the list</p>
<form name="viewutlogs" action="showutlogs" method="post">
<select name="storeList" id='storeList'>
<c:forEach var="storeId" items="${storeList}">
<c:choose>
<c:when test="${storeId == currentStore }">
<option value="${storeId }" selected="selected"> ${storeId } </option>
</c:when>
<c:otherwise>
<option value="${storeId }"> ${storeId } </option>
</c:otherwise>
</c:choose>
</c:forEach>
</select>
<input type='submit' value='Show sales' />
</form>

<div id='utogstable'>
<table border='1'>
<tr>
<th>Store ID</th>
<th>Product ID</th>
<th>No. of Items</th>
</tr>

<c:forEach var="details" items="${dtlslist}">
<tr>
<td>${details.store}</td>

<c:forEach begin="0" end="${fn:length(details.prodList) - 1}" var="index">
   
      <td><c:out value="${details.prodList[index].product}"/></td>
      <td><c:out value="${details.prodList[index].count}"/></td>
      
</c:forEach>
</tr>
</c:forEach>
</table>
</div>

</body>
</html>
