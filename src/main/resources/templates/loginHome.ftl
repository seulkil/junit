<html>

<head>
<style>
	#header
	{
		background-color:black;
		color:red;
		text-align:center;
		padding:5px;
	}
	
	#footer
	{
		background-color:black;
		color:red;
		text-align:center;
		padding:5px;
	}
	fieldset
	{
  		font-size:20px;
  		padding:10px;
 		width:250px;
 		line-height:1.8; 	
 	}
	
	form
	{
	    width: 300px;
	    margin: 0 auto;
    }
    <title> <h2> Login </h2> </title>
    
    <script src="//code.jquery.com/jquery-1.11.0.min.js"></script>    
    <script src="/js/home-control.js"></script>
	
	
</style>
</head>

<body>    
   
    <div id="header">
    	<h2> </h2>              
    </div>
    <br><br><br><br><br>
	
    <form id="login" action="codeSubmit.ftl" method="GET">    
    	<fieldset>
    	   		<legend><strong>Login</strong> </legend>
    	  		<table>
    	  			<tr>
    	  				<td> UserName: </td>
    	   				<td><input type="text" name="username"> </td>
    	   				
    	   			</tr>
    	   			<tr>
    	   				<td> Password: </td>
    	   				<td><input type="password" name="password"> </td>
    	   			</tr>
    	   			<tr>
    	   				<td></td>
    	   				<td><input type="reset" value="Reset"></td>
    	   				<td><input type="submit" value="Submit"></td>
    	   				
    	   			</tr>	
    	   		</table>
    	</fieldest>  
    </form>
      
</body>

</html>