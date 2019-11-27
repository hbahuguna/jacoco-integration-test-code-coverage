package org.jacocointegration.codecoverage.jmx;

import org.jacocointegration.codecoverage.report.JacocoReport;
import org.jacocointegration.configuration.ConfigurationProperties;
import org.jacocointegration.configuration.ConfigurationPropertyKey;

import javax.management.MalformedObjectNameException;
import javax.management.remote.JMXServiceURL;
import java.io.IOException;

public class JmxBasedJacocoReport extends JacocoReport {

    private static final String SERVICE = "service";
    private static final String JMX = "jmx";
    private static final String JMXMP = "jmxmp";
    private static final String JACOCO_OBJECT_NAME = "org.jacoco";
    private static final String TYPE_RUNTIME = "type=Runtime";

    @Override
    public void generateReport() throws IOException, MalformedObjectNameException {
        ConfigurationProperties config = new ConfigurationProperties();
        String serviceUrl = String.join(":",SERVICE, JMX, JMXMP, "//" +
                config.getProperty(ConfigurationPropertyKey.JMX_RUNTIME_SERVER),
                config.getProperty(ConfigurationPropertyKey.JMX_PORT));
        JMXConnection jmxc = new JMXConnection(new JMXServiceURL(serviceUrl));
        generateCodeCoverageReport(jmxc.getRemoteObjectData(String.join(":", JACOCO_OBJECT_NAME, TYPE_RUNTIME)));
    }
}
