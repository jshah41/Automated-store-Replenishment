<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<html>
<head>
	<title>Home</title>
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
<br/><br/><br/>

Replenishment request is posted for ${currentStore }!!!
<a href="replenish"><input type='submit' value='OK' /></a>

</body>
</html>
