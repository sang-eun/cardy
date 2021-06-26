package game.cardy

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class CardyApplication

fun main(args: Array<String>) {
    runApplication<CardyApplication>(*args)
}
