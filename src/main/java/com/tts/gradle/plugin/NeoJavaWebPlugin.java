package com.tts.gradle.plugin;
import org.gradle.api.Plugin;
import org.gradle.api.Project;
import org.gradle.api.Task;

import com.tts.gradle.plugin.tasks.Deploy;
import com.tts.gradle.plugin.tasks.InstallLocal;
import com.tts.gradle.plugin.tasks.InstallSdk;
import com.tts.gradle.plugin.tasks.Start;
import com.tts.gradle.plugin.tasks.TestTask;

public class NeoJavaWebPlugin implements Plugin<Project> {
	
	private static final String GROUP_NAME = "Neo Sdk Plugin";

	@Override
	public void apply(Project project) {
		project.getLogger().info("Creating Project: " + this);
        project.getExtensions().create("scpSettings", NeoJavaWebExtension.class, project);
        
        Task installSdk = project.getTasks().create("installSdk", InstallSdk.class);
        installSdk.setDescription("Installs the Sap Neo SDK");
        installSdk.setGroup(GROUP_NAME);
        
        Task installLocal = project.getTasks().create("installLocal", InstallLocal.class);
        installLocal.setDescription("Installs a local Server");
        installLocal.setGroup(GROUP_NAME);
        
        Task deploy = project.getTasks().create("deploy", Deploy.class);
        deploy.setDescription("Deploy application to a SCP Account");
        deploy.setGroup(GROUP_NAME);
        
        Task info = project.getTasks().create("info", TestTask.class);
        info.setDescription("Just a test Task");
        info.setGroup(GROUP_NAME);
        
        Task start = project.getTasks().create("start", Start.class);
        start.setDescription("Starts the application");
        start.setGroup(GROUP_NAME);
        
        Task stop = project.getTasks().create("stop", Start.class);
        stop.setDescription("Stops the application");
        stop.setGroup(GROUP_NAME);
        
        Task restart = project.getTasks().create("restart", Start.class);
        restart.setDescription("Restarts the application");
        restart.setGroup(GROUP_NAME);
        
	}
	
}
