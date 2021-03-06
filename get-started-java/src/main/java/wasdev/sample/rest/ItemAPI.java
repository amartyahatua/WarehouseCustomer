/*******************************************************************************
 * Copyright (c) 2017 IBM Corp.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *******************************************************************************/ 
package wasdev.sample.rest;

import java.util.ArrayList;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Application;
import com.cloudant.client.api.CloudantClient;
import com.cloudant.client.api.Database;

import wasdev.sample.Customer;
import wasdev.sample.District;
import wasdev.sample.Item;
import wasdev.sample.NewOrder;
import wasdev.sample.Order;
import wasdev.sample.OrderLine;
import wasdev.sample.Warehouse;
import wasdev.sample.store.CloudantItemStore;
import wasdev.sample.store.DBClient;
import wasdev.sample.store.Transaction;

@ApplicationPath("api")
@Path("/items")
public class ItemAPI extends Application {
	private Database db;
	public Database getDb() {
		return db;
	}

	public void setDb(Database db) {
		this.db = db;
	}

	//Our database store
	public static CloudantClient store = DBClient.getInstance();



	/**
	 * Gets all Items.
	 * REST API example:
	 * <code>
	 * GET http://localhost:9080/GetStartedJava/api/Items
	 * </code>
	 * 
	 * Response:
	 * <code>
	 * [ "Bob", "Jane" ]
	 * </code>
	 * @return A collection of all the Items
	 */
	@GET
	@Path("/")
	@Produces({"application/json"})
	public String getItems() {
		//setDb(store.database("mydb", true)); 
		//Database db = store.database("mydb_test", true);
		CloudantItemStore cld = new CloudantItemStore();

		/*Set Data*/
		//cld.persistAllStock(db);
		

		
		//Database warehousedb = store.database("warehouse", true);
		//cld.persistAllWarehouse(warehousedb);

		//Database districtdb = store.database("district", true);
		//cld.persistAllDistrict(districtdb);

		//Database customerdb = store.database("customer", true);
		//cld.persistAllCustomer(customerdb);

		//Database neworderdb = store.database("neworder", true);
		//cld.persistAllCustomer(neworderdb);

		//Database itdb = store.database("item", true);
		//cld.persistAllItem(itdb);

		//Database stkdb = store.database("stock", true);
		//cld.persistAllStock(stkdb);
		
		//Database hstdb = store.database("history", true);
		//cld.persistAllHistory(hstdb);
		
		Warehouse wrh = null;
		District disinfo= null;
		Customer custinfo= null;
		/*Get Data*/
		try{
			wrh = cld.getWareHouse("1001");
			try {
				Thread.sleep(1000);
				System.out.println("Sleeping ---------------");
			} catch(InterruptedException ex) {
				ex.printStackTrace();
			}
		}
		catch (Exception e) {
			// TODO: handle exception
			System.out.println("Exceptoion_A1:"+ e.getMessage().toString());
		}
		try{
			try {
				Thread.sleep(5000);
				System.out.println("Sleeping ---------------");
			} catch(InterruptedException ex) {
				ex.printStackTrace();
			}
			disinfo = cld.getDistrict("200001001");
		}
		catch (Exception e) {
			// TODO: handle exception
			System.out.println("Exceptoion_A2:"+ e.getMessage().toString());
		}
		try{
			try {
				Thread.sleep(5000);
				System.out.println("Sleeping ---------------");
			} catch(InterruptedException ex) {
				ex.printStackTrace();
			}
		 custinfo = cld.getCustomer("100001");
		}
		catch (Exception e) {
			// TODO: handle exception
			System.out.println("Exceptoion_A3:"+ e.getMessage().toString());
		}
		
		Order odr = new Order();
		odr.setOid("1000001");
		odr.setOdid("200000000");
		odr.setOwid("1001");
		odr.setOcid("100000");
		odr.setOentryd("10102017");
		odr.setOcarrierid("99999");
		odr.setOolcnt("3");
		
		int count = Integer.parseInt(odr.getOolcnt());
		
		odr.setOalllocal("1");
	
		//Database orderdb = store.database("order", true);
		//cld.persistAllOrder(orderdb, odr);
		
		
		/*NewOrder nodr = new NewOrder();
		nodr.setNooid(odr.getOid());
		nodr.setNodid(odr.getOdid());
		nodr.setNowid(odr.getOwid());
		Database nwodrdb = store.database("new_oder", true);
		cld.persistAllNewOrder(nwodrdb, nodr);
		*/
		ArrayList<String> olOidList = new ArrayList<>();
		ArrayList<String> olDidList = new ArrayList<>();
		ArrayList<String> olWidList = new ArrayList<>();
		ArrayList<String> olNumList = new ArrayList<>();
		ArrayList<String> olIList = new ArrayList<>();
		ArrayList<String> olDupplyWidList = new ArrayList<>();
		ArrayList<String> olDelDList = new ArrayList<>();
		ArrayList<String> olQntList = new ArrayList<>();
		ArrayList<String> olAmtList = new ArrayList<>();
		ArrayList<String> olDstInfoList = new ArrayList<>();
		
		olOidList.add("1000001");
		olOidList.add("1000002");
		olOidList.add("1000003");
		
		olDidList.add("200000000");
		olDidList.add("200000000");
		olDidList.add("200000000");
		
		olWidList.add("1001");
		olWidList.add("1001");
		olWidList.add("1001");
		
		olNumList.add("1");
		olNumList.add("2");
		olNumList.add("3");
		
		olIList.add("28070");
		olIList.add("28367");
		olIList.add("22502");
		
		olDupplyWidList.add("1001");
		olDupplyWidList.add("1001");
		olDupplyWidList.add("1001");
		
		olDelDList.add("10102017");
		olDelDList.add("10102017");
		olDelDList.add("10102017");
		
		olQntList.add("20");
		olQntList.add("20");
		olQntList.add("10");
		
		olAmtList.add("233444");
		olAmtList.add("233455");
		olAmtList.add("232214");
		
		olDstInfoList.add("ABC");
		olDstInfoList.add("DEF");
		olDstInfoList.add("GHI");
		
		Database orderlinedb = store.database("order_line", true);
		
		
		/*Transaction tran = new Transaction();
		tran.select1("1001","200001001","100001",wrh, disinfo, custinfo, odr, olOidList, olDidList, olWidList, olNumList,
				olIList, olDupplyWidList, olDelDList, olQntList, olAmtList, olDstInfoList);
		*/
		
		
		/*OrderLine odrl;
		for(int i=0; i<count; i++)
		{	
			odrl = new OrderLine();
			odrl.setOloid(olOidList.get(i));
			odrl.setOldid(olDidList.get(i));
			odrl.setOlwid(olWidList.get(i));
			odrl.setOlnumber(olNumList.get(i));
			odrl.setOliid(olIList.get(i));
			odrl.setOlsupplywid(olDupplyWidList.get(i));
			odrl.setOldeliveryd(olDelDList.get(i));
			odrl.setOlquantity(olQntList.get(i));
			odrl.setOlamount(olAmtList.get(i));
			odrl.setOldistinfo(olDstInfoList.get(i));
			//cld.persistAllOrderLine(orderlinedb,odrl);
			
		}*/
		
		
		
		//System.out.println(wrh.getWcity());
		//System.out.println(disinfo.getDcity());
		//System.out.println(custinfo.getCcity());
		
		
		/*History histinfo = cld.getHistory();
		NewOrder norder = cld.getNewOrder();
		Stock stk = cld.getStock();
	
		
		Order odr = cld.getOrder();
		OrderLine odrl = cld.getOrderLine();
		Item it = cld.getItem();
*/


		if (store == null) {
			return "[]";
		}

		/*List<String> names = new ArrayList<String>();
		for (Item doc : cld.getAll(db)) {
			String name = doc.getName();
			if (name != null){
				names.add(name);
			}
		}*/

		/*for (Stock doc : cld.getAllStock(db)) {
			String name = doc.;
			if (name != null){
				names.add(name);
			}
		}*/


		//return new Gson().toJson(names);
		return "a";
	}

	/**
	 * Creates a new Item.
	 * 
	 * REST API example:
	 * <code>
	 * POST http://localhost:9080/GetStartedJava/api/Items
	 * <code>
	 * POST Body:
	 * <code>
	 * {
	 *   "name":"Bob"
	 * }
	 * </code>
	 * Response:
	 * <code>
	 * {
	 *   "id":"123",
	 *   "name":"Bob"
	 * }
	 * </code>
	 * @param Item The new Item to create.
	 * @return The Item after it has been stored.  This will include a unique ID for the Item.
	 */
	@POST
	@Produces("application/text")
	@Consumes("application/json")
	public String newToDo(Item Item) {
		if(store == null) {
			//return String.format("Hello %s!", Item.getName());
			return "abc";
		}
		//CloudantItemStore cld = new CloudantItemStore();
		//return String.format("Hello %s! I've added you to the database.", Item.getName());
		return "abc";

	}

}