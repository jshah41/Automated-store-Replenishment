package com.cs441.autorep.controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;

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

import com.cs441.autorep.dao.ConnectionFactory;
import com.cs441.autorep.interfaces.UserManager;
import com.cs441.autorep.model.StockItem;

@Controller
public class StockController {
	
	private static final Logger logger = LoggerFactory.getLogger(StockController.class);
	
	@Autowired
	UserManager userManager;
	
	/**
	 * This method is used to displaying the inventory stock.
	 * @param req
	 * @param res
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/stockInventory", method = RequestMethod.GET)
	public ModelAndView showDashboard(HttpServletRequest req, HttpServletResponse res) throws Exception {
		logger.info("Welcome to dashboard");
		
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		ArrayList<HashMap<String, Integer>> results = new ArrayList<HashMap<String,Integer>>();
		ArrayList<StockItem> stockRes = new ArrayList<StockItem>();
		
		HttpSession session = req.getSession();
		session.getAttribute("userId");
		
		ArrayList<String> storeList = userManager.getUserStoreId((String)session.getAttribute("userId"));
		
		String query = "SELECT product_id, count(id) FROM autorep2.sku where store_id = ? GROUP BY product_id";
		con = ConnectionFactory.getConnection();
		ps = con.prepareStatement(query);
		
		for(String store: storeList) {
			ps.setInt(1, Integer.parseInt(store));
			rs = ps.executeQuery();
			while(rs.next()) {
				StockItem stockItem = new StockItem();
				stockItem.setPID(rs.getString(1));
				stockItem.setQuant(Integer.parseInt(rs.getString(2)));
				stockItem.setSID(store);
				stockRes.add(stockItem);
			}
		}
		System.out.println(results);
				
		ModelAndView model = new ModelAndView("stockReport");
		model.addObject("productCatalog", stockRes);
		return model;
	}
}
