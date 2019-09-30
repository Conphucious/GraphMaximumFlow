import java.util.List;

import nodes.ProjectNode;

public class Evaluation {
	
	private List<ProjectNode> list;
	private int profit, cost, net;
	
	public Evaluation(List<ProjectNode> list, int profit, int cost) {
		this.list = list;
		this.profit = profit;
		this.cost = cost;
		net = profit + cost;
	}
	
	public List<ProjectNode> getList() {
		return list;
	}
	
	public void setList(List<ProjectNode> list) {
		this.list = list;
	}
	
	public int getProfit() {
		return profit;
	}
	
	public void setProfit(int profit) {
		this.profit = profit;
	}
	
	public int getCost() {
		return cost;
	}
	
	public void setCost(int cost) {
		this.cost = cost;
	}
	
	public int getNet() {
		return net;
	}
	
	public void setNet(int net) {
		this.net = net;
	}
	
	@Override
	public String toString() {
		return "Evaluation [list=" + list + ", profit=" + profit + ", cost=" + cost + ", net=" + net + "]";
	}
	
}
