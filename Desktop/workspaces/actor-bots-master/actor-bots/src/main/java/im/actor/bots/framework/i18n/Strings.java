package im.actor.bots.framework.i18n;

import java.util.Random;

public class Strings {
    static int i =-1;
    public static final String[] UNKNOWN_MESSAGES = {
            "سلام کافیست برای اغاز مسابقه برروی [شروع](send:/شروع) کلیک کنید",
            "درخواست شما نامفهوم است لطفا دوباره درخواست خود را وارد کنید",
            "متوجه منظور شما نمیشوم",
            "میشه دوباره بگی چی میگی",
            "داداش کلا منظورتو نمیفهمم بیخیال شو","گفتم که بیخیال شو لطفا",
    };
    private static Random random = new Random();
    public static int next(){
        i++;
        if (i == 5){
            i=0;
        }

        return i;
    }
    public static String unknown() {
       return UNKNOWN_MESSAGES[next()];
        // return UNKNOWN_MESSAGES[random.nextInt(UNKNOWN_MESSAGES.length)];
    }

}
