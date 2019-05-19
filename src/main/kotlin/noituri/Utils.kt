package noituri

import noituri.commands.Command
import org.reflections.Reflections
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

sealed class Utils {
    companion object {
        val Commands = mutableSetOf<Command>()

        fun init() {
            val commands = Reflections("noituri.commands").getSubTypesOf(Command::class.java)
            commands.forEach {
                it.newInstance()
            }
        }

        fun addCommand(command: Command) = Commands.add(command)

        fun log(message: Any) {
            val time = LocalDateTime.now()
            val formatter = DateTimeFormatter.ofPattern("HH:mm:ss")

            when (message) {
                is String -> {
                    println("[${time.format(formatter)}] $message")
                }
                else -> {
                    println("[${time.format(formatter)}] Could not print data")
                }
            }
        }
    }
}