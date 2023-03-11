package com.pubnub.api.endpoints.pubsub

class Person @JvmOverloads constructor(
    val name: String,
    val surname: String,
    val email: String = "default@mail.com",
    val age: Int = 10
) {

    fun printPerson(){
        println("Person: $name, $surname, $email, $age")
    }

}

fun main(){
    println("d")
    Person("Johny", "Bravo").printPerson()
}