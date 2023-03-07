package learning.helloboot

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.bind.annotation.RestController
import java.util.Objects

@RestController
@RequestMapping("/hello")
class HelloController(private val exampleService: IExampleService) {
    @GetMapping("/")
    fun hello(name: String?) // param is query string. ex) URI : /hello?name=hi
        : String {
        return "Hello $name"
    }

    @GetMapping("/greet")
    fun greet(name: String?): String{

        // Objects.requireNonNull() :: if (name == null) throw Exception...
        return exampleService.greet(Objects.requireNonNull(name!!))
    }
}