package FuitsMarket;

import java.util.HashMap;
import java.util.concurrent.ThreadLocalRandom;

public class Consumer implements Runnable{
	Market market;
	int ConsumerID;
	int conCapacity;
	
	public Consumer(int id,Market market,int _conCapacity)
	{
		this.market=market;
		ConsumerID = id;
		conCapacity=_conCapacity;
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
		int numOfApples = ThreadLocalRandom.current().nextInt(0, conCapacity);
		int numOfBananas = ThreadLocalRandom.current().nextInt(0, conCapacity);
		int numOfGrapes = ThreadLocalRandom.current().nextInt(0, conCapacity);
		int numOfCherries = ThreadLocalRandom.current().nextInt(0, conCapacity);
		
		pbag.put("apple", numOfApples);
		pbag.put("banana", numOfBananas);
		pbag.put("grapes", numOfGrapes);
		pbag.put("cherries", numOfCherries);
	}
}
