package sg.standard.implementations;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicBoolean;

public class SampleThreadPool {

	private final BlockingQueue<Runnable> workQ = new LinkedBlockingQueue<>();
	private final int poolCapacity;
	Thread[] pool;
	AtomicBoolean shutdown = new AtomicBoolean(false);

	public SampleThreadPool(int poolCapacity) {
		super();
		this.poolCapacity = poolCapacity;
		pool = new Thread[poolCapacity];
		initPool();
		startPool();
	}

	private void initPool() {
		for (int i = 0; i < poolCapacity; i++) {
			Thread t = new Thread(new Runnable() {

				@Override
				public void run() {
					System.out.println(Thread.currentThread().getName()+" started");
					while(!Thread.interrupted()) {
						Runnable work;
						try {
							work = workQ.take();
							work.run();
						} catch (InterruptedException e) {
							if (shutdown.get()) return; 
							e.printStackTrace();
						}
						
					}
				}

			});
			pool[i] = t;
		}

	}
	
	private void startPool() {
		for (Thread t:pool) {
			t.start();
		}
	}
	
	private void stop() {
		if (!shutdown.get()) shutdown.set(true);
		for (Thread t:pool) {
			t.interrupt();
			try {
				t.join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	private void shutdown() {
		shutdown.set(true);
		while(!workQ.isEmpty()) {
			
		}
		stop();
		
	}

	public void execute(Runnable r) {
		if (!shutdown.get())
			workQ.add(r);
	}

	public static void main(String[] args) {
		SampleThreadPool pool = new SampleThreadPool(5);
		for (int i = 0; i < 1000; i++) {
			final int j = i;
			Runnable r = new Runnable() {

				@Override
				public void run() {
					System.out.println(" hello from a new runnable: "+j);
					
				}
				
			};
			pool.execute(r);
		}
		pool.shutdown();
	}
}
