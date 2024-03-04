import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Assignment2017 {
    public static int[][] readIn(String filename) {
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
		
		int[][] input = new int[list.size()][list.get(0).split(", ").length];
		for (int i = 0; i < list.size(); i++) {
			String[] temp = list.get(i).split(", ");
			for (int j = 0; j < temp.length; j++) {
				input[i][j] = Integer.parseInt(temp[j].trim());
			}
		}
		return input;
	}

    public static void main(String[] args) {
        int[][] spreadsheet = readIn("2017/input.txt");
		
		// Your work goes here!
    }
}
