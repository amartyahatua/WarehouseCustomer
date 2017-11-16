package wasdev.sample.store;

import java.beans.AppletInitializer;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import com.cloudant.client.api.Database;

import wasdev.sample.Customer;
import wasdev.sample.District;
import wasdev.sample.Item;
import wasdev.sample.NewOrder;
import wasdev.sample.Order;
import wasdev.sample.OrderLine;
import wasdev.sample.Stock;
import wasdev.sample.Warehouse;
import wasdev.sample.rest.ItemAPI;

public class Transaction {
	public String c_discount;
	public String c_last;
	public String c_credit;
	public String w_tax;
	public String d_next_o_id;
	public String d_tax_INTO;
	public String d_tax;
	public String o_id;
	public String o_all_local; 
	public String ol_i_id;
	public String ol_quantity;
	public String i_price;
	public String i_name;
	public String i_data;
	public String ol_supply_w_id;
	public String s_quantity;
	public String s_data;
	public String s_dist_01;
	public String s_dist_02;
	public String s_dist_03;
	public String s_dist_04;
	public String s_dist_05;
	public String s_dist_06;
	public String s_dist_07;
	public String s_dist_08;
	public String s_dist_09;
	public String s_dist_10;
	public float total = 0;
	public float ol_amount = 0;
	public int s_quantityInt;
	public int ol_quantityInt;
	ArrayList<String> infoReturn = new ArrayList<>();



	public ArrayList<String> select(String w_id, String d_id, String c_id, Warehouse wrh, District disinfo, Customer custinfo, 
			ArrayList<String> iidList,ArrayList<String> widList, ArrayList<String> cntList)
	{
		if(custinfo.getCdid().equals(d_id) && custinfo.getCwid().equals(w_id))
		{
			c_discount = custinfo.getCdiscount();
			c_last = custinfo.getClast();
			c_credit = custinfo.getCcredit();
			w_tax = wrh.getWtax();
		}
		else
			System.out.println("No");

		if(disinfo.getDwid().equals(w_id))
		{
			d_next_o_id = disinfo.getDnextoid();
			d_tax = disinfo.getDtax();
			System.out.println("Yes");
		}


		if(disinfo.getDwid().equals(w_id))
		{
			int intd_next_o_id = Integer.parseInt(d_next_o_id);
			intd_next_o_id++;
			d_next_o_id = Integer.toString(intd_next_o_id);
		}

		o_id = d_next_o_id;
		o_all_local = "0";

		Order ord = new Order();
		ord.setOid(o_id);
		ord.setOdid(d_id);
		ord.setOwid(w_id);
		ord.setOcid(c_id);
		Date dNow = new Date( );
		SimpleDateFormat ft = new SimpleDateFormat ("yyyy.MM.dd");
		ord.setOentryd(ft.format(dNow));
		ord.setOcarrierid("99999");
		ord.setOolcnt(Integer.toString(cntList.size()));
		ord.setOalllocal(o_all_local);


		CloudantItemStore cld = new CloudantItemStore();
		Database orderdb = ItemAPI.store.database("order", true);

		try{
			cld.persistAllOrder(orderdb,ord);
		}
		catch (Exception e) {
			// TODO: handle exception
			System.out.println("Exceptoion_1:"+ e.getMessage().toString());
		}
		NewOrder nwodr = new NewOrder();
		nwodr.setNooid(o_id);
		nwodr.setNodid(d_id);
		nwodr.setNowid(w_id);
		Database nwodrdb = ItemAPI.store.database("new_oder", true);

		try{
			try {
				Thread.sleep(5000);
				System.out.println("Sleeping ---------------");
			} catch(InterruptedException ex) {
				ex.printStackTrace();
			}
			cld.persistAllNewOrder(nwodrdb, nwodr);
		}
		catch (Exception e) {
			// TODO: handle exception
			System.out.println("Exceptoion_2:"+ e.getMessage().toString());
		}
		int o_ol_cnt = 0;
		for(int i=0;i<cntList.size();i++)
				o_ol_cnt++;

		ArrayList<Integer> priceList = new ArrayList<>();
		ArrayList<String>  itemNameList = new ArrayList<>();
		ArrayList<String>  stockList = new ArrayList<>();
		ArrayList<String>  olDstInfoList = new ArrayList<>();
		
		for (int ol_number=0; ol_number<o_ol_cnt; ol_number++ )
		{	
			// Check all the hardcoded values and find appropriate replacement 
			ol_i_id = iidList.get(ol_number);
			ol_quantity = cntList.get(ol_number);
			try{
				try {
					Thread.sleep(5000);
					System.out.println("Sleeping ---------------");
				} catch(InterruptedException ex) {
					ex.printStackTrace();
				}
				Item it = cld.getItem(ol_i_id);
				i_price = it.get_iPrice();
				i_name = it.get_iName();
				i_data = it.get_iData();
			}
			catch (Exception e) {
				// TODO: handle exception
				System.out.println("Exceptoion_3:"+ e.getMessage().toString());
			}


			priceList.add(Integer.parseInt(i_price));
			itemNameList.add(i_name);

			try{
				try {
					Thread.sleep(5000);
					System.out.println("Sleeping ---------------");
				} catch(InterruptedException ex) {
					ex.printStackTrace();
				}
				Stock stk = cld.getStock(ol_i_id);
				ol_supply_w_id = widList.get(ol_number);
				
				if(stk.getSwid().equals(ol_supply_w_id)) 
				{
					s_quantity=stk.getSquantity();
					s_data=stk.getSdata();
					s_dist_01=stk.getSdist1();
					s_dist_02=stk.getSdist2();
					s_dist_03=stk.getSdist3();
					s_dist_04=stk.getSdist4();
					s_dist_05=stk.getSdist5();
					s_dist_06=stk.getSdist6();
					s_dist_07=stk.getSdist7();
					s_dist_08=stk.getSdist8();
					s_dist_09=stk.getSdist9();
					s_dist_10=stk.getSdist10();

					/*ol_i_id = olDelDList.get(ol_number);*/
					String tempDistLast = d_id.substring(d_id.length()-2, d_id.length());
					olDstInfoList.add("s_dist_"+tempDistLast);
					stockList.add(s_quantity);
					s_quantityInt  = Integer.parseInt(s_quantity);
					ol_quantityInt = Integer.parseInt(ol_quantity);
					
					if( s_quantityInt < ol_quantityInt )
						s_quantityInt = s_quantityInt - ol_quantityInt;
					else
						s_quantityInt = s_quantityInt-ol_quantityInt+91;
					stk.setSquantity(Integer.toString(s_quantityInt));
					Database dbstock = ItemAPI.store.database("stock", true);
				cld.updateStock(dbstock,stk,Integer.toString(s_quantityInt)); // update stock


				ol_amount=ol_quantityInt * Integer.parseInt(i_price)*(Float.parseFloat(w_tax)+Float.parseFloat(d_tax)*((1-Float.parseFloat(c_discount)/100))); 
				System.out.println(ol_amount);
				
				total = total + (ol_amount);

				}
			}
			catch (Exception e) {
				// TODO: handle exception
				System.out.println("Exceptoion_4:"+ e.getMessage().toString());
			}
			
			OrderLine odrLine = new OrderLine();
			odrLine.setOloid(o_id);
			odrLine.setOldid(d_id);
			odrLine.setOlwid(w_id);
			odrLine.setOlnumber(Integer.toString(ol_number));
			odrLine.setOliid(ol_i_id);
			odrLine.setOlsupplywid(ol_supply_w_id);
			odrLine.setOlquantity(ol_quantity);
			odrLine.setOlamount(Float.toString(ol_amount));
			Database oderLinedb = ItemAPI.store.database("oder_line",true );
			cld.persistAllOrderLine(oderLinedb,odrLine);
			
			
			
		}
		
		infoReturn.add(ol_supply_w_id);
		infoReturn.add(ol_i_id);
		infoReturn.add(i_name);
		infoReturn.add(Integer.toString(ol_quantityInt));
		infoReturn.add(Integer.toString(s_quantityInt));
		infoReturn.add("G");
		infoReturn.add(i_price);
		infoReturn.add(Float.toString(ol_amount));
		
		return infoReturn;

		}

	}

