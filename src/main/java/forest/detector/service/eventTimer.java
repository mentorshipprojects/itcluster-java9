package forest.detector.service;
import forest.detector.bot.TelegramBot;

import javax.sql.DataSource;
import java.io.IOException;
import java.text.ParseException;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class eventTimer {
    TelegramBot bot = new TelegramBot();
    private HtmlParser parser;
    final DataSource dataSource;

    public eventTimer(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public void UpdateTimer() {
        TimerTask repeatedTask = new TimerTask() {
            public void run() {
                System.out.println("Task performed on " + new Date());

//                parser = new HtmlParser(dataSource);
//                try {
//                    int[] c = parser.ticketParser();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                } catch (ParseException e) {
//                    e.printStackTrace();
//                }
//                // here must be code for update data base and telegram bot alert
//                // if we have new info.
//                //bot.groupAlert("test");
            }
        };
        Timer timer = new Timer("Timer");

        long delay = 1000L;
        long period = 1000L *  60L *  60L *  24L; //  set delay for timer (24 hours like this set)
        timer.scheduleAtFixedRate(repeatedTask, delay, period);
    }

}
