# READEME - Weighted Graph:

This assignment  about weighted graphs, and its implications. how to find the shortest path between two given nodes, Check if a  graph is connectivity and  copy,save and load a graph.

#  The implements :
-   **weighted_graph**  (implemented by  **WGraph_DS**) - an object which represents a weighted graph.
    
    -   **node_info**  (implemented by  **NodeInfo**) - an object displays the vertices in the graph and the actions that are performed on them. The vertex receives a key, tag, and data.
-   **weighted_graph_algorithms**  (implemented by  **WGraph_Algo**) - an object that implements some basic graph algorithms with # Dijkstra's algorithm.
- The main reasons that i chose HashMap is because i saw that we need to do things in O(1) and we neet to work whit Collections , for example methods - add ,size,values,remove,getV and more.
- i created a class edge_info for to save all the data and to do all the action on the neibers and the edges. 

#  Methods
### WGraph_DS
In this class i implements the interfaces - weighted_graph and  Serializable.
Additionally in this class we created two Internal classes - *node_info* and *edge_info*. 

**node_info**

| Name |  Description|
|--|--|
| NodeInfo () |  Constructor|
| NodeInfo(int k) | Constructor that got a key   |
| getKey() | Returns the nodes key |
| getInfo() | Returns the nodes String |
| setInfo(String s) | Set the info nodes |
| getTag() | Returns the nodes tag |
| setTag(double t) |Set the tag nodes  |
| compareTo(node_info n) | comper between two nodes tag |

**edge_info**
| Name |  Description|
|--|--|
| edge_info()|  Constructor|
| setWeight(int dest, double w)| Set the weight of the edge
| connectEdge(int n, double w) | Connect between to nodes |
| hasNi(int n) |  Check if to nodes key have a neibers|
| getNi() | Returns a collection of the neibers |
|getWeight(int dest) | Returns the whight of edge |
| removeSrc()  |  Remove all the nodes in the Hashmap|
|  removeEdge(int n)| Remove a edge |


**WGraph_DS**

| Name |  Description|
|--|--|
| WGraph_DS ()|  Constructor|
| getNode(int key)| Returns a node with a key
| hasEdge(int node1, int node2) |Check if is an edge between two nodes |
| getEdge(int node1, int node2) |  Returns the weight of the edge|
| addNode(int key) | Add node to the graph |
|connect(int node1, int node2, double w) | Connect btween two nodes and init the wight of the edge |
| getV()  |  Returns a Collection representing all the nodes in the graph|
|  getV(int node_id)| Returns all the neibers of node id |
| removeNode(int key)| Remove a node from the graph |
| nodeSize()| Returns the number of nodes in the graph |
| edgeSize()| Returns the number of edges in the graph |
|getMC()  | Returns the number of actions performed on the graph |


### WGraph_Algo

In this class i implements the interfaces -  weighted_graph_algorithms.

| Name |  Description|
|--|--|
| init(weighted_graph g)|  Initialization the graph|
|getGraph() | Returns a pointer to the initialized graph
| copy()| Copy constructor - deep copy |
| isConnected()|  Checking connectivity of the graph|
| shortestPathDist(int src, int dest)| Returns the sort distance between src to dest whit Dijkstra's algorithm |
|shortestPath(int src, int dest)| Returns the way from src to dist whit Dijkstra's algorithm and whit a list |
| save(String file)  | Saves a graph to a file whit Serialization|
| load(String file) | Load a graph from a file whit Deserialization |


## Links I used for the project

- https://en.wikipedia.org/wiki/Connectivity_(graph_theory)
- https://stackoverflow.com/questions/30140030/how-to-check-if-graph-is-connected
- https://www.geeksforgeeks.org/check-if-a-directed-graph-is-connected-or-not/
- https://en.wikipedia.org/wiki/Priority_queue
- https://en.wikipedia.org/wiki/Dijkstra%27s_algorithm
- https://www.youtube.com/watch?v=pVfj6mxhdMw
- https://docs.oracle.com/javase/8/docs/api/java/util/HashMap.html
- https://www.tutorialspoint.com/java/java_serialization.htm
- https://www.geeksforgeeks.org/serialization-in-java/
- https://www.softwaretestinghelp.com/dijkstras-algorithm-in-java/
- https://www.baeldung.com/java-dijkstra
- https://docs.oracle.com/javase/8/docs/api/java/util/Objects.html