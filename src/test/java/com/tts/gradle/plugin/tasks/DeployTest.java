package com.tts.gradle.plugin.tasks;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;
import java.io.File;
import java.io.PrintWriter;
import org.gradle.testkit.runner.BuildResult;
import org.gradle.testkit.runner.GradleRunner;
import org.gradle.testkit.runner.TaskOutcome;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

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
		printWriter.println("plugins { id 'com.tts.scp-neo-plugin' } ");
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
