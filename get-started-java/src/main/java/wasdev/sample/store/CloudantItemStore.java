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
package wasdev.sample.store;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Collection;
import java.util.List;
import com.cloudant.client.api.Database;

import wasdev.sample.Customer;
import wasdev.sample.District;
import wasdev.sample.History;
import wasdev.sample.Item;
import wasdev.sample.NewOrder;
import wasdev.sample.Order;
import wasdev.sample.OrderLine;
import wasdev.sample.Stock;
import wasdev.sample.Warehouse;
import wasdev.sample.rest.ItemAPI;

public class CloudantItemStore implements ItemStore{

	private Database db = null;
	/*private static final String databaseName = "mydb";

	public CloudantItemStore(){
		CloudantClient cloudant = createClient();
		if(cloudant!=null){
			db = cloudant.database(databaseName, true);
		}
	}

	public Database getDB(){
		return db;
	}*/

	@Override
	public Collection<Item> getAll(Database db){
		List<Item> docs;
		//ItemAPI vapi = new ItemAPI();

		try {
			docs = db.getAllDocsRequestBuilder().includeDocs(true).build().getResponse().getDocsAs(Item.class);
			System.out.println(docs.size());
		} catch (IOException e) {
			return null;
		}
		return docs;
	}

	@Override
	public Item get(String id) {
		return db.find(Item.class, id);
	}

	@Override
	public Item persist(Item td) {

		String id = null;
		final String COMMA_DELIMITER = ",";
		BufferedReader br = null;

		/*try {
			br = new BufferedReader(new FileReader("C:\\Users\\ahatua\\Google Drive\\fall17\\CSC733\\assignment2\\get-started-java\\item.csv"));
			br.readLine();
			String line = "";
			while ((line = br.readLine()) != null) 
			{
				String[] employeeDetails = line.split(COMMA_DELIMITER);
				if(employeeDetails.length > 0 )
				{
					td.set_id(employeeDetails[0]);
					td.set_iData(employeeDetails[1]);
					td.set_iImId(employeeDetails[2]);
					td.set_iName(employeeDetails[3]);
					td.set_iPrice(employeeDetails[4]);
					id = db.save(td).getId();
				}
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/




		return db.find(Item.class, id);
	}

	@Override
	public Item update(String id, Item newItem) {
		Item Item = db.find(Item.class, id);
		Item.setName(newItem.getName());
		db.update(Item);
		return db.find(Item.class, id);

	}

	@Override
	public void delete(String id) {
		Item Item = db.find(Item.class, id);
		db.remove(id, Item.get_rev());

	}

	@Override
	public int count(Database db) throws Exception {
		return getAll(db).size();
	}

	@Override
	public void setTargetDB(String id) {
		// TODO Auto-generated method stub

	}

	@Override
	public Database getDB() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override


	public void persistAllStock(Database db) {
		// TODO Auto-generated method stub
		String id = null;
		//Stock st = null;
		final String COMMA_DELIMITER = ",";
		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader("C:\\Users\\ahatua\\Google Drive\\fall17\\CSC733\\assignment2\\get-started-java\\stock_.csv"));
			br.readLine();
			String line = "";
			int count = 0;
			while ((line = br.readLine()) != null) 
			{	Stock st = new Stock();
			String[] stockDetails = line.split(COMMA_DELIMITER);
			if(stockDetails.length > 0 )
			{ 
				int len = stockDetails.length ;
				st.setSid(stockDetails[0]);
				st.setSwid(stockDetails[1]);
				st.setSquantity(stockDetails[2]);
				st.setSdist1(stockDetails[3]);
				st.setSdist2(stockDetails[4]);
				st.setSdist3(stockDetails[5]);
				st.setSdist4(stockDetails[6]);
				st.setSdist5(stockDetails[7]);
				st.setSdist6(stockDetails[8]);
				st.setSdist7(stockDetails[9]);
				st.setSdist8(stockDetails[10]);
				st.setSdist9(stockDetails[11]);
				st.setSdist10(stockDetails[12]);
				st.setSydt(stockDetails[13]);
				st.setSordercnt(stockDetails[14]);
				st.setSremotecnt(stockDetails[15]);
				st.setSdata(stockDetails[16]);


				id = db.save(st).getId();

				count++;
				if(count == 9)
				{
					count=0;
					try {
						Thread.sleep(5000);
						System.out.println("Sleeping ---------------");
					} catch(InterruptedException ex) {
						ex.printStackTrace();
					}
				}

			}
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	public Collection<Stock> getAllStock(Database db) {
		List<Stock> docs;
		//ItemAPI vapi = new ItemAPI();

		try {
			docs = db.getAllDocsRequestBuilder().includeDocs(true).build().getResponse().getDocsAs(Stock.class);
			System.out.println(docs.size());
		} catch (IOException e) {
			return null;
		}
		return docs;

	}

	@Override
	public void persistAllOrder(Database db,Order or) {
		// TODO Auto-generated method stub
		db.save(or);
	}

	@Override
	public void persistAllOrderLine(Database db, OrderLine odrl) {
		db.save(odrl);
		// TODO Auto-generated method stub	
	}

	@Override
	public void persistAllWarehouse(Database db) {
		// TODO Auto-generated method stub
		String id = null;
		//Stock st = null;
		final String COMMA_DELIMITER = ",";
		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader("C:\\Users\\ahatua\\Google Drive\\fall17\\CSC733\\assignment2\\get-started-java\\warehouse.csv"));
			br.readLine();
			String line = "";
			int count = 0;
			while ((line = br.readLine()) != null) 
			{	
				Warehouse wrh = new Warehouse();
				String[] stockDetails = line.split(COMMA_DELIMITER);
				if(stockDetails.length > 0 )
				{ 
					int len = stockDetails.length ;
					wrh.setWid(stockDetails[0]);
					wrh.setWname(stockDetails[1]);
					wrh.setWstreet1(stockDetails[2]);
					wrh.setWstreet2(stockDetails[3]);
					wrh.setWcity(stockDetails[4]);
					wrh.setWstate(stockDetails[5]);
					wrh.setWzip(stockDetails[6]);
					wrh.setWtax(stockDetails[7]);
					wrh.setWytd(stockDetails[8]);

					id = db.save(wrh).getId();

					count++;
					if(count == 9)
					{
						count=0;
						try {
							Thread.sleep(5000);
							System.out.println("Sleeping ---------------");
						} catch(InterruptedException ex) {
							ex.printStackTrace();
						}
					}

				}
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void persistAllDistrict(Database db) {
		// TODO Auto-generated method stub
		String id = null;
		//Stock st = null;
		final String COMMA_DELIMITER = ",";
		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader("C:\\Users\\ahatua\\Google Drive\\fall17\\CSC733\\assignment2\\get-started-java\\district.csv"));
			br.readLine();
			String line = "";
			int count = 0;
			while ((line = br.readLine()) != null) 
			{	
				District dist = new District();
				String[] stockDetails = line.split(COMMA_DELIMITER);
				if(stockDetails.length > 0 )
				{ 
					int len = stockDetails.length ;
					dist.setDid(stockDetails[0]);
					dist.setDwid(stockDetails[1]);
					dist.setDname(stockDetails[2]);
					dist.setDstreet1(stockDetails[3]);
					dist.setDstreet2(stockDetails[4]);
					dist.setDcity(stockDetails[5]);
					dist.setDstate(stockDetails[6]);
					dist.setDzip(stockDetails[7]);
					dist.setDtax(stockDetails[8]);
					dist.setDytd(stockDetails[8]);
					dist.setDnextoid(stockDetails[8]);
					id = db.save(dist).getId();

					count++;
					if(count == 9)
					{
						count=0;
						try {
							Thread.sleep(5000);
							System.out.println("Sleeping ---------------");
						} catch(InterruptedException ex) {
							ex.printStackTrace();
						}
					}

				}
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	public void persistAllCustomer(Database db) {
		// TODO Auto-generated method stub
		String id = null;
		//Stock st = null;
		final String COMMA_DELIMITER = ",";
		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader("C:\\Users\\ahatua\\Google Drive\\fall17\\CSC733\\assignment2\\get-started-java\\customer.csv"));
			br.readLine();
			String line = "";
			int count = 0;
			while ((line = br.readLine()) != null) 
			{	
				Customer cus = new Customer();
				String[] stockDetails = line.split(COMMA_DELIMITER);
				if(stockDetails.length > 0 )
				{ 
					int len = stockDetails.length ;
					cus.setCid(stockDetails[0]);
					cus.setCdid(stockDetails[1]);
					cus.setCwid(stockDetails[2]);
					cus.setCfirst(stockDetails[3]);
					cus.setCmiddle(stockDetails[4]);
					cus.setClast(stockDetails[5]);
					cus.setCstreet1(stockDetails[6]);
					cus.setCstreet2(stockDetails[7]);
					cus.setCcity(stockDetails[8]);
					cus.setCstate(stockDetails[9]);
					cus.setCzip(stockDetails[10]);
					cus.setCphone(stockDetails[11]);
					cus.setCsince(stockDetails[12]);
					cus.setCcredit(stockDetails[13]);
					cus.setCcreditlim(stockDetails[14]);
					cus.setCdiscount(stockDetails[15]);
					cus.setCbalance(stockDetails[16]);
					cus.setCytdpayment(stockDetails[17]);
					cus.setCpaymentcnt(stockDetails[18]);
					cus.setCdeliverycnt(stockDetails[19]);
					cus.setCdata(stockDetails[20]);


					id = db.save(cus).getId();

					count++;
					if(count == 9)
					{
						count=0;
						try {
							Thread.sleep(5000);
							System.out.println("Sleeping ---------------");
						} catch(InterruptedException ex) {
							ex.printStackTrace();
						}
					}

				}
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void persistAllHistor(Database db) {
		// TODO Auto-generated method stub

		String id = null;
		//Stock st = null;
		final String COMMA_DELIMITER = ",";
		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader("C:\\Users\\ahatua\\Google Drive\\fall17\\CSC733\\assignment2\\get-started-java\\Stock.csv"));
			br.readLine();
			String line = "";
			int count = 0;
			while ((line = br.readLine()) != null) 
			{	History hs = new History();
			String[] stockDetails = line.split(COMMA_DELIMITER);
			if(stockDetails.length > 0 )
			{ 
				int len = stockDetails.length ;
				hs.setHcid(stockDetails[0]);
				hs.setHcdid(stockDetails[1]);
				hs.setHcwid(stockDetails[2]);
				hs.setHdid(stockDetails[3]);
				hs.setHwid(stockDetails[4]);
				hs.setHdate(stockDetails[5]);
				hs.setHamount(stockDetails[6]);
				hs.setHdata(stockDetails[7]);

				id = db.save(hs).getId();

				count++;
				if(count == 9)
				{
					count=0;
					try {
						Thread.sleep(5000);
						System.out.println("Sleeping ---------------");
					} catch(InterruptedException ex) {
						ex.printStackTrace();
					}
				}

			}
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}							

	

	@Override
	public Customer getCustomer(String id) {
		// TODO Auto-generated method stub
		//String id = "100101";
		Database customerdb = ItemAPI.store.database("customer",true );
		Customer cust = customerdb.find(Customer.class,id);
		
		return cust;
	}

	@Override
	public District getDistrict(String id) {
		// TODO Auto-generated method stub
		//String id = "100001";
		Database distdb = ItemAPI.store.database("district",true );
		District dist = distdb.find(District.class,id);
		return dist;
	}

	@Override
	public History getHistory() {
		// TODO Auto-generated method stub
		String id = "100001";
		Database histdb = ItemAPI.store.database("history",true );
		History hist = histdb.find(History.class,id);
		return hist;
	}

	@Override
	public NewOrder getNewOrder() {
		// TODO Auto-generated method stub
		String id = "100001";
		Database nwodb = ItemAPI.store.database("neworder",true );
		NewOrder newor = nwodb.find(NewOrder.class,id);
		return newor;
	}

	@Override
	public Stock getStock(String id ) {
		// TODO Auto-generated method stub
		//String id = "100001";
		Database stkdb = ItemAPI.store.database("stock",true );
		Stock stk = stkdb.find(Stock.class,id);
		return stk;
	}

	@Override
	public Warehouse getWareHouse(String id) {
		// TODO Auto-generated method stub
		//String id = "100001";
		Database wrdb = ItemAPI.store.database("warehouse",true );
		Warehouse wrh = wrdb.find(Warehouse.class,id);
		return wrh;
	}

	@Override
	public Order getOrder() {
		// TODO Auto-generated method stub
		String id = "100001";
		Database ordb = ItemAPI.store.database("order",true );
		Order ord = ordb.find(Order.class,id);
		return ord;
	}

	@Override
	public OrderLine getOrderLine() {
		// TODO Auto-generated method stub
		String id = "100001";
		Database ordldb = ItemAPI.store.database("order_line",true );
		OrderLine ordl = ordldb.find(OrderLine.class,id);
		return ordl;
	}
	
	@Override
	public Item getItem(String id) {
		// TODO Auto-generated method stub
		//String id = "100001";
		Database itdb = ItemAPI.store.database("item",true );
		Item it = itdb.find(Item.class,id);
		return it;
	}

	@Override
	public void persistAllItem(Database db) {
		// TODO Auto-generated method stub
		String id = null;
		//Stock st = null;
		final String COMMA_DELIMITER = ",";
		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader("C:\\Users\\ahatua\\Google Drive\\fall17\\CSC733\\assignment2\\get-started-java\\item.csv"));
			br.readLine();
			String line = "";
			int count = 0;
			while ((line = br.readLine()) != null) 
			{	Item it = new Item();
			String[] stockDetails = line.split(COMMA_DELIMITER);
			if(stockDetails.length > 0 )
			{ 
				int len = stockDetails.length ;
				it.set_id(stockDetails[0]);
				it.set_iImId(stockDetails[1]);
				it.set_iName(stockDetails[2]);
				it.set_iPrice(stockDetails[3]);
				it.set_iData(stockDetails[4]);

				id = db.save(it).getId();

				count++;
				if(count == 9)
				{
					count=0;
					try {
						Thread.sleep(5000);
						System.out.println("Sleeping ---------------");
					} catch(InterruptedException ex) {
						ex.printStackTrace();
					}
				}

			}
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Override
	public void persistAllHistory(Database db) {
		// TODO Auto-generated method stub
		String id = null;
		//Stock st = null;
		final String COMMA_DELIMITER = ",";
		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader("C:\\Users\\ahatua\\Google Drive\\fall17\\CSC733\\assignment2\\get-started-java\\Stock.csv"));
			br.readLine();
			String line = "";
			int count = 0;
			while ((line = br.readLine()) != null) 
			{	History hst = new History();
			String[] stockDetails = line.split(COMMA_DELIMITER);
			if(stockDetails.length > 0 )
			{ 
				int len = stockDetails.length ;
				hst.setHcid(stockDetails[0]);
				hst.setHcdid(stockDetails[1]);
				hst.setHcwid(stockDetails[2]);
				hst.setHdid(stockDetails[3]);
				hst.setHwid(stockDetails[4]);
				hst.setHdate(stockDetails[5]);
				hst.setHamount(stockDetails[6]);
				hst.setHdata(stockDetails[7]);
				
				id = db.save(hst).getId();

				count++;
				if(count == 9)
				{
					count=0;
					try {
						Thread.sleep(5000);
						System.out.println("Sleeping ---------------");
					} catch(InterruptedException ex) {
						ex.printStackTrace();
					}
				}

			}
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	

	@Override
	public void persistAllNewOrder(Database db, NewOrder nword) {
		// TODO Auto-generated method stub
		db.save(nword);
	}
	@Override
	public void updateStock(Database dbstock, Stock stk, String setquantity) {
		// TODO Auto-generated method stub
		Stock stock = dbstock.find(Stock.class, stk.getSid());
		stock.setSquantity(setquantity);
		String x = stock.get_rev();
		stock.set_rev(x);
		dbstock.update(stock);
		System.out.println("Here++++++++++");
	}

	
}
