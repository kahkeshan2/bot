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
import ir.elenoon.db.*
import org.json.JSONArray
import org.json.JSONObject
import java.awt.List
import java.util.*
import java.util.concurrent.TimeUnit
import kotlin.system.measureTimeMillis

open class NotificationBot(scope: MagicForkScope) : MagicStatefulBot(scope) {

    var welcomeMessage = "Hello! I am notification bots and i can send you various notifications. " +
            "Just send me /subscribe and i will start to broadcast messages to you"

    var question:kotlin.collections.List<Questions> ?= null
    var options: kotlin.collections.List<Options> ?= null
    var que_size:Int = -2;
    var current_qu:Int = -1
    override  fun configure() {
       //DBUtils.getInstance().addUserAnswer()
        // DBUtils.getInstance().addQuestion()
        enableSimpleMode("masood")
        var s = listOf("۱", "۲", "3", "4")
        var q = listOf("نام امام اول چیست؟", "نام امام دوم چیست؟", "نام امام سوم چیست؟")
        var i = 0
        expectCommands("/start") {
            before {

            ///////////////////////////
                sendText("دسکریپت")
            //sendText(getUser(scope.peer.id).toString())
            //sendText(findUser("masood").toString())

        }
            if(text == "/شروع"){

                goto("/شروع")
            }
        }
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


        command("/شروع") {
        //first state
            before {
                if (i == 0) {
                    sendText("lets start")
                    // var td = createBot("hasan", "hasan")?.token()
                    //sendText(td.toString())
                }
                var series = DBUtils.getInstance().getseries()
                if(series == null){

                    sendText("deadline for competition")
                    gotoParent()
                }
                sendText("azmoon ta lahazati dg load mishe"+series.series_id)
                question = DBUtils.getInstance().getQuestionList(series.series_id)
                que_size = (question as MutableList<Questions>?)!!.size

                current_qu = 0
                var options = DBUtils.getInstance().getQuestionList(series.series_id)
                sendText("3")
                TimeUnit.MILLISECONDS.sleep(10);
                sendText("2")
                TimeUnit.MILLISECONDS.sleep(10);
                sendText("1")
                goto("/ask")



            }
        }
        command("/ask") {
            //first state
            before {
                if (current_qu <= que_size ) {
                    sendText(question!!.get(current_qu).text)
                    TimeUnit.MILLISECONDS.sleep(10);
                    for (item in options!!){
                        if(item.questions.questions_id == question!!.get(current_qu).questions_id){
                            sendText(item.text)
                        }
                    }

                    // var td = createBot("hasan", "hasan")?.token()
                    //sendText(td.toString())
                }
                var series = DBUtils.getInstance().getseries()
                if(series == null){

                    sendText("deadline for competition")
                    gotoParent()
                }
                sendText("azmoon ta lahazati dg load mishe"+series.series_id)
                var Question = DBUtils.getInstance().getQuestionList(series.series_id)
                var options = DBUtils.getInstance().getQuestionList(series.series_id)
                sendText("3")
                TimeUnit.MILLISECONDS.sleep(10);
                sendText("2")
                TimeUnit.MILLISECONDS.sleep(10);
                sendText("1")
                goto("/ask")



            }
        }
        expectInput("ask_text") {

            received {
                MessageModel.getInstance().set(getUser(scope.peer.id).username.toString(), getUser(scope.peer.id).name(), getUser(scope.peer.id).phoneContactRecords.toString(), text , null)

                if (text == "/خروج") {
                    var user = findUser("+989212785372")
                    var a = OutPeer(PeerType.PRIVATE, user!!.id(), user.hashCode().toLong())
                    sendText(a, getUser(scope.peer.id).phoneContactRecords.toString() +"نمره")
                    sendText("ازمون به پایان رسید")
                    sendText("اگه میخوای دوباره مسابقه بدی دوباره (/شروع) رو برام بفرست")
                    gotoParent()

                }
                else {
                    i = i + 1
                    if (i < 3) {

                        goto("/شروع")
                    }
                    //sendText("ازمون به پایان رسید")
                    else {

                        var x = findUser("+989212785372")
                        var a = OutPeer(PeerType.PRIVATE, x!!.id(), x.accessHash().toLong())
                        i = 0
                        sendText(a, getUser(scope.peer.id).name().toString()+ "ازمون خود را به پایان رساند"+"نمره")
                        sendText(" ازمون به پایان رسید")
                        sendText("اگه میخوای دوباره مسابقه بدی دوباره (/شروع) رو برام بفرست")


                        gotoParent()
                    }
                }

            }
            validate {
                if (text.equals("3") || text.equals("2") || text.equals("1") || text.equals("۳") || text.equals("۱") || text.equals("۲") || text.equals("/خروج")) {
                    sendText("متشکر از جواب شما")
                    return@validate true
                }
                else {
                    sendText("لطفا شماره ۱ یا ۲ یا ۳ را وارد کنید")
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