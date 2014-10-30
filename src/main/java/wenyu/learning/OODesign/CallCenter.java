package wenyu.learning.OODesign;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

/*
 * Imagine you have a call center with three levels of employees: fresher, technical lead (TL), product manager (PM). 
 * There can be multiple employees, but only one TL or PM. An incoming telephone call must be allocated to a fresher 
 * who is free. If a fresher can’t handle the call, he or she must escalate the call to technical lead. If the TL is 
 * not free or not able to handle it, then the call should be escalated to PM. Design the classes and data structures 
 * for this problem. Implement a method getCallHandler().
 */


enum Role {
	Fresher,
	TL, // technical leader
	PM // product manager
}

enum Status {
	Busy,
	Free
}

class CallRequest {
	public String fromName;
	public Role callType;
	public boolean callStatus;
	public CallRequest(String name, Role callType) {
		this.fromName = name;
		this.callType = callType;
		callStatus = false;
	}
}

abstract class Employee  {
	public String name;
	public Status status;
	protected Role role;
	protected Employee supervisor;
	public abstract boolean getCallHandler(CallRequest cal) throws Exception;
}

class Fresher extends Employee {
	public Fresher(String name, Employee supervisor) {
		this.name = name;
		this.status = Status.Free;
		this.role = Role.Fresher;
		this.supervisor = supervisor;
	}
	
	public boolean getCallHandler(CallRequest call) throws Exception{
		if(call.callType == Role.Fresher) {
			status = Status.Busy;
			System.out.println("Fresher(" + name + ") is handling call from " + call.fromName);
			Thread.sleep(3000); //Handle call request.
			call.callStatus = true;
			status = Status.Free;
		} else {
			synchronized(call) {
				supervisor.getCallHandler(call);
				call.wait();
			}
		}
		return call.callStatus;
	}
}

class TL extends Employee {
	private final Queue<CallRequest> queue;
	public TL(String name, Employee supervisor) {
		this.name = name;
		this.status = Status.Free;
		this.role = Role.TL;
		this.supervisor = supervisor;
		queue = new LinkedList<CallRequest>();
	}
	
	private void startJob() throws Exception {
		while(true) {
			while(!queue.isEmpty()) {
				CallRequest request = queue.poll();
				synchronized(request) {
					System.out.println("TL(" + name + ") is handling call from " + request.fromName);
					Thread.sleep(3000); //Handle call request.
					request.callStatus = true;
					request.notify();
				}
			}
		}
	}
	
	public boolean getCallHandler(CallRequest call) throws Exception {
		if(call.callType == Role.TL) {
			queue.offer(call);
		} else {
			supervisor.getCallHandler(call);
		}
		return false;
	}
}

class PM extends Employee {
	private final Queue<CallRequest> queue;
	public PM(String name) {
		this.name = name;
		this.status = Status.Free;
		this.role = Role.PM;
		queue = new LinkedList<CallRequest>();
	}
	
	private void startJob() throws Exception {
		while(true) {
			while(!queue.isEmpty()) {
				CallRequest request = queue.poll();
				synchronized(request) {
					System.out.println("PM(" + name + ") is handling call from " + request.fromName);
					Thread.sleep(3000); //Handle call request.
					request.callStatus = true;
					request.notify();
				}
			}
		}
	}
	
	public boolean getCallHandler(CallRequest call) throws Exception {
		return false;
	}
}

public class CallCenter {

	public static void main(String[] args) {
		Employee pm = new PM("Wenyu");
		Employee tl = new TL("Bei", pm);
		Employee fr1 = new Fresher("fr1", tl);
		Employee fr2 = new Fresher("fr2", tl);
		Employee fr3 = new Fresher("fr3", tl);
		
		while(true) {
			Scanner scanner = new Scanner(System.in);
			String input = scanner.nextLine();
			
			//CallRequest call = CallRequest()
		}
	}

}
