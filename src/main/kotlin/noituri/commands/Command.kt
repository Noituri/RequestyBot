package noituri.commands

import net.dv8tion.jda.api.events.message.MessageReceivedEvent
import noituri.Utils

abstract class Command(val commandName: String, val desc: String) {
    abstract fun execute(event: MessageReceivedEvent, vararg args: String)

    var payloads = HashMap<String, String>()
    var headers = HashMap<String, String>()

    init {
        Utils.addCommand(this)
    }
}