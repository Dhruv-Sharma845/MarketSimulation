package FuitsMarket.testpack;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.HashMap;

import org.junit.Test;
import org.mockito.Mockito;

import FuitsMarket.Consumer;
import FuitsMarket.Market;

public class TestConsumer {

	@Test
	public void testGenerateBag()
	{
		Market m = Mockito.mock(Market.class);
		Consumer f =new Consumer(0,m,10);
		
		HashMap<String,Integer> b =new HashMap<String,Integer>();
		f.generateBag(b);
		
		assertTrue(b.get("apple") < 10);
		assertTrue(b.get("banana") < 10);
		assertTrue(b.get("grapes") < 10);
		assertTrue(b.get("cherries") < 10);
	}
}
