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

            event.channel.sendMessage(MessageEmbed(
                    null,
                    "[GET] Status: ${response.statusCode}",
                    """```html
                    |${response.text.removeRange(2000, response.text.length)}
                    |```
                """.trimMargin(),
                    EmbedType.IMAGE,
                    OffsetDateTime.now(),
                    Utils.getStatusColor(response.statusCode),
                    null,
                    null,
                    null,
                    null,
                    MessageEmbed.Footer(
                            "Issued by ${event.author.name}",
                            event.author.avatarUrl,
                            null
                    ),
                    null,
                    null
            )).queue()
        } catch (exc: Exception) {
            event.channel.sendMessage(MessageEmbed(
                    null,
                    "Error occurred",
                    "```${exc.localizedMessage}```",
                    EmbedType.IMAGE,
                    OffsetDateTime.now(),
                    Utils.getStatusColor(404),
                    null,
                    null,
                    null,
                    null,
                    MessageEmbed.Footer(
                            "Issued by ${event.author.name}",
                            event.author.avatarUrl,
                            null
                    ),
                    null,
                    null
            )).queue()
        }
    }
}