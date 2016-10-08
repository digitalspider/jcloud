package au.com.jcloud.actionbean;

import net.sourceforge.stripes.action.*;
import net.sourceforge.stripes.validation.Validate;

/**
 * A RESTful Calculator Action Bean
 *
 * @author Rick Grashel
 */
@RestActionBean
@UrlBinding( "/calculate.action" )
public class CalculatorRestActionBean implements ActionBean {

	@Validate(required=true) private double numberOne;
	@Validate(required=true) private double numberTwo;

	/**
	 * This resolution will take the two numbers passed in,
	 * add them together, and return the result as JSON.
	 */
	public Resolution post() {
		double result = numberOne + numberTwo;
//		return null;
		return new JsonResolution( Double.toString( result ) );
	}

	public void setNumberOne( double numberOne ) { this.numberOne = numberOne; }
	public void setNumberTwo( double numberTwo ) { this.numberTwo = numberTwo; }

	// Below is needed for all action beans.
	private ActionBeanContext context;
	public ActionBeanContext getContext() { return context; }
	public void setContext(ActionBeanContext context) { this.context = context; }
}