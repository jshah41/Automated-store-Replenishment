package com.cs441.autorep.model;

import static org.junit.Assert.*;

import org.junit.Test;

import com.mongodb.DB;
import com.mongodb.MongoClient;

public class TCLogin {

	@Test
	public void test() {
		String username = "manager";
		String password = "manager";

		try{
		DB db = (new MongoClient("54.172.105.21",27017)).getDB("products");
		if(db.authenticate(username, password.toCharArray())){

			assertEquals(true, true);
			
		}
		else
			fail("test failed");}
		
		catch(Exception e){
			fail("test failed");
			
		}


	}

}
