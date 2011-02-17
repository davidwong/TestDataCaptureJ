import ch.qos.logback.classic.encoder.PatternLayoutEncoder
import ch.qos.logback.core.FileAppender
import ch.qos.logback.core.ConsoleAppender
import ch.qos.logback.core.status.OnConsoleStatusListener

import static ch.qos.logback.classic.Level.*

// for debugging the logging
statusListener(OnConsoleStatusListener)

// add a status message regarding the log location root
def CATALINA_HOME = System.getProperty("catalina.home")
addInfo("CATALINA_HOME=${CATALINA_HOME}")

// fall back appender to use for quick testing, e.g. for unit tests
appender("CONSOLE", ConsoleAppender) {
	encoder(PatternLayoutEncoder) {
	  pattern = "// Generated %d{ABSOLUTE} %n%m%n"
	}
  }

// appender to use to put all logging into one file
appender("au.com.dw.testdatacapturej-file", FileAppender) {
  file = "${CATALINA_HOME}/logs/Test.java"
  encoder(PatternLayoutEncoder) {
    pattern = "// Generated %d{ABSOLUTE} %n%m%n"
  }
}

// logger for the code generation logging
logger("au.com.dw.testdatacapturej-trace", INFO, ["au.com.dw.testdatacapturej-file"], false)

root(DEBUG, ["CONSOLE"])