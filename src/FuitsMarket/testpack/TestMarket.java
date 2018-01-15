package FuitsMarket.testpack;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.HashMap;
import java.util.concurrent.TimeoutException;

import org.junit.Test;
import org.junit.runners.model.TestTimedOutException;

import FuitsMarket.Market;

public class TestMarket {
	
	@Test
	public void testUpdateBag()
	{
		Market m =new Market(10);
		HashMap<String,Integer> h=new HashMap<String,Integer>();
		h.put("apple", 3);
		h.put("banana", 3);
		h.put("grapes", 3);
		h.put("cherries", 3);
		
		m.updateBag(true, h);
		
		HashMap<String,Integer> st=m.getMarketStock();
		
		assertTrue(h.get("apple")==st.get("apple"));
		assertTrue(h.get("banana")==st.get("banana"));
		assertTrue(h.get("grapes")==st.get("grapes"));
		assertTrue(h.get("cherries")==st.get("cherries"));
		
		
		m.updateBag(false, h);
		
		st=m.getMarketStock();
		h.put("apple", 0);
		h.put("banana", 0);
		h.put("grapes", 0);
		h.put("cherries", 0);
		
		assertTrue(h.get("apple")==st.get("apple"));
		assertTrue(h.get("banana")==st.get("banana"));
		assertTrue(h.get("grapes")==st.get("grapes"));
		assertTrue(h.get("cherries")==st.get("cherries"));
	}
	@Test(timeout = 100)
	public void testSellToInLimits()
	{
		Market m=new Market(10);
		HashMap<String,Integer> bag=new HashMap<String,Integer>();
		bag.put("apple", 3);
		bag.put("banana", 3);
		bag.put("grapes", 3);
		bag.put("cherries", 0);
		
		try
		{
			m.sellTo(0,bag);
		}
		catch(InterruptedException e){
			e.printStackTrace();
		}
		
		HashMap<String,Integer> st=m.getMarketStock();
		
		assertTrue(bag.get("apple")==st.get("apple"));
		assertTrue(bag.get("banana")==st.get("banana"));
		assertTrue(bag.get("grapes")==st.get("grapes"));
		assertTrue(bag.get("cherries")==st.get("cherries"));
	}
	
	/*@Test(timeout = 100 ,expected=TestTimedOutException.class)
	public void testSellToOutOfLimits() throws InterruptedException
	{
		Market m=new Market(4);
		HashMap<String,Integer> bag=new HashMap<String,Integer>();
		bag.put("apple", 4);
		bag.put("banana", 4);
		bag.put("grapes", 4);
		bag.put("cherries", 4);
		
		//try
		//{
			m.sellTo(0,bag);
		//}
		//catch(InterruptedException e){
			//e.printStackTrace();
		
		
		HashMap<String,Integer> st=m.getMarketStock();
		
		assertTrue(bag.get("apple")==st.get("apple"));
		assertTrue(bag.get("banana")==st.get("banana"));
		assertTrue(bag.get("grapes")==st.get("grapes"));
		assertTrue(bag.get("cherries")==st.get("cherries"));
	}*/
	@Test(timeout = 100)
	public void testPerchaseFromInLimits()
	{
		Market m=new Market(10);
		HashMap<String,Integer> bag=new HashMap<String,Integer>();
		bag.put("apple", 3);
		bag.put("banana", 3);
		bag.put("grapes", 3);
		bag.put("cherries", 0);
		
		try
		{
			m.sellTo(0,bag);
			m.purchaseFrom(0,bag);
		}
		catch(InterruptedException e){
			e.printStackTrace();
		}
		
		HashMap<String,Integer> st=m.getMarketStock();
		bag.put("apple", 0);
		bag.put("banana", 0);
		bag.put("grapes", 0);
		bag.put("cherries", 0);
		
		assertTrue(bag.get("apple")==st.get("apple"));
		assertTrue(bag.get("banana")==st.get("banana"));
		assertTrue(bag.get("grapes")==st.get("grapes"));
		assertTrue(bag.get("cherries")==st.get("cherries"));
	}
}
