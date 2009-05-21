package main;

import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.SimpleBehaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;

public class MainAgent extends Agent {

	/**
	 * The main agent that coordinates all the other one:
	 * 	- police
	 *  - paramedics
	 *  - fire department
	 *  
	 *  @author Dabiste Sorin
	 */
	private static final long serialVersionUID = 1L;

	protected void setup() {
		System.out.println(this.getLocalName() + " is ready.");
		this.addBehaviour(new SendBehaviour(this));
		this.addBehaviour(new ReceiveBehaviour(this));
	}
}

class SendBehaviour extends SimpleBehaviour {
	
	/**
	 * The behavior class that sends the messages to the proper agents. 
	 * First a new event is randomly generated
	 *  - Bank Robbery
	 *  - Unintentional Fire
	 *  - Intentional Fire
	 *  - Rebellion
	 *  - Car Accident
	 * Then the proper agents are warned about the event then according to their reporting action, 
	 * a corresponding message is printed.
	 * 
	 * @author Dabiste Sorin
	 */
	private static final long serialVersionUID = 1L;

	public SendBehaviour(Agent agent) {
		super(agent);
	}
	
	public void action() {
		AID police;
		AID paramedics;
		AID fireDept;
		ACLMessage aclMessage = new ACLMessage(ACLMessage.REQUEST);;
		
		double whatHappened = Math.random();
		
		if(whatHappened < 0.1) {
			police = new AID ("police@"+myAgent.getHap(), AID.ISGUID);
			aclMessage.addReceiver(police);
			aclMessage.setContent("Bank Robbery");
		} else if(whatHappened < 0.2) {
			police = new AID ("police@"+myAgent.getHap(), AID.ISGUID);
			paramedics = new AID ("paramedics@"+myAgent.getHap(), AID.ISGUID);
			fireDept = new AID ("firedepartment@"+myAgent.getHap(), AID.ISGUID);
			aclMessage.addReceiver(police);
			aclMessage.addReceiver(paramedics);
			aclMessage.addReceiver(fireDept);
			aclMessage.setContent("Unintetional Fire");
		} else if(whatHappened < 0.3) {
			police = new AID ("police@"+myAgent.getHap(), AID.ISGUID);
			paramedics = new AID ("paramedics@"+myAgent.getHap(), AID.ISGUID);
			fireDept = new AID ("firedepartment@"+myAgent.getHap(), AID.ISGUID);
			aclMessage.addReceiver(police);
			aclMessage.addReceiver(paramedics);
			aclMessage.addReceiver(fireDept);
			aclMessage.setContent("Rebellion");
		} else if(whatHappened < 0.4) {
			police = new AID ("police@"+myAgent.getHap(), AID.ISGUID);
			paramedics = new AID ("paramedics@"+myAgent.getHap(), AID.ISGUID);
			fireDept = new AID ("firedepartment@"+myAgent.getHap(), AID.ISGUID);
			aclMessage.addReceiver(police);
			aclMessage.addReceiver(paramedics);
			aclMessage.addReceiver(fireDept);
			aclMessage.setContent("Car Accident");
		}
		else if(whatHappened < 0.5) {
			police = new AID ("police@"+myAgent.getHap(), AID.ISGUID);
			fireDept = new AID ("firedepartment@"+myAgent.getHap(), AID.ISGUID);
			aclMessage.addReceiver(police);
			aclMessage.addReceiver(fireDept);
			aclMessage.setContent("Intentional Fire");
		}
		
		if(aclMessage.getContent() != null) {
			System.out.println("\tThere has been an " + aclMessage.getContent());
		}
		
		myAgent.send(aclMessage);
		
		try {
			Thread.sleep(1000);
		} catch(InterruptedException e) {
		}
	}
	
	public boolean done() {
		return false;
	}
}

class ReceiveBehaviour extends SimpleBehaviour {
	
	/**
	 * The behavior class that prints a message according to what the other agents reported. 
	 */
	private static final long serialVersionUID = 1L;
	private static final MessageTemplate mt = MessageTemplate.MatchPerformative(ACLMessage.INFORM);
	
	public ReceiveBehaviour(Agent agent) {
		super(agent);
	}
	
	public void action() {
		ACLMessage aclMessage = myAgent.receive(mt);
		if (aclMessage!=null) {
			if(aclMessage.getContent().compareTo("arrested") == 0) {
				System.out.println(aclMessage.getSender().getName() + " arrested all the suspects.");
			} else if(aclMessage.getContent().compareTo("cured") == 0) {
				System.out.println(aclMessage.getSender().getName() + " cured all the injured.");
			} else if(aclMessage.getContent().compareTo("estinguished") == 0) {
				System.out.println(aclMessage.getSender().getName() + " estinguished all the fires.");
			} else if(aclMessage.getContent().compareTo("busy") == 0) {
				System.out.println(aclMessage.getSender().getName() + " is busy.");
			}
		} else {
			this.block();
		}
	}
	
	public boolean done() {
		return false;
	}
}