package com.cs441.autorep.controller;

import java.net.URLDecoder;
import java.net.UnknownHostException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cs441.autorep.interfaces.Notify;
import com.cs441.autorep.model.SkuLogs;
import com.cs441.autorep.model.SuggestionJson;
import com.cs441.autorep.model.Suggestions;
import com.cs441.autorep.model.WarehouseSku;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.MongoException;
import com.mongodb.util.JSON;


@Controller
public class NotifyService {

	@Autowired
	Notify notify;
	
	/**
	 * Web service which accepts replenishment needs in JSON format, and calculates which products should be replenished
	 * from the WarehouseSku. It also writes the replenishment findings to a log in MongoDB.
	 * @param jsonString
	 * @return (returns `success` on success)
	 * @throws Exception
	 */
	@RequestMapping(value = "/notify", method = RequestMethod.POST)
	@ResponseBody
	public String insertUser(@RequestBody String jsonString) throws Exception {

		String decodedUrl = URLDecoder.decode( jsonString, "UTF-8" );
		
		System.out.println(decodedUrl);
		
		decodedUrl = decodedUrl.substring(0,decodedUrl.length()-1);

		Gson gson = new GsonBuilder().create();
        SuggestionJson suggestions = gson.fromJson(decodedUrl, SuggestionJson.class);
        
        Suggestions[] s = suggestions.getData().getSuggestions();
        
        for(int i=0; i<s.length; i++){
        	String storeId = s[i].getStore();
        	System.out.println(storeId);
        	
        	for(int j=0; j<s[i].getProdList().length; j++){
        		String product = s[i].getProdList()[j].getProduct();
        		String count = s[i].getProdList()[j].getCount();
        		
        		System.out.println("\t"+product);
        		System.out.println("\t"+count);
        	}
        } 
	    
        List<WarehouseSku> insertedSkuList = notify.insertToRepSuggestions(s);
        
        SkuLogs skuLogs = new SkuLogs();
        
        skuLogs.skuList = insertedSkuList;
        
        String json = new Gson().toJson(skuLogs);
        
       try{ 
        DB db = (new MongoClient("54.172.105.21",27017)).getDB("translogs");
		 DBCollection collection = db.getCollection("wtranlogs");
		 DBObject dbobject = (DBObject) JSON.parse(json);
		 collection.insert(dbobject);
       }
       catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (MongoException e) {
			e.printStackTrace();
		}
		System.out.println("Done");
        
        System.out.print(insertedSkuList.size()+"*"+json);
        
	    return "success";
	    
	}
	
	
}
