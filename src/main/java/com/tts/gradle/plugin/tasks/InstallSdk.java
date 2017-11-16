package com.tts.gradle.plugin.tasks;

import java.io.File;

import org.apache.ant.compress.taskdefs.Unzip;
import org.gradle.api.DefaultTask;
import org.gradle.api.artifacts.Configuration;
import org.gradle.api.artifacts.component.ComponentIdentifier;
import org.gradle.api.tasks.TaskAction;
import org.gradle.api.tasks.TaskExecutionException;
import org.gradle.internal.component.external.model.DefaultModuleComponentIdentifier;

import com.tts.gradle.plugin.NeoJavaWebExtension;

public class InstallSdk extends AbstractTask {

	/**
	 * This method reflects a gradle Task, it will install the Sap Neo Sdk so we can
	 * invoke the cli commands in later tasks the default location is
	 * ${projectDir}/.sdk
	 */
	@TaskAction
	public void installSdk() {

		super.validate(this);


		ComponentIdentifier componentIdentifier = new DefaultModuleComponentIdentifier("com.sap.cloud",
				"neo-java-web-sdk", getExtension().getSdkVersion());

		Configuration config = getProject().getConfigurations().create("download");
		config.setTransitive(false); // if required, is it?

		getLogger().info("Adding dependency: "
				+ componentIdentifier.getDisplayName().concat("@zip"));
		
		getProject().getDependencies().add(config.getName(),
				componentIdentifier.getDisplayName().concat("@zip"));
		
		File file = config.getSingleFile();
		getLogger().debug("File: " + file.getAbsolutePath() + " downloaded successfully");

		String buildDir = getExtension().getSdkLocation();
		getLogger().info("BuildDir currently set to: " + buildDir);

		File sdkDir = null;
		
		sdkDir = new File(buildDir);
		boolean created = sdkDir.mkdir();
		getLogger().info("Directory " + sdkDir.getAbsolutePath() + " was created: " + created);
		if (!created) {
			throw new TaskExecutionException(this,
					new Throwable("Directory " + buildDir + " wasn't created, aborting.."));
		}

		getLogger().info("Extracting file to: " + buildDir);
		unzip(file, sdkDir);
		// TODO we need to check if the directory already exists, and to delete it then
	}

	private void unzip(File file, File buildDir) {
		Unzip unzip = new Unzip();
		unzip.setSrc(file);
		unzip.setDest(buildDir);
		unzip.execute();

	}

}
