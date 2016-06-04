package im.actor.bots

import im.actor.bots.framework.HookData
import im.actor.bots.framework.MagicOverlord
import im.actor.bots.framework.MagicOverlordScope
import org.json.JSONObject

/**
 * Created by masood on 5/11/16.
 */
class Overload_class(scope: MagicOverlordScope) : MagicOverlord(scope) {
    override fun onWebHookReceived(hook: HookData) {
        saveState()

    }


}