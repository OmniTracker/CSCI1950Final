package engine.ai;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.NoSuchElementException;
import java.util.PriorityQueue;

public class AStar {

	private SearchProblem s;
	private PriorityQueue<GameState> frontier;
	private ArrayList<GameState> path;
	
	public AStar(Heuristic h,SearchProblem s) {
		 frontier = new PriorityQueue<GameState>(h);
		 this.s = s;
	}
	
	public ArrayList<GameState> getPath() {
		GameState start = s.getStartState();
		start.setCost(0);
		HashSet<GameState> visited = new HashSet<GameState>();
		frontier.add(start);
		boolean goal_found = false;
		HashSet<GameState> front = new HashSet<GameState>();
		front.add(start);
		HashMap<GameState,GameState> parent = new HashMap<GameState,GameState>();
		//HashMap<GameState,Double> cost = new HashMap<GameState,Double>();
		//cost.put(start, 0.0);
		ArrayList<GameState> path = new ArrayList<GameState>();
		GameState fin = start;
		int count = 0;
		while (!goal_found) {
			if (frontier.isEmpty()) {
				goal_found = true;
			}
			GameState curr;
			try {
				curr = frontier.remove();
			} catch(NoSuchElementException e) {
				continue;
			} 
			
			front.remove(curr);
			/*count++;
			System.out.println(count);
			if (count>750) {
				goal_found = true;
				fin = curr;
			}*/
			if (s.isGoalState(curr)) {
				goal_found = true;
				fin = curr;
			} else {
				visited.add(curr);
				HashSet<GameState> successors = s.getSuccessors(curr);
				for (GameState suc: successors) {
					if (!this.inSet(visited, suc)&&!this.inSet(front, suc)) {
						parent.put(suc, curr);
						//cost.put(suc, cost.get(curr)+successors.get(curr));
						frontier.add(suc);
						front.add(suc);
					}
				}
			}
					
		}
		
		while (!fin.equals(start)) {
			path.add(fin);
			fin = parent.get(fin);
		}
		//path.add(start);
		Collections.reverse(path);
		return path;
	}
	
	public GameState getNext(boolean newPath) {
		/*
		GameState start = s.getStartState();
		HashSet<GameState> successors = s.getSuccessors(start);
		for (GameState suc: successors) {
			//cost.put(suc, cost.get(curr)+successors.get(curr));
			frontier.add(suc);
		}
		
		return frontier.remove();*/
		if (newPath) {
			path = this.getPath();
		}
		if (!path.isEmpty()) {
			return path.remove(0);
		} else {
			return null;
		}
		
	}
	
	public boolean inSet(HashSet<GameState> visited, GameState test) {
		for (GameState s: visited) {
			if (s.compare(test)) {
				return true;
			}
		}
		return false;
	}
	

}
