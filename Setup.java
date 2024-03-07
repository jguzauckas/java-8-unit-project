import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class Setup {
    public static void writeStringToFile(String str, String fileName) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(fileName));
        writer.write(str);
        writer.close();
    }

    public static void write2DArrayToFile(Object[][] arr, String fileName) throws IOException {
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
        int col2 = 0;
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

    public static Integer[][] generate2017(int size) {
        Integer[][] input = new Integer[size][size];
        for (int i = 0; i < input.length; i++) {
            for (int j = 0; j < input[i].length; j++) {
                input[i][j] = (int) (Math.random() * 10000 + 1);
            }
            boolean hasDivisor = false;
            for (int j = 0; j < input[i].length; j++) {
                for (int k = 0; k < input[i].length; k++) {
                    if (j != k && input[i][j] % input[i][k] == 0) {
                        hasDivisor = true;
                    }
                }
            }
            if (!hasDivisor) {
                int rand1 = (int) (Math.random() * input[i].length);
                int rand2 = (int) (Math.random() * input[i].length);
                while (rand2 == rand1) {
                    rand2 = (int) (Math.random() * input[i].length);
                }
                input[i][rand2] = input[i][rand1] * (int) (Math.random() * 10 + 1);
            }
        }
        return input;
    }

    public static String solve2017(Integer[][] input) {
        int checksum1 = 0;
        int checksum2 = 0;
        for (int i = 0; i < input.length; i++) {
            int max = input[i][0];
            int min = input[i][0];
            int dividend  = -1;
            int divisor = -1;
            for (int j = 0; j < input[i].length; j++) {
                if (input[i][j] > max) {
                    max = input[i][j];
                }
                if (input[i][j] < min) {
                    min = input[i][j];
                }
                for (int k = 0; k < input[i].length; k++) {
                    if (j != k && input[i][j] % input[i][k] == 0) {
                        dividend = input[i][j];
                        divisor = input[i][k];
                    }
                }
            }
            checksum1 += (max - min);
            checksum2 += dividend / divisor;
        }
        String answer = "The row max/min difference checksum is " + checksum1 + ".\n";
        answer += "The row divisor checksum is " + checksum2 + ".";
        return answer;
    }

    public static void main(String[] args) {
        String[][] p2016 = generate2016(250);
        Integer[][] p2017 = generate2017(100);
        solve2017(p2017);

        try {
            write2DArrayToFile(p2016, "2016/input.txt");
            writeStringToFile(solve2016(p2016), "2016/answer.txt");
            write2DArrayToFile(p2017, "2017/input.txt");
            writeStringToFile(solve2017(p2017), "2017/answer.txt");
        } catch (IOException e1) {
			e1.printStackTrace();
		}
    }
}
