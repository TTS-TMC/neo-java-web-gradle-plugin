package com.tts.gradle.plugin.tasks;

import java.io.File;

import org.gradle.api.DefaultTask;
import org.gradle.api.artifacts.component.ComponentIdentifier;
import org.gradle.api.tasks.TaskExecutionException;
import org.gradle.internal.component.external.model.DefaultModuleComponentIdentifier;

import com.tts.gradle.plugin.NeoJavaWebExtension;

public abstract class AbstractTask extends DefaultTask {
	
	/**
	 * Tests if the neo sdk is installed
	 * @return true if sdkLocation exists, is a directory and isn't empty
	 */
	protected boolean isSdkInstalled() {
		//TODO probably we need to test more stuff here? f.e. if neo.sh/bat is present?
		File sdk = new File(getExtension().getSdkLocation());
		if (sdk.exists() && sdk.isDirectory() && sdk.list().length > 0) {
			return true;
		}
		return false;
	}

	protected NeoJavaWebExtension getExtension() {
		return getProject().getExtensions().findByType(NeoJavaWebExtension.class);
	}

}
