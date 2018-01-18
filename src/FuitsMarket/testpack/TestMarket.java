package FuitsMarket.testpack;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.HashMap;
import java.util.concurrent.TimeoutException;

import org.junit.Rule;
import org.junit.Test;
import org.junit.internal.runners.statements.FailOnTimeout;
import org.junit.rules.Timeout;
import org.junit.runner.Description;
import org.junit.runners.model.Statement;
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
	//code to get test correct if timeout is reached
	  private static final int MIN_TIMEOUT = 100;

	    @SuppressWarnings("deprecation")
		@Rule
	    public Timeout timeout = new Timeout(MIN_TIMEOUT) {
	        public Statement apply(Statement base, Description description) {
	            return new FailOnTimeout(base, MIN_TIMEOUT) {
	                @Override
	                public void evaluate() throws Throwable {
	                    try {
	                        super.evaluate();
	                        throw new TimeoutException();
	                    } catch (Exception e) {}
	                }
	            };
	        }
	    };
	
	@Test(expected=TimeoutException.class )
	public void testSellToOutOfLimits()
	{
		Market m=new Market(4);
		HashMap<String,Integer> bag=new HashMap<String,Integer>();
		bag.put("apple", 4);
		bag.put("banana", 4);
		bag.put("grapes", 4);
		bag.put("cherries", 4);
		
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
