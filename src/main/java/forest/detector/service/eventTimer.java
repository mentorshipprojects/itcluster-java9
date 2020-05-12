package forest.detector.service;
import forest.detector.bot.TelegramBot;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class eventTimer {
    TelegramBot bot = new TelegramBot();

    public void UpdateTimer() {
        TimerTask repeatedTask = new TimerTask() {
            public void run() {
                System.out.println("Task performed on " + new Date());

                // here must be code for update data base and telegram bot alert
                // if we have new info

            }
        };
        Timer timer = new Timer("Timer");

        long delay = 1000L;
        long period = 1000L;// *  60L *  60L *  24L;  set delay for timer (24 hours like this set)
        timer.scheduleAtFixedRate(repeatedTask, delay, period);
    }

}
