package com.github.kostyasha.yad.commons;

import com.github.kostyasha.yad.docker_java.com.github.dockerjava.api.DockerClient;
import com.github.kostyasha.yad.docker_java.com.github.dockerjava.core.command.RemoveContainerCmdImpl;
import hudson.Extension;
import hudson.model.AbstractDescribableImpl;
import hudson.model.Descriptor;
import org.kohsuke.stapler.DataBoundConstructor;
import org.kohsuke.stapler.DataBoundSetter;

import java.io.Serializable;

/**
 * @author Kanstantsin Shautsou
 * @see RemoveContainerCmdImpl
 */
public class DockerRemoveContainer extends AbstractDescribableImpl<DockerRemoveContainer> implements Serializable {
    private static final long serialVersionUID = 1L;

    private boolean removeVolumes;
    private boolean force;

    @DataBoundConstructor
    public DockerRemoveContainer() {
    }

    public boolean isRemoveVolumes() {
        return removeVolumes;
    }

    @DataBoundSetter
    public DockerRemoveContainer setRemoveVolumes(boolean removeVolumes) {
        this.removeVolumes = removeVolumes;
        return this;
    }

    public boolean isForce() {
        return force;
    }

    @DataBoundSetter
    public DockerRemoveContainer setForce(boolean force) {
        this.force = force;
        return this;
    }

    public void exec(DockerClient client, String containerId) {
        client.removeContainerCmd(containerId)
                .withRemoveVolumes(removeVolumes)
                .withForce(force)
                .exec();
    }

    @Extension
    public static class DescriptorImpl extends Descriptor<DockerRemoveContainer> {
        @Override
        public String getDisplayName() {
            return "Docker Remove Container";
        }
    }
}