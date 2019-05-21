package noituri.events

import net.dv8tion.jda.api.entities.ChannelType
import net.dv8tion.jda.api.events.message.MessageReceivedEvent
import noituri.Utils
import noituri.BotsConfig.Companion.config
import noituri.BotsConfig.Companion.BotSpec

class MessageHandler(var event: MessageReceivedEvent) {
    fun handle() {
        if (event.author.isBot) {
            return
        }

        when (event.channelType) {
            ChannelType.PRIVATE -> {
                val cmd = Utils.Commands.find {
                    event.message.contentDisplay.startsWith(config[BotSpec.botPrefix] + it.commandName)
                } ?: return

                if (event.isFromType(ChannelType.PRIVATE)) {
                    Utils.log("PRIV"+ ":" + event.author.name + " " + event.message.contentDisplay)
                } else {
                    Utils.log(event.guild.name + ": " + event.message.contentDisplay)
                }

                val args = event.message.contentDisplay.trim().split(" ").toTypedArray()

                cmd.execute(event, *args)
            }
            else -> {}
        }
    }
}