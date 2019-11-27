package org.jacocointegration.codecoverage.jmx;

import javax.management.JMX;
import javax.management.MBeanServerConnection;
import javax.management.MalformedObjectNameException;
import javax.management.ObjectName;
import javax.management.remote.JMXConnector;
import javax.management.remote.JMXConnectorFactory;
import javax.management.remote.JMXServiceURL;
import java.io.IOException;

public class JMXConnection {
    private final JMXServiceURL url;

    public JMXConnection(JMXServiceURL url) {
        this.url = url;
    }

    public byte[] getRemoteObjectData(String objectName) throws IOException, MalformedObjectNameException {
        JMXConnector jmxc = JMXConnectorFactory.connect(url, null);
        try {
            final MBeanServerConnection connection = jmxc.getMBeanServerConnection();

            final JacocoProxy proxy = JMX.newMBeanProxy(connection, new ObjectName(objectName), JacocoProxy.class);

            // Retrieve dump
            return proxy.getExecutionData(false);
        } finally {
            jmxc.close();
        }
    }
}
