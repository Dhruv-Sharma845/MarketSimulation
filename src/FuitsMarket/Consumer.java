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
		System.out.println("Consumer has come to purchase.");
	
		int numOfApples = ThreadLocalRandom.current().nextInt(0, 5);
		int numOfBananas = ThreadLocalRandom.current().nextInt(0, 5);
		int numOfGrapes = ThreadLocalRandom.current().nextInt(0, 5);
		int numOfCherries = ThreadLocalRandom.current().nextInt(0, 5);
	
		HashMap<String,Integer> pbag = new HashMap<String,Integer>();
		pbag.put("apple", numOfApples);
		pbag.put("banana", numOfBananas);
		pbag.put("grapes", numOfGrapes);
		pbag.put("cherries", numOfCherries);
		
		System.out.println("Consumer wants to purchase: ");
		System.out.println("apple: "+numOfApples);
		System.out.println("banana: "+numOfBananas);
		System.out.println("grapes: "+numOfGrapes);
		System.out.println("cherries: "+numOfCherries);
		
		try {
			market.purchaseFrom(pbag);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		
		
		System.out.println("Consumer is done with the purchasing.");
	}
}
