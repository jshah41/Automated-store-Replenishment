package com.cs441.autorep.interfaces;

import java.util.List;

import com.cs441.autorep.model.Suggestions;
import com.cs441.autorep.model.WarehouseSku;

public interface Notify {

	public List<WarehouseSku> insertToRepSuggestions(Suggestions[] suggestions) throws Exception;
}
