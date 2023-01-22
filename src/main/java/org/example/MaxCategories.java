package org.example;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.*;

public class MaxCategories {
    /**
     * Метод проходит по файлу data.bin, превращает каждую строку в json объект
     * В карту помещает "ключ - категория" и "значение - сумма", прибавляет если категория повторяется то,
     * возвращает содержащую категорию с максимальным значением.
     */
    public static Map.Entry<String,Long> maxCategories() {
        HashMap<String, Long> map = new HashMap<>();
        JSONParser parser = new JSONParser();
        try (BufferedReader reader = new BufferedReader(new FileReader("data.bin"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                Object obj = parser.parse(line);
                JSONObject jsonObject = (JSONObject) obj;
                String cat = CategoriesGetter.getCategories((String) jsonObject.get("title"));
                long amount = (long) jsonObject.get("sum");
                countingMap(map, cat, amount);
            }
        } catch (Exception e) {
            e.getMessage();
        }
        return maximumCalc(map);
    }

    public static void countingMap(HashMap<String, Long> map, String cat, Long amount) {
        if (map.containsKey(cat)) {
            map.put(cat, map.get(cat) + amount);
        } else {
            map.put(cat, amount);
        }
    }
    public static Map.Entry<String, Long> maximumCalc(HashMap<String, Long> map) {
        Map.Entry<String, Long> maxEntry = null;
        for (Map.Entry<String, Long> entry : map.entrySet()) {
            if (maxEntry == null || entry.getValue() > maxEntry.getValue()) {
                maxEntry = entry;
            }
        }
        return maxEntry;
    }

}
