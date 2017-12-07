package sims.michael.jacksonmodulekotlinissue

import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.KotlinModule
import org.intellij.lang.annotations.Language

@Language("JSON")
fun main(args: Array<String>) {
    val mapper = ObjectMapper().apply { registerModule(KotlinModule()) }

    val foo = mapper.readValue("""{"baz": "bazValue"}""", Foo::class.java)
    println(foo)

    val fooWithStaticCreator = mapper.readValue("""{"baz": "bazValue"}""", FooWithStaticCreator::class.java)
    println(fooWithStaticCreator)
}

data class Foo(val bar: String = "default", val baz: String = "default")

data class FooWithStaticCreator(val bar: String, val baz: String) {
    companion object {
        @JvmStatic
        @JsonCreator
        fun createFromJson(bar: String = "default", baz: String = "default"): FooWithStaticCreator = FooWithStaticCreator(bar, baz)
    }
}
