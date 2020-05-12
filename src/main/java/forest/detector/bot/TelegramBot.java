package forest.detector.bot;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public class TelegramBot extends TelegramLongPollingBot {

    public void onUpdateReceived(Update update) {      // logic for test
        Message message = update.getMessage();
        if(message != null && message.hasText()){
            String text = message.getText();
            if ("/help".equals(text)) {
                answerMsg(message, "What can i help?");
            } else if("/test".equals(text)) {
                sendMsg("941295905", "it`s work");
            } else {
                answerMsg(message, " hello ");
            }
        }
    }


    // here will be functional mass mailing


    public void sendMsg(String id, String text){       // send message to chat ID
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(id);
        sendMessage.setText(text);
        try{
            execute(sendMessage);
        } catch (TelegramApiException exception){
            exception.printStackTrace();
        }
    }

    private void answerMsg(Message message, String text) {  // answer to msg for bot in chat
        SendMessage sendMessage = new SendMessage();
        sendMessage.enableMarkdown(true);
        sendMessage.setChatId(message.getChatId().toString());
        sendMessage.setReplyToMessageId(message.getMessageId());
        sendMessage.setText(text);
        try{
            execute(sendMessage);
        } catch (TelegramApiException exception){
            exception.printStackTrace();
        }
    }

    public String getBotUsername() {
        return "IT-Cluster-Java9-project";
    }

    public String getBotToken() {
        return "1262037289:AAHOtiJ2cHchTai7ZITRWQQDN0qY6PxVj6g";
    }
}
