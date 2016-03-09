<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<html>
<head>
	<title>Replenish</title>
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
	Replenish 
</h1>

<div> Please select a store:

<form name='refreshReplenish' action='showReplenish' method="post">

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

<input type='submit' value='Show Suggestions' />

</form>

</div>
<div id='ReplenishTable'>
<table border='1'>
<tr>
	
<th>WSKU ID</th>
<th>Product ID</th>
<th>Store ID</th>
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
<c:forEach var="repItem" items="${replenishList}">
 <tr>
 <td class = "wskuid">${repItem.repId }</td>
 <td>${repItem.productId }</td>
 <td>${repItem.storeId }</td>
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


<br/>
<form name="applyRepSuggestion" action="applyReplenish" method="post" id="applyRepSuggestion">
<input type="hidden" name="wskids" id="getids"/>
<input type='button' value='Approve' id="apply"/>
</form>


</div>

<script>
	$(document).ready(function(){
		
		var ids = '';
		$('#apply').click(function(){
			$('.wskuid').each(function(){
				
				ids = ids + ','+$(this).text();
			});
			//alert(ids);
			$('#getids').val(ids);
			$('#applyRepSuggestion').submit();
		});
		
	});
</script>
</body>
</html>
