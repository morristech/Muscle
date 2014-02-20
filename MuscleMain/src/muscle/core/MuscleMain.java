package muscle.core;

public class MuscleMain {
	  
	public static void main(String[] args) {
		//Wrapper 
		//GUI contains I/O which contains IM and PM
		//iomanager parses profile and (muscle, activity) pairs
		IOManager io = new IOManager("save.txt", "items.txt");
		//this gets sent to gui, which displays all the info
		new GUIManager(io);
	}
}