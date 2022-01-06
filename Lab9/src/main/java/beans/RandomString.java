package beans;

import java.util.Random;

public class RandomString {

    private final Random random = new Random();

    private final String symbols="1234567890asdqrsdjcnvxpoi,mn!@#%^&*";

    private char[] buf;
    /**
     * Generate a random string.
     */
    public String nextString() {
        for (int idx = 0; idx < buf.length; ++idx)
            buf[idx] = symbols.charAt(random.nextInt(symbols.length()));
        return new String(buf);
    }






}
