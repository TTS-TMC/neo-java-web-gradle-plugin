# neo-java-web-gradle-plugin
Implementation of the Sap Cloud Platform Tomcat runtime in gradle

Build script snippet for use in all Gradle versions:

```
buildscript {
  repositories {
    maven {
      url "https://plugins.gradle.org/m2/"
    }
  }
  dependencies {
    classpath "gradle.plugin.com.tts:neo-java-web-gradle-plugin:0.1.8"
  }
}

apply plugin: "com.tts.scp-neo-plugin"
```

Build script snippet for new, incubating, plugin mechanism introduced in Gradle 2.1:

```
plugins {
  id "com.tts.scp-neo-plugin" version "0.1.8"
}
```
After that you need to adapt your `build.gradle` file.

```
scpSettings{
    sdkVersion = "3.39.10"
    sdkLocation = "${projectDir}/sdk"
    account = 'abcdef'
    applicationName = 'appname'
    host = 'hana.ondemand.com'
    sourceFileLocation = "${projectDir}/build"
    user = "yourUser"
    password = "password"
    enviromentVariables = [varA: 'valueA']
    jvmArgs = ["-Dsome.jvmArg=value"]
    delta = true
}
```

### Explanation of the properties

|Property    | Mandatory     | Description    |
|-------------|---------------|----------------|
|`sdkVersion`|yes|The version of the to used Sap Cloud Platform Neo Sdk|
|`sdkLocation`|yes|The location where the server should be installed|
|`account`|yes|The account name of the Sap Cloud Platform account|
|`applicationName`|yes| The application name|
