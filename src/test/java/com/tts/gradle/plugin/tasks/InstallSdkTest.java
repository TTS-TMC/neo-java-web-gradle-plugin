package com.tts.gradle.plugin.tasks;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

import java.io.File;
import java.io.PrintWriter;

import org.gradle.testkit.runner.BuildResult;
import org.gradle.testkit.runner.GradleRunner;
import org.gradle.testkit.runner.TaskOutcome;
import org.junit.Rule;
import org.junit.rules.TemporaryFolder;

public class InstallSdkTest {

	@Rule
	public final TemporaryFolder testProjectDir = new TemporaryFolder();

	//@Test
	public void testPositiveCustomLocation() throws Exception {
		setUpPositiveCustomLocation();
		BuildResult result = GradleRunner.create().withProjectDir(testProjectDir.getRoot()).withPluginClasspath()
				.withArguments("installSdk", "--info").build();
//		System.out.println(result.getOutput());

		assertThat(result.task(":" + "installSdk").getOutcome(), equalTo(TaskOutcome.SUCCESS));
		File sdkLocation = new File(testProjectDir.getRoot().getPath().concat(File.separator).concat("test"));

		assertThat("Sdk was downloaded and extracted to custom location", sdkLocation.exists(), equalTo(true));

	}

	//@Test
	public void testNegativeCustomLocation() throws Exception {
		setUpNegativeCustomLocation();
		
		try {
			BuildResult result = GradleRunner.create().withProjectDir(testProjectDir.getRoot()).withPluginClasspath()
					.withArguments("installSdk", "--info").build();
			System.out.println(result.getOutput());
			assertThat(result.task(":" + "installSdk").getOutcome(), equalTo(TaskOutcome.FAILED));
		} catch (Exception e) {
		}


	}

	private void setUpPositiveCustomLocation() throws Exception {
		File buildFile = testProjectDir.newFile("build.gradle");
		PrintWriter printWriter = new PrintWriter(buildFile);
		printWriter.println("plugins { id 'com.tts.scp-neo-plugin' version '0.0.2' } ");
		printWriter.println("scpSettings { ");
		printWriter.println("sdkVersion = '3.39.10'");
		printWriter.println("sdkLocation = \"${projectDir}"+ File.separator+ "test\"");
		printWriter.println("}");
		printWriter.println("repositories { mavenCentral()	}");
		printWriter.flush();
		printWriter.close();
	}

	private void setUpNegativeCustomLocation() throws Exception {
		File buildFile = testProjectDir.newFile("build.gradle");
		PrintWriter printWriter = new PrintWriter(buildFile);
		printWriter.println("plugins { id 'com.tts.scp-neo-plugin' version '0.0.2' } ");
		printWriter.println("scpSettings { sdkVersion = '3.39.10' ");
		printWriter.println("sdkLocation = '' }");
		printWriter.println("repositories { mavenCentral()	}");
		printWriter.flush();
		printWriter.close();
	}

}
