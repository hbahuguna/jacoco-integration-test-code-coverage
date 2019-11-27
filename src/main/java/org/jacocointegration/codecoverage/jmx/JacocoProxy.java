package org.jacocointegration.codecoverage.jmx;

public interface JacocoProxy {
    String getVersion();

    String getSessionId();

    void setSessionId(String id);

    byte[] getExecutionData(boolean reset);

    void dump(boolean reset);

    void reset();
}
