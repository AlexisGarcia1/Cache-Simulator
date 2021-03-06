package four.ways.algorithms;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import cache.simulador.readfile.ReadFileArray;
import cache.simulador.readfile.UX;

public class OPTIMO4WAYS {

	static ReadFileArray r = new ReadFileArray(); // calling class with methods: readFile() and convert()
	public int pointer = 0;
	public int hit = 0;
	public int miss = 0;
	private boolean flag;
	public long endTime = 0;
	public long startTime = 0;
	public float timeseconds = 0;
	static UX ob = new UX();

	public void optimusPrime() throws IOException, FileNotFoundException {

		startTime = System.currentTimeMillis();

		int locationFrame;

		int sizeOf = 0; // size of cache 
		r.readFile(); // calling read method 
		ArrayList<Integer> tx = r.convert(); // calling the returned array

		sizeOf = ob.stringToConvert()/4; // size of cache 

		int fileNumbers[] = new int[tx.size()];
		int f[] = new int[sizeOf];
		int pointer[] = new int[sizeOf];

		for(int i = 0; i < tx.size(); i += 1) 
			fileNumbers[i] = tx.get(i);

		for(int i = 0; i < sizeOf; i = i + 1)
		{
			
			for(int t = 0; t < tx.size(); t ++) 
				
			f[i] = fileNumbers[t]; 
			pointer[i] = 0;
			
		}
		for(int i = 0; i < tx.size(); i +=1)
		{
			for(int j = 0; j < sizeOf; j +=1)
			{
				if(f[j] == fileNumbers[i])
				{
					
					flag= true;
					hit +=1;
					break;
				}
				else
					flag= false;
			}
			if(!flag)
			{
				for(int j = 0; j < sizeOf; j +=1)
				{
					for(int k = i + 1; k < tx.size(); k +=1)
					{
						if(f[j] == fileNumbers[k])
							
							pointer[j] = k;
						
						
						else
							pointer[j] = 0;
						
					}
				}
				locationFrame = movingPointer(pointer, sizeOf);
				f[locationFrame] = fileNumbers[i];
				miss++;
			}
		}
		endTime = System.currentTimeMillis();
	}
	static int movingPointer(int pointer[],int size)
	{
		int locationFrame=0;
		
		for(int i = 1; i < size; i +=1)
			
			if((pointer[i] > pointer[i-1]) && (pointer[i]!=0))
				
				locationFrame=i;
		
		return locationFrame;
	}
	
	public int hitReturn() {

		return hit;
	}  

	public int missReturn() {

		return miss;
	}


	public float timeExecution() {
		long total = (endTime - startTime);
		timeseconds = total / 1000.00f;      
		return timeseconds;

	}
}
