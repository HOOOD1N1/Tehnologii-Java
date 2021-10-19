package lab2.classes;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Controller
 */
@WebServlet("/controller")
public class Controller extends HttpServlet {

	DataManager data = new DataManager();
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String category = request.getParameter("category");
		String key = request.getParameter("key");
		String value = request.getParameter("value");
		Cookie cookie = null;
		
		if(category != null) {
			data.addData(category, key, value);
			cookie = new Cookie("category", category);
		}else {
			ServletContext sc = getServletContext();
			data.addData((String)sc.getAttribute("initialCategoryValue"), key, value);
			cookie = new Cookie("category",(String)sc.getAttribute("initialCategoryValue") );
		}
		response.addCookie(cookie);
		request.setAttribute("data", data);
		getServletContext().getRequestDispatcher("/result.jsp").forward(request, response);

	}

}
