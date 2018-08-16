import sx.blah.discord.handle.impl.events.guild.channel.message.MessageReceivedEvent
import sx.blah.discord.api.events.EventSubscriber
import sx.blah.discord.handle.impl.events.user.PresenceUpdateEvent
import sx.blah.discord.handle.obj.StatusType


class EventListener {
    @EventSubscriber
    fun onMessageReceived(event: MessageReceivedEvent) {
//        if (event.message.content.startsWith(BotUtils.BOT_PREFIX ))
        try{
            if (!event.author.isBot) {
                val message =event.message.content
                if (message.toUpperCase() == BotUtils.BOT_PREFIX + "LISTCHANNELS") {
                    for (channel in event.guild.channels) {
                        BotUtils.sendMessage(event.channel, channel.name)
                    }
                } else if (message.toUpperCase() == BotUtils.BOT_PREFIX + "PURGE") {
                    event.channel.bulkDelete()
                } else {
                    BotUtils.sendMessage(event.channel, event.message.content)
                }
            }

        }catch(ex:Exception){
            BotUtils.sendMessage(event.channel, ex.message!!)
        }
    }

    @EventSubscriber
    fun onAuthorPresence(event: PresenceUpdateEvent) {
        if (!event.user.isBot && event.newPresence.status == StatusType.ONLINE) {
//            BotUtils.sendMessage(event.client.channels[0].name)
        }

    }
}