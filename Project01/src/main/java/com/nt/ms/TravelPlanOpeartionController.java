package com.nt.ms;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nt.entity.TravelPlan;
import com.nt.service.ITravelPlanmgmtService;

@RestController
@RequestMapping("/travelplan/api") // optional
public class TravelPlanOpeartionController {
	@Autowired
	private ITravelPlanmgmtService planService;
	
//for register purpose we use @PostMapping() and For the Json part we use @RequestBody()
   @PostMapping("/register")
	public ResponseEntity<?> registerTravelPlan(@RequestBody TravelPlan plan){
		//use service class
	   try {
		   String msg = planService.registerTravelPlan(plan);
		   return new ResponseEntity<String>(msg,HttpStatus.CREATED);
	   }catch(Exception e) {
		   e.printStackTrace();
		   return new ResponseEntity<String>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
	   }
	}
   @GetMapping("/categories")
   public ResponseEntity<?> showTravelPlanCategories(){
	   //use service class
	   try {
		   Map<Integer, String> categories = planService.getTravelPlanCategories();
		   return new ResponseEntity<Map<Integer,String>>(categories,HttpStatus.OK);
	   }catch(Exception e) {
		   e.printStackTrace();
		   return new ResponseEntity<String>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
	   }
  }
   @GetMapping("/all")
   public ResponseEntity<?> viewAllTravelPlan(){
	   try {
	   List<TravelPlan> show = planService.showAllTravelPlan();
	   return  new ResponseEntity<List<TravelPlan>>(show,HttpStatus.OK);
   }catch(Exception e) {
	   e.printStackTrace();
	   return new ResponseEntity<String>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
   }
  }
  
   @GetMapping("/findById/{id}")
   public ResponseEntity<?> showtravelPlanById(@PathVariable Integer id){
	   //use service class
	try {
		TravelPlan plan = planService.showTravelPlan(id);
		return new ResponseEntity<TravelPlan>(plan,HttpStatus.OK);
	}catch(Exception e) {
		   e.printStackTrace();
		   return new ResponseEntity<String>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
	   }
   }
   @PutMapping("/update")
   public ResponseEntity<?> updatetravelPlan(@RequestBody TravelPlan plan){
	   
	   try {
		  String update = planService.upadteTravelPlan(plan); 
		  return new ResponseEntity<String>(update,HttpStatus.OK);
	   }catch(Exception e) {
		   e.printStackTrace();
		   return new ResponseEntity<String>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
	   }
   }
   @DeleteMapping("/delete/{id}")
   public ResponseEntity<?> removeTravelPlan(@PathVariable Integer id){
	   try {
		   String delete = planService.deleteTravlePlan(id);
		   return new ResponseEntity<String>(delete,HttpStatus.OK);
	   }catch(Exception e) {
		   e.printStackTrace();
		   return new ResponseEntity<String>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
	   }
   }
   
   @GetMapping("/status/{id}/{status}")
   public ResponseEntity<?> changeStatus(@PathVariable Integer id,@PathVariable String status){
	   
	   try {
		String change = planService.changeTravelPlan(id, status);  
		return new ResponseEntity<String>(change,HttpStatus.OK);
	   }catch(Exception e) {
		   e.printStackTrace();
		   return new ResponseEntity<String>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
	   }
   }
   
}
