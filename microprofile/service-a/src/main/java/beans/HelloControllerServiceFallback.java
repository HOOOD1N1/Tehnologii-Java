package beans;

import org.eclipse.microprofile.faulttolerance.ExecutionContext;
import org.eclipse.microprofile.faulttolerance.FallbackHandler;

import Lab910.microprofile.HelloController;

public class HelloControllerServiceFallback implements FallbackHandler<HelloController> {

	@Override
	public HelloController handle(ExecutionContext context) {
		
		context.getMethod();
		context.getParameters();

		
		return null;
	}

}
