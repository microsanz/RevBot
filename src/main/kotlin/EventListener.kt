import sx.blah.discord.handle.impl.events.guild.channel.message.MessageReceivedEvent
import sx.blah.discord.api.events.EventSubscriber
import sx.blah.discord.handle.impl.events.user.PresenceUpdateEvent
import sx.blah.discord.handle.obj.StatusType
import sx.blah.discord.util.MessageBuilder


class EventListener {
    @EventSubscriber
    fun onMessageReceived(event: MessageReceivedEvent) {
//        if (event.message.content.startsWith(BotUtils.BOT_PREFIX ))
        try{
            if (!event.author.isBot) {
                val message =event.message.content
                if (message.toUpperCase() == BotUtils.BOT_PREFIX + "LISTCHANNELS") {
                    var channelListString=""
                    for (channel in event.guild.channels) {
                        channelListString+=channel.name+"\n"

                    }
                    BotUtils.sendMessage(event.channel, channelListString.trim().trimIndent())
                        BotUtils.sendMessage(event.client.channels[0],MessageBuilder(event.client).withQuote(channelListString.trim().trimIndent()).content)
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
            BotUtils.sendMessage(event.client.channels[0],"Hi "+event.user.mention())
        }

    }

}