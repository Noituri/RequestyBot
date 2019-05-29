package noituri.commands

import khttp.get
import net.dv8tion.jda.api.entities.EmbedType
import net.dv8tion.jda.api.entities.MessageEmbed
import net.dv8tion.jda.api.events.message.MessageReceivedEvent
import noituri.Utils
import java.time.OffsetDateTime

class GetCommand: Command(commandName = "get", desc = "Sends GET request to the URL") {
    override fun execute(event: MessageReceivedEvent, vararg args: String) {
        if (args.size < 2) {
            event.channel.sendMessage("Not enough arguments")
            return
        }

        var url = args[1]

        if (!url.startsWith("http")) {
            url = "http://" + url
            event.channel.sendMessage("http/https not found. Appending 'http' to URL")
        }

        try {
            val response = get(url)
            val body = """```html
                    |${response.text.removeRange(2000, response.text.length)}
                    |```
                """.trimMargin()

            Utils.sendEmbed("[GET] Status: ${response.statusCode}", body, Utils.getStatusColor(response.statusCode), event)

        } catch (exc: Exception) {
            Utils.sendEmbed("Error occurred", "```${exc.localizedMessage}```", Utils.getStatusColor(404), event)
        }
    }
}