package learning.helloboot

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import java.util.Objects

@RestController
class HelloController {

    @GetMapping("/hello")
    fun hello(name: String?) // param is query string. ex) URI : /hello?name=hi
        : String {
        return "Hello $name"
    }

    @GetMapping("/hello/greet")
    fun greet(name: String?): String{

        // Objects.requireNonNull() :: if (name == null) throw Exception...
        return ExampleService().greet(Objects.requireNonNull(name!!))
    }
}