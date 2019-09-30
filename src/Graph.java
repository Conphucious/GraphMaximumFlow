import java.util.ArrayList;
import java.util.List;

import nodes.CostNode;
import nodes.ProjectNode;

public class Graph {

	private ArrayList<ProjectNode> nodeList;

	public Graph() {
		nodeList = new ArrayList<>();
	}

	public boolean dependencyInList(List<ProjectNode> mainList, ArrayList<ProjectNode> dependentList) {
		for (int i = 0; i < dependentList.size(); i++)
			if (!mainList.contains(dependentList.get(i))) {
				return false;
			}

		return true;
	}

	public void getMaximumFlow() {
		// convert nodes to subsets
		List<List<ProjectNode>> ls = getSubsets(nodeList);
		System.out.println("--------------------\n" + ls + "\nsubsets: " + ls.size());

		List<Evaluation> retList = new ArrayList<>();

		// [x, y, z] then we got [x], [y], [z], [x,y], [x,z] [y,z] if x is reliant on y
		// then no x so [y, z], [y], [z] whichever has better value, is winner

		// list of values we're testing
		for (int i = 0; i < ls.size(); i++) {
			int profit = 0;
			int cost = 0;
			List<ProjectNode> tempList = new ArrayList<>();
			ArrayList<CostNode> costList = new ArrayList<>();
			
			boolean dependency = false;
			
			for (int j = 0; j < ls.get(i).size(); j++) {

				ProjectNode pNode = ls.get(i).get(j);

				if (pNode.hasDependency() && !dependencyInList(ls.get(i), pNode.getDependentProjects())) {
					System.out.println(ls.get(i).get(j) + " :: HAS DEPENDECY, SKIP\n");
					dependency = true;
					break;
				}

				profit += pNode.getValue();

				// if already exist then don't od this.
				ArrayList<CostNode> cosa = pNode.getDependentCosts();
				int individualCost = 0;
				for (int k = 0; k < cosa.size(); k++) {

					if (!isInCostList(costList, cosa.get(k))) {
						cost += cosa.get(k).getValue();
						costList.add(cosa.get(k)); // if has dependent cost, sum those too

						individualCost += cosa.get(k).getValue();

						CostNode x = cosa.get(k);
						for (int z = 0; z < x.getDependentCosts().size(); z++) {
							// System.out.println("TRUE HAS DEPENDENT COST");
							if (!isInCostList(costList, x.getDependentCosts().get(z))) {
								cost += x.getDependentCosts().get(z).getValue();
								individualCost += x.getDependentCosts().get(z).getValue();
								costList.add(x);
							}
						}
					}
				}
				System.out.println(ls.get(i).get(j) + " :: " + ls.get(i).get(j).getValue() + "_" + individualCost);
				tempList.add(pNode);
			}

			if (!tempList.isEmpty() && !dependency) {
				retList.add(new Evaluation(tempList, profit, cost));
				System.out.println("TOTAL: " + profit + "_" + cost);
				System.out.println("NET: " + (profit + cost) + "\n");
			}
		}

		System.out.println("\n\n");
		for (Evaluation x : retList)
			System.out.println(x);

		Evaluation mf = calculateMaxFlow(retList);

		if (mf.getNet() < 0) {
			System.out.println("DO NOTHING! ALL JOBS SUCK AND MAKE OYU LOSE MONEY");
		} else {
			System.out.println("\nFINAL RESULT: " + mf);
		}

		// System.out.println("\n--");
		// pList.add(new Value(cost + profit, ls.get(i)));
	}

	private Evaluation calculateMaxFlow(List<Evaluation> evalList) {
		Evaluation highest = evalList.get(0);

		for (int i = 1; i < evalList.size() - 1; i++)
			if (evalList.get(i).getNet() > highest.getNet())
				highest = evalList.get(i);

		return highest;
	}

	private List<List<ProjectNode>> getSubsets(List<ProjectNode> set) {
		List<List<ProjectNode>> ls = new ArrayList<>();

		for (int i = 1; i < (1 << set.size()); i++) { // zero yields empty set
			List<ProjectNode> innerList = new ArrayList<>();
			System.out.print("{ ");
			for (int j = 0; j < set.size(); j++) {
				if ((i & (1 << j)) > 0) {
					System.out.print(set.get(j).getName() + " ");
					innerList.add(set.get(j));
				}
			}
			System.out.println("}");
			ls.add(innerList);
		}

		return ls;

	}

	// // CHECKING ONE BY ONE
	// // SINGLE then one with depencies with its dependent, then depency + one
	// other, two other, three other, etc.
	// for (int i = 0; i < nodeList.size(); i++) {
	// ProjectNode pNode = nodeList.get(i);
	// if (!pNode.hasDependency()) {
	// ArrayList<CostNode> cNode = nodeList.get(i).getDependentCosts();
	// int cost = 0;
	// int profit = pNode.getValue();
	//
	// for (CostNode x : cNode)
	// cost += x.getValue();
	//
	// valueList.add(new Value(nodeList.get(i), profit, cost));
	// }
	// }
	//
	// for (Value v : valueList)
	// System.out.println(v);

	public boolean isWorthIt(int profit, int cost) {
		if (profit + cost < 0)
			return false;
		return true;
	}

	public boolean isInCostList(ArrayList<CostNode> costList, CostNode cNode) {
		for (int i = 0; i < costList.size(); i++)
			if (costList.get(i) == cNode)
				return true;
		return false;
	}

	// public void traverseAboveNegative() {
	// ArrayList<ProjectNode> goodTravel = new ArrayList<>();
	//
	// System.out.println("Traversing paths...");
	//
	// //int profit = 0, cost = 0;
	//
	// //System.out.println("P: " + profit + " - C: " + cost);
	//
	// for (int i = 0; i < nodes.size(); i++) {
	// int profit = 0, cost = 0;
	// System.out.println("Starting at: " + nodes.get(i).getName());
	// profit += nodes.get(i).getValue();
	//
	// for (int j = 0; j < nodes.get(i).getDependentCosts().size(); j++) {
	// cost += nodes.get(i).getDependentCosts().get(j).getValue();
	// }
	//
	// // if dependent projects exist in there, then also recursion add this on.
	//
	// System.out.println("P: " + profit + " - C: " + cost);
	// }
	// }

	public void addToGraph(ProjectNode pNode) {
		nodeList.add(pNode);
	}

	@Override
	public String toString() {
		return "ProjectGraph [nodes=" + nodeList + "\n]";
	}

}
