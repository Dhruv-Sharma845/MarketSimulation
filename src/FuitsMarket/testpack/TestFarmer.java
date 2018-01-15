package FuitsMarket.testpack;

import org.junit.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

import java.util.HashMap;

import FuitsMarket.Farmer;
import FuitsMarket.Market;

public class TestFarmer {
	
	@Test
	public void testGenerateBag()
	{
		Market m = Mockito.mock(Market.class);
		Farmer f =new Farmer(0,m,10);
		
		HashMap<String,Integer> b =new HashMap<String,Integer>();
		f.generateBag(b);
		
		assertTrue(b.get("apple") < 10);
		assertTrue(b.get("banana") < 10);
		assertTrue(b.get("grapes") < 10);
		assertTrue(b.get("cherries") < 10);
	}
}
