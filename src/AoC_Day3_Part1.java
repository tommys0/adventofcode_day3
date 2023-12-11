import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class AoC_Day3_Part1 {

    private static final int[][] directions = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}, {1, 1}, {-1, -1}, {1, -1}, {-1, 1}};

    public static void main(String[] args) {
        long sum = 0;
        String inputFileName = "text.txt"; // Replace with your input file name

        try (BufferedReader br = new BufferedReader(new FileReader(inputFileName))) {
            List<String> list = br.lines().collect(Collectors.toList());
            char[][] css = new char[list.size()][];
            IntStream.range(0, list.size()).forEach(i -> css[i] = list.get(i).toCharArray());

            for (int i = 0; i < css.length; i++) {
                int num = 0;
                boolean isNum = false;

                for (int j = 0; j < css[i].length; j++) {
                    int tmp = findNum(css[i][j]);

                    if (tmp == -1) {
                        if (isNum) {
                            sum += num;
                            isNum = false;
                        }
                        num = 0;
                    } else {
                        num = num * 10 + tmp;

                        for (int[] dir : directions) {
                            if (i + dir[0] >= 0 && i + dir[0] < css.length && j + dir[1] >= 0 && j + dir[1] < css[i + dir[0]].length) {
                                if (css[i + dir[0]][j + dir[1]] != '.' && findNum(css[i + dir[0]][j + dir[1]]) == -1) {
                                    isNum = true;
                                }
                            }
                        }
                    }
                }

                if (isNum) {
                    sum += num;
                }
            }
        } catch (IOException | NumberFormatException e) {
            e.printStackTrace();
        }

        System.out.println(sum);
    }

    private static int findNum(char c) {
        if (c >= '0' && c <= '9') {
            return c - '0';
        }
        return -1;
    }
}
