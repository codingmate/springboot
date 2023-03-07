package learning.helloboot

class ExampleService : IExampleService {
    override fun greet(name: String): String {
        return "Hello! $name. Nice to meet you."
    }
}