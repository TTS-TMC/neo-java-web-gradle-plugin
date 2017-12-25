package com.tts.gradle.plugin;

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

	public NeoJavaWebExtension(Project project) {
		this.project = project;
	}

	/**
	 * This method will return the given sdkLocation, in case it is null, we throw
	 * an error
	 * 
	 * @return location of the Sdk
	 * @throws TaskExecutionException
	 */
	@OutputDirectory
	public String getSdkLocation() throws Throwable {
		if (sdkLocation == null || sdkLocation.isEmpty()) {
			new Throwable("sdkLocation is empty, please check your gradle build file");
		}
		return sdkLocation;
	}

	public void setSdkLocation(String sdkLocation) {
		this.sdkLocation = sdkLocation;
	}

	public String getSdkVersion() throws Throwable {
		if (sdkVersion == null || sdkVersion.isEmpty()) {
			throw new Throwable("sdkVersion can't be empty, please check your gradle build file");
		}
		return sdkVersion;
	}

	public void setSdkVersion(String sdkVersion) {
		this.sdkVersion = sdkVersion;
	}

	@OutputDirectory
	public String getServerLocation() throws Throwable {
		if (serverLocation == null || serverLocation.isEmpty()) {
			throw new Throwable("serverLocation can't be empty, please check your gradle build file");
		}
		return serverLocation;
	}

	public void setServerLocation(String serverLocation) {
		this.serverLocation = serverLocation;
	}

	public String getSourceFileLocation() throws Throwable {
		if (sourceFileLocation == null || sourceFileLocation.isEmpty()) {
			throw new Throwable("sourceFileLocation can't be empty, please check your gradle build file");
		}
		return sourceFileLocation;
	}

	public void setSourceFileLocation(String sourceFileLocation) {
		this.sourceFileLocation = sourceFileLocation;
	}

	@Deprecated
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
				+ account + ", applicationName=" + applicationName + ", host=" + host + ", user=" + user
				+ ", password=*****" + ", runtime=" + runtime + ", runtime_version=" + runtimeVersion
				+ ", enviromentVariables=" + enviromentVariables + ", jvmArgs=" + jvmArgs + "]";
	}

	public String getAccount() throws Throwable {
		if (account == null || account.isEmpty()) {
			throw new Throwable("account can't be empty, please check your gradle build file");
		}
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getApplicationName() throws Throwable {
		if (applicationName == null || applicationName.isEmpty()) {
			throw new Throwable("applicationName can't be empty, please check your gradle build file");
		}
		return applicationName;
	}

	public void setApplicationName(String applicationName) {
		this.applicationName = applicationName;
	}

	public String getHost() throws Throwable {
		if (host == null || host.isEmpty()) {
			throw new Throwable("host can't be empty, please check your gradle build file");
		}
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public String getUser() throws Throwable {
		if (user == null) {
			project.getLogger().info("Searching for user..");
			user = (String) project.findProperty("user");
			if (user == null || user.isEmpty()) {
				throw new Throwable("user can't be empty, please check your gradle build file");
			}
		}
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getPassword() throws Throwable {
		if (password == null) {
			project.getLogger().info("Searching for password..");
			password = (String) project.findProperty("password");
			if (password == null || password.isEmpty()) {
				throw new Throwable("password can't be empty, please check your gradle build file");
			}
		}
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
