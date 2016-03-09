package com.cs441.autorep.controller;


import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.cs441.autorep.interfaces.RepSuggestionsManager;
import com.cs441.autorep.interfaces.UserManager;
import com.cs441.autorep.model.RepSuggestions;

@SessionAttributes({"currentStore"})
@Controller
public class ReplenishController {
	
	private static final Logger logger = LoggerFactory.getLogger(ReplenishController.class);
	
	@Autowired
	UserManager userManager;
	
	@Autowired
	RepSuggestionsManager repsuggestionsManager;
	
	/**
	 * Redirects the user to `replenish.jsp`
	 * @param req
	 * @param res
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/replenish", method = RequestMethod.GET)
	public ModelAndView showDashboard(HttpServletRequest req, HttpServletResponse res) throws Exception {
		logger.info("Welcome to replenish");
		
		HttpSession session = req.getSession();
		session.setAttribute("userId", "1");
		
		ArrayList<String> storeList = userManager.getUserStoreId((String)session.getAttribute("userId"));
		String currentStore = storeList.get(0);
		
		session.setAttribute("currentStore",currentStore);
		
		ModelAndView model = new ModelAndView("replenish");
		model.addObject("storeList", storeList );
		model.addObject("inventoryList",getReplenishList(currentStore));
		model.addObject("currentStore",currentStore);
		
		return model;
	}
	
	/**
	 * Shows replenishment page according to the selected `Store ID`
	 * @param req
	 * @param res
	 * @return ModelAndView (redirects the user to `replenish.jsp`)
	 * @throws Exception
	 */
	@RequestMapping(value = "/showReplenish", method = RequestMethod.POST)
	public ModelAndView showInventory(HttpServletRequest req, HttpServletResponse res) throws Exception {
		logger.info("Inside showReplenish method => ReplenishController");
		
		HttpSession session = req.getSession();
		ArrayList<String> storeList = userManager.getUserStoreId((String)session.getAttribute("userId"));
		String currentStore = req.getParameter("storeList");
		session.setAttribute("currentStore",currentStore);
		
		
		ModelAndView model = new ModelAndView("replenish");
		model.addObject("storeList", storeList );
		
		model.addObject("replenishList",getReplenishList(currentStore));
		model.addObject("currentStore",currentStore);
		
		return model;
	}
	
	/**
	 * This method is called when a user approves replenishment suggestions. Product details are moved from ReplenishmentSuggestions
	 * to SKU table.
	 * @param req
	 * @param res
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/applyReplenish", method = RequestMethod.POST)
	public ModelAndView replenished(HttpServletRequest req, HttpServletResponse res) throws Exception {
		logger.info("Inside replenished method => ReplenishController");
		
		HttpSession session = req.getSession();
		String wskuids = req.getParameter("wskids");
		//System.out.println( " RS Controler" + wskuids);
		repsuggestionsManager.updateReplenish(wskuids);
						
		ModelAndView model = new ModelAndView("applyReplenish");
	
		model.addObject("currentStore",session.getAttribute("currentStore"));
		
		return model;
		
	}
	
	/**
	 * Gets the list of replenishment suggestions from the database.
	 * @param storeId
	 * @return
	 * @throws Exception
	 */
	private ArrayList<RepSuggestions> getReplenishList(String storeId) throws Exception{
		
		ArrayList<RepSuggestions> repsuggestionsList = repsuggestionsManager.getReplenish(storeId);
		
		return repsuggestionsList;
		
	}
	
}