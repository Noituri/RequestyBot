package noituri

import com.uchuhimo.konf.Config
import com.uchuhimo.konf.ConfigSpec
import net.dv8tion.jda.api.JDABuilder
import noituri.BotsConfig.Companion.config
import noituri.BotsConfig.Companion.BotSpec
import noituri.events.EventDispatcher

class BotsConfig {
    companion object {
        object BotSpec : ConfigSpec() {
            val token by required<String>(description = "token for your bot")
            val botPrefix by required<String>(description = "bot's prefix")
        }

        val config = Config{ addSpec(BotSpec) }.from.json.file("./config.json")
    }
}

fun main() {
    Utils.init()

    val builder = JDABuilder(config[BotSpec.token])
            .addEventListeners(EventDispatcher())
            .build()

    builder.awaitReady()
}