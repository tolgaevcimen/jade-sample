package agents;

import behaviors.FireDepartmentBehaviour;
import jade.core.Agent;

public class FireDepartmentAgent extends Agent {

	/**
	 * The fire department class that cures all the injured people
	 * in case of:
	 *  - unintentional fire
	 *  - intentional fire
	 *  - car accident
	 *  
	 *  @author Dabiste Sorin
	 */
	private static final long serialVersionUID = 1L;

	protected void setup() {
		System.out.println(this.getLocalName() + " is ready.");
		addBehaviour(new FireDepartmentBehaviour(this));
	}
}
