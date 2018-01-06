package FuitsMarket;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

public class TestMarketSimulation {
	
	public static void main(String[] args)
	{
		
		int marketCapacity = 100;
		Market market=new Market(marketCapacity);
		
		while(1)
		{
			int arrivalType = ThreadLocalRandom.current().nextInt(0, 2);
		
		
			if(arrivalType == 0)
			{
				Farmer f=new Farmer(market);
				Thread t=new Thread(f);
				t.start();
			}
				//code for farmer
				System.out.println("Farmer has come to sell.");
			
			int numOfApples = ThreadLocalRandom.current().nextInt(0, 100);
			int numOfBananas = ThreadLocalRandom.current().nextInt(0, 100);
			int numOfGrapes = ThreadLocalRandom.current().nextInt(0, 100);
			int numOfCherries = ThreadLocalRandom.current().nextInt(0, 100);
			
			Map<String,Integer> sbag = new HashMap<String,Integer>();
			sbag.put("apple", numOfApples);
			sbag.put("banana", numOfBananas);
			sbag.put("grapes", numOfGrapes);
			sbag.put("cherries", numOfCherries);
		}
		else if(arrivalType == 1)
		{
			//code for consumer
		}
	}
}
