package io.jenkins.plugins.extlogging.api.util;

import hudson.model.Run;
import io.jenkins.plugins.extlogging.api.impl.ExternalLoggingEventWriter;
import io.jenkins.plugins.extlogging.api.ExternalLoggingMethod;
import jenkins.model.logging.LogBrowser;

import javax.annotation.CheckForNull;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;

/**
 * @author Oleg Nenashev
 * @since TODO
 */
public class MockLoggingMethod extends ExternalLoggingMethod {

    private File baseDir;
    transient MockExternalLoggingEventWriter writer;

    public MockLoggingMethod(Run<?,?> run, File baseDir) {
        super(run);
        this.baseDir = baseDir;
    }

    @Override
    protected Run<?, ?> getOwner() {
        return (Run)super.getOwner();
    }

    @Override
    public ExternalLoggingEventWriter createWriter() {
        try {
            writer = new MockExternalLoggingEventWriter(new File(baseDir, getOwner().getFullDisplayName() + ".txt"));
        } catch (IOException ex) {
            throw new AssertionError(ex);
        }
        return writer;
    }


    public MockExternalLoggingEventWriter getWriter() {
        return writer;
    }

    @Override
    public OutputStream decorateLogger(OutputStream logger) {
        throw new AssertionError("Not Implemented");
    }

    @CheckForNull
    @Override
    public LogBrowser getDefaultLogBrowser() {
        return new MockLogBrowser(getOwner(), baseDir);
    }
}