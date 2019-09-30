package nodes;
import java.util.ArrayList;

public class ProjectNode extends Node {
	
	private int profit;
	private String name;
	
	private ArrayList<CostNode> dependentCosts;
	private ArrayList<ProjectNode> dependentProjects;
	
	public ProjectNode(int profit, String name) {
		super(profit, name);
		dependentCosts = new ArrayList<>();
		dependentProjects = new ArrayList<>();
	}
	
	public boolean hasDependency() {
		return !dependentProjects.isEmpty();
	}

	public ArrayList<CostNode> getDependentCosts() {
		return dependentCosts;
	}
	
	public void addToDependentCosts(CostNode cNode) {
		dependentCosts.add(cNode);
	}

	public void setDependentCosts(ArrayList<CostNode> dependentCosts) {
		this.dependentCosts = dependentCosts;
	}

	public ArrayList<ProjectNode> getDependentProjects() {
		return dependentProjects;
	}

	public void addToDependentProjects(ProjectNode pNode) {
		dependentProjects.add(pNode);
	}
	
	public void setDependentProjects(ArrayList<ProjectNode> dependentProjects) {
		this.dependentProjects = dependentProjects;
	}

	@Override
	public String toString() {
//		ArrayList<String> info = new ArrayList<>();
//		for (ProjectNode x : dependentProjects)
//			info.add(x.getProfit() + ", " + x.getName());
//		
//		return "\n\tProjectNode [" + profit + ", " + name + " ]"
//				+ "\n\t\tdependent costs:" + dependentCosts
//				+ "\n\t\tdependent projects: \n\t\t\t" + info + "\n";
		
//		return "\n\tProjectNode [profit=" + profit + ", name=" + name + "]\n\t" + dependentCosts
//				+ "\n\tDP:" + dependentProjects + "\n\t";
		
		return getName();
	}
	
}
