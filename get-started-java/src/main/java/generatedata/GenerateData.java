package generatedata;

import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;
import java.util.stream.Collectors;

public class GenerateData {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			//createItemData();
			createStockData();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void createStockData() throws IOException {
		
		int elements = 100;
		ArrayList<String> iid = new ArrayList<>();
		FileWriter writer = new FileWriter("stock.csv");
		for(int i=0; i<elements; i++)
		{	
			iid = new ArrayList<>();
			iid.add(Integer.toString(random(1000,9999)));
			
			iid.add(Integer.toString(random(100,500)));
			iid.add(Integer.toString(random(100,500)));
			iid.add(Integer.toString(random(100,500)));
			iid.add(Integer.toString(random(100,500)));
			iid.add(Integer.toString(random(100,500)));
			iid.add(Integer.toString(random(100,500)));
			iid.add(Integer.toString(random(100,500)));
			iid.add(Integer.toString(random(100,500)));
			iid.add(Integer.toString(random(100,500)));
			iid.add(Integer.toString(random(100,500)));
			iid.add(date());
			iid.add(getSaltString(10));
			iid.add(Integer.toString(random(100, 200)));
			iid.add(Integer.toString(random(100, 200)));
			String collect = iid.stream().collect(Collectors.joining(","));
			writer.append(collect);
			writer.append("\n");
		}
		writer.close();
		
		
		
		
		
	}

	public static void createItemData() throws IOException
	{
		int elements = 100;
		ArrayList<String> iid = new ArrayList<>();
		FileWriter writer = new FileWriter("item.csv");
		for(int i=0; i<elements; i++)
		{	
			iid = new ArrayList<>();
			iid.add(Integer.toString(random(20000,80000)));
			iid.add(Integer.toString(random(20000,80000)));
			iid.add(getSaltString(10));
			iid.add(Integer.toString(random(100, 200)));
			iid.add(getSaltString(50));
			String collect = iid.stream().collect(Collectors.joining(","));
			writer.append(collect);
			writer.append("\n");
		}
		writer.close();
	}



	static String getSaltString(int size) {
		String SALTCHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
		StringBuilder salt = new StringBuilder();
		Random rnd = new Random();
		while (salt.length() < size) { // length of the random string.
			int index = (int) (rnd.nextFloat() * SALTCHARS.length());
			salt.append(SALTCHARS.charAt(index));
		}
		String saltStr = salt.toString();
		return saltStr;

	}


	static String getString(int num)
	{	

		String name = "";
		while(num>10)
		{
			int nChar = num%10;
			char c = (char)nChar;
			name=name+c;
			num=num/10;
		}


		return name;
	}

	static int random(int left, int right)
	{
		String rString;
		int R  =(int)( left + (int) (Math.random()*(right - left)));

		return R;

	}
	
	static String date()
	
	{
		Date dNow = new Date( );
	      SimpleDateFormat ft = 
	      new SimpleDateFormat ("yyyy.MM.dd");
	      return ft.format(dNow);
	}
}	
