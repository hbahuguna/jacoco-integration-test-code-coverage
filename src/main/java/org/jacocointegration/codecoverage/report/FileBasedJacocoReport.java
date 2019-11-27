package org.jacocointegration.codecoverage.report;

import org.jacocointegration.configuration.ConfigurationProperties;
import org.jacocointegration.configuration.ConfigurationPropertyKey;

import javax.management.MalformedObjectNameException;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class FileBasedJacocoReport extends JacocoReport {
    @Override
    public void generateReport() throws IOException, MalformedObjectNameException {
        ConfigurationProperties config = new ConfigurationProperties();
        String jacocoBinaryPath = config.getProperty(ConfigurationPropertyKey.JACOCO_BINARY_PATH);
        File jacocoBinary = new File(jacocoBinaryPath);
        generateCodeCoverageReport(Files.readAllBytes(jacocoBinary.toPath()));
    }
}
