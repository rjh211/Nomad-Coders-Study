package org.example.jmx;

import javax.management.MBeanServerConnection;
import javax.management.ObjectName;
import javax.management.openmbean.CompositeData;
import javax.management.remote.JMXConnector;
import javax.management.remote.JMXConnectorFactory;
import javax.management.remote.JMXServiceURL;
import java.io.IOException;
import java.lang.management.ManagementFactory;
import java.lang.management.MemoryMXBean;
import java.lang.management.MemoryUsage;
import java.util.HashMap;
import java.util.Map;

public class JmxExample {

	static String JMX_URL = "service:jmx:rmi:///jndi/rmi://127.0.0.1:1099/jmxrmi";
	static JMXConnector jmxConnector = null;
	// Intellij를 사용하는경우
	// intellij의 자체 tomcat경로가 설정되며 intellij tomcat설정에서 JMX port 설정시 자동으로 해당경로 하위에 remote.access/remote.password 파일이 생긴다.
	// remote.access 파일에는 access ID와 해당 ID에 대한 권한을 설정한다.
	// remote.password 파일에는 access ID와 해당 ID의 password가 생성된다.
	static String JMX_ACCESS = "";
	static String JMX_PASSWORD = "";

	static public void run() throws Exception {
		try{
			JMXServiceURL jmxUrlObj = new JMXServiceURL(JMX_URL);

			long[] serverMem = getServerMem(jmxUrlObj);
			long[] localMem = getLocalUsageMemory();

			System.out.println("usage Memory");
			System.out.println("Server : " + serverMem[0]);
			System.out.println("Local : " + localMem[0]);
			System.out.println("max Memory");
			System.out.println("Server : " + serverMem[1]);
			System.out.println("Local : " + localMem[1]);

			System.out.printf("Server Used Percentage : %.2f%%\n", (double) serverMem[0] / serverMem[1] * 100);
			System.out.printf("Local Used Percentage : %.2f%%\n", (double) localMem[0] / localMem[1] * 100);

		} catch(Exception e) {
			e.printStackTrace();
			closeConnector();
		}
	}

	static public long[] getLocalUsageMemory(){
		MemoryMXBean memoryMXBean = ManagementFactory.getMemoryMXBean();
		MemoryUsage heapMemoryUsage = memoryMXBean.getHeapMemoryUsage();

		long usedHeap = heapMemoryUsage.getUsed();
		long maxHeap = heapMemoryUsage.getMax();

		return new long[]{usedHeap, maxHeap};
	}

	static public long[] getServerMem(JMXServiceURL jmxUrlObj) throws Exception {
		jmxConnector = getConnector(jmxUrlObj);

		MBeanServerConnection mbsc = jmxConnector.getMBeanServerConnection();

		ObjectName memoryBean = ObjectName.getInstance(ManagementFactory.MEMORY_MXBEAN_NAME);

		CompositeData heapMemoryUsageData = (CompositeData) mbsc.getAttribute(memoryBean, "HeapMemoryUsage");

		long usedHeapMemory = (Long) heapMemoryUsageData.get("used");
		long commitedHeapMemory = (Long) heapMemoryUsageData.get("committed");
		long maxHeapMemory = (Long) heapMemoryUsageData.get("max");

		return new long[]{usedHeapMemory, maxHeapMemory};
	}
	static JMXConnector getConnector(JMXServiceURL jmxUrlObj) throws Exception {
		Map<String, Object> env = new HashMap<String, Object>();
		env.put(JMXConnector.CREDENTIALS, new String[] {JMX_ACCESS, JMX_PASSWORD});

		return JMXConnectorFactory.connect(jmxUrlObj, env);
	}

	static void closeConnector() throws IOException {
		if(jmxConnector != null)
			jmxConnector.close();
	}
}
