import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class CommandPrompt {
	
	private String command;
	private ArrayList<String> output;
	private Integer exitCode;
	
	public CommandPrompt(String command) {
		
		this.command = command;
		this.output = null;
		this.exitCode = null;
	}
	
	private ArrayList<String> runCommand() throws IOException, InterruptedException {
		
		ArrayList<String> returnVal = new ArrayList<String>();
		ProcessBuilder processBuilder = new ProcessBuilder("JavaCommandPrompt", this.command);
		processBuilder.redirectErrorStream(true);
		Process process = processBuilder.start();
		BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
		String line;
		while ((line = reader.readLine()) != null) {
			returnVal.add(line);
		}
		this.exitCode = process.waitFor();
		return returnVal;
	}
	
	public Integer execute() {
		
		try {
			this.output = this.runCommand();
			
		} catch (IOException | InterruptedException e) {
			e.printStackTrace();
		}
		return this.exitCode;
	}
	
	public ArrayList<String> getOutput() {
		
		return this.output;
	}
}