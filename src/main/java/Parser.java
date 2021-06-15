import java.io.IOException;
import java.net.URL;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

public class Parser {

    private static Document getPage() throws IOException {
        String url = "http://www.cbr.ru/scripts/XML_daily.asp";
        Document page = Jsoup.parse(new URL(url), 3000);
        return page;
    }

    public static void main(String[] args) throws IOException {
        Document page = getPage();
        Elements valuteJpy = page.select("Valute[ID=\"R01820\"]");
        Elements value = valuteJpy.select("Value");
        double valueRus = 0;

        System.out.println("Японские иены\tРоссийские рубли");
        try{
            Double valueDouble = new Double(value.text().replace(',','.'));
            valueRus = 1 / valueDouble;
            System.out.println("1\t\t\t\t" + valueRus);
        } catch (NumberFormatException e){
            System.out.println("Неверный формат строки");
        }
    }
}
