package lab2.classes;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebFilter("/result.jsp")
public class ResultFilter implements Filter {

    public ResultFilter() {
        // TODO Auto-generated constructor stub
    }

	public void destroy() {
		// TODO Auto-generated method stub
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		System.out.println("It works");
		
		ResponseWrapper wrapper
        = new ResponseWrapper((HttpServletResponse) response);
		HttpServletRequest req = (HttpServletRequest) request;
		
		System.out.println("The new request is " + req.getQueryString());
		
		chain.doFilter(request, wrapper);
		String content = wrapper.toString();
	    // Modify the content
		content = "<h1>New Page</h1>";
	    content += "<h2>Have a nice day!</h2>";
	  //Send the modified content using the original response 
	    PrintWriter out = response.getWriter();
	    out.write(content);
	}

	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
