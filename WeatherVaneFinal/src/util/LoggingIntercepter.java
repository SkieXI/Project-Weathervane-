package util;

import java.io.Serializable;

import javax.interceptor.AroundInvoke;
import javax.interceptor.InvocationContext;
/**
 * @Author Jerran Fredricks/Joe Lean
 **/
public class LoggingIntercepter implements Serializable {

	private static final long serialVersionUID = -8801794374011675197L;

	@AroundInvoke
	public Object methodInterceptor(InvocationContext ctx)throws Exception{
		
		System.out.println("******************* Intercepting call to method: " + 
				ctx.getTarget().getClass() + "." + ctx.getMethod().getName() + "()");
		return ctx.proceed();
		
	}
	
}
