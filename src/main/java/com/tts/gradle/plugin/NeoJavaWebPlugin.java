package com.tts.gradle.plugin;
import org.gradle.api.Plugin;
import org.gradle.api.Project;

import com.tts.gradle.plugin.tasks.InstallSdk;

public class NeoJavaWebPlugin implements Plugin<Project> {

	@Override
	public void apply(Project project) {
        project.getExtensions().create("scpSettings", NeoJavaWebExtension.class);
        project.getTasks().create("installSdk", InstallSdk.class);
	}
	


}
