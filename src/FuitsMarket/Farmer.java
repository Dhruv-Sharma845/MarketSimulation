package FuitsMarket;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

public class Farmer implements Runnable{
	
	Market market;
	int FarmerID;
	int prodCapacity;
	
	public Farmer(int _id,Market market,int _prodCap)
	{
		FarmerID = _id;
		this.market=market;
		prodCapacity=_prodCap;
	}
	public void run()
	{
		//code for farmer
		HashMap<String,Integer> sbag = new HashMap<String,Integer>();
		generateBag(sbag);
		
		try {
			market.sellTo(FarmerID,sbag);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		market.showMarketCondition();
	}
	
	public void generateBag(HashMap<String,Integer> sbag)
	{
		int numOfApples = ThreadLocalRandom.current().nextInt(0, prodCapacity);
		int numOfBananas = ThreadLocalRandom.current().nextInt(0, prodCapacity);
		int numOfGrapes = ThreadLocalRandom.current().nextInt(0, prodCapacity);
		int numOfCherries = ThreadLocalRandom.current().nextInt(0, prodCapacity);
	
		sbag.put("apple", numOfApples);
		sbag.put("banana", numOfBananas);
		sbag.put("grapes", numOfGrapes);
		sbag.put("cherries", numOfCherries);
	
	}
}
