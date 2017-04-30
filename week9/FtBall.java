package week9;
// code inspired from http://jeffe.cs.illinois.edu/teaching/algorithms/2009/notes/17-maxflowapps.pdf and wiki
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class FtBall {

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int cas = Integer.parseInt(br.readLine());
		FbTour[] tours = new FbTour[cas];
		for (int tc = 0; tc < cas; tc++) {
			String[] input = br.readLine().split(" ");
			int numTeams = Integer.parseInt(input[0]);
			int numMatches = Integer.parseInt(input[1]);
			FbTour tour = new FbTour(numTeams);
			tours[tc] = tour;

			String[] wins = br.readLine().split(" ");
			for (int i = 1; i <= numTeams; i++) {
				tour.teams[i - 1] = new Team(i, Integer.parseInt(wins[i - 1]));
			}

			for (int i = 0; i < numMatches; i++) {
				String[] match = br.readLine().split(" ");
				int teamA = Integer.parseInt(match[0]);
				int teamB = Integer.parseInt(match[1]);
				tour.matches[teamA][teamB] += 1;
				tour.matches[teamB][teamA] += 1;
				tour.teams[teamA - 1].maxWins += 1;
				tour.teams[teamB - 1].maxWins += 1;
			}

			tour.calcResult();
			if (tc != cas - 1)
				br.readLine();
		}

		for (int i = 0; i < cas; i++) {
			FbTour tour = tours[i];
			sb.append("Case #");
			sb.append((i + 1));
			sb.append(":");
			for (Team t : tour.teams) {
				sb.append(String.format(" %s", t.isWins ? "yes" : "no"));
			}
			sb.append("\n");
		}
		System.out.println(sb.toString());
	}
}

class Node {
	int num;
	int wgt;
	List<Edge> incidentEdges;

	public Node(int id) {
		this.num = id;
		this.wgt = 0;
		this.incidentEdges = new ArrayList<Edge>();
	}
}

class Edge {
	int source;
	int destination;
	int wgt;
	int flow;
	int reverse;

	public Edge(int start, int end, int capacity, int rev) {
		this.source = start;
		this.destination = end;
		this.wgt = capacity;
		this.flow = 0;
		this.reverse = rev;
	}
}

class FbTour {
	int[][] matches;
	int numTeams;
	Team[] teams;

	public FbTour(int numberOfTeams) {
		this.numTeams = numberOfTeams;
		matches = new int[numberOfTeams + 1][numberOfTeams + 1];
		for (int i = 0; i < matches.length; i++) {
			Arrays.fill(matches[i], 0);
		}
		teams = new Team[numberOfTeams];
	}

	public void calcResult() {
		Team[] temporaryTeams = new Team[numTeams];
		System.arraycopy(teams, 0, temporaryTeams, 0, numTeams);
		Arrays.sort(temporaryTeams);
		int maxCurrentWin = 0;
		for (Team t : temporaryTeams) {
			maxCurrentWin = t.presentWins > maxCurrentWin ? t.presentWins : maxCurrentWin;
		}

		for (Team t : temporaryTeams) {
			if (t.maxWins < maxCurrentWin) {
				t.isWins = false;
				continue;
			}
			int teamNum = t.num;
			int numberOfMatchesBetweenOtherTeams = 0;

			for (int i = 1; i < matches.length; i++) {
				for (int j = 1; j < matches.length; j++) {
					if (i != teamNum && j != teamNum && matches[i][j] != 0 && i < j) {
						numberOfMatchesBetweenOtherTeams += matches[i][j];
					}
				}
			}

			int targetNum = 600;
			Node source = new Node(0);
			Node target = new Node(targetNum);
			Graph graph = new Graph(0, targetNum);
			graph.nodes.put(0, source);
			graph.nodes.put(targetNum, target);
			int nodeCount = 199;

			for (int i = 1; i < matches.length; i++) {
				if (i != teamNum) {
					Node teamNode = new Node(i);
					graph.nodes.put(i, teamNode);
					teamNode.incidentEdges.add(new Edge(i, targetNum, teams[teamNum - 1].maxWins - teams[i - 1].presentWins, target.incidentEdges.size()));
					target.incidentEdges.add(new Edge(targetNum, i, 0, teamNode.incidentEdges.size() - 1));
				}
				for (int j = 1; j < matches.length; j++) {
					if (i != teamNum && j != teamNum) {
						if (i > j && matches[i][j] > 0) {
							Node matchNode = new Node(++nodeCount);
							graph.nodes.put(nodeCount, matchNode);

							matchNode.incidentEdges.add(new Edge(nodeCount, 0, 0, matchNode.incidentEdges.size()));
							source.incidentEdges.add(new Edge(0, nodeCount, matches[i][j], matchNode.incidentEdges.size() - 1));

							matchNode.incidentEdges.add(new Edge(nodeCount, i, Integer.MAX_VALUE, graph.nodes.get(i).incidentEdges.size()));
							graph.nodes.get(i).incidentEdges.add(new Edge(i, nodeCount, 0, matchNode.incidentEdges.size() - 1));

							matchNode.incidentEdges.add(new Edge(nodeCount, j, Integer.MAX_VALUE, graph.nodes.get(j).incidentEdges.size()));
							graph.nodes.get(j).incidentEdges.add(new Edge(j, nodeCount, 0, matchNode.incidentEdges.size() - 1));
						}
					}
				}
			}

			graph.doDinitz();
			if (graph.maxFlow == numberOfMatchesBetweenOtherTeams) {
				t.isWins = true;
			} else {
				t.isWins = false;
			}
		}
	}
}

class Team implements Comparable<Team> {
	int num;
	int presentWins;
	int maxWins;
	boolean isWins;

	public Team(int id, int currentWin) {
		this.num = id;
		this.presentWins = currentWin;
		this.maxWins = currentWin;
		isWins = false;
	}
	public int compareTo(Team t1) {
		return Integer.compare(maxWins, t1.maxWins);
	}
}

class Graph {
	Map<Integer, Node> nodes;

	int sourceNode, targetNode;
	int maxFlow;

	public Graph(int source, int target) {
		nodes = new HashMap<Integer, Node>();
		this.sourceNode = source;
		this.targetNode = target;
		maxFlow = 0;
	}

	/*Code taken from https://sites.google.com/site/indy256/algo/dinic_flow*/
	public void doDinitz() {
		Map<Integer, Integer> dist = new HashMap<Integer, Integer>();
		while (doBfs(sourceNode, targetNode, dist)) {
			Map<Integer, Integer> ptr = new HashMap<Integer, Integer>();
			for (Node v : nodes.values()) {
				ptr.put(v.num, 0);
			}
			while (true) {
				int df = doDinicDfs(ptr, dist, targetNode, sourceNode, Integer.MAX_VALUE);
				if (df == 0)
					break;
				maxFlow += df;
			}
		}
	}

	private boolean doBfs(int sourceId, int targetId, Map<Integer, Integer> dist) {
		dist.clear();
		dist.put(sourceId, 0);
		int[] Q = new int[nodes.size()];
		int sizeOfQ = 0;
		Q[sizeOfQ++] = sourceId;
		for (int i = 0; i < sizeOfQ; i++) {
			int u = Q[i];
			for (Edge e : nodes.get(u).incidentEdges) {
				if (!dist.containsKey(e.destination) && e.flow < e.wgt) {
					dist.put(e.destination, dist.get(u) + 1);
					Q[sizeOfQ++] = e.destination;
				}
			}
		}
		return dist.containsKey(targetId);
	}

	private int doDinicDfs(Map<Integer, Integer> ptr, Map<Integer, Integer> dist, int targetId, int u, int flow) {
		if (u == targetId) {
			return flow;
		}
		while(ptr.get(u) < nodes.get(u).incidentEdges.size()){
			Edge e = nodes.get(u).incidentEdges.get(ptr.get(u));
			if (dist.containsKey(e.destination) && dist.containsKey(u) && (dist.get(e.destination) == dist.get(u) + 1) && e.flow < e.wgt) {
				int df = doDinicDfs(ptr, dist, targetId, e.destination, Math.min(flow, e.wgt - e.flow));
				if (df > 0) {
					e.flow += df;
					nodes.get(e.destination).incidentEdges.get(e.reverse).flow -= df;
					return df;
				}
			}
			 ptr.replace(u, ptr.get(u) + 1);
		}
		return 0;
	}
}