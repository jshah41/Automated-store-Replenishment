package com.cs441.autorep.interfaces;

import java.util.ArrayList;

public interface UserManager {

	public ArrayList<String> getUserStoreId(String userId) throws Exception;
	public int getUserId(String username) throws Exception;
	
}
