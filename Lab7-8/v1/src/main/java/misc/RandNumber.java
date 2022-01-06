package misc;

import javax.enterprise.inject.Produces;

import interfaces.TextInterface;

@TextInterface
public class RandNumber {
	private java.util.Random random = 
		    new java.util.Random( System.currentTimeMillis() );

	private java.util.Random getRandom() {
		        return random;
		}
	@Produces @TextInterface int next() {
	    return getRandom().nextInt(12);
	}	
}
