package im.actor.bots

import im.actor.bots.framework.MagicBotFork
import im.actor.bots.framework.MagicBotMessage
import im.actor.bots.framework.MagicBotTextMessage
import im.actor.bots.framework.MagicForkScope
import im.actor.bots.framework.persistence.MagicPersistentBot
import im.actor.bots.framework.stateful.*
import java.math.*;

/**
 * Very simple echo bot that forwards message
 */
import im.actor.bots.framework.*
import im.actor.bots.framework.stateful.*
import ir.elenoon.db.MessageModel

import ir.elenoon.db.DBUtils
import org.json.JSONArray
import org.json.JSONObject
import java.awt.List
import java.util.*
import kotlin.system.measureTimeMillis

open class NotificationBot(scope: MagicForkScope) : MagicStatefulBot(scope) {

    var welcomeMessage = "Hello! I am notification bots and i can send you various notifications. " +
            "Just send me /subscribe and i will start to broadcast messages to you"



     override  fun configure() {

        enableSimpleMode("masood")
         DBUtils.getInstance().addUserAnswer()
         var s = listOf("۱", "۲", "3", "4")
        var q = listOf("نام امام اول چیست؟", "نام امام دوم چیست؟", "نام امام سوم چیست؟")
        var i = 0

        //sendText("سلام کافیست برای اغاز مسابقه برروی [شروع](send:/شروع) کلیک کنید")

        /*       oneShot("/start") {

        loop@ for (i in 1..12) {
            var b = (Random().nextFloat() * 10).toInt()
            sendText(i.toString())
            goto("ask_text")
        }
           expectInput("ask_text") {

                received {
                    sendText("ok")
                    goto("main")
                }



            }



        }*/


        command("/start") {

            before {
                if (i == 0) {
                    sendText("خب شروع میکنیم")
                    // var td = createBot("hasan", "hasan")?.token()
                    //sendText(td.toString())
                }

                MessageModel.getInstance().set(getUser(scope.peer.id).username.toString(), getUser(scope.peer.id).name(), getUser(scope.peer.id).phoneContactRecords.toString(), text , null)
                sendText("information")
                sendText("شما مایل به خواندن چند ربع حذب می باشید . بر روی حذب انتخابی خود کلیک کنید")
                sendText("[1](send:1)")
                sendText("[2](send:2)")
                sendText("[3](send:3)")
                sendText("[4](send:2)")
                goto("ask_hizb")
                //sendText(getUser(scope.peer.id).toString())
                //sendText(findUser("masood").toString())

            }
        }

        expectInput("ask_hizb") {

            received {
               // MessageModel.getInstance().set(getUser(scope.peer.id).username.toString(), getUser(scope.peer.id).name(), getUser(scope.peer.id).phoneContactRecords.toString(), text , null)

                if (text == "/خروج") {
                    var user = findUser("+989212785372")
                    var a = OutPeer(PeerType.PRIVATE, user!!.id(), user.hashCode().toLong())
                    sendText(a, getUser(scope.peer.id).phoneContactRecords.toString() +"نمره")
                    sendText("ازمون به پایان رسید")
                    sendText("اگه میخوای دوباره مسابقه بدی دوباره (/شروع) رو برام بفرست")
                    gotoParent()

                }
                else {
                    sendText("az aye felan ta felan")
                    sendText("[انجام دادم](send:تمام)")
                    sendText("[انصراف میدهم](send:انصراف)")
                    goto("done_cancel")
                    }
                    //sendText("ازمون به پایان رسید")
                   // else {



                         }



            validate {
                if (text.equals("3")  || text.equals("1") || text.equals("۳") || text.equals("۱") || text.equals("۲")|| text.equals("2") || text.equals("/خروج")|| text.equals("4") || text.equals("۴") ) {
                    sendText("متشکر از جواب شما")
                    return@validate true
                }
                else {
                    sendText("لطفا شماره ۱ یا ۲ یا ۳ یا ۴ را وارد کنید")
                    return@validate false
                }

            }
        }
         expectInput("done_cancel") {

             received {
                 //MessageModel.getInstance().set(getUser(scope.peer.id).username.toString(), getUser(scope.peer.id).name(), getUser(scope.peer.id).phoneContactRecords.toString(), text , null)

                 if (text == "تمام") {
                     sendText("تا الان چند حذب خوندی")
                     gotoParent()

                 }
                 else if(text == "انصراف"){
                     sendText("از تلاش شما متشکریم")
                     gotoParent()
                 }
                 //sendText("ازمون به پایان رسید")
                 // else {



             }



             validate {
                 if (text.equals("انصراف") || text.equals("تمام") ) {
                     sendText("متشکر از جواب شما")
                     return@validate true
                 }
                 else {
                     sendText("لطفا یکی از گزینه های بالا را انتخاب کنید")
                     return@validate false
                 }

             }
         }

    }




    /* oneShot("/salam") {

         expectCommands {
             oneShot("/help") {
                 sendText("Help!")
             }
             oneShot("/hint") {
                 sendText("Hint!")
             }
         }
         if (isAdmin(scope.sender)) {
             sendText(scope.name)
         } else {
             sendText(scope.sender.toString())
         }
     }
     oneShot("/subscribe") {
         sendText("Congratulations! You have successfully *subscribed* to my notifications! To unsubscribe send /unsubscribe")
         sendToOverlord(NotificationOverlord.Subscribe(scope.peer))
     }
     oneShot("/unsubscribe") {
         sendText("You have *unsubscribed* from my notifications. Feel free to subscribe again with /subscribe command")
         sendToOverlord(NotificationOverlord.Unsubscribe(scope.peer))
     }
     oneShot("/subscribe_admin") {
         sendText("Congratulations! You have successfully *subscribed* to admin notifications! To unsubscribe send /unsubscribe_admin")
         sendToOverlord(NotificationOverlord.SubscribeAdmin(scope.peer))
     }
     oneShot("/unsubscribe_admin") {
         sendText("You have *unsubscribed* from admin notifications. Feel free to subscribe again with /subscribe_admin command")
         sendToOverlord(NotificationOverlord.UnsubscribeAdmin(scope.peer))
     }
     oneShot("/hook_url") {

             var hook = scope.botKeyValue.getStringValue("notification_url")
             if (hook == null) {
                 hook = createHook("notification_hook_url")
                 scope.botKeyValue.setStringValue("notification_url", hook)
             }
             sendText("Notification hook is: *$hook*")

     }
     raw("/send") {
         var broadcastMessage: String? = null
         before {
             sendText("What do you want to broadcast?")
             goto("message")
         }
         expectInput("message") {
             received {
                 broadcastMessage = text
                 sendText("Success. Are you sure want to send message $broadcastMessage? Send yes or no in response.")
                 goto("confirm")
             }
             validate {
                 if (!isText) {
                     sendText("Please, send valid text message")
                     return@validate false
                 }
                 return@validate true
             }
         }
         expectInput("confirm") {
             received {
                 when (text.toLowerCase()) {
                     "yes" -> {
                         sendToOverlord(NotificationOverlord.DoBroadcast(broadcastMessage!!))
                         sendText("Message sent!")
                         goto("main")
                     }
                     "no" -> {
                         sendText("Message send cancelled.")
                         goto("main")
                     }
                 }
             }
             validate {
                 if (isText) {
                     when (text.toLowerCase()) {
                         "yes" -> {
                             return@validate true
                         }
                         "no" -> {
                             return@validate true
                         }
                     }
                 }
                 sendText("Please, send yes or no.")
                 return@validate false
             }
         }
     }*/



    fun isSenderAdmin(): Boolean {
        if (scope.sender == null) {

            return false
        }
        val sender = getUser(scope.sender!!.id())
        if (sender.username.isPresent && admins.contains(sender.username.get())) {
            return true
        }
        return false
    }
}
class NotificationOverlord(scope: MagicOverlordScope) : MagicOverlord(scope) {
    val keyValue = scope.botKeyValue
    val subscribers = ArrayList<OutPeer>()
    val adminSubscribers = ArrayList<OutPeer>()
    // Events
    fun onText(text: String) {
        for (s in subscribers) {
            sendText(s, text)
        }
        onAdminText("Broadcasted message\n$text")
    }
    fun onAdminText(text: String) {
        for (s in adminSubscribers) {
            sendText(s, text)
        }
    }
    fun onSubscribe(peer: OutPeer) {
        if (subscribers.contains(peer)) {
            return
        }
        subscribers.add(peer)
        saveSubscribers()
        onAdminText("Subscribed $peer")
    }
    fun onUnsubscribe(peer: OutPeer) {
        subscribers.remove(peer)
        saveSubscribers()
        onAdminText("Unsubscribed $peer")
    }
    fun onSubscribeAdmin(peer: OutPeer) {
        if (adminSubscribers.contains(peer)) {
            return
        }
        adminSubscribers.add(peer)
        saveSubscribers()
    }
    fun onUnsubscribeAdmin(peer: OutPeer) {
        adminSubscribers.remove(peer)
        saveSubscribers()
    }
    fun saveSubscribers() {
        val storage = JSONObject()
        val peers = JSONArray()
        for (s in subscribers) {
            peers.put(s.toJson())
        }
        storage.put("peers", peers)
        val adminPeers = JSONArray()
        for (s in adminSubscribers) {
            adminPeers.put(s.toJson())
        }
        storage.put("adminPeers", adminPeers)
        keyValue.setStringValue("storage", storage.toString())
    }
    fun loadSubscribers() {
        subscribers.clear()
        adminSubscribers.clear()
        try {
            val storage = JSONObject(keyValue.getStringValue("storage"))
            val peers = storage.getJSONArray("peers")
            for (i in 0..peers.length()) {
                try {
                    subscribers.add(outPeerFromJson(peers.getJSONObject(i)))
                } catch(e: Exception) {
                    e.printStackTrace()
                }
            }
            val adminPeers = storage.getJSONArray("adminPeers")
            for (i in 0..adminPeers.length()) {
                try {
                    adminSubscribers.add(outPeerFromJson(peers.getJSONObject(i)))
                } catch(e: Exception) {
                    e.printStackTrace()
                }
            }
        } catch(e: Exception) {
            e.printStackTrace()
        }
    }
    // Processor
    override fun preStart() {
        super.preStart()
        loadSubscribers()


    }
    override fun onReceive(update: Any?) {
        if (update is Subscribe) {
            onSubscribe(update.peer)
        } else if (update is Unsubscribe) {
            onUnsubscribe(update.peer)
        } else if (update is SubscribeAdmin) {
            onSubscribeAdmin(update.peer)
        } else if (update is UnsubscribeAdmin) {
            onUnsubscribeAdmin(update.peer)
        } else if (update is DoBroadcast) {
            onText(update.message)
        } else {
            super.onReceive(update)
        }
    }
    override fun onWebHookReceived(hook: HookData) {
        if (hook.jsonBody != null) {
            val text = (hook.jsonBody as JSONObject).optString("text")
            if (text != null) {
                onText(text)
            }
        }
    }
    data class DoBroadcast(val message: String)
    data class Subscribe(val peer: OutPeer)
    data class Unsubscribe(val peer: OutPeer)
    data class SubscribeAdmin(val peer: OutPeer)
    data class UnsubscribeAdmin(val peer: OutPeer)
}