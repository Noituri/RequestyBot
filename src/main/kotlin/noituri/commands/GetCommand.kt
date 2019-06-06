package noituri.commands

import com.beust.klaxon.JsonObject
import com.beust.klaxon.Parser
import khttp.get
import net.dv8tion.jda.api.entities.EmbedType
import net.dv8tion.jda.api.entities.MessageEmbed
import net.dv8tion.jda.api.events.message.MessageReceivedEvent
import noituri.State
import noituri.Utils
import java.lang.StringBuilder
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
            event.channel.sendMessage("http/https not found. Appending 'http' to URL").queue()
        }

        try {
            var response = get(url)

            val header = State.headers[event.message.author.id]
            if (header != null) {
                val json = (Parser().parse(StringBuilder(header)) as JsonObject).map as MutableMap<String, String>
                response = get(url, headers=json)
            }

            var resp = response.text

            if (response.text.length > 2000) {
                resp = response.text.removeRange(2000, response.text.length)
            }

            val body = """```html
                    |$resp
                    |```
                """.trimMargin()

            Utils.sendEmbed("[GET] Status: ${response.statusCode}", body, Utils.getStatusColor(response.statusCode), event)

        } catch (exc: Exception) {
            Utils.sendEmbed("Error occurred", "```${exc.localizedMessage}```", Utils.getStatusColor(404), event)
        }
    }
}