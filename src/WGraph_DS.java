package ex1;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;





public class WGraph_DS implements weighted_graph  , Serializable{

	private static final long serialVersionUID = 3636537648434223L;
	private HashMap<Integer,node_info> mapNodes =  new HashMap<Integer,node_info> ();
	private HashMap<Integer,edge_info> mapEdges =  new HashMap<Integer,edge_info> ();
	//HashMap<node_info, HashMap<Integer, node_info>> mapEdges2 = new HashMap<node_info,HashMap<Integer, node_info>>();
	private static int counterEdge = 0 ;
	private static int counterMC= 0;

	
	

	private class NodeInfo implements node_info , Serializable,Comparable<node_info> {
		private static final long serialVersionUID = 86785677463452323L;
		private int counter = 0 ;   
		private	int key; 
		private String info;
		private double tag;
		private double weight;
		private HashMap<Integer,node_info> map =  new HashMap<Integer,node_info> ();

		public NodeInfo () {
			this.key = counter;  
			counter ++ ; 
			info = "";
			tag = -1;
			weight = -1;
			map =  new HashMap<Integer,node_info> (); 
		}
		private NodeInfo(int k) {
			this.key = k;
			this.tag = -1;
			this.info = null;
		}

		//		public boolean has(int key) {
		//			if (map.containsKey(key)) return true;
		//			return false;
		//		}

		@Override
		public int getKey() {
			return this.key;
		}

		@Override
		public String getInfo() {
			return this.info;
		}

		@Override
		public void setInfo(String s) {
			this.info=s ;
		}

		@Override
		public double getTag() {
			return this.tag;
		}
//		public void setWeight( double w) {
//			this.weight =w;
//		}

		@Override
		public void setTag(double t) {
			this.tag =t;		
		}
//		public void addNi(node_info t) {
//			if (t == null || t.getKey() == this.getKey()) return; 
//			else {
//				map.put(t.getKey(),t) ;
//			}
//		}
		public int compareTo(node_info n) {
			if (this.tag> n.getTag()) return 1;
			else if (this.tag < n.getTag()) return -1;
			return 0;
		}
	}





	private class edge_info implements Serializable {
		private static final long serialVersionUID = 324325463768865835L;
		private HashMap<Integer,Double> map3;

		private edge_info() {
			map3 = new HashMap<>();
		}

		private void setWeight(int dest, double w) {
			map3.put(dest,w);
		}

		private void connectEdge(int n, double w) {
			map3.put(n,w);
		}

		private boolean hasNi(int n) {
			return map3.containsKey(n);
		}

		private Collection<node_info> getNi() {
			Collection<node_info> nieber = new ArrayList<>();
			for (Integer k :map3.keySet()) {
				nieber.add(mapNodes.get(k));
			}
			return nieber;
		}

		private double getWeight(int dest) {
			return map3.get(dest);
		}

		private void removeSrc() {
			map3.clear();
		}

		private void removeEdge(int n) {
			map3.remove(n);
		}
	}


	/**
	 * Constructor
	 */
	public WGraph_DS () {
		mapNodes = new HashMap<Integer,node_info>();
		mapEdges =  new HashMap<Integer,edge_info> ();
		counterEdge = 0;
		counterMC = 0;
	}


	@Override
	public node_info getNode(int key) {
		if (mapNodes.get(key) == null) return null;
		return mapNodes.get(key);
	}


	@Override
	public boolean hasEdge(int node1, int node2) {
		if (node1 == node2) return false;
        if (node1 == node2 && mapNodes.containsKey(node1)) return true;
		if (! mapNodes.containsKey(node1) || ! mapNodes.containsKey(node2)) return false;
		return mapEdges.get(node1).map3.containsKey(node2);
	}

	@Override
	public double getEdge(int node1, int node2) {
		if(node1==node2) return 0;
		if (! mapNodes.containsKey(node2) || ! mapNodes.containsKey(node1)) return -1;
		if(mapEdges.get(node1).map3.containsKey(node2)) {
			double x = mapEdges.get(node1).getWeight(node2);
			return x;
		}else {
			return -1;
		}
	}

	@Override
	public void addNode(int key) {
		if (mapNodes.containsKey(key))return;
		else {
			node_info  a = new NodeInfo (key);
			mapNodes.put(key, a);
			edge_info n = new edge_info ();
			mapEdges.put(key,n);
			counterMC ++ ;
		}
	}

	@Override
	public void connect(int node1, int node2, double w) {
		// the weight can't be negative
		if (w < 0)
			return;
		if (!mapNodes.containsKey(node1) && !mapNodes.containsKey(node2))
			return;
		if (hasEdge(node1, node2)) {
			mapEdges.get(node1).setWeight(node2, w);
			mapEdges.get(node2).setWeight(node1, w);//????
			counterMC++;
		}
		//chek if the node are the same and if node1 isn't already connect to node2
		if ((node1 == node2) || (hasEdge(node1, node2)))
			return;
		//Checking that they are not null
		if ((mapNodes.get(node1) == null) || (mapNodes.get(node2) == null))
			return;
		//else, connect them to each other
		else {
			this.mapEdges.get(node1).connectEdge(node2, w);
			this.mapEdges.get(node2).connectEdge(node1, w);
			counterEdge++;
			counterMC++;
			return;
		}
	}



	@Override
	public Collection<node_info> getV() {
		return mapNodes.values();
	}

	@Override
	public Collection<node_info> getV(int node_id) {
		return mapEdges.get(node_id).getNi();
	}

	@Override
	public node_info removeNode(int key) {
		if (!mapNodes.containsKey(key)) {
			return null;
		}
		else {
			node_info t= mapNodes.get(key);
			mapNodes.remove(key);
			for (node_info node : this.getV(key)) {
				mapEdges.get(node.getKey()).removeSrc();
				counterMC++;
				counterEdge--;
			}
			mapNodes.remove(key);
			counterMC++;
			//counterEdge--;
			return t;
		}
	}

	@Override
	public void removeEdge(int node1, int node2) {
		if (mapNodes.containsKey(node1) && mapNodes.containsKey(node2) && mapEdges.get(node1).hasNi(node2) ) {
			mapEdges.get(node2).removeEdge(node1);
			mapEdges.get(node1).removeEdge(node2);
			counterMC++;
			counterEdge -- ;
		}else return;

	}

	@Override
	public int nodeSize() {
		return mapNodes.size();
	}

	@Override
	public int edgeSize() {
		return counterEdge;
	}

	@Override
	public int getMC() {
		return counterMC;
	}
	public void addNi(node_info n)
	{
		if (n == null)
			return;
		else
			mapNodes.put(n.getKey(), n);
	}

	// ********** in this method i will use in the BFS algorithm  *************
	public void setcounterEdge(int counterEdge) {
		this.counterEdge = counterEdge;
	}

	public void setcounterMC(int counterMC) {
		counterMC = counterMC;
	}
	public WGraph_DS(WGraph_DS gr) {
		weighted_graph graphCopy = new WGraph_DS();
		for (node_info n : this.getV()) {
			graphCopy.addNode(n.getKey());
			for (node_info in : this.getV(n.getKey())) {
				graphCopy.connect(n.getKey(), in.getKey(), this.getEdge(n.getKey(), in.getKey()));
			}
		}
	}

}
