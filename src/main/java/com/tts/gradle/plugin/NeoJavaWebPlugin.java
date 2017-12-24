package com.tts.gradle.plugin;
import org.gradle.api.Plugin;
import org.gradle.api.Project;
import org.gradle.api.Task;

import com.tts.gradle.plugin.tasks.AbstractTask;
import com.tts.gradle.plugin.tasks.Deploy;
import com.tts.gradle.plugin.tasks.InstallLocal;
import com.tts.gradle.plugin.tasks.InstallSdk;
import com.tts.gradle.plugin.tasks.Start;
import com.tts.gradle.plugin.tasks.StartStopRestart;
import com.tts.gradle.plugin.tasks.TestTask;

public class NeoJavaWebPlugin implements Plugin<Project> {

	@Override
	public void apply(Project project) {
		project.getLogger().info("Creating Project: " + this);
        project.getExtensions().create("scpSettings", NeoJavaWebExtension.class, project);
        
        Task installSdk = project.getTasks().create("installSdk", InstallSdk.class);
        installSdk.setDescription("Installs the Sap Neo SDK");
        installSdk.setGroup("Neo Sdk Plugin");
        
        Task installLocal = project.getTasks().create("installLocal", InstallLocal.class);
        installLocal.setDescription("Installs a local Server");
        installLocal.setGroup("Neo Sdk Plugin");
        
        Task deploy = project.getTasks().create("deploy", Deploy.class);
        deploy.setDescription("Deploy application to a SCP Account");
        deploy.setGroup("Neo Sdk Plugin");
        
        Task info = project.getTasks().create("info", TestTask.class);
        info.setDescription("Just a test Task");
        info.setGroup("Neo Sdk Plugin");
        
        Task start = project.getTasks().create("start", StartStopRestart.class);
        start.setDescription("Starts the application");
        start.setGroup("Neo Sdk Plugin");
        
        Task stop = project.getTasks().create("stop", StartStopRestart.class);
        stop.setDescription("Stops the application");
        stop.setGroup("Neo Sdk Plugin");
        
        Task restart = project.getTasks().create("restart", StartStopRestart.class);
        restart.setDescription("Restarts the application");
        restart.setGroup("Neo Sdk Plugin");
        
	}
	
}
