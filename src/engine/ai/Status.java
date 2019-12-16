package engine.ai;

public class Status {

	public static final Status SUCCESS = new Status("Success");
	public static final Status FAILURE = new Status("Failure");
	public static final Status RUNNING = new Status("Running");
	public String status;
	
	public Status(String s) {
		status = s;
	}
	
}
