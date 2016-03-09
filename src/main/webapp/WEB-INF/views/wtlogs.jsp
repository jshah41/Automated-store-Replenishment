<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>  
<%@ page session="false" %> 
<html>
<head>
	<title>Warehouse Transaction Logs</title>
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

#wtlogstable{
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
	Warehouse Replenish Requests Logs 
</h1>

<p>Please select store from the list</p>
<form name="viewwtlogs" action="showwtlogs" method="post">
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
<input type='submit' value='Show Requests' />
</form>

<div id='wtlogstable'>
<table border='1'>
<tr>
<th>WSKU ID</th>
<th>Store ID</th>
<th>Product ID</th>
<th>Pack Size</th>
<th>Expiry Date</th>
<th>Discount</th>
<th>Manufacturing Date</th>
<th>MRP</th>
<th>Price</th>
<th>Weight</th>
<th>Note</th>
<th>Vendor ID</th>
<th>Warehouse ID</th>
</tr>
<c:forEach var="repItem" items="${whlist}">
 <tr>
 
 <td>${repItem.skuId }</td>
 <td>${repItem.storeId }</td>
 <td>${repItem.productId }</td>
  <td>${repItem.packSize }</td>
 <td>${repItem.expiryDate }</td>
 <td>${repItem.discount }</td>
 <td>${repItem.dateOfMf }</td>
 <td>${repItem.mrp }</td>
 <td>${repItem.unitPrice }</td>
 <td>${repItem.weight }</td>
 <td>${repItem.note }</td>
 <td>${repItem.vendorId }</td>
 <td>${repItem.warehouseId }</td>
 </tr>
</c:forEach>
</table>
</div>

</body>
</html>
