package com.tts.gradle.plugin;

import java.util.Map;

import org.gradle.api.Project;
import org.gradle.api.Task;
import org.gradle.api.tasks.OutputDirectory;
import org.gradle.api.tasks.TaskExecutionException;

public class NeoJavaWebExtension {

	private final Project project;
	
	private String sdkLocation = ".sdk";
	private String sdkVersion;
	private String serverLocation = "server";
	private String sourceFileLocation;
	private String account;
	private String applicationName;
	private String host;
	private String user;
	private String password;
	private String runtime = "3";
	private String runtime_version = "neo-java-web";
    private Map<String, String> envVars;
	//commandLine neo(), 'deploy', '--account', account, '--application', application, '--host', host, '--password', password, '--user', user, '--source', war.archivePath, '--ev', destinationName, '-V', '-Dspring.profiles.active=production', '--runtime-version', '3', '--runtime', 'neo-java-web'
	
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
			// TODO throw error
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
	
	public void validate(Task task) throws TaskExecutionException {
		if (sdkVersion == null || sdkVersion.equals("")) {
			throw new TaskExecutionException(task, new Throwable("Please provide a Sdk Version for Sap Neo Java Web Sdk in your build.gradle"));
		}
	}

	@Override
	public String toString() {
		return "NeoJavaWebExtension [project=" + project + ", sdkLocation=" + sdkLocation + ", sdkVersion=" + sdkVersion
				+ ", serverLocation=" + serverLocation + ", sourceFileLocation=" + sourceFileLocation + ", account="
				+ account + ", applicationName=" + applicationName + ", host=" + host + ", user=" + user + ", password="
				+ password + ", runtime=" + runtime + ", runtime_version=" + runtime_version + "]";
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getApplicationName() {
		return applicationName;
	}

	public void setApplicationName(String applicationName) {
		this.applicationName = applicationName;
	}

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRuntime() {
		return runtime;
	}

	public void setRuntime(String runtime) {
		this.runtime = runtime;
	}

	public String getRuntime_version() {
		return runtime_version;
	}

	public void setRuntime_version(String runtime_version) {
		this.runtime_version = runtime_version;
	}

	public Project getProject() {
		return project;
	}

}
