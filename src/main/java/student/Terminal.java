package student;
import java.io.*;

public class Terminal {

	public Terminal() {
		// TODO Auto-generated constructor stub
	}

	
	public void promptInput(String input) {
		System.out.println(input);

	}

	public String[] readInput() throws IOException {
		InputStreamReader converter = new InputStreamReader(System.in);
        BufferedReader in = new BufferedReader(converter);
        String input = in.readLine(); 
        return input.split(" ");
	}

}
