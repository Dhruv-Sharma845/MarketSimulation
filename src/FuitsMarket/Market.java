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
		Stock.put("apple", 0);
		Stock.put("banana", 0);
		Stock.put("grapes", 0);
		Stock.put("cherries", 0);
	}
	public synchronized void sellTo(HashMap<String,Integer> sellingBag) throws InterruptedException
	{
		int tempSum=capacity;
		while(remSpace < tempSum)
		{
			tempSum=0;
			for(Map.Entry<String, Integer> entry : sellingBag.entrySet())
			{
				tempSum +=entry.getValue();
				
			}
			if(remSpace<tempSum)
			{
				System.out.println("Farmer is waiting..");
				wait();
			}
			else
			{
				remSpace-=tempSum;
				break;	
			}
			
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
		boolean flag=false;
		
		int tempSum=capacity;
		while(flag==false )
		{
			flag=true;
			tempSum=0;
			for(Map.Entry<String, Integer> entry : purchasingBag.entrySet())
			{
				if(entry.getValue() > Stock.get(entry.getKey()) )
					flag=false;
				tempSum +=entry.getValue();
			}
			if(flag==false )
			{
				System.out.println("Consumer is waiting...");
				wait();
			}
			else
			{
				remSpace+=tempSum;
				break;	
			}
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
