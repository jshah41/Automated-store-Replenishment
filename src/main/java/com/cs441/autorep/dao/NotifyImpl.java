package com.cs441.autorep.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.cs441.autorep.interfaces.Notify;
import com.cs441.autorep.model.Suggestions;
import com.cs441.autorep.model.WarehouseSku;

public class NotifyImpl implements Notify{

	@Override
	public List<WarehouseSku> insertToRepSuggestions(Suggestions[] suggestions)
			throws Exception {
		
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		PreparedStatement insertPs = null;
		PreparedStatement deletePs = null;
		
		List<WarehouseSku> jsonList = new ArrayList<WarehouseSku>();

		try {

			con = ConnectionFactory.getConnection();

			for(int i=0; i<suggestions.length; i++){
				
				int storeId = Integer.parseInt(suggestions[i].getStore());
				
				System.out.println("#Processing for Store: "+storeId);
				
				for(int j=0; j<suggestions[i].getProdList().length; j++){
					
					ps = con.prepareStatement("SELECT `warehousesku`.`id`,`warehousesku`.`Product_id`,"
							+ "`warehousesku`.`Warehouse_id`,`warehousesku`.`packSize`,`warehousesku`.`expiryDate`,"
							+ "`warehousesku`.`discount`,`warehousesku`.`dateOfMf`,`warehousesku`.`mrp`,"
							+ "`warehousesku`.`unitPrice`,`warehousesku`.`weight`,`warehousesku`.`note`,"
							+ "`warehousesku`.`Vendor_id` FROM `autorep2`.`warehousesku` where Product_id=?");
					
					ps.setInt(1, Integer.parseInt( suggestions[i].getProdList()[j].getProduct() ) );
					
					rs = ps.executeQuery();
					
					int count = 0;
					
					while(rs.next()){
						
						count ++;
						
						if(count > Integer.parseInt(suggestions[i].getProdList()[j].getCount() ) ){
							break;
						}
						
						WarehouseSku sku = new WarehouseSku();
						
						sku.setStoreId(storeId);
						
						sku.setSkuId(rs.getInt(1));
						sku.setProductId(rs.getInt(2));
						sku.setWarehouseId(rs.getInt(3));
						sku.setPackSize(rs.getInt(4));
						sku.setExpiryDate(rs.getDate(5));
						sku.setDiscount(rs.getInt(6));
						sku.setDateOfMf(rs.getDate(7));
						sku.setMrp(rs.getInt(8));
						sku.setUnitPrice(rs.getInt(9));
						sku.setWeight(rs.getInt(10));
						sku.setNote(rs.getString(11));
						sku.setVendorId(rs.getInt(12));
						
						jsonList.add(sku);
						
						insertPs = con.prepareStatement("INSERT INTO `autorep2`.`repsuggestions` "
								+ "(`id`,`Product_id`,`Store_id`,`packSize`,`expiryDate`,`discount`,"
								+ "`dateOfMf`,`mrp`,`unitPrice`,`weight`,`note`,`Vendor_id`,`Warehouse_id`)"
								+ " VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?)");
						
						insertPs.setInt(1, sku.getSkuId());
						insertPs.setInt(2, sku.getProductId());
						insertPs.setInt(3, sku.getStoreId());
						insertPs.setInt(4, sku.getPackSize());
						insertPs.setDate(5, sku.getExpiryDate());
						insertPs.setInt(6, sku.getDiscount());
						insertPs.setDate(7, sku.getDateOfMf());
						insertPs.setInt(8, sku.getMrp());
						insertPs.setInt(9, sku.getUnitPrice());
						insertPs.setInt(10, sku.getWeight());
						insertPs.setString(11, sku.getNote());
						insertPs.setInt(12, sku.getVendorId());
						insertPs.setInt(13, sku.getWarehouseId());
						
						insertPs.executeUpdate();
						
						System.out.println("-- Warehouse SKU `"+sku.getSkuId()+"` is being replenished to store `"+sku.getStoreId()+"`."
								+ " It has product `"+sku.getProductId()+"` --");
						
						deletePs = con.prepareStatement("DELETE from `autorep2`.`warehousesku` where id=?");
						deletePs.setInt(1, sku.getSkuId());
						
						deletePs.executeUpdate();
						
						System.out.println("*** Warehouse SKU `"+sku.getSkuId()+"` has been deleted");
						
					}

				}
			}
			return jsonList;
		}
		finally{
			con.close();
			if(null != con)
			ps.close();
			if(null != ps)
			rs.close();
			if(null != insertPs)
			insertPs.close();
			if(null != deletePs)
			deletePs.close();
		}
		
	}

}
