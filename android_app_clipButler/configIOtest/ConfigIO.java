import java.util.ArrayList;
import java.io.*;

public class ConfigIO {
	public static void main(String[] args) throws Exception {
		ConfigIO io = new ConfigIO();
		String rawInput = readFileAsString("testinput.txt");
		io.writeCopyStringToConfig("config.txt", rawInput);
		
		String[] lines = io.readLinesFromFile("config.txt");
		System.out.println(testArrToString(lines));
	}
	
	public String[] readLinesFromFile(String fileLoc) throws Exception {
		ArrayList<String> lines = new ArrayList<>();
		File target = new File(fileLoc);
		BufferedReader br = new BufferedReader(new FileReader(target));
		String line;
		while ((line = br.readLine()) != null) {
			String filteredLine = line.replace("\\n", "\n");
			lines.add(filteredLine);
		}
		return lines.toArray(new String[0]);
	}
	
	public static String readFileAsString(String fileLoc) throws Exception {
		StringBuilder builder = new StringBuilder();
		File target = new File(fileLoc);
		BufferedReader br = new BufferedReader(new FileReader(target));
		String line;
		while ((line = br.readLine()) != null) {
			String filteredLine = line.replace("\\n", "\n");
			builder.append(filteredLine+'\n');
		}
		return builder.toString();
	}
	
	public static String testArrToString(String[] array) {
		StringBuilder builder = new StringBuilder();
		int index = 0;
		for (String str : array) {
			builder.append(index + ": " + str + '\n');
			index++;
		}
		return builder.toString();
	}
	
	public String filterInput(String input) throws Exception {
		if (input == null || input.equals("")) {
			return "";
		}
		return input.replace("\n","\\n") + '\n';
	}
	
	public void writeCopyStringToConfig(String outputLoc, String input) throws Exception {
		FileWriter fw = new FileWriter(outputLoc, true);
		String filteredInput = filterInput(input);
		if (!filteredInput.equals("")) {
			fw.write(filterInput(input));
		}
		fw.close();
	}
}