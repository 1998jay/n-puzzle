import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Stack;
import java.io.File;

public class test {

	public static State readBoard(String file) throws Exception {
		try {
			File myObj = new File(file);
			Scanner input = new Scanner(myObj);
			int size = Integer.parseInt(input.nextLine());

			if (size == 1 || size == 0 || size > 11)
				throw new Exception();

			int[][] tielss = new int[size][size];
			int j = 0;
			while (input.hasNextLine()) {

				String line = input.nextLine();
				String data[] = line.split(" ");

				for (int i = 0; i < data.length; i++) {
					int tile = Integer.parseInt(data[i]);
					if (tile < 0 || tile > (size * size) - 1)
						throw new Exception();
					else {

						tielss[j][i] = tile;
					}

				}
				j++;
			}
			Map<Integer, Integer> map = new HashMap<Integer, Integer>();

			for (int k = 0; k < tielss.length; k++) {
				for (int y = 0; y < tielss.length; y++) {
					int value = tielss[k][y];
					if (map.containsKey(value))
						throw new Exception();
					else {
						map.put(value, 0);
					}
				}

			}

			input.close();

			State state = new State(tielss, 0, null);
			return state;
		}

		catch (Exception e) {

			System.out.println(e.toString());
			return null;

		}

	}

	public static void main(String[] args) throws Exception {

		State state = readBoard("input.txt");
		state.toString();
		

		solver problemSolver = new solver(state);
		System.out.println("----------------------------");
		Stack<State> getSolution = problemSolver.getSolution();
		
		for (int i = getSolution.size() - 1; i >= 0; i--) {
			getSolution.get(i).display();
		}
		System.out.println(getSolution.size());
	}
}
