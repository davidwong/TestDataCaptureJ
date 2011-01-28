The general pattern for testing.

Source files
------------

1. Base aspect file (abstract)
This contains the aspects, but has an abstract pointcut. It may also have other abstract methods.

2. Base adaptor aspect file (abstract) - Optional
If the base aspect file contains abstract methods, then the adaptor will have default implementations for them.
Note that the pointcut is still abstract.
The usage of this class is for implementing the concrete aspect file in aop.xml using the adaptor as the superclass.
e.g.
	<aspect name="base.aspect"/>
	<aspect name="adaptor.aspect"/>
	
	<concrete-aspect name="concrete.aspect"
                            extends="adaptor.aspect">
             <pointcut name="pointcut_method" expression="..."/>
    </concrete-aspect>
    
3a. Concrete aspect file
Extends either the base aspect file or the base adaptor and implements a concrete pointcut. Should also have concrete
implementations for any other abstract methods.

3b. Test concrete aspect file
Simplified version of the concrete aspect file for unit au.com.dw.testing. The pointcut should refer to a test method in a Junit/TestNG
test class and each test class may also have a flag to enable/disable the pointcut.

Logging
-------

There are 2 styles of logging.

1. Use an ArgumentLogger implementation, passing the required ToStringStyle. Pass the StringBuilder as a param to logArgument().
The 3rd party implementations just outputs strings in various formats, but the custom reflection implementation also uses reflection.

2. Use a ReflectionProcessor implementation, which uses reflection and possibly recursion to log the internal fields of complex objects.
This uses it's own internal StringBuilder and returns a String from log().

3. Use an implementation of RecursiveArgumentLogger, which is an adaptor between ArgumentLogger and ReflectionProcessor.

Aspects
-------

- only enable the flags for the aspects to be tested, all the others should be disabled
e.g. private final static boolean debugOn = true;

- there are some test aspects in which the pointcuts only point to JUnit test methods

JBoss
-----

In order to test in JBoss 4:
(for server wide tracing without the need to alter applications)
- make sure JBoss is setup to use server scope classloading
- put the appropriate aspectj jars needed for weaving into [server instance]/lib folder, these may include
aspectjrt.jar, aspectjweaver.jar, aspectjtools.jar(?), org.aspectj.matcher.jar(?)
- configure run.bat to include aspectjweaver.jar
e.g. -javaagent:C:\apps\jboss-4.0.3SP1\server\all\lib\aspectjweaver.jar

1. Configure log4j.xml in [server instance]/conf with the appenders used for aspect tracing
2. Export the project as jar with aspectj support to the [server instance]/lib folder

In order to use JMX to dynamically configure aspect tracing:

3. Copy the sar folder to [server instance]/deploy folder including the aspect trace jar file.

Tomcat
------
(Extracts)
**********************************

Just to outline, all you need to do:
- Create and compile aspects that you need to weave.
- Create a jar file that includes
  -- the aspects
  -- an aop.xml file listing that aspect (see AspectJ guide for the syntax)
2. Add -javaagent:path/to/aspectjweaver.jar to Tomcat's command line. I
add the following to startup.bat:
set ASPECTJ_WEAVING=-javaagent:%SPRING_HOME%\lib\aspectj\aspectjweaver.jar
set JAVA_OPTS=%ASPECTJ_WEAVING%

You will will META-INF/aop.xml on either Tomcat's common classpath (i.e. in
a jar in TOMCAT_HOME/lib directory) or your webapps (i.e in a jar in
TOMCAT_HOME/webapp/your-app/lib or direct text file in
TOMCAT_HOME/webapp/your-app/classes directory).

**********************************

In order to test in Tomcat 6:
(for server wide tracing without the need to alter applications)
- setup shared folder in Tomcat catalina.properties, e.g. shared.loader=${catalina.base}/shared/lib/*.jar
- put the appropriate aspectj jars needed for weaving into [Tomcat home]/lib folder, e.g. aspectjrt.jar, aspectjweaver.jar
- configure either startup.bat or catalina.bat (depending on whether the path can be hardcoded or need to use %CATALINA_...% in which case needs to go
into catalina.bat where %CATALINA_...% is defined) to include aspectjweaver.jar
e.g.
set ASPECTJ_WEAVING=-javaagent:%CATALINA_BASE%\lib\aspectjweaver.jar -Xmx1024m
set JAVA_OPTS=%JAVA_OPTS% %ASPECTJ_WEAVING%
- (Optional) increase heap size
e.g. -Xmx1024m

1. Configure log4j.xml in [Tomcat home]/lib with the appenders used for aspect tracing
2. Copy log4j jar to the [Tomcat home]/lib folder
3. Export the project as jar with aspectj support to the [Tomcat home]/shared/lib folder
4. Copy any project dependency jars to the [Tomcat home]/shared/lib folder
e.g. commons-lang, commons-collection

The JMX code is only for JBoss, as Tomcat requires additional coding and wiring to handle JMX.
