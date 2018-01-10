package FuitsMarket;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

public class Farmer implements Runnable{
	
	Market market;
	int FarmerID;
	
	public Farmer(int _id,Market market)
	{
		FarmerID = _id;
		this.market=market;
	}
	public void run()
	{
		//code for farmer
		
	
		int numOfApples = ThreadLocalRandom.current().nextInt(0, 10);
		int numOfBananas = ThreadLocalRandom.current().nextInt(0, 10);
		int numOfGrapes = ThreadLocalRandom.current().nextInt(0, 10);
		int numOfCherries = ThreadLocalRandom.current().nextInt(0, 10);
	
		HashMap<String,Integer> sbag = new HashMap<String,Integer>();
		sbag.put("apple", numOfApples);
		sbag.put("banana", numOfBananas);
		sbag.put("grapes", numOfGrapes);
		sbag.put("cherries", numOfCherries);
		
		
		try {
			market.sellTo(FarmerID,sbag);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		market.showMarketCondition();
	}
}
