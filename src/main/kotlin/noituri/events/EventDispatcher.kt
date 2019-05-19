package noituri.events

import net.dv8tion.jda.api.events.GenericEvent
import net.dv8tion.jda.api.events.ReadyEvent
import net.dv8tion.jda.api.events.message.MessageReceivedEvent
import net.dv8tion.jda.api.hooks.EventListener
import noituri.Utils

class EventDispatcher: EventListener {
    override fun onEvent(event: GenericEvent) {
        when (event) {
            is ReadyEvent -> Utils.log("Bot is ready!")
            is MessageReceivedEvent -> MessageHandler(event).handle()
        }
    }
}