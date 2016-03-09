package com.cs441.autorep.controller;

import java.net.URLDecoder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cs441.autorep.interfaces.UserTransactions;

@Controller
public class UserTranService {
	
	@Autowired
	UserTransactions utrans;
	
	/**
	 * Used to update User Transaction (buying) logs into MongoDB NoSQL database.
	 * @param jsonString
	 * @throws Exception
	 */
	@RequestMapping(value = "/tranlogs", method = RequestMethod.POST)
	@ResponseBody
	public void insertUserTranLogs(@RequestBody String jsonString) throws Exception{
		String decodedUrl = URLDecoder.decode( jsonString, "UTF-8" );
		decodedUrl = decodedUrl.substring(0,decodedUrl.length()-1);
		
		utrans.UpdateLogs(decodedUrl);
		System.out.println(decodedUrl + "test");
		
		
		
	}
	
}
