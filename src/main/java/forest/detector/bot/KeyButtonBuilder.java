package forest.detector.bot;

import lombok.SneakyThrows;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class KeyButtonBuilder {
    private Map keydata = new HashMap();

    @SneakyThrows  // parse property to Map and return
    public Map SetMenu(String link){
        Properties prop = new Properties();
        final ClassLoader loader = getClass().getClassLoader();
            try (InputStream config = loader.getResourceAsStream(link)) {
                prop.load(config);
            }
                keydata.clear();
                keydata = prop;

        for (Object s : keydata.keySet()) {
            System.out.println(keydata.toString());
        }
            return keydata;
    }
}
