package com.tts.gradle.plugin;

import java.io.File;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;

import org.gradle.api.Project;
import org.gradle.api.tasks.OutputDirectory;
import org.gradle.api.tasks.TaskExecutionException;

public class NeoJavaWebExtension {

	private String defaultSdkLocation = ".sdk";
	private String defaultServerLocation = "server";

	private String sdkLocation = ".sdk";
	private String sdkVersion;
	private String serverLocation = "server";
	private String sourceFileLocation;
	private final Project project;

	public NeoJavaWebExtension(Project project) {
		this.project = project;
		this.project.getLogger().info("Extension Created: " + this.toString());

	}

	/**
	 * This method will return the given sdkLocation, in case it is null, we throw
	 * an error, the returned location is always relative to projectDir
	 * 
	 * @return location relativ to projectDir
	 */
	@OutputDirectory
	public String getSdkLocation() {
		if (sdkLocation == null || sdkLocation.equals("")) {
			throw new TaskExecutionException(null, new Throwable("sdkLocation is empty, please add it in your build.gradle"));
		}
		return project.getProjectDir().getAbsolutePath().concat(File.separator).concat(sdkLocation);
	}

	public void setSdkLocation(String sdkLocation) {
		this.sdkLocation = sdkLocation;
	}

	public String getSdkVersion() {
		return sdkVersion;
	}

	public void setSdkVersion(String sdkVersion) {
		this.sdkVersion = sdkVersion;
	}

	@OutputDirectory
	public String getServerLocation() {
		if (serverLocation == null || serverLocation.equals("")) {
			// TODO throw error
		}
		return new StringBuilder(getSdkLocation()).append(File.separator).append(serverLocation).toString();
	}

	public void setServerLocation(String serverLocation) {
		this.serverLocation = serverLocation;
	}

	public String getSourceFileLocation() {
		return sourceFileLocation;
	}

	public void setSourceFileLocation(String sourceFileLocation) {
		this.sourceFileLocation = sourceFileLocation;
	}

	@Override
	public String toString() {
		return "NeoJavaWebExtension [defaultSdkLocation=" + defaultSdkLocation + ", defaultServerLocation="
				+ defaultServerLocation + ", sdkLocation=" + sdkLocation + ", sdkVersion=" + sdkVersion
				+ ", serverLocation=" + serverLocation + ", sourceFileLocation=" + sourceFileLocation + ", project="
				+ project + "]";
	}

}
