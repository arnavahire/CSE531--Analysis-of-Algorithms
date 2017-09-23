
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class Prim6{

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		prim();
	}
	public static void prim() throws IOException
	{
		// For scanning the input from input file
		
		File file = new File("/home/arnav/input.txt");
		Scanner scan=new Scanner(file);
		
		// For putting the output from console to output file
		
		File output=new File("/home/arnav/output.txt");
		FileOutputStream fos=new FileOutputStream(output);
		PrintStream ps=new PrintStream(fos);
		System.setOut(ps);
		
		// We will scan  the first line independently
		
		String[] split=scan.nextLine().split(" ");
		int vertices=Integer.parseInt(split[0]);  // here we will get total vertices for the graph
		int edges=Integer.parseInt(split[1]);     // here we will get total edges for the graph
		
		if(vertices==1)
		{
			System.out.println("Please enter more than 1 vertex.");
			System.exit(0);
		}
			
	//	System.out.println(vertices+" "+edges);
		
		//create a graph
		
		int graph[][]=new int[vertices][vertices];
		
		// Scan other lines 
		try
		{
				while(scan.hasNextLine())
				{
					
					String[] split2=scan.nextLine().split(" ");   // we obtain the splitted string where we get vertex1,vertex2,weight
					int vertex1=Integer.parseInt(split2[0]);
					int vertex2=Integer.parseInt(split2[1]);
					int weight=Integer.parseInt(split2[2]);
		//			System.out.println((vertex1-1)+" "+(vertex2-1)+" "+weight);
					int v1=vertex1-1;                                             //our graph always starts with 0th vertex such that graph[0][1], graph[0][2] exists. So we convert all the graphs to our graph
					int v2=vertex2-1;
					graph[v1][v2]=weight;
					graph[v2][v1]=weight;
				}
		}
		catch(Exception e)
		{
			System.out.println("You have entered wrong input. Please provide correct input.");
			System.exit(0);
		}
	/*	Code To check the adjacency matrix
	 * for(int i=0;i<vertices;i++)
		{
			for(int j=0;j<vertices;j++)
			{
				System.out.println("Graph: ("+i+","+j+") weight: "+graph[i][j]);
			}
		}
	*/	
		
		
		
	
		
	
		int visited[]=new int[vertices];
		int dv[]=new int[vertices];
		int pi[]=new int[vertices];
		int s=0;
		LinkedList<Integer> S=new LinkedList();
		LinkedList<Integer> U=new LinkedList<>();
		LinkedList<Integer> V=new LinkedList<>();
		LinkedList<Integer> W=new LinkedList<>();
		
		int u;
		
		HeapClass heap=new HeapClass();
		
	
		
		for(int i=0;i<vertices;i++)   //in this for loop we cant use vertex 0 in the grraph. our graph will always begin with vertex 1
		{
			visited[i]=0;
			
		}
		
		
		
		//Prim's algorithm starts--------------------------------------------------------
		
		//insert into priority queue
		
		visited[0]=1;
		//pi[0]=0;    // set parent of 1st vertex as itself
		
		// set 1st vertex with dv=0 and for every other vertex dv=infinity and insert in priority queue
		
		for(int i=0;i<vertices;i++)
		{
			if(visited[i]==1)
			{
				dv[i]=0;
				heap.insert(i, dv[i]);
			}
			else
			{
				dv[i]=99999;
				heap.insert(i, dv[i]);
			}
		}
		
		for(int i=0;i<vertices;i++)  // set parent of each node as -1 for every other vertex i.e each vertex has no parent
		{
			pi[i]=-1;            
		}
		
	//for(int i=0;i<vertices;i++)
	//	System.out.println("v :"+i+" d(v) :"+dv[i]);

	/*	To check whether elements are getting stored in heap
	 * for(int i=0;i<heap.getSize();i++)
		{
			System.out.println("Heap v :"+heap.a[i+1]+"dv: "+heap.key[i]);  //a[0] has nothing stored in it as we are storing everything from a[1]
		}
	*/	
		int total=0;
	
		while(s<vertices)
		{
			if(heap.getSize()==0)
			{
				break;
			}
			else
			{
				u=heap.extract_min();
				total=total+dv[u];         // vertex containing minimum weight will be added to tree weight
				S.add(u);         // add u to the solution'
				s++;
			
				for(int v=0;v<vertices;v++)   // V- S elements are stored in the heap
				{	
					if(S.contains(v))  // if solution contains v then dont take v
					{
						continue;
					}
					else
					{
						if((graph[u][v]==graph[v][u])&&graph[u][v]!=0)   
						{
							if(graph[u][v]<dv[v])
							{
								dv[v]=graph[u][v];
								heap.decrease_key(v,dv[v]);
								pi[v]=u;
							}
							else
							{
							
							}
						}	
					}	
				}
				if(u==0)  // there will be no edge for the first vertex so we dont add 
				{
					
				}
				else     // add the edge in the U list and V list 
				{
					U.add(pi[u]+1);   //plus one to bring the graph back to normal input form
					V.add(u+1);       //plus one to bring the graph back to normal input form
					W.add(dv[u]);
				}
			}
		}
		
		System.out.println(total);
		for(int i=0;i<U.size();i++)
		{
			System.out.println(U.get(i)+" "+V.get(i)+" "+W.get(i));
		}
		
	}
}