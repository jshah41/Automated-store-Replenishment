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
import com.cs441.autorep.model.Details;
import com.cs441.autorep.model.SalesDataJson;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;


@Controller
public class UserTranLogsController {
	
	private static final Logger logger = LoggerFactory.getLogger(UserTranLogsController.class);
	
	@Autowired
	UserManager userManager;
	

	@RequestMapping(value = "/utlogs", method = RequestMethod.GET)
	public ModelAndView showUTLogs(HttpServletRequest req, HttpServletResponse res) throws Exception {
		logger.info("Welcome to UTlogs");
	
		HttpSession session = req.getSession();
		//String usid = (String)session.getAttribute("userId");
		
		ArrayList<String> storeList = userManager.getUserStoreId((String)session.getAttribute("userId"));
		String currentStore = storeList.get(0);
		
		ArrayList<Details> dtlsAl =  retriveNOSQLDB();
		
		ModelAndView model = new ModelAndView("utlogs");
		//model.addObject("usid", usid );
		model.addObject("storeList", storeList );
		model.addObject("currentStore",currentStore);
		model.addObject("dtlslist",dtlsAl);
		//model.addObject("inventoryList",getInventoryList(currentStore));
	//	model.addObject("currentStore",currentStore);
		
		return model;
	}
	
	@RequestMapping(value = "/showutlogs", method = RequestMethod.POST)
	public ModelAndView showInventory(HttpServletRequest req, HttpServletResponse res) throws Exception {
	
		HttpSession session = req.getSession();
		//String currentStore = req.getParameter("storeList");
		ArrayList<String> storeList = userManager.getUserStoreId((String)session.getAttribute("userId"));
		String currentStore = req.getParameter("storeList");
		
		ArrayList<Details> dtlsAl =  retriveByStoreNOSQLDB(currentStore);
		
		ModelAndView model = new ModelAndView("utlogs");
		model.addObject("dtlslist",dtlsAl);
		model.addObject("storeList", storeList );
		model.addObject("currentStore",currentStore);
		
		return model;
	
	}
	
	private ArrayList<Details> retriveByStoreNOSQLDB(String currentStore){

		ArrayList<Details> dtlsAL = new ArrayList<Details>();
		try{
			
			 DB db = (new MongoClient("54.172.105.21",27017)).getDB("translogs");
			 DBCollection collection = db.getCollection("utranlogs");
			
			
			 Gson gson = new GsonBuilder().create();
			 DBCursor cursor = collection.find(); 
			 

			 while (cursor.hasNext()){

				 DBObject resultElement = cursor.next();
				 resultElement.removeField("_id");
				 String json = resultElement.toString();
 
				 
				 SalesDataJson sdlogs = gson.fromJson(json, SalesDataJson.class);
				 Details[] s = sdlogs.getSalesData().getDetails();
			     for(int i=0; i<s.length; i++){
			        	String storeId = s[i].getStore();
			        	
			        	if(storeId.equals(currentStore))
			        	{
			        	dtlsAL.add(s[i]);
			       
			     }
			        }
			     
				 
			 }
			 
			  
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return dtlsAL;
	}
	
	private ArrayList<Details> retriveNOSQLDB()
	{
		ArrayList<Details> dtlsAL = new ArrayList<Details>();
		try{
			
			 DB db = (new MongoClient("54.172.105.21",27017)).getDB("translogs");
			 DBCollection collection = db.getCollection("utranlogs");
			
			 DBCursor cursor = collection.find();
			 Gson gson = new GsonBuilder().create();
			 
			 
			 while (cursor.hasNext()){
				 DBObject resultElement = cursor.next();
				 resultElement.removeField("_id");
				 String json = resultElement.toString();
				// System.out.println("JSON :" + json); 
				 
				 SalesDataJson sdlogs = gson.fromJson(json, SalesDataJson.class);
				 Details[] s = sdlogs.getSalesData().getDetails();
			     for(int i=0; i<s.length; i++){
			        	//String storeId = s[i].getStore();
			        	dtlsAL.add(s[i]);
			        	//System.out.println(storeId);
			        	
			        /*	for(int j=0; j<s[i].getProdList().length; j++){
			        		String product = s[i].getProdList()[j].getProduct();
			        		String count = s[i].getProdList()[j].getCount();
			        		
			        		System.out.println("\t"+product);
			        		System.out.println("\t"+count);
			        	} */
			        }
			     
				 
			 }
			 
			  
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return dtlsAL;
		
	}
	
}
