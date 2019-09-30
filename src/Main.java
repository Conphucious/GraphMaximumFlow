import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import nodes.CostNode;
import nodes.Node;
import nodes.ProjectNode;

public class Main {

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);

		String fileName = "";
		while (!fileName.equals("exit")) {

			System.out.print("\n\nEnter the file path: ");
			fileName = input.nextLine();

			File file = new File(fileName);
			
			if (fileName.trim().toLowerCase().equals("exit"))
				return;
			
			if (!file.exists())
				System.out.println("File not found!");
			else
				calculateFlow(file);
		}
		
		input.close();
	}

	public static void calculateFlow(File file) {
		List<Node> nodeList = createNodes(file);
		Graph x = linkDependencies(nodeList, file);
		x.getMaximumFlow();
	}

	public static List<Node> createNodes(File file) {
		String fileStr = "";
		List<Node> nodeList = new ArrayList<>();

		Scanner textInput;
		try {
			textInput = new Scanner(file);
			while (textInput.hasNextLine()) {
				
				
				String liner = textInput.nextLine();
				fileStr += liner + "\n";

				String[] line = liner.split(" ");

				if (!line[0].isEmpty()) {
					String name = line[0];
					int value = Integer.valueOf(line[1]);

					Node node = null;

					if (value < 0)
						node = new CostNode(value, name);
					else if (value >= 0)
						node = new ProjectNode(value, name);

					nodeList.add(node);
				}
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		System.out.println(fileStr);
		return nodeList;
	}

	public static Graph linkDependencies(List<Node> nodeList, File file) {
		Scanner textInput;
		try {
			textInput = new Scanner(file);
			while (textInput.hasNextLine()) {
				String[] line = textInput.nextLine().split(" ");

				if (!line[0].isEmpty()) {
					int value = Integer.valueOf(line[1]);

					for (Node node : nodeList) {
						if (node.getName().equals(line[0]) && node.getValue() == value) {
							if (line.length > 2) {
								String[] dependencies = line[2].split(",");
								for (String name : dependencies) {
									for (int i = 0; i < nodeList.size(); i++) {
										if (nodeList.get(i).getName().equals(name)) {
											if (value < 0) { // cost
												((CostNode) node).addToDependentCosts((CostNode) nodeList.get(i));
											} else if (value >= 0) { // profit
												if (nodeList.get(i).getValue() < 0)
													((ProjectNode) node)
															.addToDependentCosts((CostNode) nodeList.get(i));
												else if (nodeList.get(i).getValue() >= 0)
													((ProjectNode) node)
															.addToDependentProjects((ProjectNode) nodeList.get(i));
											}
										}
									}
								}
							}

						}
					}
				}
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		Graph graph = new Graph();
		for (Node pNode : nodeList) {
			if (pNode instanceof ProjectNode) {
				graph.addToGraph((ProjectNode) pNode);
			}
		}

		return graph;
	}

}
