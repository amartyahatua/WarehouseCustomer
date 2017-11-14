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
package wasdev.sample;

/**
 * Represents a Visitor document stored in Cloudant.
 */

public class Item {
	private String _id;
	private String _rev;
	private String nameOfVisitor = null;
	private String iImId;
	private String iName;
	private String iPrice;
	private String iData;

	public Item() {
		this.nameOfVisitor = "";
	}

	/**
	 * Gets the ID.
	 * 
	 * @return The ID.
	 */
	public String get_id() {
		return _id;
	}

	/**
	 * Sets the ID
	 * 
	 * @param _id
	 *            The ID to set.
	 */
	public void set_id(String _id) {
		this._id = _id;
	}

	/**
	 * Gets the revision of the document.
	 * 
	 * @return The revision of the document.
	 */
	public String get_rev() {
		return _rev;
	}

	/**
	 * Sets the revision.
	 * 
	 * @param _rev
	 *            The revision to set.
	 */
	public void set_rev(String _rev) {
		this._rev = _rev;
	}
	
	/**
	 * Gets the visitorName of the document.
	 * 
	 * @return The name of the document.
	 */
	public String getName() {
		return nameOfVisitor;
	}

	/**
	 * Sets the name
	 * 
	 * @param name
	 *            The visitorName to set.
	 */
	public void setName(String visitorName) {
		this.nameOfVisitor = visitorName;
	}
	
	public String get_iImId() {
		return iImId;
	}

	public void set_iImId(String _iImId) {
		this.iImId = _iImId;
	}

	public String get_iName() {
		return iName;
	}

	public void set_iName(String _iName) {
		this.iName = _iName;
	}

	public String get_iPrice() {
		return iPrice;
	}

	public void set_iPrice(String _iPrice) {
		this.iPrice = _iPrice;
	}

	public String get_iData() {
		return iData;
	}

	public void set_iData(String _iData) {
		this.iData = _iData;
	}
	

}