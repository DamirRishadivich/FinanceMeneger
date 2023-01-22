package org.example;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class RequestService {

    /** Метод сохраняет полученные запросы от клиента в файл data.bin в виде строки */
    public static void saveToLogFile (String request) {
        try (PrintWriter writer = new PrintWriter(new FileWriter("data.bin", true))) {
            writer.println(request);
        } catch (IOException e) {
            e.getMessage();
        }
    }
    /**
     * Метод возвращает строку с максимальной категорией, взаимодействует с классом MaxCategories
     */
    public static JSONObject JSONResponse() { // Это ужас, а не код!!!!
        JSONObject obj = new JSONObject();
        JSONArray list = new JSONArray();

        try {
            list.add("category: " + MaxCategories.maxCategories().getKey());
            list.add("sum: " + MaxCategories.maxCategories().getValue());
        } catch (Exception ex) {
            list.add("За весь период нет покупок");
        }

        obj.put("maxCategory", list);

        return obj;
    }
}
