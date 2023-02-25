package learning.helloboot

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class HelloController {

        @GetMapping("/hello")
        fun hello(name: String?) // param is query string. ex) URI : /hello?name=hi
            : String {
            return "Hello $name"
        }
}