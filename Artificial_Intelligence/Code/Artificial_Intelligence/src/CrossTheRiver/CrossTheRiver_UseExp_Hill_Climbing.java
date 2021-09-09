package CrossTheRiver;

import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Stack;

import CrossTheRiver.State.AMOUNT_PEOPLE_WOLF;

public class CrossTheRiver_UseExp_Hill_Climbing {
	public static Stack<State> stateStack = new Stack<State>();
	public static PriorityQueue<State> statePQueue = new PriorityQueue<State>();
	public static LinkedList<State> wasSet = new LinkedList<>();
	public static LinkedList<Node> step = new LinkedList<>();
	public static int count = 0;

	public static void main(String[] args) {
		stateStack.push(new State(3, 3, true));
		wasSet.add(new State(3, 3, true));
		while (true) {
			count++;
			State state = stateStack.pop();
			if (state == null) {
				System.out.println("empty");
				break;
			}
			if (state.isEnd()) {
				System.out.println(state.toString());
				System.out.println(count);
				finishGame();
				return;
			}
			changeState(state, 1, 0);
			changeState(state, 0, 1);
			changeState(state, 1, 1);
			changeState(state, 2, 0);
			changeState(state, 0, 2);
			if(!statePQueue.isEmpty()) {
				for(int i = 0; i< statePQueue.size(); i++) {
					stateStack.push(statePQueue.poll());
				}
			}
			System.out.println(state.toString());
		}
		
		
	}

	private static void finishGame() {
		State state = new State(0, 0, false);
		for (int i = step.size() - 1; i >= 0 ; i--) {
			if(state.equal(step.get(i).getStateChild())) {
				System.out.println(step.get(i).toString());
				state = step.get(i).getStateParent();
			}
		}
	}

	private static void changeState(State state, int people, int wolf) {
		State nextState;
		if (state.isBoat()) {
			nextState = new State(state.getPeople() - people, state.getWolf() - wolf, !state.isBoat());
		} else {
			nextState = new State(state.getPeople() + people, state.getWolf() + wolf, !state.isBoat());
		}

		if (isWolfEatPeople(nextState)) {
			if (!isDuplicate(nextState)) {
				statePQueue.add(nextState);
				wasSet.add(nextState);
				step.add(new Node(people, wolf, state, nextState));
			}
		}
	}

	/**
	 * @param state this is State other if state duplicate the return, else add
	 *              states
	 */
	private static boolean isDuplicate(State state) {
		for (int i = 0; i < wasSet.size(); i++) {
			if (wasSet.get(i).equal(state)) {
				return true;
			}
		}
		return false;
	}

	private static boolean isWolfEatPeople(State state) {
		if (state.getPeople() < state.getWolf() && state.getPeople() != 0) {
			return false;
		}

		if (state.getPeople() < 0 || state.getWolf() < 0) {
			return false;
		}

		if (state.getPeople() > AMOUNT_PEOPLE_WOLF.MAX_PEOPLE || state.getWolf() > AMOUNT_PEOPLE_WOLF.MAX_WOLF) {
			return false;
		}

		if (AMOUNT_PEOPLE_WOLF.MAX_PEOPLE - state.getPeople() < AMOUNT_PEOPLE_WOLF.MAX_WOLF - state.getWolf()
				&& state.getPeople() != AMOUNT_PEOPLE_WOLF.MAX_PEOPLE) {
			return false;
		}
		return true;
	}
}
