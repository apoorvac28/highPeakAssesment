package highpeak;

import java.util.*;

public class GFG {
	static Scanner sc = new Scanner(System.in);

	static class Job {
		int start, finish, profit;

		Job(int start, int finish, int profit) {
			this.start = start;
			this.finish = finish;
			this.profit = profit;
		}
	}

	static int latestNonConflict(Job arr[], int i) {
		for (int j = i - 1; j >= 0; j--) {
			if (arr[j].finish <= arr[i - 1].start)
				return j;
		}
		return -1;
	}

	static int findMaxProfitRec(Job arr[], int n) {
		if (n == 1)
			return arr[n - 1].profit;
		int inclProf = arr[n - 1].profit;
		int i = latestNonConflict(arr, n);
		if (i != -1)
			inclProf += findMaxProfitRec(arr, i + 1);
		int exclProf = findMaxProfitRec(arr, n - 1);
		return Math.max(inclProf, exclProf);
	}

	static int findMaxProfit(Job arr[], int n) {
		Arrays.sort(arr, new Comparator<Job>() {
			public int compare(Job j1, Job j2) {
				return j1.finish - j2.finish;
			}
		});

		return findMaxProfitRec(arr, n);
	}

	public static void main(String args[]) {
		System.out.println("Enter number of jobs");
		int m = sc.nextInt();
		Job arr[] = new Job[m];

		System.out.println("Enter job start time, end time, and earnings");
		for (int i = 0; i < m; i++) {
			int startTime = sc.nextInt();
			int endTime = sc.nextInt();
			int earnings = sc.nextInt();
			arr[i] = new Job(startTime, endTime, earnings);
		}

		int n = arr.length;
		System.out.println("Earnings:" + findMaxProfit(arr, n));
		sc.close();
	}

}
