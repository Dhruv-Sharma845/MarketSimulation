package FuitsMarket;

import java.util.HashMap;
import java.util.Map;

public class Market {

	private HashMap<String,Integer> Stock;
	private int capacity;
	private int remSpace;
	
	public Market(int _cap)
	{
		capacity = _cap;
		remSpace = _cap;
		
		Stock=new HashMap<String,Integer>();
	}
	public synchronized void sellTo(HashMap<String,Integer> sellingBag) throws InterruptedException
	{
		int tempSum=capacity;
		while((tempSum+remSpace) > capacity)
		{
			tempSum=0;
			for(Map.Entry<String, Integer> entry : sellingBag.entrySet())
			{
				tempSum +=entry.getValue();
			
			}
			if((tempSum+remSpace) > capacity)
			{
				wait();
			}
			else
				break;
		}
		for(Map.Entry<String, Integer> entry : sellingBag.entrySet())
		{
			if(Stock.containsKey(entry.getKey()))
			{
				Stock.put(entry.getKey(), entry.getValue() + Stock.get(entry.getKey()));
			}
			else
			{
				Stock.put(entry.getKey(), entry.getValue());
			}
		}
		notifyAll();
	}
	public synchronized void purchaseFrom(HashMap<String,Integer> purchasingBag) throws InterruptedException
	{
		int tempSum=0;
		while(tempSum > (capacity-remSpace) )
		{
			tempSum=0;
			for(Map.Entry<String, Integer> entry : purchasingBag.entrySet())
			{
				tempSum +=entry.getValue();
			}
			if(tempSum > (capacity-remSpace) )
			{
				wait();
			}
			else
				break;
		}
		for(Map.Entry<String, Integer> entry : purchasingBag.entrySet())
		{
			if(Stock.containsKey(entry.getKey()))
			{
				Stock.put(entry.getKey(), Stock.get(entry.getKey())- entry.getValue());
			}
			else
			{
				Stock.put(entry.getKey(), entry.getValue());
			}
		}
		notifyAll();
	}
	
}
