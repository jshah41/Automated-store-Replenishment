package com.cs441.autorep.interfaces;

import java.util.ArrayList;

import com.cs441.autorep.model.RepSuggestions;

public interface RepSuggestionsManager {
	
	public ArrayList<RepSuggestions> getReplenish(String storeId) throws Exception;
	public void updateReplenish(String wskuids) throws Exception;

}
