<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<html>
<head>
	<title>Dashboard</title>
</head>
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
	a {
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


</style>
<body>
<ul><li> <a href="dashboard"> Inventory</a></li>

<li><a href="replenish">Replenishment</a></li>
<li><a href="utlogs">Transaction Logs</a></li>
<li><a href="wtlogs">Warehouse Logs</a></li>
<li><a href="stockInventory">Stock Report</a></li>

</ul>
<h1 class="header">
	Dashboard 
</h1>

<div> Please select a store from the list to view the inventory </div>

<form name='refreshInventory' action='showInventory' method="post">

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

<input type='submit' value='Display Inventory' />
</form>


<div id='inventoryTable'>
<table border='1'>
<tr>
<th>SKU ID</th>
<th>Product ID</th>
<th>Product Name</th>
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
<c:forEach var="skuItem" items="${inventoryList}">
 <tr>
 <td>${skuItem.skuId }</td>
 <td>${skuItem.productId }</td>
 <td>${skuItem.productName }</td>
 <td>${skuItem.packSize }</td>
 <td>${skuItem.expiryDate }</td>
 <td>${skuItem.discount }</td>
 <td>${skuItem.dateOfMf }</td>
 <td>${skuItem.mrp }</td>
 <td>${skuItem.unitPrice }</td>
 <td>${skuItem.weight }</td>
 <td>${skuItem.note }</td>
 <td>${skuItem.vendorId }</td>
 <td>${skuItem.warehouseId }</td>
 
 </tr>
</c:forEach>
</table>
</div>

</body>
</html>
