package com.nt.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nt.cfgs.AppConfigProperties;
import com.nt.constant.TravelPlanConstant;
import com.nt.entity.PlanCategory;
import com.nt.entity.TravelPlan;
import com.nt.repository.IPlanCategoryRepository;
import com.nt.repository.ITravelPlanRepository;

@Service
public class TravelPlanMgmtServiceImpl implements ITravelPlanmgmtService {
	@Autowired
	private IPlanCategoryRepository planCategoryRepo;
	@Autowired
	private ITravelPlanRepository planTravelRepo;
	
	private Map<String ,String> messages;
	
	@Autowired
	public TravelPlanMgmtServiceImpl(AppConfigProperties props) {
		messages=props.getMessages();
	}
	
	@Override
	public String registerTravelPlan(TravelPlan plan) {
		//save the object
		TravelPlan saved = planTravelRepo.save(plan);
		return saved.getPlanId()!=null?messages.get(TravelPlanConstant.SAVE_SUCCESS)+saved.getPlanId():messages.get(TravelPlanConstant.SAVE_FAILURE);
	}

	@Override
	public Map<Integer, String> getTravelPlanCategories() {
		// get All travel Plan
		List<PlanCategory> list = planCategoryRepo.findAll();
		Map<Integer,String> categoriesMap=new HashMap<Integer, String>();
		list.forEach(category->{
			categoriesMap.put(category.getCategoryId(), category.getCategoryName());
		});
		return categoriesMap;
	}

	@Override
	public List<TravelPlan> showAllTravelPlan() {
		// show all Travel Plan
		return planTravelRepo.findAll();
	}

	@Override
	public TravelPlan showTravelPlan(Integer id) {
		// show travel plan by id
		return planTravelRepo.findById(id).orElseThrow(()-> new IllegalArgumentException(messages.get(TravelPlanConstant.FIND_BY_ID_FAILURE)));
	}

	@Override
	public String upadteTravelPlan(TravelPlan plan) {
		// updateTravel Plan
		Optional<TravelPlan> pId = planTravelRepo.findById(plan.getPlanId());
		if(pId.isPresent()) {
			//update the object
			planTravelRepo.save(plan);
			return plan.getPlanId()+messages.get(TravelPlanConstant.UPDATE_SUCCESS);
		}else {
			return plan.getPlanId()+messages.get(TravelPlanConstant.UPDATE_FAILURE);	
		}	
		
	}

	@Override
	public String deleteTravlePlan(Integer planId) {
		// delete plan id
		Optional<TravelPlan> plan = planTravelRepo.findById(planId);
		if(plan.isPresent()) {
			planTravelRepo.deleteById(planId);
			return planId+messages.get(TravelPlanConstant.DELETE_SUCCESS);
		}else
		return planId+" "+messages.get(TravelPlanConstant.DELETE_FAILURE);
	}

	@Override
	public String changeTravelPlan(Integer planId, String status) {
		// changeTravel plan 
		Optional<TravelPlan> pid = planTravelRepo.findById(planId);
		if(pid.isPresent()) {
			TravelPlan plan = pid.get();
			plan.setActiveSW(status);
			planTravelRepo.save(plan);
			return planId+messages.get(TravelPlanConstant.STATUS_CHANGE_SUCCESS);
		}else		
		return planId+messages.get(TravelPlanConstant.STATUS_CHANGE_FAILURE);
	}
}