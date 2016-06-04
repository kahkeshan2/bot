package im.actor.bots

/**
 * Very simple echo bot that forwards message
 */
import im.actor.bots.framework.*
import im.actor.bots.framework.stateful.*
import ir.elenoon.constants.Constant
import ir.elenoon.db.DBUtils
import ir.elenoon.db.UsersAnswers
import ir.elenoon.models.QuestionsModel
import ir.elenoon.utils.Utils
import org.json.JSONArray
import org.json.JSONObject
import java.util.*

open class NotificationBot(scope: MagicForkScope) : MagicStatefulBot(scope) {

    var welcomeMessage = ""



    override  fun configure() {
        var questions:kotlin.collections.List<QuestionsModel> ?= null
        var user_series:List<UsersAnswers>
        var userId:Int = -1
        var que_size:Int = -2;
        var current_qu:Int = 0
        enableSimpleMode("masood")



        //sendText("سلام کافیست برای اغاز مسابقه برروی [شروع](send:/شروع) کلیک کنید")
        expectCommands("/start",{before {
            if (current_qu == 0) {
                sendText(Constant.welcome)
                userId = DBUtils.getInstance().insertUserIfNotExist(getUser(scope.peer.id).phoneContactRecords.toString())
            }
            current_qu = 0
            goto("select")

        }})

        expectInput("select") {

            received {
                // MessageModel.getInstance().set(getUser(scope.peer.id).username.toString(), getUser(scope.peer.id).name(), getUser(scope.peer.id).phoneContactRecords.toString(), text , null)

                if (text == "مسابقه") {
                    questions = DBUtils.getInstance().getQuestionsModels()
                    if(questions != null && (questions as List<QuestionsModel>).size != 0){
                        que_size = (questions as List<QuestionsModel>).size
                        sendText(Constant.competitionIsReady)
                        goto("/شروع")
                    }
                    else{
                        sendText(Constant.expiredcompetition)
                        goto("/start")
                    }
                }

                else if(text == "کارنامه"){
                    goto("/کارنامه")
                }
                else {
                    goto("/start")
                    }
                    //sendText("ازمون به پایان رسید")



            }
            validate {
                if ( text.equals("مسابقه") || text.equals("کارنامه")) {
                    return@validate true
                }
                else {
                    sendText(Constant.invalidInput)
                    return@validate false
                }

            }
        }

        command("/شروع") {

            before {
                if (current_qu == 0) {
                    sendText(Constant.LetStart)
                    sendText(Constant.cancelCompetition)


                }
                if (current_qu < que_size ) {
                    sendText(questions!!.get(current_qu).question.text)

                    for (item in questions!!.get(current_qu).optionses) {
                        questions!!.get(current_qu).optionses.indexOf(item)+1
                        sendText("["+item.text+"]"+"(send:"+(questions!!.get(current_qu).optionses.indexOf(item)+1).toString()+")")


                    }

                    goto("ask_text")

                }


            }
        }
        expectInput("ask_text") {

            received {
               // MessageModel.getInstance().set(getUser(scope.peer.id).username.toString(), getUser(scope.peer.id).name(), getUser(scope.peer.id).phoneContactRecords.toString(), text , null)

                if (text == "/خروج") {
                    var current_date:Date = Utils.getDate()
                    var user = findUser("+989212785372")
                    var a = OutPeer(PeerType.PRIVATE, user!!.id(), user.hashCode().toLong())
                    sendText(a, getUser(scope.peer.id).phoneContactRecords.toString() +"نمره")
                    sendText(Constant.finishedCompetition)

                    gotoParent()

                }
                else {
                    var current_date:Date = Utils.getDate()


                    if (current_qu < que_size-1) {
                        if(current_date < questions!!.get(0).series.start_time || current_date > questions!!.get(0).series.expire_time){
                            sendText(Constant.expiredcompetition)
                            current_qu = 0
                            goto("/start")
                        }else{
                            DBUtils.getInstance().insertOrUpdateUserAnswer(userId, (questions as List<QuestionsModel>).get(current_qu).series, (questions as List<QuestionsModel>).get(current_qu).question, (questions as List<QuestionsModel>).get(current_qu).optionses.get(text.toInt()-1))
                            current_qu++
                            goto("/شروع")
                        }
                    }
                    //sendText("ازمون به پایان رسید")
                    else {
                        if(current_date < questions!!.get(0).series.start_time || current_date > questions!!.get(0).series.expire_time){
                            sendText(Constant.expiredcompetition)
                            current_qu = 0
                            goto("/start")
                        }else{
                            DBUtils.getInstance().insertOrUpdateUserAnswer(userId, (questions as List<QuestionsModel>).get(current_qu).series, (questions as List<QuestionsModel>).get(current_qu).question, (questions as List<QuestionsModel>).get(current_qu).optionses.get(text.toInt()-1))
                            var x = findUser("+989212785372")
                            var a = OutPeer(PeerType.PRIVATE, x!!.id(), x.accessHash().toLong())
                            sendText(Constant.finishedCompetition)
                            current_qu= 0
                            sendText(a, getUser(scope.peer.id).name().toString()+ "ازمون خود را به پایان رساند"+"نمره")
                            goto("/start")
                        }




                    }
                }

            }
            validate {
                if (text.equals("3") || text.equals("2") || text.equals("1") || text.equals("۳") || text.equals("۱") || text.equals("۲") || text.equals("/خروج")) {
                    sendText(Constant.thanks)
                    return@validate true
                }
                else {
                    sendText(Constant.invalidInput)
                    return@validate false
                }

            }
        }

        command("/کارنامه") {

            before {
                user_series = DBUtils.getInstance().getAllUserSeries(userId)
                if(user_series != null && (user_series as List<UsersAnswers>).size !=0) {
                    sendText(Constant.selectWorkbook)
                    for (item in user_series) {
                        sendText(item.date.toString())

                    }
                }
                if (current_qu < que_size ) {
                    sendText(questions!!.get(current_qu).question.text)

                    for (item in questions!!.get(current_qu).optionses) {
                        questions!!.get(current_qu).optionses.indexOf(item)+1
                        sendText("["+item.text+"]"+"(send:"+(questions!!.get(current_qu).optionses.indexOf(item)+1).toString()+")")


                    }

                    goto("ask_text")

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