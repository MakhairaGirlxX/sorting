package SortingProject;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class SortingDriver {
	public static void main(String[] args) {		
		Integer [] arr = new Integer[5];
		
		arr[0] = 3;
		arr[1] = 6;
		arr[2] = 2;
		arr[3] = 9;
		arr[4] = 5;
		
		long start = System.currentTimeMillis();		
		ISorting<Integer> task = new Sorting<Integer>(arr);
		
		task.setAlgorithm("selection sort");
		task.getData("ascending");
		
		task.setComparator(new Comparator<Integer>()
		{
			public int compare(Integer num1, Integer num2)
			{
				num1 = arr[0];
				num2 = arr[2];
				if(num1.compareTo(num2) > 1)
				{
					return 1;
				}
				else if(num1.compareTo(num2) < 1)
				{
					return 0;
				}
				return -1;
			}
		});
       
		long end = System.currentTimeMillis();
        
		System.out.println("Sorted List: " + task.displaySortedList());
		System.out.println("Execution Time: " + task.executionTime(end, start));
		
	}

}


class Sorting<T> implements ISorting<T>
{
	public Integer[] arr;
	Comparator<Integer> comp;


	public Sorting(Integer[] arr)
	{
		this.arr = arr;
		comp = null;
	}

	@Override
	public IAction getData(String data) {
		
		switch(data)
		{
		case "descending":
			System.out.println("Descending Order");
			return new Descend(arr);
		case "ascending":
			System.out.println("Ascending Order");
			displaySortedList();
			break;
		case "random":
			System.out.println("Random Order");
			return new Randomize(arr);
		default:
			System.out.println("No Match");
		}
		return null;	
		
	}

	@Override
	public IAction setAlgorithm(String alg) {
		
		switch(alg)
		{
		case "insertion sort":
			System.out.println("Insertion Sort");
			return new InsertionSort(arr);
		case "bubble sort":
			System.out.println("Bubble Sort");
			return new BubbleSort(arr);
		case "selection sort":
			System.out.println("Selection Sort");
			return new SelectionSort(arr);
		case "merge sort":
			System.out.println("Merge Sort");
			return new MergeSort(arr);
		case "heapsort":
			System.out.println("Heap Sort");
			return new HeapSort(arr);
		default:
			System.out.println("No Match");
		}
		return null;			
	}

	@Override
	public void setComparator(Comparator<Integer> comp) {
			this.comp = comp;
	}


	@Override
	public String displaySortedList() {
		return Arrays.toString(arr);
	}

	@Override
	public long executionTime(long end, long start) {
		long execution = end - start;
		return execution;
	}
	
}
class Randomize implements IAction
{
	public Randomize(Integer[] arr)
	{
		takeAction(arr);
	}
	
	public void takeAction(Integer[] arr)
	{
		List<Integer> list = Arrays.asList(arr);
		Collections.shuffle(list);
	}
}

class Descend implements IAction
{
	public Descend(Integer[] arr)
	{
		takeAction(arr);
	}
	@Override
	public void takeAction(Integer[] arr) {	
		Arrays.sort(arr, Collections.reverseOrder());
	}
	
}


class InsertionSort implements IAction
{

	public InsertionSort(Integer[] arr) {
		takeAction(arr);
	}

	@Override
	public void takeAction(Integer[] arr) 
	{
		int n = arr.length;
        for (int i = 1; i < n; ++i) {
            int key = arr[i];
            int j = i - 1;
 
            while (j >= 0 && arr[j] > key) {
                arr[j + 1] = arr[j];
                j = j - 1;
            }
            arr[j + 1] = key;
        }
		
	}
	
}

class BubbleSort implements IAction
{
	public BubbleSort(Integer[] arr)
	{
		takeAction(arr);
	}

	@Override
	public void takeAction(Integer[] arr) {
		int n = arr.length; 
        for (int i = 0; i < n-1; i++) 
            for (int j = 0; j < n-i-1; j++) 
                if (arr[j] > arr[j+1]) 
                { 
                    int temp = arr[j]; 
                    arr[j] = arr[j+1]; 
                    arr[j+1] = temp; 
                } 
	}

}		

class SelectionSort implements IAction
{
	public SelectionSort(Integer[] arr)
	{
		takeAction(arr);
	}

	@Override
	public void takeAction(Integer[] arr) {
		for(int i = 0; i < arr.length - 1; i++)
		{
			int ind = i;
			for(int j = i + 1; j < arr.length; j++)
			{
				if(arr[j] < arr[ind])
				{
					ind = j;
				}
			}
			int temp = arr[ind];
			arr[ind] = arr[i];
			arr[i] = temp;
		}
		
	}
	
}

class MergeSort implements IAction
{
	public MergeSort(Integer[] arr)
	{
		takeAction(arr);
	}

	public void takeAction(Integer[] arr) {
		
		  if(arr == null)
	        {
	            return;
	        }
	 
	        if(arr.length > 1)
	        {
	            int mid = arr.length / 2;
	 
	            Integer[] left = new Integer[mid];
	            for(int i = 0; i < mid; i++)
	            {
	                left[i] = arr[i];
	            }
	             
	            Integer[] right = new Integer[arr.length - mid];
	            for(int i = mid; i < arr.length; i++)
	            {
	                right[i - mid] = arr[i];
	            }
	            takeAction(left);
	            takeAction(right);
	 
	            int i = 0;
	            int j = 0;
	            int k = 0;
	 

	            while(i < left.length && j < right.length)
	            {
	                if(left[i] < right[j])
	                {
	                    arr[k] = left[i];
	                    i++;
	                }
	                else
	                {
	                    arr[k] = right[j];
	                    j++;
	                }
	                k++;
	            }

	            while(i < left.length)
	            {
	                arr[k] = left[i];
	                i++;
	                k++;
	            }
	            while(j < right.length)
	            {
	                arr[k] = right[j];
	                j++;
	                k++;
	            }
	        }
	}
	
}

class HeapSort implements IAction
{
	public HeapSort(Integer[] arr)
	{
		takeAction(arr);
	}

	@Override
	public void takeAction(Integer[] arr) {
		makeHeap(arr);
		for(int i = arr.length - 1; i > 0; i--)
		{
			Integer temp = arr[0];
			arr[0] = arr[i];
			arr[i] = temp;
			
			reheapDown(arr, 0, i - 1);
		}
		
	}
	
	static void makeHeap(Integer[] arr)
	{
		for(int i = arr.length/2; i >= 0; i--)
		{
			reheapDown(arr, i, arr.length - 1);
		}
	}

	static void reheapDown(Integer[] arr, int i, int endIndex) 
	{
		if(i >= endIndex) return;
		else
		{
			int nextInd = i;
			int left = 2 * i + 1, right = 2 * i + 2;
			if(left == endIndex && arr[left] > arr[i])
			{
				nextInd = left;
			}
			if(right <= endIndex)
			{
				if(arr[right] > arr[left] && arr[right] > arr[i])
				{
					nextInd = right;
				}
				else if(arr[left] > arr[i])
				{
					nextInd = left;
				}
			}
			if(nextInd != i)
			{
				int temp = arr[i];
				arr[i] = arr[nextInd];
				arr[nextInd] = temp;
				
			}
			
		}
		
	}
	
}

interface IAction
{
	void takeAction(Integer[] arr);
}

interface ISorting <T>
{
	IAction getData(String data);
	String displaySortedList();
	IAction setAlgorithm(String alg);
	void setComparator(Comparator<Integer> comparator);
	long executionTime(long end, long start);
}
