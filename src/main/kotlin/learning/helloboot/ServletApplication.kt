package learning.helloboot

import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory
import org.springframework.boot.web.servlet.ServletContextInitializer
import org.springframework.boot.web.servlet.server.ServletWebServerFactory
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import javax.servlet.http.HttpServlet
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse


fun main(args: Array<String>) {
    // ServletWebServerFactory : Servlet Container 추상화
    val servletWebServerFactory: ServletWebServerFactory
    // Tomcat의 Servlet Container 생성 팩토리로 간편하게 인스턴스 생성
            = TomcatServletWebServerFactory()
    // TomcatFactory 생성된 컨테이너의 WebServer를 가져옴
    servletWebServerFactory.getWebServer(

        ServletContextInitializer { // 재사용하지 않고, 이 곳에서만 사용할 것이므로 lambda 사용
            // servlet container 실행자
            it.addServlet("hello", object : HttpServlet() {
                override fun service(req: HttpServletRequest, res: HttpServletResponse) {

                    // query string
                    val name: String = req.getParameter("name")
                    val paramMap = req.parameterMap
                    println(paramMap.keys.joinToString())
                    val values = StringBuilder()
                    for ( key in paramMap.keys )
                        for ( value in paramMap[key]!! )
                            values.append("$key : ${value}\n")
                    println(values)

                // status code
                    //res.status = 200
                    res.status = HttpStatus.OK.value()
                // header : contents-type
                    //res.setHeader("Content-Type", "text/plain")
                    res.setHeader(HttpHeaders.CONTENT_TYPE, MediaType.TEXT_PLAIN_VALUE)
                // body
                    res.writer.println("Hello!, input : ${values}")
                    res.writer.println("name : $name")
                }
            }).addMapping("/hello")
        } // functional     interface : only one method
    )
        .start() // Tomcat을 실행
}
