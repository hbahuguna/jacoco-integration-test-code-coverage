package org.jacocointegration.codecoverage.report;

import javax.management.MalformedObjectNameException;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;

public abstract class JacocoReport {
    private String rootDir;
    private String[] projectDir;
    private static final String DEST_FILE = "jacoco.exec";
    private static final String BIN = "bin";
    private static final String SRC = "src";
    private static final String COVERAGE_REPORT_DIR = "coveragereport";

    public JacocoReport() {
        this.rootDir = "/opt";
        this.projectDir = new String[]{"about", "jacoco"};
    }

    public JacocoReport(String rootDir, String... projectDir) {
        this.rootDir = rootDir;
        this.projectDir = projectDir;
    }

    public abstract void generateReport() throws IOException, MalformedObjectNameException;

    protected void generateCodeCoverageReport(byte[] data) throws IOException {
        Path destFilePath = getPath(DEST_FILE);
        final FileOutputStream localFile = new FileOutputStream(destFilePath.toFile());
        try {
            localFile.write(data);
        } finally {
            localFile.close();
        }

        File bin = getPath(BIN).toFile();
        File src = getPath(SRC).toFile();
        File reportDir = getPath(COVERAGE_REPORT_DIR).toFile();
        String title = Paths.get(rootDir, projectDir).toFile().getName();
        // generate code coverage report
        ReportGenerator generator = new ReportGenerator(destFilePath.toFile(), bin, src, reportDir, title);
        generator.create();
    }

    private Path getPath(String file) {
        String[] destFileDirs = Arrays.copyOf(projectDir, projectDir.length + 1);
        destFileDirs[projectDir.length] = file;
        return Paths.get(rootDir, destFileDirs);
    }
}
