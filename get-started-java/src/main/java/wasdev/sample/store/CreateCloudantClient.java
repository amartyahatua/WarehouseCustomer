package wasdev.sample.store;

import java.net.URL;

import com.cloudant.client.api.ClientBuilder;
import com.cloudant.client.api.CloudantClient;
import com.google.gson.JsonObject;

public  class CreateCloudantClient  {
	
	
	public static CloudantClient createClient() {

		String url;

		if (System.getenv("VCAP_SERVICES") != null) {
			// When running in Bluemix, the VCAP_SERVICES env var will have the credentials for all bound/connected services
			// Parse the VCAP JSON structure looking for cloudant.
			JsonObject cloudantCredentials = VCAPHelper.getCloudCredentials("cloudant");
			if(cloudantCredentials == null){
				System.out.println("No cloudant database service bound to this application");
				return null;
			}
			url = cloudantCredentials.get("url").getAsString();
		} else {
			System.out.println("Running locally. Looking for credentials in cloudant.properties");
			url = VCAPHelper.getLocalProperties("cloudant.properties").getProperty("cloudant_url");
			if(url == null || url.length()==0){
				System.out.println("To use a database, set the Cloudant url in src/main/resources/cloudant.properties");
				return null;
			}
		}

		try {
			System.out.println("Connecting to Cloudant");
			CloudantClient client = ClientBuilder.url(new URL(url)).build();
			return client;
		} catch (Exception e) {
			System.out.println("Unable to connect to database");
			//e.printStackTrace();
			return null;
		}
	}
	
}