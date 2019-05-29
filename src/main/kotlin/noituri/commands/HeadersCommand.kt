package noituri.commands

import net.dv8tion.jda.api.entities.EmbedType
import net.dv8tion.jda.api.entities.MessageEmbed
import net.dv8tion.jda.api.events.message.MessageReceivedEvent
import noituri.BotsConfig
import noituri.Utils
import java.time.OffsetDateTime

class HeadersCommand : Command(commandName = "header", desc = "Sets or gets headers") {
    override fun execute(event: MessageReceivedEvent, vararg args: String) {
        if (args.size > 1) {
            headers[event.author.id] = args.joinToString("").removePrefix(BotsConfig.config[BotsConfig.Companion.BotSpec.botPrefix] + "header")
            Utils.sendEmbed("Header", "Successfully updated", Utils.getStatusColor(200), event)
        } else {
            val p = headers[event.author.id]
            if (p == null) {
                Utils.sendEmbed("Error occurred", "Header does not exist", Utils.getStatusColor(404), event)
                return
            }

            Utils.sendEmbed("Header", "```$p```", Utils.getStatusColor(200), event)

        }
    }
}