import java.util.Arrays;

/**
 * This class is used to test the sorter class, and produce outputs relating to algorithmic efficiency.
 * CSC 1351 Project #3
 * @author Tristan Miller
 * @since 11/02/2015
 * @see Sorter, ArrayUtil, StopWatch
 *
 */
public class SorterDemo {
	public static void main(String[] args){
		long[][] runTimes = new long[6][5];
		for(int k=10000;k<=60000;k+=10000){
			int arraySizeIndex = k/10000;
			int sortTypeIndex = 0;
			long[] data = ArrayUtil.randomIntArray(k, 1000);
			long[] dataCopy = Arrays.copyOf(data, data.length);
			StopWatch timer = new StopWatch();
			
			try{
				timer.reset();
				timer.start();
				Sorter.insertionSort(dataCopy);
				timer.stop();
				runTimes[arraySizeIndex-1][sortTypeIndex++]=timer.getElapsedTime();
				dataCopy = Arrays.copyOf(data,data.length);
			}catch(StackOverflowError ex){runTimes[arraySizeIndex-1][sortTypeIndex++]=999999;}
			
			try{
				timer.reset();
				timer.start();
				Sorter.selectionSort(dataCopy);
				timer.stop();
				runTimes[arraySizeIndex-1][sortTypeIndex++]=timer.getElapsedTime();
				dataCopy = Arrays.copyOf(data,data.length);
			}catch(StackOverflowError ex){runTimes[arraySizeIndex-1][sortTypeIndex++]=999999;}
			
			try{
				timer.reset();
				timer.start();
				Sorter.bubbleSort(dataCopy);
				timer.stop();
				runTimes[arraySizeIndex-1][sortTypeIndex++]=timer.getElapsedTime();
				dataCopy = Arrays.copyOf(data,data.length);
			}catch(StackOverflowError ex){runTimes[arraySizeIndex-1][sortTypeIndex++]=999999;}
			
			try{
				timer.reset();
				timer.start();
				Sorter.quickSort(dataCopy);
				timer.stop();
				runTimes[arraySizeIndex-1][sortTypeIndex++]=timer.getElapsedTime();
				dataCopy = Arrays.copyOf(data,data.length);
			}catch(StackOverflowError ex){runTimes[arraySizeIndex-1][sortTypeIndex++]=999999;}
			
			try{
				timer.reset();
				timer.start();
				Sorter.mergeSort(dataCopy);
				timer.stop();
				runTimes[arraySizeIndex-1][sortTypeIndex++]=timer.getElapsedTime();
				dataCopy = Arrays.copyOf(data,data.length);
			}catch(StackOverflowError ex){runTimes[arraySizeIndex-1][sortTypeIndex++]=999999;}
				
				
		}
		
		System.out.println("_____________________________________________________________________________");
		System.out.printf("%30s%n","Arrays With Random Integers");
		System.out.println("_____________________________________________________________________________");
		System.out.printf("%7s  %7s  %7s  %7s  %7s  %7s%n","n","Insertion","Selection","Bubble","Quick","Merge");
		for(int k=0;k<6;k++){
				System.out.printf("%7d  %2.5f  %2.5f  %2.5f  %2.5f  %2.5f%n",(k+1)*10000,runTimes[k][0]/1000.0,runTimes[k][1]/1000.0,
																			runTimes[k][2]/1000.0,runTimes[k][3]/1000.0,runTimes[k][4]/1000.0);
		}
		System.out.println("\n\n\n");
		
		for(int k=10000;k<=60000;k+=10000){
			int arraySizeIndex = k/10000;
			int sortTypeIndex = 0;
			long[] data = ArrayUtil.ascIntArray(k);
			long[] dataCopy = Arrays.copyOf(data, data.length);
			StopWatch timer = new StopWatch();
			
			try{
				timer.reset();
				timer.start();
				Sorter.insertionSort(dataCopy);
				timer.stop();
				runTimes[arraySizeIndex-1][sortTypeIndex++]=timer.getElapsedTime();
				dataCopy = Arrays.copyOf(data,data.length);
			}catch(StackOverflowError ex){runTimes[arraySizeIndex-1][sortTypeIndex++]=999999;}
			
			try{
				timer.reset();
				timer.start();
				Sorter.selectionSort(dataCopy);
				timer.stop();
				runTimes[arraySizeIndex-1][sortTypeIndex++]=timer.getElapsedTime();
				dataCopy = Arrays.copyOf(data,data.length);
			}catch(StackOverflowError ex){runTimes[arraySizeIndex-1][sortTypeIndex++]=999999;}
			
			try{
				timer.reset();
				timer.start();
				Sorter.bubbleSort(dataCopy);
				timer.stop();
				runTimes[arraySizeIndex-1][sortTypeIndex++]=timer.getElapsedTime();
				dataCopy = Arrays.copyOf(data,data.length);
			}catch(StackOverflowError ex){runTimes[arraySizeIndex-1][sortTypeIndex++]=999999;}
			
			try{
				timer.reset();
				timer.start();
				Sorter.quickSort(dataCopy);
				timer.stop();
				runTimes[arraySizeIndex-1][sortTypeIndex++]=timer.getElapsedTime();
				dataCopy = Arrays.copyOf(data,data.length);
			}catch(StackOverflowError ex){runTimes[arraySizeIndex-1][sortTypeIndex++]=999999;}
			
			try{
				timer.reset();
				timer.start();
				Sorter.mergeSort(dataCopy);
				timer.stop();
				runTimes[arraySizeIndex-1][sortTypeIndex++]=timer.getElapsedTime();
				dataCopy = Arrays.copyOf(data,data.length);
			}catch(StackOverflowError ex){runTimes[arraySizeIndex-1][sortTypeIndex++]=999999;}
				
				
		}
		
		System.out.println("_____________________________________________________________________________");
		System.out.printf("%30s%n","Arrays With Ascending Integers");
		System.out.println("_____________________________________________________________________________");
		System.out.printf("%7s  %7s  %7s  %7s  %7s  %7s%n","n","Insertion","Selection","Bubble","Quick","Merge");
		for(int k=0;k<6;k++){
				System.out.printf("%7d  %2.5f  %2.5f  %2.5f  %2.5f  %2.5f%n",(k+1)*10000,runTimes[k][0]/1000.0,runTimes[k][1]/1000.0,
																			runTimes[k][2]/1000.0,runTimes[k][3]/1000.0,runTimes[k][4]/1000.0);
		}
		
		System.out.println("\n\n\n");
		
		for(int k=10000;k<=60000;k+=10000){
			int arraySizeIndex = k/10000;
			int sortTypeIndex = 0;
			long[] data = ArrayUtil.descIntArray(k);
			long[] dataCopy = Arrays.copyOf(data, data.length);
			StopWatch timer = new StopWatch();
			try{
				timer.reset();
				timer.start();
				Sorter.insertionSort(dataCopy);
				timer.stop();
				runTimes[arraySizeIndex-1][sortTypeIndex++]=timer.getElapsedTime();
				dataCopy = Arrays.copyOf(data,data.length);
			}catch(StackOverflowError ex){runTimes[arraySizeIndex-1][sortTypeIndex++]=999999;}
			
			try{
				timer.reset();
				timer.start();
				Sorter.selectionSort(dataCopy);
				timer.stop();
				runTimes[arraySizeIndex-1][sortTypeIndex++]=timer.getElapsedTime();
				dataCopy = Arrays.copyOf(data,data.length);
			}catch(StackOverflowError ex){runTimes[arraySizeIndex-1][sortTypeIndex++]=999999;}
			
			try{
				timer.reset();
				timer.start();
				Sorter.bubbleSort(dataCopy);
				timer.stop();
				runTimes[arraySizeIndex-1][sortTypeIndex++]=timer.getElapsedTime();
				dataCopy = Arrays.copyOf(data,data.length);
			}catch(StackOverflowError ex){runTimes[arraySizeIndex-1][sortTypeIndex++]=999999;}
			
			try{
				timer.reset();
				timer.start();
				Sorter.quickSort(dataCopy);
				timer.stop();
				runTimes[arraySizeIndex-1][sortTypeIndex++]=timer.getElapsedTime();
				dataCopy = Arrays.copyOf(data,data.length);
			}catch(StackOverflowError ex){runTimes[arraySizeIndex-1][sortTypeIndex++]=999999;}
			
			try{
				timer.reset();
				timer.start();
				Sorter.mergeSort(dataCopy);
				timer.stop();
				runTimes[arraySizeIndex-1][sortTypeIndex++]=timer.getElapsedTime();
				dataCopy = Arrays.copyOf(data,data.length);
			}catch(StackOverflowError ex){runTimes[arraySizeIndex-1][sortTypeIndex++]=999999;}
				
		}
		
		System.out.println("_____________________________________________________________________________");
		System.out.printf("%30s%n","Arrays With Descending Integers");
		System.out.println("_____________________________________________________________________________");
		System.out.printf("%7s  %7s  %7s  %7s  %7s  %7s%n","n","Insertion","Selection","Bubble","Quick","Merge");
		for(int k=0;k<6;k++){
				System.out.printf("%7d  %2.5f  %2.5f  %2.5f  %2.5f  %2.5f%n",(k+1)*10000,runTimes[k][0]/1000.0,runTimes[k][1]/1000.0,
																			runTimes[k][2]/1000.0,runTimes[k][3]/1000.0,runTimes[k][4]/1000.0);
		}


	}
}
