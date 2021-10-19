package lab2.classes;

import java.io.PrintWriter;
import java.io.StringWriter;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;

public class ResponseWrapper extends HttpServletResponseWrapper {
	private final StringWriter output;
    
	public ResponseWrapper(HttpServletResponse response) {
        super(response);
        output = new StringWriter();
    }
    @Override
    public PrintWriter getWriter() {
        // Hide the original writer
        return new PrintWriter(output);
    }
    @Override
    public String toString() {
        return output.toString();
    }
}

