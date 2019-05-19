package noituri.commands

import net.dv8tion.jda.api.events.message.MessageReceivedEvent

class GetCommand: Command(commandName = "get", desc = "Sends GET request to the URL") {
    override fun execute(event: MessageReceivedEvent, vararg args: String) {
        event.channel.sendMessage("GET: Implement me " + args[0]).queue()
    }
}