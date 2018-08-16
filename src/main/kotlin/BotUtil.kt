
import sx.blah.discord.util.DiscordException
import sx.blah.discord.util.RequestBuffer
import sx.blah.discord.handle.obj.IChannel
import sx.blah.discord.api.ClientBuilder
import sx.blah.discord.api.IDiscordClient

internal object BotUtils {

    // Constants for use throughout the bot
    var BOT_PREFIX = "!"

    // Handles the creation and getting of a IDiscordClient object for a token
    fun getBuiltDiscordClient(token: String): IDiscordClient {

        // The ClientBuilder object is where you will attach your params for configuring the instance of your bot.
        // Such as withToken, setDaemon etc
        return ClientBuilder()
                .withToken(token)
                .build()

    }

    // Helper functions to make certain aspects of the bot easier to use.
    fun sendMessage(channel: IChannel, message: String) {

        // This might look weird but it'll be explained in another page.
        RequestBuffer.request {
            try {
                channel.sendMessage(message)
            } catch (e: DiscordException) {
                System.err.println("Message could not be sent with error: ")
                e.printStackTrace()
            }
        }

        /*
            // The below example is written to demonstrate sending a message if you want to catch the RLE for logging purposes
            RequestBuffer.request(() -> {
                try{
                    channel.sendMessage(message);
                } catch (RateLimitException e){
                    System.out.println("Do some logging");
                    throw e;
                }
            });
            */

    }
}
