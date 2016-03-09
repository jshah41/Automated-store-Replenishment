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

import com.cs441.autorep.interfaces.UserManager;
import com.cs441.autorep.model.SkuWLogs;
import com.cs441.autorep.model.WarehouseLogJson;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;

@Controller
public class WarehouseTranLogsController {

	
	private static final Logger logger = LoggerFactory.getLogger(WarehouseTranLogsController.class);
	
	@Autowired
	UserManager userManager;
	

	@RequestMapping(value = "/wtlogs", method = RequestMethod.GET)
	public ModelAndView showWTLogs(HttpServletRequest req, HttpServletResponse res) throws Exception {
		logger.info("Welcome to WTlogs");
	
		HttpSession session = req.getSession();
		
		ArrayList<String> storeList = userManager.getUserStoreId((String)session.getAttribute("userId"));
		String currentStore = storeList.get(0);
		
		ArrayList<SkuWLogs> whAL =  retriveNOSQLDB();
		ModelAndView model = new ModelAndView("wtlogs");
		model.addObject("storeList", storeList );
		model.addObject("currentStore",currentStore);
		model.addObject("whlist",whAL);
		
		return model;
	}
	
	@RequestMapping(value = "/showwtlogs", method = RequestMethod.POST)
	public ModelAndView showInventory(HttpServletRequest req, HttpServletResponse res) throws Exception {
	
		HttpSession session = req.getSession();

		ArrayList<String> storeList = userManager.getUserStoreId((String)session.getAttribute("userId"));
		String currentStore = req.getParameter("storeList");
		
		ArrayList<SkuWLogs> whAl =  retriveByStoreNOSQLDB(currentStore);
		
		ModelAndView model = new ModelAndView("wtlogs");
		model.addObject("whlist", whAl);
		model.addObject("storeList", storeList );
		model.addObject("currentStore",currentStore);
		
		return model;
	
	}
	
	private ArrayList<SkuWLogs> retriveByStoreNOSQLDB(String currentStore){

		ArrayList<SkuWLogs> whAL = new ArrayList<SkuWLogs>();
		try{
			
			 DB db = (new MongoClient("54.172.105.21",27017)).getDB("translogs");
			 DBCollection collection = db.getCollection("wtranlogs");
			
			
			 Gson gson = new GsonBuilder().create();
			 DBCursor cursor = collection.find(); 
			 

			 while (cursor.hasNext()){

				 DBObject resultElement = cursor.next();
				 resultElement.removeField("_id");
				 String json = resultElement.toString();
 
				 
				 WarehouseLogJson whlogs = gson.fromJson(json, WarehouseLogJson.class);
				    	
				 SkuWLogs[] skuwlogs = whlogs.getSkuList();
			 	 for(int i=0;i<skuwlogs.length;i++){
			 		int storeid = skuwlogs[i].getStoreId();
			 		int cusid = Integer.parseInt(currentStore);
			 		if(cusid == storeid)
		        	{
			 			whAL.add(skuwlogs[i]);
		        	}
			 		 
			 		 
			 	 }	
			 	 
			        	
			    
			     
				 
			 }
			 
			  
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return whAL;
	}
	
	private ArrayList<SkuWLogs> retriveNOSQLDB()
	{
		ArrayList<SkuWLogs> whAL = new ArrayList<SkuWLogs>();
		try{
			
			 DB db = (new MongoClient("54.172.105.21",27017)).getDB("translogs");
			 DBCollection collection = db.getCollection("wtranlogs");
			
			
			 DBCursor cursor = collection.find();
			 Gson gson = new GsonBuilder().create();
			
			 
			 while (cursor.hasNext()){
			
				 DBObject resultElement = cursor.next();
				 resultElement.removeField("_id");
				 String json = resultElement.toString();
				 WarehouseLogJson whlogs = gson.fromJson(json, WarehouseLogJson.class);
			 	 SkuWLogs[] skuwlogs = whlogs.getSkuList();
			 	 for(int i=0;i<skuwlogs.length;i++){
			 		whAL.add(skuwlogs[i]);
			 		 
			 	 }		  
				 
			 }
			 
			  
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return whAL;
		
	}
}
