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
import org.springframework.web.servlet.ModelAndView;

import com.cs441.autorep.interfaces.InventoryManager;
import com.cs441.autorep.interfaces.UserManager;
import com.cs441.autorep.model.Inventory;

@Controller
public class DashboardController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	@Autowired
	UserManager userManager;
	
	@Autowired
	InventoryManager inventoryManager;
	
	/**
	 * This method is used to display the inventory list after login
	 * @param req
	 * @param res
	 * @return ModelAndView (Redirects to dashboard.jsp)
	 * @throws Exception
	 */
	@RequestMapping(value = "/dashboard", method = RequestMethod.GET)
	public ModelAndView showDashboard(HttpServletRequest req, HttpServletResponse res) throws Exception {
		logger.info("Welcome to dashboard");
		
		HttpSession session = req.getSession();
		
		ArrayList<String> storeList = userManager.getUserStoreId((String)session.getAttribute("userId"));
		String currentStore = storeList.get(0);
		
		session.setAttribute("currentStore",currentStore);
		
		ModelAndView model = new ModelAndView("dashboard");
		model.addObject("storeList", storeList );
		model.addObject("inventoryList",getInventoryList(currentStore));
		model.addObject("currentStore",currentStore);
		
		return model;
	}
	
	/**
	 * This method is used to display inventory page according to the selected `Store ID`.
	 * @param req
	 * @param res
	 * @return ModelAndView (Redirects to dashboard.jsp)
	 * @throws Exception
	 */
	@RequestMapping(value = "/showInventory", method = RequestMethod.POST)
	public ModelAndView showInventory(HttpServletRequest req, HttpServletResponse res) throws Exception {
		logger.info("Inside showInventory method => DashboardController");
		
		HttpSession session = req.getSession();
		ArrayList<String> storeList = userManager.getUserStoreId((String)session.getAttribute("userId"));
		
		String currentStore = req.getParameter("storeList");
		session.setAttribute("currentStore",currentStore);
		
		ModelAndView model = new ModelAndView("dashboard");
		model.addObject("storeList", storeList );
		
		
		model.addObject("inventoryList",getInventoryList(currentStore));
		model.addObject("currentStore",currentStore);
		
		return model;
	}
	
	/** 
	 * This private method fetches the Inventory list from the database according to the parameter `storeId`
	 * @param storeId
	 * @return inventoryList (ArrayList containing Inventory details)
	 * @throws Exception
	 */
	private ArrayList<Inventory> getInventoryList(String storeId) throws Exception{
		
		ArrayList<Inventory> inventoryList = inventoryManager.getInventory(storeId);
		
		return inventoryList;
		
	}
	
}