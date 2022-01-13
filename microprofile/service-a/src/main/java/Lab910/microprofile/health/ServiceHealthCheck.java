package Lab910.microprofile.health;

import org.eclipse.microprofile.health.Health;
//import org.eclipse.microprofile.health.Readiness;
//import org.eclipse.microprofile.health.Liveness;
import org.eclipse.microprofile.health.HealthCheck;
import org.eclipse.microprofile.health.HealthCheckResponse;
import org.eclipse.microprofile.health.HealthCheckResponseBuilder;


import javax.enterprise.context.ApplicationScoped;

//@Readiness
//@Liveness
@Health
@ApplicationScoped
public class ServiceHealthCheck implements HealthCheck {
	
    @Override
    public HealthCheckResponse call() {

    	return HealthCheckResponse
    			.named(ServiceHealthCheck.class.getSimpleName())
    			.withData("ready",true)
    			.up() //or .status(getMemUsage() < 0.9)
    			.build();
    	

    }
}
