package agents;

import jade.core.Agent;
import behaviors.ParamedicsBehaviour;

public class ParamedicsAgent extends Agent {

	/**
	 * The paramedics class that cures all the injured people
	 * in case of:
	 *  - unintentional fire
	 *  - car accident
	 *  - rebellion
	 *  
	 *  @author Dabiste Sorin
	 */
	private static final long serialVersionUID = 1L;

	protected void setup() {
		System.out.println(this.getLocalName() + " is ready.");
		addBehaviour(new ParamedicsBehaviour(this));
	}
 
}
