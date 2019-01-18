<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
  <title>NBIOT Alarm PUC</title>
  <meta charset="utf-8">
  <meta http-equiv="refresh" content="10;url=/NBIOT/DevicePage?sensorIMEI=${sensorIMEI}&ateDeviceName=${ateName}" />
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

<input type="hidden" name ="latitude" id ="latitude" value = ${device.latitude}></input>
<input type="hidden" name = "longitude" id = "longitude" value = ${device.longitude}></input>

<div class="container-fluid"  >
  
  <div class="row"  style = "background-color: #146bca" >
	  <div class="col-sm-12" >
	  	  <table class="table">
		      <tr align="center">
		        <td style = "border: none;"><img src="images/HeaderImage.jpg"  width="20" height="20"> <font color="white"> <b>  NBIOT Fire Alarm   </b> </font> </td>
		      </tr>
	      </table>
	  </div>
	</div>
	
  <div class="row"  style = "background-color: #444243" >
	  <div class="col-sm-12" >
	  	  <table class="table">
		      <tr align="center">
		        <td style = "border: none;" onclick="location.href='/NBIOT/DevicePage?sensorIMEI=${sensorIMEI}&ateDeviceName=${ateName}'" >
			        <c:choose>
		  				<c:when test="${device.fireAlarmStatusCode}">
				         	<img src="images/green.jpg"  width="200" height="100"> 
				         </c:when>
				         <c:otherwise>
		    			    <img src="images/redicon.jpg"  width="200" height="100"> 
				        </c:otherwise>
					</c:choose>
		        </td>
		      </tr>
	      </table>
	  </div>
	</div>
  
  <div class="row" >
    <div class="col-sm-2" ></div>
   
    <div class="col-sm-8" >
    
   
	   <table class="table borderless"  style = "border: none;" >
      
	      
		      <tr align="left" style="border-bottom:2pt solid #A9A9A9;" >
		        <td style = "border: none;">  <img src="images/deviceTypeIcon.jpg"  width="40" height="40"> <font color="black"> <b>  Device Type  </b> </font> </td>
		        <td style = "border: none;">  <font color="green"> <b> ${device.deviceType}  </b> </font> </td>
		       </tr>
		       
		      
		       <tr align="left" style="border-bottom:2pt solid #A9A9A9;">
		        <td style = "border: none;">  <img src="images/LocationIcon.jpg"  width="40" height="40"> <font color="black"> <b>  Location  </b> </font> </td>
		        <td style = "border: none;">  <font color="green"> <b>  ${device.location}  </b> </font> </td>
		        </tr>
		        
		      
		       <tr align="left" style="border-bottom:2pt solid #A9A9A9;">
		        <td style = "border: none;">  <img src="images/ConnectedStatusIcon.png"  width="40" height="40">  <font color="black"> <b>  Connection Status </b> </font> </td>
		        <td style = "border: none;">  <font color="green"> <b>  ${device.connectedStatus}   </b> </font> </td>
		       </tr>
		       
		      
		       <tr align="left" style="border-bottom:2pt solid #A9A9A9;">
		        <td style = "border: none;"> <img src="images/TemperedIcon.jpg"  width="40" height="40">  <font color="black"> <b>  Tampered  </b> </font> </td>
		        <td style = "border: none;">  
		         <c:choose>
		  				<c:when test="${device.tampered}">
				         	<img src="images/rediconCircle.jpg"  width="20" height="20"> 
				         </c:when>
				         <c:otherwise>
		    			    <img src="images/greeniconCircle.png"  width="20" height="20"> 
				        </c:otherwise>
					</c:choose>  
				</td>
		       </tr>
		       
		      
		       <tr align="left" style="border-bottom:2pt solid #A9A9A9;">
		        <td style = "border: none;"> <img src="images/BatteryIcon.jpg"  width="40" height="40">   <font color="black"> <b>  Battery  </b> </font> </td>
		        <td style = "border: none;"  align="left">  <font color="green"> <b>   ${device.battery}  </b> </font> </td>
		       </tr>
		      
			
			  <tr align="left" style="border-bottom:2pt solid #A9A9A9;"  onclick="location.href='/NBIOT/GoogleMap?latitude=${device.latitude}&longitude=${device.longitude}'">
		        <td style = "border: none;">  <img src="images/LocationDevice.jpg"  width="40" height="40"> <font color="black"> <b>  Location Device  </b> </font> </td>
		        <td style = "border: none;vertical-align: middle;" >  <img src="images/Arrow.JPG" align="left"  width="30" height="20"> </td>
		       </tr>
		           
		     
			     
	   </table>
    </div>
    
    <div class="col-sm-2" ></div>
  </div>
</div>



</body>
</html>