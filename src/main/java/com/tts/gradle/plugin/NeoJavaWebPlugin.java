package com.tts.gradle.plugin;
import org.gradle.api.Plugin;
import org.gradle.api.Project;

import com.tts.gradle.plugin.tasks.InstallLocal;
import com.tts.gradle.plugin.tasks.InstallSdk;

public class NeoJavaWebPlugin implements Plugin<Project> {

	@Override
	public void apply(Project project) {
		project.getLogger().info("Creating Project: " + this);
        project.getExtensions().create("scpSettings", NeoJavaWebExtension.class, project);
        project.getTasks().create("installSdk", InstallSdk.class);
        project.getTasks().create("installLocal", InstallLocal.class);
	}
	


}
