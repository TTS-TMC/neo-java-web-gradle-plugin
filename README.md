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
After that you need to adapt your `build.gradle` file. Please be aware that you need the `gradle war plugin` because you only can deploy war files to the Scp Neo Stack

```
scpSettings{
    sdkVersion = "3.39.10"
    sdkLocation = "${projectDir}/sdk"
    serrverlocation="${projectDir}/myServer"
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


| Property | Mandatory | Default | Description |
| --- | --- | --- | --- |
|`sdkVersion`|yes|no|Specifies the version which should be used. please check [https://tools.hana.ondemand.com](https://tools.hana.ondemand.com) for most recent version
|`sdkLocation`|yes|${projectDir/.sdk}|The location where the sdk should be installed, can be pointed to an already installed location|
|`serverLocation`|no|${projectDir/server}|The location where the server for local development should be installed, can be pointed to an already installed location|
|`account`|for some tasks|no|The sub/account name of the Sap Cloud Platform account|
|`applicationName`|for some tasks|no| The application name, please be aware of naming convention of the Scp(only smal ltters, without spaces, dashes, underscore etc.)|
|`host`|for some tasks|hana.ondemand.com|The Region host of your Scp account|
|`sourceFileLocation`|for some tasks|no|the path of the war file, will be defaulted in future versions|
|`user`|for most tasks|no|your Scp username or mail adress. **This property should go to your $GRADLE_USER_HOME/gradle.properties file so it doesn't end up on your SCM**|
|`password`|for most tasks|no|your Scp password. **This property should go to your $GRADLE_USER_HOME/gradle.properties file so it doesn't end up on your SCM**|
|`enviromentVariables`|no|no|A map of enviroment variables you like to pass to the application|
|`jvmArgs`|no|no|A list of jvm Arguments you like to pass to the application, e.g. "-Dspring.profiles.active=someProfile"|
|`delta`|no|false|for development only, you deploy in delta mode, can be passed via commandline -Pdelta=true|

### Available tasks

`delpoy` - deploys the war file to the Scp account

`installSdk` - installs the neo sdk with the provided version of your build.gradle file

`installLocal` - installs a local server

`start` - starts the application in the scp account

`stop` - stops the application in the scp account

`restart` - restarts the application in the scp account
