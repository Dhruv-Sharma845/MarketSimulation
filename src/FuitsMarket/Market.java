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
	public synchronized void showMarketCondition()
	{
		System.out.println("\t\t\t\t\tapple banana grapes cherries");
		System.out.print("\t\t\t\t\t"+Stock.get("apple")+"     ");
		System.out.print(Stock.get("banana")+"      ");
		System.out.print(Stock.get("grapes")+"      ");
		System.out.println(Stock.get("cherries"));
	}
	public synchronized void sellTo(int id,HashMap<String,Integer> sellingBag) throws InterruptedException
	{
		System.out.println("Farmer "+id+" has come to sell.");
		System.out.println("Farmer wants to sell:");
		showBag(sellingBag,true);
		
		int tempSum=capacity;
		while(remSpace <= tempSum)
		{
			tempSum=0;
			for(Map.Entry<String, Integer> entry : sellingBag.entrySet())
				tempSum +=entry.getValue();	
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
		updateBag(true,sellingBag);
		notifyAll();
		System.out.println("Farmer "+id+" is done with the selling.");
	}
	public synchronized void purchaseFrom(int id,HashMap<String,Integer> purchasingBag) throws InterruptedException
	{
		System.out.println("\t\t\t\t\t\t\t\t\tConsumer "+id+" has come to purchase.");
		System.out.println("\t\t\t\t\t\t\t\t\tConsumer wants to purchase: ");
		showBag(purchasingBag,false);
		
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
				System.out.println("\t\t\t\t\t\t\t\t\tConsumer is waiting...");
				wait();
			}
			else
			{
				remSpace+=tempSum;
				break;	
			}
		}
		updateBag(false,purchasingBag);
		notifyAll();
		System.out.println("\t\t\t\t\t\t\t\t\tConsumer " + id +" is done with the purchasing.");
	}
	public void showBag(HashMap<String,Integer> fBag,boolean isFarmer)
	{
		if(!isFarmer)
		{
			System.out.print("\t\t\t\t\t\t\t\t\t");
		}
		System.out.println("apple: "+fBag.get("apple"));
		if(!isFarmer)
		{
			System.out.print("\t\t\t\t\t\t\t\t\t");
		}
		System.out.println("banana: "+fBag.get("banana"));
		if(!isFarmer)
		{
			System.out.print("\t\t\t\t\t\t\t\t\t");
		}
		System.out.println("grapes: "+fBag.get("grapes"));
		if(!isFarmer)
		{
			System.out.print("\t\t\t\t\t\t\t\t\t");
		}
		System.out.println("cherries: "+fBag.get("cherries"));
	}
	public void updateBag(boolean isFarmer,HashMap<String,Integer> fBag)
	{
		for(Map.Entry<String, Integer> entry : fBag.entrySet())
		{
			if(Stock.containsKey(entry.getKey()))
			{
				if(!isFarmer)
					Stock.put(entry.getKey(), Stock.get(entry.getKey())- entry.getValue());
				else if(isFarmer)
					Stock.put(entry.getKey(), entry.getValue() + Stock.get(entry.getKey()));
			}
			else
			{
				Stock.put(entry.getKey(), entry.getValue());
			}
		}
	}
	public HashMap<String,Integer> getMarketStock()
	{
		return (HashMap<String,Integer>)Stock.clone();
	}
}
