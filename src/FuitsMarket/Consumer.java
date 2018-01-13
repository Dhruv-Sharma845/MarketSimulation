package FuitsMarket;

import java.util.HashMap;
import java.util.concurrent.ThreadLocalRandom;

public class Consumer implements Runnable{
	Market market;
	int ConsumerID;
	
	public Consumer(int id,Market market)
	{
		this.market=market;
		ConsumerID = id;
	}
	public void run()
	{
		//code for farmer
		HashMap<String,Integer> pbag = new HashMap<String,Integer>();
		generateBag(pbag);
			
		try {
			market.purchaseFrom(ConsumerID,pbag);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		market.showMarketCondition();
	}
	public void generateBag(HashMap<String,Integer> pbag)
	{
		int numOfApples = ThreadLocalRandom.current().nextInt(0, 5);
		int numOfBananas = ThreadLocalRandom.current().nextInt(0, 5);
		int numOfGrapes = ThreadLocalRandom.current().nextInt(0, 5);
		int numOfCherries = ThreadLocalRandom.current().nextInt(0, 5);
		
		pbag.put("apple", numOfApples);
		pbag.put("banana", numOfBananas);
		pbag.put("grapes", numOfGrapes);
		pbag.put("cherries", numOfCherries);
	}
}
