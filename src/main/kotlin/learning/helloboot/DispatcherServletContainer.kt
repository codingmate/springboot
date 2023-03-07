package learning.helloboot

import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory
import org.springframework.boot.web.server.WebServer
import org.springframework.boot.web.servlet.ServletContextInitializer
import org.springframework.boot.web.servlet.server.ServletWebServerFactory
import org.springframework.context.support.registerBean
import org.springframework.web.context.support.GenericWebApplicationContext
import org.springframework.web.servlet.DispatcherServlet

fun main(args: Array<String>) {
    val applicationContext = GenericWebApplicationContext()
    applicationContext.registerBean<HelloController>()
    applicationContext.registerBean<ExampleService>()
    applicationContext.refresh()

    val serverFactory: ServletWebServerFactory = TomcatServletWebServerFactory()
    val webServer: WebServer = serverFactory.getWebServer(
        ServletContextInitializer {
            it.addServlet("dispatcherServlet",
                DispatcherServlet(applicationContext)
            ).addMapping("/*")
        }
    )
    webServer.start()
}
