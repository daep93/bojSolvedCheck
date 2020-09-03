
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class Crawler {

    public static void main(String[] args) throws NumberFormatException, IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        String [] users = new String[] {"eoy1313   ", "choihwan2 ", "gse123    ", "ckdhyeon95", "eespacio  ", "hhan43    ", "coldbrew  ", "rlawhdtjd9","nahyun0128"};
        String URL = "https://www.acmicpc.net/user/";
        int input = Integer.parseInt(br.readLine());
        for (String user : users) {
            Connection conn = Jsoup.connect(URL + user);

            try {
                Document doc = conn.get();
                Elements span = doc.getElementsByClass("problem_number");
                boolean success = false;
                for (Element element : span) {
                    int number = Integer.parseInt(element.getElementsByTag("a").text());
                    if (number == input) {
                        success = true;
                        break;
                    }
                }
                if (success) sb.append(user).append(": solved\n");
                else sb.append(user).append(": yet\n");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        bw.write(sb.toString());
        bw.flush();
        bw.close();

    }

}
