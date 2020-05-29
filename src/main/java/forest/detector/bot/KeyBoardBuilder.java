package forest.detector.bot;

import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class KeyBoardBuilder {
    private InlineKeyboardMarkup inlineKeyboardMarkup;

    public KeyBoardBuilder(Map<String,String> menu){
        setInlineKeyboardMarkup(menu);
    }

    //
    public void setInlineKeyboardMarkup(Map<String,String> menu){
        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();

        var rowList = new ArrayList<InlineKeyboardButton>();
        var rowList2 = new ArrayList<InlineKeyboardButton>();
        for(Map.Entry<String, String> item : menu.entrySet()){
            if(rowList.size() < 2) {
                rowList.add(new InlineKeyboardButton(item.getKey()).setCallbackData(item.getValue()));
            }else{
                rowList2.add(new InlineKeyboardButton(item.getKey()).setCallbackData(item.getValue()));
            }

        }
        if(rowList2.size() != 0){

            inlineKeyboardMarkup.setKeyboard(List.of(rowList, rowList2));
        }else {
            inlineKeyboardMarkup.setKeyboard(Collections.singletonList(rowList));
        }
        this.inlineKeyboardMarkup = inlineKeyboardMarkup;
    }

    public InlineKeyboardMarkup getInlineKeyboardMarkup() {
        return inlineKeyboardMarkup;
    }
}

