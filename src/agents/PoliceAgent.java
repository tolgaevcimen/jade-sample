package agents;

import behaviors.PoliceBehaviour;
import jade.core.Agent;


public class PoliceAgent extends Agent {

	/**
	 * The police class that cures all the injured people
	 * in case of:
	 *  - intentional fire
	 *  - bank robbery
	 *  - car accident
	 *  - rebellion
	 *  
	 *  @author Dabiste Sorin
	 */
	private static final long serialVersionUID = 1L;

	protected void setup() {
		System.out.println(this.getLocalName() + " is ready.");
		addBehaviour(new PoliceBehaviour(this));
	}
}


