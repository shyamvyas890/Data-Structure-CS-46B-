package trees;

import java.util.ArrayList;
import java.util.Collections;
import java.util.PriorityQueue;
@SuppressWarnings("unused")
public class Heap {
	ArrayList<Integer> minHeap= new ArrayList<Integer>();
	public Heap() {
		minHeap.add(0);
	}
	public void add(int a) {
		minHeap.add(a);
		upwardBubbler(minHeap.size()-1);
	}
	public int removeRoot() {
		int val= minHeap.get(1);
		int replacement= minHeap.remove(minHeap.size()-1);
		minHeap.set(1, replacement);
		downwardBubbler(1);
		return val;
	}
	
	public void indexSwitcher(int a, int b) {
		int temp= minHeap.get(a);
		minHeap.set(a, minHeap.get(b));
		minHeap.set(b, temp);
	}
	public void upwardBubbler(int index) {
		int parent= getParentOfIndex(index);
        if(index==1){
            return;
        }
        else if (minHeap.get(parent)>minHeap.get(index)){
            indexSwitcher(parent,index);
            upwardBubbler(parent);
        }
	}
	public void downwardBubbler(int index) {
		int child= getChildOfIndex(index);
	       if(child>minHeap.size()-1){
	           return;
	       }
	       else if(minHeap.get(child)<minHeap.get(index)){
	           indexSwitcher(child,index);
	           downwardBubbler(child);
	       }
	}
	public int getParentOfIndex(int index){
        return (int)Math.floor(index/2.0);
    }
    public int getChildOfIndex(int index){
       int a= 2*index;
       int b= a+1;
       if(a>minHeap.size()-1){
           return a;
       } 
       if(b>minHeap.size()-1){
           return a;
       } 
       if(minHeap.get(a)<minHeap.get(b)){
           return a;
       }
       return b;
    }
    public static void main(String[] args) {
		ArrayList<Integer> b= new ArrayList<Integer>(20);
		b.set(4, 3);
		for(int d: b) {
			System.out.println(d);
		}
		
		
	}
	
	
	
	
}
