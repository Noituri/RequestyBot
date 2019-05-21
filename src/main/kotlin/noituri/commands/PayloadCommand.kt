package noituri.commands

import net.dv8tion.jda.api.entities.EmbedType
import net.dv8tion.jda.api.entities.MessageEmbed
import net.dv8tion.jda.api.events.message.MessageReceivedEvent
import noituri.BotsConfig.Companion.BotSpec
import noituri.BotsConfig.Companion.config
import noituri.Utils
import java.time.OffsetDateTime

class PayloadCommand: Command(commandName = "payload", desc = "Sets or gets payload") {
    override fun execute(event: MessageReceivedEvent, vararg args: String) {
        if (args.size > 1) {
            payloads[event.author.id] = args.joinToString("").removePrefix(config[BotSpec.botPrefix] + "payload")
            event.channel.sendMessage(MessageEmbed(
                    null,
                    "Payload",
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
            val p = payloads[event.author.id]

            if (p == null) {
                event.channel.sendMessage(MessageEmbed(
                        null,
                        "Error",
                        "Payload does not exist",
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
                    "Payload",
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
        }
    }
}
