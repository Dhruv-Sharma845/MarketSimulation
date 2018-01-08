package FuitsMarket;

import java.util.HashMap;
import java.util.concurrent.ThreadLocalRandom;

public class Consumer implements Runnable{
	Market market;
	
	public Consumer(Market market)
	{
		this.market=market;
	}
	public void run()
	{
		//code for farmer
	
		int numOfApples = ThreadLocalRandom.current().nextInt(0, 5);
		int numOfBananas = ThreadLocalRandom.current().nextInt(0, 5);
		int numOfGrapes = ThreadLocalRandom.current().nextInt(0, 5);
		int numOfCherries = ThreadLocalRandom.current().nextInt(0, 5);
	
		HashMap<String,Integer> pbag = new HashMap<String,Integer>();
		pbag.put("apple", numOfApples);
		pbag.put("banana", numOfBananas);
		pbag.put("grapes", numOfGrapes);
		pbag.put("cherries", numOfCherries);
		
		
		try {
			market.purchaseFrom(pbag);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		market.showMarketCondition();
	}
}
