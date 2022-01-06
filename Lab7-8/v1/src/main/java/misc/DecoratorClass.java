package misc;

import javax.decorator.Decorator;
import javax.decorator.Delegate;
import javax.enterprise.inject.Any;
import javax.inject.Inject;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;

import beans.User;
import interfaces.UserInterface;

@Decorator
public abstract class DecoratorClass implements UserInterface {
    
    @Inject
    @Delegate
    UserInterface user;
    
	public String show() {
		String s = user.show();
		return s + "//";
	}
}
