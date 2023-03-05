package learning.helloboot

import org.springframework.http.HttpMethod
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

class FrontServletController {
    // 공통적 코드
    // 인증, 보안, 다국어, 공통 기능

    // hello, user, 404
    fun mapping(req: HttpServletRequest, res: HttpServletResponse) {
        val helloServletController = HelloServletContainerController()


        if ( req.requestURI.equals("/hello") && req.method.equals(HttpMethod.GET.name) ) {

            val name = req.getParameter("name")
            res.status = HttpStatus.OK.value()
        //res.setHeader(HttpHeaders.CONTENT_TYPE, MediaType.TEXT_PLAIN_VALUE)
            res.contentType = MediaType.TEXT_PLAIN_VALUE
            res.writer.println(helloServletController.hello(name))
        }
        else if (req.requestURI.equals("/board")) {
            //
        }
        else {
            res.status = HttpStatus.NOT_FOUND.value()
        }
    }
}