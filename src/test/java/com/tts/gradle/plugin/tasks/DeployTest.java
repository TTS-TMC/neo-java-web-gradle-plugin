package com.tts.gradle.plugin.tasks;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.when;

import java.io.File;
import java.io.PrintWriter;
import java.util.List;

import org.gradle.testfixtures.ProjectBuilder;
import org.gradle.testkit.runner.BuildResult;
import org.gradle.testkit.runner.GradleRunner;
import org.gradle.testkit.runner.TaskOutcome;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.internal.util.reflection.Whitebox;
import org.mockito.stubbing.Stubber;

import com.tts.gradle.plugin.NeoJavaWebExtension;

public class DeployTest {

	@Rule
	public final TemporaryFolder testProjectDir = new TemporaryFolder();
	
	@Test
	public void test() throws Exception {
		setUpBuildFile();
		BuildResult result = GradleRunner.create().withProjectDir(testProjectDir.getRoot()).withPluginClasspath()
				.withArguments("installSdk", "deploy", "--info").forwardOutput().build();
		assertThat(result.task(":" + "deploy").getOutcome(), equalTo(TaskOutcome.SUCCESS));
	}
	
	private void setUpBuildFile() throws Exception {
		File buildFile = testProjectDir.newFile("build.gradle");
		PrintWriter printWriter = new PrintWriter(buildFile);
		printWriter.println("plugins { id 'com.tts.scp-neo-plugin' version '0.0.2' } ");
		printWriter.println("scpSettings { ");
		printWriter.println("sdkVersion = '3.39.10'");
		printWriter.println("sdkLocation = \"${projectDir}"+ File.separator+ "test\"");
		printWriter.println("account = 'abcdef'");
		printWriter.println("applicationName = 'test'");
		printWriter.println("host = 'hana.ondemand.test'");
		printWriter.println("user = 'testUser'");
		printWriter.println("password = 'shouldntBeInHere'");
		printWriter.println("sourceFileLocation = 'someBuildDir'");
		printWriter.println("}");
		printWriter.println("repositories {");
		printWriter.println(" mavenCentral()");		 
		printWriter.println(" mavenLocal()");		 
		printWriter.println("	}");
		printWriter.flush();
		printWriter.close();
	}
}
