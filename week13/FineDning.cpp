#include <iostream>
#include <queue>
#include <algorithm>
#include <climits>
#include <cmath>
#include <vector>

using namespace std;
struct Node {
    int to, weight;
    Node(int to, int weight) {
        this->to = to;
        this->weight = weight;
    }
};
int V;
int varTest = 0;
int conPos = 0;
bool checkInGraph (Node node) {
    if(node.to == varTest){
        conPos = node.to;
        }
    return node.to == varTest; 
}

bool bfs(int s, int t, vector<int>& parent, vector<vector<Node>>& graph)
{
    vector<bool> visited(V, false);
    queue<int> queue;
    queue.push(s);
    visited[s] = true;
    parent[s] = -1;
    while (queue.size() != 0)
    {
        int u = queue.front();
        queue.pop();
        for (Node& v : graph[u])
        {
            if (!visited[v.to] && v.weight > 0)
            {
                queue.push(v.to);
                parent[v.to] = u;
                visited[v.to] = true;
            }
        }
    }
    return (visited[t] == true);
}

static int fordFulkerson(int s, int t, vector<vector<Node>>& graph)
{
    int u, v;
    vector<int> parent(V, 0);  
    int maxFlow = 0;   
    while (bfs(s, t, parent, graph)) {
        int pathFlow = 1;
        int path_flow = INT_MAX;
        for (v = t; v != s; v = parent[v])// finding the maximum flow in the path (augumented graph)
        {
            u = parent[v];           
            auto it = find_if(graph[u].begin(), graph[u].end(), [&v](const Node& node) { return node.to == v; });//searching for the node
            path_flow = min(path_flow, it->weight);
        }
        for (v=t; v != s; v=parent[v])// updating the value of capacity in the augumented graph
        {
            u = parent[v];           
            auto it = find_if(graph[u].begin(), graph[u].end(), [&v](const Node& node) { return node.to == v; });
            it->weight -= path_flow;           
            it = find_if(graph[v].begin(), graph[v].end(), [&u](const Node& node) { return node.to == u; });
            it->weight += path_flow;
        }       
        maxFlow += pathFlow;//summing up all the path flows 
    }
    return maxFlow;
}

int main(int argc, const char * argv[]) {  
    int t;
    int n, m, b;
    int meal, bev;
    int start, end;    
    cin >> t;   
    for (int i = 1; i <= t; i++) {
        cin >> n >> m >> b;        
        V = n + m + m + b + 2;
        vector<vector<Node>> graph(V);      
        for (int j = 1; j <= n; j++) {
            cin >> meal >> bev;
            graph[0].push_back(Node(j, 1));
            graph[j].push_back(Node(n + meal, 1));
            start = n + meal;
            end = n + m + meal;
            varTest = start;
            if (find_if(graph[end].begin(), graph[end].end(), checkInGraph) == graph[end].end())
                graph[end].push_back(Node(start, 0));
            varTest = end;
            if (find_if(graph[start].begin(), graph[start].end(), checkInGraph) == graph[start].end())
                graph[start].push_back(Node(end, 1));
            start = end;
            end = n + m + m + bev;
            varTest = start;
            if (find_if(graph[end].begin(), graph[end].end(), checkInGraph) == graph[end].end())
                graph[end].push_back(Node(start, 0));
            varTest = end;
            if (find_if(graph[start].begin(), graph[start].end(), checkInGraph) == graph[start].end())
                graph[start].push_back(Node(end, 1));
            start = end;
            end = V - 1;          
            if (find_if(graph[start].begin(), graph[start].end(), [&end] (const Node& node) { return node.to == end; }) == graph[start].end())
                graph[start].push_back(Node(end, 1));           
            if (find_if(graph[end].begin(), graph[end].end(), [&start] (const Node& node) { return node.to == start; }) == graph[end].end())
                graph[end].push_back(Node(start, 0));
        }       
         cout << "Case #" << i << ": " << fordFulkerson(0, V - 1, graph) << endl;
    }  
    return 0;
}