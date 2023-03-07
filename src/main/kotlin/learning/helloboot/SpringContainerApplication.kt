package learning.helloboot

import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory
import org.springframework.boot.web.servlet.ServletContextInitializer
import org.springframework.boot.web.servlet.server.ServletWebServerFactory
import org.springframework.context.support.GenericApplicationContext
import org.springframework.context.support.registerBean
import org.springframework.http.MediaType

import javax.servlet.http.HttpServlet
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse


fun main(args: Array<String>) {
    val applicationContext = GenericApplicationContext()
    applicationContext.apply{
        this.registerBean<HelloController>()
        this.refresh()
    }
    val servletWebServerFactory: ServletWebServerFactory = TomcatServletWebServerFactory()

    servletWebServerFactory.getWebServer(
        ServletContextInitializer { // 재사용하지 않고, 이 곳에서만 사용할 것이므로 lambda 사용
            it.addServlet("hello", object : HttpServlet() {
                override fun service(req: HttpServletRequest, res: HttpServletResponse) {
                    val name = "Tomson"
                    val helloController = applicationContext.getBean(HelloController::class.java)
                    val ret = helloController.hello(name)

                    res.contentType = MediaType.TEXT_PLAIN_VALUE
                    res.writer.println(ret)
                }
            }).addMapping("/hello")
            it.addServlet("greet", object : HttpServlet() {
                override fun service(req: HttpServletRequest, res: HttpServletResponse) {
                    val name = req.getParameter("name")
                    val helloController = applicationContext.getBean(HelloController::class.java)
                    val ret = helloController.greet(name)

                    res.contentType = MediaType.TEXT_PLAIN_VALUE
                    res.writer.println(ret)
                }
            }).addMapping("/hello/greet")
        }
    )
        .start() // Tomcat을 실행

}


