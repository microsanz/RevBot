class Main() {

    companion object {

        val TOKEN = "NDc5MzMwNzUyNTkzMzk1NzEy.DlbP8A.8NyNHhuGDZzVAtQiIUuhWfAOKww"
        val LOGIN = true
        @JvmStatic
        fun main(args: Array<String>) {


            val cli = BotUtils.getBuiltDiscordClient(TOKEN)

            /*
        // Commented out as you don't really want duplicate listeners unless you're intentionally writing your code
        // like that.
        // Register a listener via the IListener interface
        cli.getDispatcher().registerListener(new IListener<MessageReceivedEvent>() {
            public void handle(MessageReceivedEvent event) {
                if(event.getMessage().getContent().startsWith(BotUtils.BOT_PREFIX + "test"))
                    BotUtils.sendMessage(event.getChannel(), "I am sending a message from an IListener listener");
            }
        });
        */

            // Register a listener via the EventSubscriber annotation which allows for organisation and delegation of events
            cli.dispatcher.registerListener(EventListener())

            // Only login after all events are registered otherwise some may be missed.
            cli.login()

            if (cli.isLoggedIn){
                System.out.print("Yeay Logged In")
            }
        }

    }
}
