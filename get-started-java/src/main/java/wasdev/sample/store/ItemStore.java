/*
 * Copyright IBM Corp. 2017
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
 */
package wasdev.sample.store;

import java.util.Collection;

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

/**
 * Defines the API for a ToDo store.
 *
 */
public interface ItemStore {

  	/**
	 * Get the target db object.
	 * 
	 * @return Database.
  	 * @throws Exception 
	 */
  public Database getDB();
  
  	/**
	 * Gets all Items from the store.
	 * 
	 * @return All Items.
  	 * @throws Exception 
	 */
  public Collection<Item> getAll(Database db);
  public Collection<Stock> getAllStock(Database db);

  /**
   * Gets an individual ToDo from the store.
   * @param id The ID of the ToDo to get.
   * @return The ToDo.
   */
  public Item get(String id);

  /**
   * Persists a Item to the store.
   * @param td The ToDo to persist.
   * @return The persisted ToDo.  The ToDo will not have a unique ID..
   */
  public Item persist(Item vi);

  /**
   * Updates a ToDo in the store.
   * @param id The ID of the Item to update.
   * @param td The Item with updated information.
   * @return The updated Item.
   */
  public Item update(String id, Item vi);

  /**
   * Deletes a ToDo from the store.
   * @param id The ID of the Item to delete.
   */
  public void delete(String id);
  
  /**
   * Counts the number of Items
   * @return The total number of Items.
 * @throws Exception 
   */
  public int count(Database db) throws Exception;
  
  public void persistAllStock(Database db); 
  public void persistAllOrder(Database db,Order or);
  
  public void persistAllNewOrder(Database db, NewOrder nword);
  
  public void persistAllOrderLine(Database db, OrderLine odrl);
  
  public void persistAllWarehouse(Database db);
  
  public void persistAllDistrict(Database db);
  
  public void persistAllCustomer(Database db);
  
  public void persistAllHistor(Database db);
  
  public void persistAllItem(Database db);
  
  public void persistAllHistory(Database db);
  
  public void setTargetDB(String id);
  
  public Customer getCustomer(String id);
  
  public District getDistrict(String id);
  
  public History getHistory();
  
  public NewOrder getNewOrder();
  
  public Stock getStock(String id );
  
  public Warehouse getWareHouse(String id);
  
  public Order getOrder();
  
  public OrderLine getOrderLine();
  
  public Item getItem(String id);
  
  public void updateStock(Database dbstock,Stock stk,String str);
}
