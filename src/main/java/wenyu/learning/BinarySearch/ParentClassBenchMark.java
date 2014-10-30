package wenyu.learning.BinarySearch;

import java.lang.management.ManagementFactory;
import java.lang.management.ThreadMXBean;
import java.lang.reflect.Method;

public abstract class ParentClassBenchMark {
	
	private ThreadMXBean bean;
	private long startTimeNano;
	private long endTimeNano;

	private void setUp() {
		System.out.println("================= Starting to count time =================");
		bean = ManagementFactory.getThreadMXBean();
		startTimeNano = bean.getCurrentThreadCpuTime();
	}
	
	private void tearDown(int runtime) {
		bean = ManagementFactory.getThreadMXBean();
		endTimeNano = bean.getCurrentThreadCpuTime();
		System.out.println("================= It costs: " + (endTimeNano-startTimeNano)/runtime + " each time =================");
	}
	
	public static void demoEntry(ParentClassBenchMark ins, int runtime, Method method, Object ...args) {
		try {
			ins.setUp();
			System.out.println("*** Test Method Name: " + method.getName() + " ***");
			int count = runtime;
			while(count-->0) {
				method.invoke(ins, args);
			}
			ins.tearDown(runtime);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
