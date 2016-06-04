package im.actor.bots


import im.actor.bots.framework.farm
import im.actor.bots.framework.traits.sharedBugSnagClient

fun main(args: Array<String>) {


    farm("NewFarm") {


        bot(NotificationBot::class) {
            name = "bot0"
            //ansari
              token = "817cd8d23e7627432996967cdce140d3bff8bbf3"
              //token = "cd32ee5bbf073b3ed369d051d31ccbe8a50f5ecb"
          //actor  token =   "7ac12a91715b5369355f51941684ce30022c3b7f"

        }

    }
}