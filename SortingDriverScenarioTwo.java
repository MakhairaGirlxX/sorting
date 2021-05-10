package SortingProjectS2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class SortingDriverScenarioTwo {
	public static void main(String[] args) {		
		ArrayList<Student> list = new ArrayList<Student>();
		
		Student s1 = new Student("Rocky", 3.5, 15);
		Student s2 = new Student("Sam", 2.0, 25);
		Student s3 = new Student("Ryan", 3.2, 10);
		
		list.add(s1);
		list.add(s2);
		list.add(s3);
		
		long start = System.currentTimeMillis();		
		ISorting<Student> task = new Sorting<Student>(list);
		
		task.setAlgorithm("insertion sort");
		task.getData("ascending");
		
		task.setComparator(new Comparator<Student>()
		{
			public int compare(Student s1, Student s2)
			{
				if(s1.getGPA() > s2.getGPA())
				{
					return 1;
				}
				if(s1.getGPA() < s2.getGPA())
				{
					return -1;
				}
				return 0;
				
			}
		});
       
		long end = System.currentTimeMillis();
        
		task.displaySortedList();
		System.out.println("Execution Time: " + task.executionTime(end, start));
		
	}

}
class Student
{
	String name;
	double gpa;
	int creditHours;
	
	Student(String n, double g, int c)
	{
		n = name;
		g = gpa;
		c = creditHours;
	}
	
	public String getName()
	{
		return name;
	}
	
	public double getGPA()
	{
		return gpa;
	}
	
	public int getCreditHours()
	{
		return creditHours;
	}
}


class Sorting<T> implements ISorting<T>
{
	public ArrayList<Student> list;
	Comparator<T> comp;


	public Sorting(ArrayList<Student> list)
	{
		this.list = list;
		comp = null;
	}

	@Override
	public IAction getData(String data) {
		
		switch(data)
		{
		case "descending":
			System.out.println("Descending Order");
			return new Descend(list);
		case "ascending":
			System.out.println("Ascending Order");
			displaySortedList();
			break;
		case "random":
			System.out.println("Random Order");
			return new Randomize(list);
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
			return new InsertionSort(list);
		case "bubble sort":
			System.out.println("Bubble Sort");
			//return new BubbleSort(list);
		case "selection sort":
			System.out.println("Selection Sort");
			//return new SelectionSort(list);
		case "merge sort":
			System.out.println("Merge Sort");
			//return new MergeSort(list);
		case "heapsort":
			System.out.println("Heap Sort");
			//return new HeapSort(list);
		default:
			System.out.println("No Match");
		}
		return null;			
	}

	@Override
	public void setComparator(Comparator<T> comp) {
			this.comp = comp;
	}


	@Override
	public void displaySortedList() {
		for(int i = 0; i < list.size(); i++)
		{
			System.out.println(list.get(i));
		}
	}

	@Override
	public long executionTime(long end, long start) {
		long execution = end - start;
		return execution;
	}
	
}

class Randomize implements IAction
{
	
	public Randomize(ArrayList<Student> list)
	{
		takeAction(list);
	}
	
	public void takeAction(ArrayList<Student> list)
	{
		Collections.shuffle(list);
	}
}

class Descend implements IAction
{
	public Descend(ArrayList<Student> list)
	{
		takeAction(list);
	}
	@Override
	public void takeAction(ArrayList<Student> list) {	
		Collections.reverse(list);
	}
	
}


class InsertionSort implements IAction
{
	Comparator<Student> comp;
	
	public InsertionSort(ArrayList<Student> list) {
		
		takeAction(list);
	}

	public void takeAction(ArrayList<Student> list) 
	{
		for(int i = 1; i < list.size(); i++)
		{
			Student key = list.get(i);
			
			for(int j = i - 1; j >= 0; j--)
			{
				if(comp.compare(key, list.get(j)) < 0)
				{
					list.set(j + 1, list.get(j));
					
					if(j == 0)
					{
						list.set(0, key);
					}
					
				}
				
				else
				{
					list.set(j + 1, key);
					break;
				}
			}
		}
	}
}


interface IAction
{
	void takeAction(ArrayList<Student> list);
}

interface ISorting <T>
{
	IAction getData(String data);
	void displaySortedList();
	IAction setAlgorithm(String alg);
	void setComparator(Comparator<T> comparator);
	long executionTime(long end, long start);
}
