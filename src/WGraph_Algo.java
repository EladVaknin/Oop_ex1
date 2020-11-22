package ex1;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.PriorityQueue;
import java.util.Queue;








public class WGraph_Algo implements weighted_graph_algorithms   {

	weighted_graph graph = new WGraph_DS ();



	@Override
	public void init(weighted_graph g) {
		this.graph =g;

	}

	@Override
	public weighted_graph getGraph() {

		return this.graph;
	}

	@Override
	public weighted_graph copy() {
	      weighted_graph result = new WGraph_DS();
	        if (graph.nodeSize() == 0) return result;
	        for (node_info n : this.graph.getV()) {
	            result.addNode(n.getKey());
	        }
	        if (graph.edgeSize() == 0) return result;
	        for (node_info n : this.graph.getV()) {
	            for (node_info inner : this.graph.getV(n.getKey())) {
	                result.connect(n.getKey(),inner.getKey(),this.graph.getEdge(n.getKey(),inner.getKey()));
	            }
	        }
	        return result;
	}

	@Override
	public boolean isConnected() {
		boolean flag = true; ;
		int v = graph.nodeSize();
		if (this.graph==null ||this.graph.nodeSize() <= 1) return flag;
		Collection<node_info> set = graph.getV(); 
		for (node_info n : set) {
			n.setTag(0);
		}
		Queue<Integer> queue = new LinkedList<>();
		int key= this.graph.getV().iterator().next().getKey();
		int counter = 0;
		queue.add(key);
		while (!queue.isEmpty()) {
			key= queue.poll();
			Collection<node_info> get = graph.getV(key); 
			if (get != null) {
				for (node_info b : get) {
					if (b.getTag() == 0) {
						counter ++ ;
						queue.add(b.getKey());
						b.setTag(1);
					}
				}
			}
		}
		if (v == counter) {
			flag = true;
		}else {
			flag = false;
		}
		return flag;
	}


	public double shortestPathDist(int src, int dest) {
        if (src == dest || graph == null || graph.getNode(src) == null || graph.getNode(dest) == null) {
            return -1;
        }
        double dis;
        PriorityQueue<node_info> queue = new PriorityQueue<>();
        node_info tmp = graph.getNode(src);
        queue.add(tmp);
        tmp.setTag(0);
        while (!queue.isEmpty()) {
        	tmp = queue.poll();
            if (!Objects.equals(tmp.getInfo(), "V")) { 
            	tmp.setInfo("V");
                if (tmp.getKey() == dest){
                    break;
                }
                Collection<node_info> get = graph.getV(tmp.getKey()); 
                for (node_info node : get) {
                    if (node.getTag() == -1) {
                        node.setTag(Double.MAX_VALUE);
                    }
                    double prev = tmp.getTag() + graph.getEdge(tmp.getKey(), node.getKey());
                    if (prev < node.getTag()) {
                        node.setTag(prev);
                        queue.add(node);
                    }
                }
            }
        }
        tmp = graph.getNode(dest);
        dis = tmp.getTag();
        if (!Objects.equals(tmp.getInfo(), "V")){
            return -1;
        }
        Collection<node_info> set2 =graph.getV(); 
        for (node_info n : set2) {
            if (n.getInfo() != null || n.getTag() != 0) {
                n.setTag(-1);
                n.setInfo(null);
            }
        }
        return dis;
    }

	
    public List<node_info> shortestPath(int src, int dest) {
    	List<node_info> way = new ArrayList<>();
        if (graph == null || src == dest || graph.getNode(src) == null || graph.getNode(dest) == null) {
            return null;
        }
        PriorityQueue<node_info> queue = new PriorityQueue<>();
        HashMap<node_info,node_info> mapParent = new HashMap<>();
        node_info tmp = graph.getNode(src);
        queue.add(tmp);
        tmp.setTag(0);
        while (!queue.isEmpty()) {
        	tmp = queue.poll();
            if (!Objects.equals(tmp.getInfo(),"V")) {
            	tmp.setInfo("V");
                if (tmp.getKey() == dest){
                    break;
                }
                for (node_info node : graph.getV(tmp.getKey())) {
                    if (node.getTag() == -1) {
                        node.setTag(Double.MAX_VALUE);
                    }
                    double prev = tmp.getTag() + graph.getEdge(tmp.getKey(),node.getKey());
                    if (prev  < node.getTag()) {
                        node.setTag(prev );
                        mapParent.put(node,tmp);
                        queue.add(node);
                    }
                }
            }
        }
        tmp= graph.getNode(dest);
        if (!Objects.equals(tmp.getInfo(), "V")) return null;
        way.add(0,tmp);
        while (tmp.getKey() != src) {
        	way.add(0,mapParent.get(tmp));
        	tmp = mapParent.get(tmp);
        }
        Collection<node_info> set = graph.getV(); 
        for (node_info n : set) {
            if (n.getInfo() != null || n.getTag() != 0) {
                n.setTag(-1);
                n.setInfo(null);
            }
        }
        return way;
    }

	@Override
	public boolean save(String file) {
		 boolean flag = false;
	        try {
	            FileOutputStream file2 = new FileOutputStream (file, true);
	            ObjectOutputStream  graph = new ObjectOutputStream (file2);
	            graph.writeObject(this.graph);
	            file2.close();
	            graph.close();
	            flag = true;
	        } catch (FileNotFoundException e) {
	            System.out.println("not found the file");
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	        return flag;
	    }

	

	@Override
	public boolean load(String file) {
		boolean flag;
		try {
			FileInputStream filefile = new FileInputStream(file);
			ObjectInputStream in = new ObjectInputStream(filefile);
			this.graph= (WGraph_DS) in.readObject();
			in.close();
			filefile.close();
			flag = true;
			return flag;
		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
			flag =false;
			return flag;
		}
	}

}
