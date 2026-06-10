package task4;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class ExchangeRateAPI {

    private static final String API_KEY = "61fb62b7e5bbf7e107406b54";

    public static double getRate(String from, String to) {

        try {

            String apiUrl = "https://v6.exchangerate-api.com/v6/"
                    + API_KEY + "/latest/" + from;

            URL url = new URL(apiUrl);

            HttpURLConnection con =
                    (HttpURLConnection) url.openConnection();

            con.setRequestMethod("GET");

            BufferedReader br = new BufferedReader(
                    new InputStreamReader(con.getInputStream()));

            StringBuilder response = new StringBuilder();

            String line;

            while ((line = br.readLine()) != null) {
                response.append(line);
            }

            br.close();

            String json = response.toString();

            String search = "\"" + to + "\":";

            int start = json.indexOf(search);

            if (start == -1) {
                return -1;
            }

            start += search.length();

            int end = start;

            while (end < json.length() &&
                    (Character.isDigit(json.charAt(end))
                            || json.charAt(end) == '.')) {

                end++;
            }

            return Double.parseDouble(json.substring(start, end));

        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }
}