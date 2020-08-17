import java.util.Comparator;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;
import java.util.Stack;

import com.sun.xml.internal.bind.v2.schemagen.xmlschema.List;

import javafx.scene.Parent;

public class solver {
	State initialState;

	public solver(State initial) {
		initialState = initial;
	} // constructor

	public int getSolutionLength() {
		return getSolution().size();

	}// returns the length of the solution

	public Stack<State> getSolution() {

		// add state
		PriorityQueue<State> frontier = new PriorityQueue<>(Comparator.comparingInt(s -> s.fValue));
		frontier.add(initialState);

		Set<State> explored = new HashSet<State>();
		// first loop is for checking if its empty or goal, otherwise we will
		// add it to the explored list
		State currentstate = initialState;
		while (true) {
			if (frontier.isEmpty())
				break;

			currentstate = frontier.poll();

			if ((currentstate).isGoal())

				break;

			explored.add(currentstate);

			boolean isExist;
			for (State neighbor : currentstate.neighbors()) {

				// Check if the number in already exist
				isExist = explored.stream().anyMatch(State -> State.equals(neighbor));

				if (!isExist)

					frontier.add(neighbor);



			}

		}

		Stack<State> solution = new Stack<State>();
		// return the path of the solution
		while (currentstate != null) {
			solution.add(currentstate);
			currentstate = currentstate.parentState;
		}
		return solution;
	} // returns the path solution
}