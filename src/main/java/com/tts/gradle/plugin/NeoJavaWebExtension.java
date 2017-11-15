package com.tts.gradle.plugin;

import java.io.File;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;

import org.gradle.api.Project;
import org.gradle.api.tasks.OutputDirectory;

public class NeoJavaWebExtension {
	
	private String defaultSdkLocation = ".sdk";
	private String defaultServerLocation = "server";
	
	private String sdkLocation;
	private String sdkVersion;
	private String serverLocation;
	private String sourceFileLocation;
	private final Project project;
	
	
	
	public NeoJavaWebExtension(Project project) {
		super();
		this.project = project;
		this.project.getLogger().info("Extension Created: " + this.toString());
		this.defaultSdkLocation = this.project.getProjectDir().getAbsolutePath().concat(defaultSdkLocation);
				
	}
	/**
	 * This method will return the given sdkLocation, in case it is null, we return the defaultSdkLocation
	 * @return
	 */
	@OutputDirectory
	public String getSdkLocation() {
		if (sdkLocation == null || sdkLocation.equals("")) {
			return defaultSdkLocation;
		}
		return sdkLocation;
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
			return defaultSdkLocation.concat(File.separator).concat(defaultServerLocation);
		}
		return serverLocation;
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
