package com.tts.gradle.plugin;

import java.io.File;
import java.util.List;
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
	private String runtime = "neo-java-web";
	private String runtimeVersion = "3";
	private Map<String, String> enviromentVariables;
	private List<String> jvmArgs;
	private boolean delta;
	

	
//	private final Property<Map> test;

	public NeoJavaWebExtension(Project project) {
		this.project = project;
		//this.project.getLogger().info("Extension Created: " + this.toString());
	}
	

	/**
	 * This method will return the given sdkLocation, in case it is null, we throw
	 * an error
	 * 
	 * @return location of the Sdk
	 * @throws TaskExecutionException
	 */
	@OutputDirectory
	public String getSdkLocation() throws TaskExecutionException {
		if (sdkLocation == null || sdkLocation.equals("")) {
			throw new TaskExecutionException(null,
					new Throwable("sdkLocation is empty, please add it in your build.gradle"));
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
			throw new TaskExecutionException(task,
					new Throwable("Please provide a Sdk Version for Sap Neo Java Web Sdk in your build.gradle"));
		}
	}

	@Override
	public String toString() {
		return "NeoJavaWebExtension [project=" + project + ", sdkLocation=" + sdkLocation + ", sdkVersion=" + sdkVersion
				+ ", serverLocation=" + serverLocation + ", sourceFileLocation=" + sourceFileLocation + ", account="
				+ account + ", applicationName=" + applicationName + ", host=" + host + ", user=" + user + ", password="
				+ password + ", runtime=" + runtime + ", runtime_version=" + runtimeVersion + ", enviromentVariables="
				+ enviromentVariables + ", jvmArgs=" + jvmArgs + "]";
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
		if (user == null) {
			user = (String) project.findProperty("user");
			if(user == null) {
				File file = new File(project.getGradle().getGradleUserHomeDir().getAbsolutePath().concat("gradle.properties"));
				
			}
			
		}
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

	public String getRuntimeVersion() {
		return runtimeVersion;
	}

	public void setRuntimeVersion(String runtime_version) {
		this.runtimeVersion = runtime_version;
	}

	public Project getProject() {
		return project;
	}

	public Map<String, String> getEnviromentVariables() {
		return enviromentVariables;
	}


	public void setEnviromentVariables(Map<String, String> enviromentVariables) {
		this.enviromentVariables = enviromentVariables;
	}


	public boolean isDelta() {
		return delta;
	}


	public void setDelta(boolean delta) {
		this.delta = delta;
	}


	public List<String> getJvmArgs() {
		return jvmArgs;
	}


	public void setJvmArgs(List<String> jvmArgs) {
		this.jvmArgs = jvmArgs;
	}



}
