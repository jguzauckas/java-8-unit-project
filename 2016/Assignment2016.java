import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Assignment2016 {
    public static String[][] readIn(String filename) {
		BufferedReader in;
		String str = "";

		ArrayList<String> list = new ArrayList<String>();

		try {
			in = new BufferedReader(new FileReader(filename));
			while ((str = in.readLine()) != null) {
				list.add(str);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		
		String[][] input = new String[list.size()][list.get(0).split(", ").length];
		for (int i = 0; i < list.size(); i++) {
			input[i] = list.get(i).split(", ");
		}
		return input;
	}

    public static void main(String[] args) {
        String[][] instructions = readIn("2016/input.txt");
		
		for (String[] strArr : instructions) {
			for (String str : strArr) {
				System.out.println(str);
			}
		}
		// Your work goes here!
    }
}
