package behaviors;

import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.SimpleBehaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;

public class FireDepartmentBehaviour extends SimpleBehaviour {
	
	/**
	 * The behavior class that reports the appropriate message about its action.
	 *  - busy
	 *  - estinguished 
	 *  
	 *  @author Dabiste Sorin
	 */
	private static final long serialVersionUID = 1L;
	private static final MessageTemplate mt = MessageTemplate.MatchPerformative(ACLMessage.REQUEST);
	
	public FireDepartmentBehaviour(Agent agent) {
		super(agent);
	}
	
	public void action() {
		ACLMessage aclMessage = myAgent.receive(mt);

		if (aclMessage!=null) {
			if(aclMessage.getContent().compareTo("Unintentional Fire") == 0 
					|| aclMessage.getContent().compareTo("Intentional Fire") == 0
					|| aclMessage.getContent().compareTo("Car Accident") == 0)
				Report();
		} else {
			this.block();
		}
	}
	
	private boolean IsBusy() {
		if(Math.random() < 0.5) {
			return true;
		} else {
			return false;
		}
	}
	
	private void Report() {
		AID r = new AID ("coordinator@"+myAgent.getHap(), AID.ISGUID);
		
		ACLMessage aclMessage = new ACLMessage(ACLMessage.INFORM);
		aclMessage.addReceiver(r);
		if(IsBusy()) { 
			aclMessage.setContent("busy");
		} else {
			aclMessage.setContent("estinguished");
		}
		
		myAgent.send(aclMessage);
	}

	
	public boolean done() {
		return false;
	}
}