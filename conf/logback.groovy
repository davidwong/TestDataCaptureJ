import ch.qos.logback.classic.encoder.PatternLayoutEncoder
import ch.qos.logback.core.FileAppender
import ch.qos.logback.core.ConsoleAppender
import ch.qos.logback.classic.sift.GSiftingAppender
import ch.qos.logback.classic.sift.MDCBasedDiscriminator
import ch.qos.logback.core.status.OnConsoleStatusListener

import static ch.qos.logback.classic.Level.*

// for debugging the logging
statusListener(OnConsoleStatusListener)

// add a status message regarding the log location root
def CATALINA_HOME = System.getProperty("catalina.home")
addInfo("CATALINA_HOME=${CATALINA_HOME}")

// add a timestamp to the 'FILE' appender
// not used for 'SIFT' as haven't worked out how to do that yet, so timestamp added in code instead
def bySecond = timestamp("yyyyMMdd'T'HHmmss")

// fall back appender to use for quick testing, e.g. for unit tests
appender("CONSOLE", ConsoleAppender) {
	encoder(PatternLayoutEncoder) {
	  pattern = "// Generated %d%n%m%n"
	}
  }

// appender to use to put all logging into one file
appender("FILE", FileAppender) {
  file = "${CATALINA_HOME}/logs/Test-${bySecond}.java"
  encoder(PatternLayoutEncoder) {
    pattern = "// Generated %d%n%m%n"
  }
}

// appender to use to put each logged object into a separate file
appender("SIFT", GSiftingAppender) {
  discriminator(MDCBasedDiscriminator) {
    key="traceClass"
    defaultValue = "unknownValue"
  }
  sift {
    appender("FILE-${traceClass}", FileAppender) {
      file = "${CATALINA_HOME}/logs/${traceClass}.java"
      encoder(PatternLayoutEncoder) {
        pattern = "// Generated %d%n%m%n"
      }
    }
  }
}

// logger for the code generation logging
logger("au.com.dw.testdatacapturej-trace", INFO, ["SIFT"], false)

root(DEBUG, ["CONSOLE"])