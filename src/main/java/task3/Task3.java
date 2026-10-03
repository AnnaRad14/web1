package task3;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Task3 {

    public static void main(String[] args) {

        try {
            String xml = Files.readString(
                    Path.of("src/main/resources/task3.xml")
            );

            Pattern pattern = Pattern.compile(
                    "<Name>(.*?)</Name>"
            );

            Matcher matcher = pattern.matcher(xml);

            System.out.println("Names:");

            while (matcher.find()) {
                System.out.println(matcher.group(1));
            }

        } catch (IOException e) {
            System.out.println("Помилка при читанні XML-файлу");
            e.printStackTrace();
        }
    }
}