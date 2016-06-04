import im.actor.bots.framework.*

class MyEchoBot(scope: MagicForkScope) : MagicBotFork(scope) {

    override fun onMessage(message: MagicBotMessage) {
        when (message) {
            is MagicBotTextMessage -> {

                sendText("Received: ${message.text}")
            }
        }
    }
}