package com.springboot.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.springboot.Model.ATEPojo;
import com.springboot.Model.DevicePojo;
import com.springboot.Service.ATEService;


@Controller
//@RequestMapping("/NBIOT")
public class HelloController {

   @Autowired
   ATEService  ateService;
	
   @RequestMapping("/")
   public String index() {
      return "index";
   }
   

   @RequestMapping("/ATEDevicePage")
   public ModelAndView showATEDevice(Model model) {
      ModelAndView modelAndView = new ModelAndView("ATEDevicePage");
      
      String ateName = ateService.getATEList().get(0);
      List<ATEPojo> ate = ateService.getATEDeviceList(ateName);
      modelAndView.addObject("ateList", ate);
      modelAndView.addObject("ateName", ateName);
      return modelAndView;
   }
   
   @RequestMapping("/DevicePage")
   public ModelAndView showDevice(HttpServletRequest request, HttpServletResponse response ,Model model) {
      ModelAndView modelAndView = new ModelAndView("DevicePage");
      
      String ateName = (String) request.getParameter("ateDeviceName");
      String sensorIMEI = (String) request.getParameter("sensorIMEI");
      DevicePojo device = ateService.getDeviceAttribute(ateName, sensorIMEI);
      modelAndView.addObject("ateName", ateName);
      modelAndView.addObject("sensorIMEI", sensorIMEI);
      modelAndView.addObject("device", device);
      return modelAndView;
   }
   
   @RequestMapping("/GoogleMap")
   public ModelAndView showGoogleMap(HttpServletRequest request, HttpServletResponse response,Model model) {
      ModelAndView modelAndView = new ModelAndView("GoogleMapPage");
      
      String latitude = (String) request.getParameter("latitude");
      String longitude = (String) request.getParameter("longitude");
      modelAndView.addObject("latitude", latitude);
      modelAndView.addObject("longitude", longitude);
      return modelAndView;
   }
   
}
