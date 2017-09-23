
public class HeapClass {
	
	static int size=0;
	static int[] index=new int[10000];
	static int[] key=new int[10000];
	static int[] a=new int[10000];
	static int count=0;
	
	

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		HeapClass Heap=new HeapClass();
		Heap.insert(1,2);
		Heap.insert(2,3);
		Heap.insert(3,4);
		Heap.insert(4,8);
		Heap.insert(5,5);
		Heap.insert(6,6);
		Heap.insert(7,9);
		
		for(int i=1;i<=Heap.getSize();i++)
		{
			System.out.println(Heap.key[i]+" "+Heap.a[i]);
		}
		
		
		
	}
	
	public static void getHeapElements()
	{
		HeapClass heap=new HeapClass();
		for(int i=0;i<heap.getSize();i++)
		System.out.println("Heap: "+a[i]+" "+key[i]);
	}
	
	public static int getSize()
	{
		return size;
	}
	
	
	
	public static void insert(int value,int distance)
	{
		if(size<10000)
		{
			size=size+1;
			a[size]=value;
			index[value]=size;
			key[value]=distance;
			heapify_up(size);
			count++;              // element added to heap
		}
		else
		{
			System.out.println("Cannot insert into heap");
		}
	}
	
	public static void heapify_up(int i)
	{
		int j,temp;
		while(i>1)
		{
			j=(int) Math.floor(i/2);
			if(key[a[j]]>key[a[i]])
			{
				temp=a[i];
				a[i]=a[j];
				a[j]=temp;
				index[a[i]]=i;
				index[a[j]]=j;
				i=j;
			}
			else
			{
				break;
			}
		}
	}
	
	public static int extract_min()
	{
		int ret;
		ret=a[1];
		a[1]=a[size];
		index[a[1]]=1;
		if(size>1)
		{
			size=size-1;
			if(size>=1)
			{
				heapify_down(1);
			}
		}	
		else
		{
	//		Heap is EMPTY
	//		System.out.println("heap is now empty");
		}
		return ret;
	}
	
	public static void heapify_down(int i)
	{
		int j,temp;
		while(2*i<=size)
		{
			if(2*i==size||key[a[2*i]]<=key[a[(2*i)+1]])
			{
				j=2*i;
			}
			else
			{
				j=(2*i)+1;
			}
			if(key[a[j]]<key[a[i]])
			{
				temp=a[i];
				a[i]=a[j];
				a[j]=temp;
				index[a[i]]=i;
				index[a[j]]=j;
				i=j;
			}
			else
			{
				break;
			}
		}
	}

	public static void decrease_key(int value,int new_dist)
	{
		key[value]=new_dist;
		heapify_up(index[value]);
	}
	
	
}
