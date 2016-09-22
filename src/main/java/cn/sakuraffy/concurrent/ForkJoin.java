package cn.sakuraffy.concurrent;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;

public class ForkJoin extends RecursiveTask<Long>{

	private static final long serialVersionUID = 1L;

	private static final int THRESHOLD = 10000;
	private long start;
	private long end;
	
	public ForkJoin(long start, long end) {
		super();
		this.start = start;
		this.end = end;
	}
	
	@Override
	protected Long compute() {
		long sum = 0;
		boolean canComputor = (end - start) < THRESHOLD;
		if (canComputor) {
			for(long i = start; i <= end; i++) {
				sum += i;
			}
		}else {
			long step = (end -start) / 100;
			ArrayList<ForkJoin> subTasks = new ArrayList<>();
			long pos = start;
			for (int i = 0; i < 100; i++) {
				long lastOne = pos + step;
				if (lastOne > end) {
					lastOne = end;
				}
				ForkJoin subTask = new ForkJoin(pos, lastOne);
				pos += step + 1;
				subTasks.add(subTask);
				subTask.fork();
			}
			for (ForkJoin t : subTasks) {
				sum += t.join();
			}
		}
		return sum;
	}

	public static void main(String[] args) throws InterruptedException, ExecutionException {
		ForkJoinPool pool = new ForkJoinPool();
		ForkJoin fj = new ForkJoin(0, 200000L);
		ForkJoinTask<Long> task = pool.submit(fj);
		long ret = task.get();
		System.out.println(ret);
	}
}
