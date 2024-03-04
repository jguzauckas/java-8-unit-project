import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Setup {
    public static void writeStringToFile(String str, String fileName) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(fileName));
        writer.write(str);
        writer.close();
    }

    public static void write2DArrayToFile(String[][] arr, String fileName) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(fileName));
        String str = "";
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++) {
                if (j == arr[i].length - 1) {
                    str += arr[i][j];
                } else {
                    str += arr[i][j] + ", ";
                }
            }
            str += "\n";
        }
        str.trim();
        writer.write(str);
        writer.close();
    }

    public static String[][] generate2016(int size) {
        String[][] input = new String[(int) (Math.random() * 11 + 10)][size];
        String[] directions = new String[] {"U", "D", "L", "R"};
        for (int i = 0; i < input.length; i++) {
            for (int j = 0; j < input[i].length; j++) {
                int rand = (int) (Math.random() * 4);
                input[i][j] = directions[rand];
            }
        }
        return input;
    }

    public static String solve2016(String[][] input) {
        int[][] pinpad = new int[][] {{1, 2, 3},
                                      {4, 5, 6},
                                      {7, 8, 9}};
        String[][] newPinpad = new String[][] {{null, null, "1", null, null},
                                               {null, "2", "3", "4", null},
                                               {"5", "6", "7", "8", "9"},
                                               {null, "A", "B", "C", null},
                                               {null, null, "D", null, null}};
        String part1 = "";
        String part2 = "";
        int row1 = 1;
        int col1 = 1;
        int row2 = 2;
        int col2 = 2;
        for (int i = 0; i < input.length; i++) {
            for (int j = 0; j < input[i].length; j++) {
                if (input[i][j] == null) {
                } else if (input[i][j].equals("U")) {
                    if (row1 > 0) {
                        row1--;
                    }
                } else if (input[i][j].equals("D")) {
                    if (row1 < 2) {
                        row1++;
                    }
                } else if (input[i][j].equals("L")) {
                    if (col1 > 0) {
                        col1--;
                    }
                } else if (input[i][j].equals("R")) {
                    if (col1 < 2) {
                        col1++;
                    }
                }
                if (input[i][j] == null) {
                } else if (input[i][j].equals("U")) {
                    if (row2 > 0 && newPinpad[row2 - 1][col2] != null) {
                        row2--;
                    }
                } else if (input[i][j].equals("D")) {
                    if (row2 < 4 && newPinpad[row2 + 1][col2] != null) {
                        row2++;
                    }
                } else if (input[i][j].equals("L")) {
                    if (col2 > 0 && newPinpad[row2][col2 - 1] != null) {
                        col2--;
                    }
                } else if (input[i][j].equals("R")) {
                    if (col2 < 4 && newPinpad[row2][col2 + 1] != null) {
                        col2++;
                    }
                }
            }
            part1 += pinpad[row1][col1];
            part2 += newPinpad[row2][col2];
        }
        String answer = "The bathroom code for the simple pinpad is " + part1 + ".\n";
        answer += "The bathroom code for the complex pinpad is " + part2 + ".";
        return answer;
    }

    public static ArrayList<String> generate2018(int size) {
        ArrayList<String> result = new ArrayList<String>();
        for (int i = 0; i < size; i++) {
            String str = "";
            for (int j = 0; j < 25; j++) {
                str += Character.toString((char) ((int) (Math.random() * 26 + 97)));
            }
            result.add(str);
        }
        return result;
    }

    public static String solve2018(ArrayList<String> list) {
        int countDouble = 0;
        int countTriple = 0;
        for (String str : list) {
            boolean hasDouble = false;
            boolean hasTriple = false;
            for (int i = 0; i < str.length(); i++) {
                int count = 0;
                String temp = str.substring(i, i + 1);
                for (int j  = 0; j < str.length(); j++) {
                    if (temp.equals(str.substring(j, j + 1))) {
                        count++;
                    }
                }
                if (count == 2) {
                    hasDouble = true;
                } else if (count == 3) {
                    hasTriple = true;
                }
            }
            if (hasDouble) {
                countDouble++;
            }
            if (hasTriple) {
                countTriple++;
            }
        }
        String answer = "There are " + countDouble + " double appearances and " + countTriple + " triple appearances. ";
        answer += "This produces a checksum of " + countDouble + " * " + countTriple + " = " + (countDouble * countTriple);
        return answer;
    }

    public static ArrayList<Integer> generate2019(int size) {
        ArrayList<Integer> intList = new ArrayList<Integer>();
        size = size - (size % 4);
        for (int i = 0; i < size - 1; i++) {
            if (i % 4 == 0) {
                if (Math.random() > 0.2) {
                    intList.add(1);
                } else if (Math.random() > 0.2) {
                    intList.add(2);
                } else {
                    intList.add(99);
                }
            } else {
                int temp = (int) (Math.random() * i);
                if (temp % 4 == 0) {
                    temp++;
                }
                intList.add(temp);
            }
        }
        intList.add(99);
        return intList;
    }

    public static String solve2019(ArrayList<Integer> in) {
        in.set(1, 12);
        in.set(2, 2);
        for (int i = 0; i < in.size(); i += 4) {
            if (in.get(i) == 1) {
                in.set(in.get(i + 3), in.get(in.get(i + 1)) + in.get(in.get(i + 2)));
            } else if (in.get(i) == 2) {
                in.set(in.get(i + 3), in.get(in.get(i + 1)) * in.get(in.get(i + 2)));
            } else {
                i = in.size();
            }
        }

        String answer = "The value at position 1 is " + in.get(1) + "\n";
        return answer;
    }

    public static void main(String[] args) {
        String[][] p2016 = generate2016(250);

        try {
            write2DArrayToFile(p2016, "2016/input.txt");
            writeStringToFile(solve2016(p2016), "2016/answer.txt");
        } catch (IOException e1) {
			e1.printStackTrace();
		}
    }
}