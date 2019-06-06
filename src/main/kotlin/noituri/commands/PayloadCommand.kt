package noituri.commands

import net.dv8tion.jda.api.entities.EmbedType
import net.dv8tion.jda.api.entities.MessageEmbed
import net.dv8tion.jda.api.events.message.MessageReceivedEvent
import noituri.BotsConfig.Companion.BotSpec
import noituri.BotsConfig.Companion.config
import noituri.State
import noituri.Utils
import java.time.OffsetDateTime

class PayloadCommand: Command(commandName = "payload", desc = "Sets or gets payload") {
    override fun execute(event: MessageReceivedEvent, vararg args: String) {
        if (args.size > 1) {
            State.payloads[event.message.author.id] = args.joinToString("").removePrefix(config[BotSpec.botPrefix] + "payload")
            Utils.sendEmbed("Payload", "Successfully updated", Utils.getStatusColor(200), event)
        } else {
            val p = State.payloads[event.message.author.id]

            if (p == null) {
                Utils.sendEmbed("Error", "Payload does not exist", Utils.getStatusColor(404), event)

                return
            }

            Utils.sendEmbed("Payload", "```$p```", Utils.getStatusColor(200), event)
        }
    }
}
