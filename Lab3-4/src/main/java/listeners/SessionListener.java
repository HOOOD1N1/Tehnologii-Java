package listeners;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;


@WebListener
public class SessionListener implements HttpSessionListener {

	private static int numberOfUsersOnline = 0;
	  
    public SessionListener() {
        // TODO Auto-generated constructor stub
    }
    
    public static int getNumberOfUsersOnline() { 
    	  return numberOfUsersOnline;
    	 }

	
    public void sessionCreated(HttpSessionEvent event)  { 
         // TODO Auto-generated method stub
    	 System.out.println("Session created by Id : " + event.getSession().getId());
         synchronized (this) {
       numberOfUsersOnline++;
      }
    }

	
    public void sessionDestroyed(HttpSessionEvent event)  { 

    	 System.out.println("Session destroyed by Id : " + event.getSession().getId());
         synchronized (this) {
       numberOfUsersOnline--;
      }
    }
	
}
