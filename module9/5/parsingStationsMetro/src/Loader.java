import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Loader {
    public static void main(String[] args){
        try {
            Document doc = Jsoup.connect("https://ru.wikipedia.org/wiki/%D0%A1%D0%BF%D0%B8%D1%81%D0%BE%D0%BA_%D1%81%D1%82%D0%B0%D0%BD%D1%86%D0%B8%D0%B9_%D0%9C%D0%BE%D1%81%D0%BA%D0%BE%D0%B2%D1%81%D0%BA%D0%BE%D0%B3%D0%BE_%D0%BC%D0%B5%D1%82%D1%80%D0%BE%D0%BF%D0%BE%D0%BB%D0%B8%D1%82%D0%B5%D0%BD%D0%B0").get();
            Element table = doc.select("table").get(3);
            Elements rows = table.select("tr");
           // rows.forEach(row -> System.out.println(row.select("td").get(0).text()));

            JSONObject metro = new JSONObject();
            JSONObject stations = new JSONObject();
            JSONObject lines = new JSONObject();
            metro.put("Stations", stations);
            metro.put("Lines", lines);


            System.out.println(rows.size());
            for (int i = 1; i < rows.size(); i++) {
                Element row = rows.get(i);
                Elements columns = row.select("td");
                String columnNumberLine = columns.get(0).getElementsByClass("sortkey").get(0).text();
                String columnNameLine = columns.get(0).attr("span[title]");
                String columnNameStation = columns.get(1).select("a").get(0).text();

                System.out.print(columnNumberLine);
                System.out.print(columnNameLine + " ");
                System.out.print(columnNameStation);
                System.out.println();
            }
            //stationsList.stream().collect(Collectors.groupingBy(Station::getNumber)).forEach((s, stations1) -> System.out.println(s + "  " + stations1));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
