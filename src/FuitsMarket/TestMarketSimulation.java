package FuitsMarket;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

public class TestMarketSimulation {
	
	public static void main(String[] args)
	{
		
		int marketCapacity = 100;
		Market market=new Market(marketCapacity);
		
		int counter=0;
		int totCount=4;
		while(counter<totCount)
		{
			counter++;
			int arrivalType = ThreadLocalRandom.current().nextInt(0, 2);
			if(arrivalType == 0)
			{
				Farmer f=new Farmer(counter,market);
				Thread t=new Thread(f);
				t.start();
			}
			else if(arrivalType == 1)
			{
				Consumer c=new Consumer(counter,market);
				Thread t=new Thread(c);
				t.start();
			}
		}
	}
}
