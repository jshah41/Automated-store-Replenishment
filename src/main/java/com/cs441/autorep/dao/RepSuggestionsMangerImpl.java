package com.cs441.autorep.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.cs441.autorep.interfaces.RepSuggestionsManager;
import com.cs441.autorep.model.RepSuggestions;

public class RepSuggestionsMangerImpl implements RepSuggestionsManager{

	@Override
	public ArrayList<RepSuggestions> getReplenish(String storeId)
			throws Exception {
		// TODO Auto-generated method stub
Connection con = ConnectionFactory.getConnection();
		
		String query = "SELECT s.`id`, s.`Product_id`, s.`Store_id`,  s.`packSize`, s.`expiryDate`, s.`discount`, s.`dateOfMf`, "
				+ "s.`mrp`, s.`unitPrice`,s.`weight`, s.`note`, s.`Vendor_id`, s.`Warehouse_id` FROM `autorep2`.`repsuggestions` s "
				+ "where s.Store_id = ?";
		
		PreparedStatement ps = con.prepareStatement(query);
		ps.setInt(1, Integer.parseInt(storeId));
		
		ResultSet rs = ps.executeQuery();
		
		ArrayList<RepSuggestions> results = new ArrayList<RepSuggestions>();
		
		while(rs.next()){
			RepSuggestions i = new RepSuggestions();
			
			i.setRepId(rs.getString(1));
			
			i.setProductId(rs.getString(2));
			i.setStoreId(rs.getString(3));
			i.setPackSize(rs.getString(4));
			//String exp = rs.getString(5);
			
			//i.setExpiryDate(exp.substring(0,exp.lastIndexOf(" ")));
			i.setExpiryDate(rs.getString(5));
			i.setDiscount(rs.getString(6));
			
			//String dof = rs.getString(7);
			//i.setDateOfMf(dof.substring(0,dof.lastIndexOf(" ")));
			
			i.setDateOfMf(rs.getString(7));
			i.setMrp(rs.getString(8));
			i.setUnitPrice(rs.getString(9));
			i.setWeight(rs.getString(10));
			i.setNote(rs.getString(11));
			i.setVendorId(rs.getString(12));
			i.setWarehouseId(rs.getString(13));
			
			results.add(i);
		}
		
		return results;
	}
	
	@Override
	public void updateReplenish(String wskuids) throws Exception{
		
		Connection con = ConnectionFactory.getConnection();
		String query3 = " delete from autorep2.repsuggestions where id = ?";
		String query1 = "SELECT s.`id`, s.`Product_id`, s.`Store_id`,  s.`packSize`, s.`expiryDate`, s.`discount`, s.`dateOfMf`, "
				+ "s.`mrp`, s.`unitPrice`,s.`weight`, s.`note`, s.`Vendor_id`, s.`Warehouse_id` FROM `autorep2`.`repsuggestions` s "
				+ "where s.id = ?";
		
		String query2 = "INSERT INTO `autorep2`.`sku` (`id`, `Product_id`, `Store_id`, `packSize`, `expiryDate`, `discount`, `dateOfMf`, `mrp`, `unitPrice`, `weight`, `note`, `Vendor_id`, `Warehouse_id`)"
				+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		
		PreparedStatement ps1 = con.prepareStatement(query1);
		PreparedStatement ps2 = con.prepareStatement(query2);
		PreparedStatement ps3 = con.prepareStatement(query3);
		
		
		
		String str[] = wskuids.split("[\\s,]+");
		
		for(int i = 1 ; i<str.length;i++){
			ps1.setInt(1, (Integer.parseInt(str[i])));
			System.out.println("int" + (Integer.parseInt(str[i])));
			ResultSet rs1 = ps1.executeQuery();
			
			while(rs1.next()){
				System.out.println("results set");
				ps2.setInt(1,rs1.getInt(1));
				ps2.setInt(2,rs1.getInt(2));
				ps2.setInt(3,rs1.getInt(3));
				ps2.setInt(4,rs1.getInt(4));
				ps2.setDate(5, rs1.getDate(5));
				ps2.setInt(6,rs1.getInt(6));
				ps2.setDate(7, rs1.getDate(7));
				ps2.setInt(8,rs1.getInt(8));
				ps2.setInt(9,rs1.getInt(9));
				ps2.setInt(10,rs1.getInt(10));
				ps2.setString(11, rs1.getString(11));
				ps2.setInt(12,rs1.getInt(12));
				ps2.setInt(13,rs1.getInt(13));
				ps2.executeUpdate();
				
			}
			
			ps3.setInt(1, Integer.parseInt(str[i]) );
			ps3.executeUpdate();
			
		}
		
}
	
}
