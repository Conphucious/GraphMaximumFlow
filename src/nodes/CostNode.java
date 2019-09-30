package nodes;
import java.util.ArrayList;

public class CostNode extends Node {

	private ArrayList<CostNode> dependentCosts;
	// can have dependent cost
	
	public CostNode(int cost, String name) {
		super(cost, name);
		dependentCosts = new ArrayList<>();
	}

	public void addToDependentCosts(CostNode cNode) {
		dependentCosts.add(cNode);
	}

	public void setDependentCosts(ArrayList<CostNode> dependentCosts) {
		this.dependentCosts = dependentCosts;
	}
	
	public ArrayList<CostNode> getDependentCosts() {
		return dependentCosts;
	}

	public boolean hasDependency() {
		return !dependentCosts.isEmpty();
	}

	@Override
	public String toString() {
		return "\n\t\t\tCostNode [cost=" + getValue() + ", name=" + getName() + "]";
	}
	
}
