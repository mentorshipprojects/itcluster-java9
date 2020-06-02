package forest.detector.bot;

import forest.detector.bot.repository.BotRepository;
import lombok.SneakyThrows;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;

import java.util.HashMap;
import java.util.Map;

public class KeyHandler {
    private KeyButtonBuilder mb = new KeyButtonBuilder();
    private String menuName = "Головне меню";
    private String data = "";
    private String navigationLink = "botMainMenu.properties";
    private KeyBoardBuilder inlineKeyBoard = new KeyBoardBuilder(mb.SetMenu(navigationLink));
    private BotRepository botRepository = new BotRepository();

    // menu title generation
    public void setMenuName(String navigationLink) {
        switch (navigationLink) {
            case "botMainMenu.properties":
                menuName = "Головне меню";
                break;
            case "botFilterMenu.properties":
                menuName = "Фільтри";
                break;
            case "botTicketMenu.properties":
                menuName = "Білети на вирубку";
                break;
        }
    }

    public String getMenuName() {
        return menuName;
    }

    @SneakyThrows
    public synchronized String ikeyBoardHandler(CallbackQuery callbackQuery){
        switch (callbackQuery.getData()) {
            case "get5":
                data = "LAST 5 TICKETS:";
                break;
            case "getLast":
                return botRepository.getLastTicket();
            case "getByForestry":

                break;
            default:
            inlineKeyBoard.setInlineKeyboardMarkup(mb.SetMenu(callbackQuery.getData()));
            navigationLink = callbackQuery.getData();
            setMenuName(navigationLink);
            data = null;
        }
        return data;
    }

    public InlineKeyboardMarkup getNewKeyBoard(){
        return inlineKeyBoard.getInlineKeyboardMarkup();
    }
}
