package forest.detector.bot;

import lombok.SneakyThrows;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.DeleteMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

import javax.sql.DataSource;

public class TelegramBot extends TelegramLongPollingBot {
    private PropertyBot property = new PropertyBot();
    private KeyHandler handler = new KeyHandler();
    private String msgID = "";
 //   private BotDB botDB = new BotDB();

    private static Logger log = LoggerFactory.getLogger(TelegramBot.class);

    @SneakyThrows
    @Override
    public void onUpdateReceived(Update update) {
        if(update.hasMessage()) {
            if(update.getMessage().getText() != null || update.getMessage().getText().equals("/bot") || update.getMessage().getText().equals("/key")) {
              //  System.out.println(botDB.getTicket());
                sendKeyboard(update.getMessage().getChatId().toString(), handler.getMenuName());
            }
        } else if(update.hasCallbackQuery()){
            if(handler.ikeyBoardHandler(update.getCallbackQuery()) != null) {
                sendMsg(update.getCallbackQuery().getMessage().getChatId().toString(), handler.ikeyBoardHandler(update.getCallbackQuery()));
            } else {
                int msgID = Integer.parseInt(update.getCallbackQuery().getMessage().getMessageId().toString());
                execute(new DeleteMessage(update.getCallbackQuery().getMessage().getChatId().toString(), msgID));
                sendKeyboard(update.getCallbackQuery().getMessage().getChatId().toString(), handler.getMenuName());
            }
        }
    }

    @SneakyThrows
    public synchronized void sendMsg(String chatID, String msg){
        var sendMessage = new SendMessage();
        sendMessage.enableMarkdown(true);
        sendMessage.setChatId(chatID);
        sendMessage.setText(msg);
        execute(sendMessage);
    }

    @SneakyThrows
    public synchronized void sendKeyboard(String chatID, String msg){
        SendMessage sendMessage = new SendMessage();
        sendMessage.enableMarkdown(true);
        sendMessage.setChatId(chatID);
        sendMessage.setText(msg);
        sendMessage.setReplyMarkup(handler.getNewKeyBoard());
        execute(sendMessage);
    }

    // Send msg to Forester channel
    public void groupAlert(String text){
        sendMsg(property.getForestChannelID(), text);
    }


    public String getBotUsername() {
        return property.getBotName();
    }

    public String getBotToken() {
        return property.getToken();
    }
}
