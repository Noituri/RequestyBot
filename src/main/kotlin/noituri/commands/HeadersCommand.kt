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
            event.channel.sendMessage(MessageEmbed(
                    null,
                    "Header",
                    "Successfully updated",
                    EmbedType.IMAGE,
                    OffsetDateTime.now(),
                    Utils.getStatusColor(200),
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
        } else {
            val p = headers[event.author.id]

            if (p == null) {
                event.channel.sendMessage(MessageEmbed(
                        null,
                        "Error",
                        "Header does not exist",
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

                return
            }

            event.channel.sendMessage(MessageEmbed(
                    null,
                    "Header",
                    "```$p```",
                    EmbedType.IMAGE,
                    OffsetDateTime.now(),
                    Utils.getStatusColor(200),
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
        }    }
}