/**
 * This class is to be used to sort arrays of Long data, and has various sorts to do so.
 * CSC 1351 Project #3
 * @author Tristan Miller
 * @since 11/02/2015
 * @see ArrayUtil
 *
 */
public class Sorter {
	/**
	 * performs a selection sort on the array parameter to sort it in ascending order.
	 * @param data the array to be sorted
	 */
	public static void selectionSort(long[] data){
		for(int i = 0;i<data.length-1;i++){
			int minPos = minimumPosition(data,i);
			ArrayUtil.swap(data,minPos,i);
		}
	}
	/**
	 * used by the selection sort method to give the minimum position.
	 * @param data array to look at
	 * @param from minimum index to be examined
	 * @return minimum index
	 */
	private static int minimumPosition(long[] data, int from){
		int minPos = from;
		for (int i = from+1; i<data.length;i++){
			if(data[i]<data[minPos])
				minPos = i;
		}
		return minPos;
	}
	
	/**
	 * Performs a bubble sort on the array parameter to sort it in ascending order.
	 * @param data the array to be sorted
	 */
	public static void bubbleSort(long[] data){
		int j;
		boolean notDone = true;
		long temp;
		while(notDone){
			notDone = false;
			for(j =0;j<data.length-1;j++){
				if(data[j]<data[j+1]){
					temp = data[j];
					data[j]=data[j+1];
					notDone=true;
				}
			}
		}
	}
	
	/**
	 * Performs a insertion sort on the array parameter to sort it in ascending order.
	 * @param data the array to be sorted
	 */
	public static void insertionSort(long[] data){
		for(int i =1;i<data.length;i++){
			long next = data[i];
			int j = i;
			while(j>0 && data[j-1]>next){
				data[j]=data[j-1];
				j--;
			}
			data[j]=next;
		}
	}
	/**
	 * performs a merge sort on the array parameter to sort it in ascending order.
	 * @param data the array to be sorted
	 */
	public static void mergeSort(long[] data){
		if(data.length<=1)
			return;
		long[] first = new long[data.length/2];
		long[] second = new long[data.length-first.length];
		for(int i = 0;i<first.length;i++)
			first[i]=data[i];
		for(int i =0;i<second.length;i++){
			second[i]=data[first.length+i];
		}
		mergeSort(first);
		mergeSort(second);
		merge(first,second,data);
	}
	/**
	 * To be used by the merge sort method to combine two lists in a sorted manner
	 * @param first the first list
	 * @param second the second list
	 * @param data the array to be copied to
	 */
	private static void merge(long[] first, long[] second, long[] data){
		int iFirst = 0;
		int iSecond = 0;
		int j = 0;
		while(iFirst<first.length && iSecond < second.length){
			if(first[iFirst]<second[iSecond]){
				data[j]=first[iFirst];
				iFirst++;
			}
			else{
				data[j]=second[iSecond];
				iSecond++;
			}
			j++;
		}
		while(iFirst <first.length){
			data[j]=first[iFirst];
			iFirst++;j++;
		}
		while(iSecond <second.length){
			data[j]=second[iSecond];
			iSecond++;j++;
		}
	}
	
	
	/**
	 * performs a quick sort on the array parameter to sort it in ascending order
	 * @param data the array to be sorted
	 */
	public static void quickSort(long[] data){
		quickSort(data,0,data.length-1);
	}
	/**
	 * the auxillary method to be used by quickSort, for recursive purposes.
	 * @param data the array to be sorted
	 * @param from min index
	 * @param to max index
	 */
	private static void quickSort(long[] data, int from, int to){
		if(from>=to)return;
		int p = partition(data,from,to);
		quickSort(data,from,p);
		quickSort(data,p+1,to);
	}
	
	/**
	 * finds a partition index for quick sorting
	 * @param data the array to be sorted
	 * @param from the min index
	 * @param to the max index
	 * @return partition index
	 */
	private static int partition(long[] data,int from, int to){
		long pivot = (int)data[from];
		int i = from-1;
		int j = to+1;
		while(i<j){
			i++; while(data[i]<pivot){i++;}
			j--; while(data[j]>pivot){j--;}
			if(i<j){ArrayUtil.swap(data, i, j);}
		}
		return j;
	}
}
