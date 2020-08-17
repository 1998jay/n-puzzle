import java.util.ArrayList;
import java.util.List;

public class State {
	int[][] tiless;
	int fValue;
	int gValue;
	State parentState;

	public State(int[][] tiles, int gValue, State parent) {
		tiless = tiles;
		this.gValue = gValue;
		fValue = this.gValue + manhattan();
		parentState = parent;

	}// constructor

	public State(State s) { // copy constructor
		tiless = new int[s.tiless.length][s.tiless.length];
		for (int i = 0; i < s.tiless.length; i++) {
			for (int j = 0; j < s.tiless.length; j++) {
				this.tiless[i][j] = s.tiless[i][j];

			}

		}
		gValue = s.gValue;
		fValue = s.fValue;

	}

	public String toString() {
		System.out.println("Hamming distance of the puzzle = " + hamming());

		System.out.println("Manhattan distance of the puzzle = " + manhattan());
		System.out.println("This puzzle is a goal:?" + isGoal());
		System.out.println("number of inversions " + nbInversions());
		System.out.println("is it solvable " + isSolvable());
		System.out.println("Solution :");
		return null;

	}

	// string representation of the State (according to the output format specified
	// before)

	public boolean equals(Object y) {

		State outState = (State) y;
		if (outState.tiless.length != this.tiless.length)
			return false;
		for (int i = 0; i < tiless.length; i++) {
			for (int j = 0; j < tiless.length; j++) {
				if (tiless[i][j] != outState.tiless[i][j])
					return false;
			}

		}
		return true;
	}

	/// checks if the current State is equal to the State y
	public int hamming() {

		int count = 1;
		int hamming = 0;
		for (int i = 0; i < tiless.length; i++) {
			for (int j = 0; j < tiless.length; j++) {
				if (count != tiless[i][j] && tiless[i][j] != 0)
					hamming++;
				count++;
			}
		}
		return hamming;
	}

	// computes the hamming distance for the current state
	public int manhattan() {
		int x, y = 0;
		int distance = 0;
		for (int i = 0; i < tiless.length; i++) {
			for (int j = 0; j < tiless.length; j++) {
				if (tiless[i][j] != 0) {

					x = ((tiless[i][j] - 1) / tiless.length);
					y = ((tiless[i][j] - 1) % tiless.length);

					distance += (Math.abs(x - i) + Math.abs(y - j));
				}
			}
		}
		return distance;
	}

	// computes the Manhattah distance for the current state

	public boolean isGoal() {
		int cheak = hamming();
		if (cheak == 0)
			return true;
		return false;

	}

	// Checks if the current state is a goal

	public List<State> neighbors() {
		List<State> myList = new ArrayList<State>();
		int blankX = 0;
		int blankY = 0;
//store the Dimension of blank place 
		for (int i = 0; i < tiless.length; i++)
			for (int j = 0; j < tiless.length; j++) {
				if (tiless[i][j] == 0) {
					blankX = i;
					blankY = j;
					break;
				}
			}

		// check the direction of the move of the blank
		int directions[][] = { { blankX - 1, blankY }, // Up
				{ blankX + 1, blankY }, // Down
				{ blankX, blankY + 1 }, // Right
				{ blankX, blankY - 1 } }; // Left

		int move[];
		for (int k = 0; k < directions.length; k++) {
			move = directions[k];
			int new_xplace = move[0];
			int new_yplace = move[1];

			if ((new_xplace >= 0 && new_xplace < tiless.length) && (new_yplace >= 0 && new_yplace < tiless.length)) {
				

				State newState = new State(this);
				newState.parentState = this;

			
					// swap
					 int temp = newState.tiless[new_xplace][new_yplace];
					newState.tiless[new_xplace][new_yplace] = newState.tiless[blankX][blankY];
					newState.tiless[blankX][blankY] = temp;

//change g and f 
					
					++newState.gValue;
					newState.fValue = newState.gValue + newState.manhattan();
					// add to list
					myList.add(newState);
					
			}

		}
		return myList;

	}// returns all neighboring States

	public boolean isSolvable() {
		int grid = tiless.length;
		if ((grid % 2) != 0 && nbInversions() % 2 == 0)
			return true;
		for (int i = tiless.length - 1; i > 0; i--)
			for (int j = tiless.length - 1; j > 0; j--) {

				if ((grid % 2) == 0 && (i % 2) == 0 && tiless[i][j] == 0 && (nbInversions() % 2) != 0)
					return true;
				if ((grid % 2) == 0 && (i % 2) != 0 && tiless[i][j] == 0 && nbInversions() % 2 == 0)
					return true;

			}
		return false;
	}

	// returns trues is the State is solvable
	public int nbInversions() {

		int inversions = 0;
		int[] arr = new int[tiless.length * tiless.length];

		int k = 0;
		for (int i = 0; i < tiless.length; i++)
			for (int j = 0; j < tiless.length; j++) {
				arr[k] = tiless[i][j];

				k++;

			}

		for (int i = 0; i < arr.length - 1; i++) {
			if ((arr[(i + 1)]) == 0)
				continue;

			if (arr[i] > (arr[(i + 1)]))

				inversions += 1;

		}

		return inversions;

	}

	// return the number of inversions

	public void display() {
		for (int i = 0; i < tiless.length; i++) {
			for (int j = 0; j < tiless.length - 1; j++) {
				System.out.print(tiless[i][j] + " ");

			}

			System.out.print(tiless[i][tiless.length - 1]);
			System.out.println();
		}

		System.out.println();

	}

}
