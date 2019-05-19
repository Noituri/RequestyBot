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
                    Utils.log("PRIV"+ ":" + event.author.name + " " + config[BotSpec.botPrefix] + cmd.commandName)
                } else {
                    Utils.log(event.guild.name + ": " + config[BotSpec.botPrefix] + cmd.commandName)
                }

                val args = event.message.contentDisplay.split(" ").toTypedArray()
                cmd.execute(event, *args)
            }
            else -> {}
        }
    }
}