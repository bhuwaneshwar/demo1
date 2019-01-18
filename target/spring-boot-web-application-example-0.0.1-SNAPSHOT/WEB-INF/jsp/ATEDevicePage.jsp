<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
  <title>NBIOT Alarm PUC</title>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
  <style type="text/css">
  
  hr {
    display: block;
    height: 3px;
    border: 0;
    border-top:3px solid #ccc;
    margin: 1em 0;
    padding: 0; 
}
  
  </style>
  
</head>
<body style="background-color:#D9D9D9" >


<div class="container-fluid"  >
  
  <div class="row"  style = "background-color: #146bca" >
	  <div class="col-sm-12" >
	  	  <table class="table">
		      <tr align="center">
		        <td style = "border: none;"><img src="images/HeaderImage.jpg"  width="20" height="20"> <font color="white"> <b>  NBIOT Device List  </b> </font> </td>
		      </tr>
	      </table>
	  </div>
	</div>
  
  <div class="row" >
    <div class="col-sm-4" ></div>
   
    <div class="col-sm-4" >
    
   
	    <table class="table borderless"  style = "border: none;" >
		    
		     <c:forEach items="${ateList}" var="item">
		      
		      <tbody onclick="location.href='/NBIOT/DevicePage?sensorIMEI=${item.sensorIMEI}&ateDeviceName=${ateName}'" style="border-bottom:2pt solid #A9A9A9; !important">
			      <tr align="left" >
			        <td style = "border: none;">  <font color="green"> <b>  ${item.location}  </b> </font> </td>
			      </tr>
			      
			      <tr align="left">
			         <td style = "border: none;" >  <font color="black"> <b> ${item.sensorType}  </b> </font> </td>
			      </tr>
			      
			       <tr align="left">
			          <td style = "border: none;">  <font color="black"> <b> ${item.sensorIMEI}  </b> </font> </td>
			      </tr>
			
			    </tbody>   
			      
			    
		     </c:forEach>
	      </table>
    </div>
    
    <div class="col-sm-4" ></div>
  </div>
</div>



</body>
</html>