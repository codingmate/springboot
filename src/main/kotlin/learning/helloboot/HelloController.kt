package learning.helloboot

import org.springframework.web.bind.annotation.GetMapping
import java.util.Objects

//@RestController
class HelloController(private val exampleService: IExampleService) {
    @GetMapping("/hello")
    fun hello(name: String?) // param is query string. ex) URI : /hello?name=hi
        : String {
        return "Hello $name"
    }

    @GetMapping("/hello/greet")
    fun greet(name: String?): String{

        // Objects.requireNonNull() :: if (name == null) throw Exception...
        return exampleService.greet(Objects.requireNonNull(name!!))
    }
}