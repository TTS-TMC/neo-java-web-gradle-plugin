package com.tts.gradle.plugin;

import java.io.File;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;

import org.gradle.api.tasks.OutputDirectory;

public class NeoJavaWebExtension {
	
	private String defaultSdkLocation = ".sdk";
	private String defaultServerLocation = defaultSdkLocation.concat(File.separator).concat("server");
	
	private String sdkLocation;
	private String sdkVersion;
	private String serverLocation;
	private String sourceFileLocation;
	
	@OutputDirectory
	public String getSdkLocation() {
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
	public String getDefaultSdkLocation() {
		return defaultSdkLocation;
	}
	public String getDefaultServerLocation() {
		return defaultServerLocation;
	}
}
