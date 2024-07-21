package com.nt.service;

import java.util.List;
import java.util.Map;

import com.nt.entity.TravelPlan;

public interface ITravelPlanmgmtService {
	public String registerTravelPlan(TravelPlan plan);  //save operation
	public Map<Integer,String> getTravelPlanCategories(); //select operation
	public List<TravelPlan> showAllTravelPlan(); //for select operation
	public TravelPlan showTravelPlan(Integer id); //for Edit operation form launch
	public String upadteTravelPlan(TravelPlan plan); //for update opration form submission
	public String deleteTravlePlan(Integer planId); //for Delete opration (hard deletion)
	public String changeTravelPlan(Integer planId,String status); //for soft deletion activity
}
