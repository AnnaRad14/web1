package task5;

import javax.xml.transform.Source;
import javax.xml.transform.Result;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

import java.io.File;
import java.util.Collections;
import java.util.List;

public class Main {

    private static final String XML_FILE =
            "src/main/resources/device.xml";

    private static final String XSD_FILE =
            "src/main/resources/device.xsd";

    private static final String XSL_FILE =
            "src/main/resources/device.xsl";

    private static final String RESULT_FILE =
            "src/main/resources/critical.xml";


    public static void main(String[] args) {

        System.out.println(
                "===== XML + XSD + PARSERS ====="
        );


        // =========================================
        // 1. XML VALIDATION
        // =========================================

        System.out.println();
        System.out.println(
                "1. Перевірка XML через XSD:"
        );


        boolean valid =
                XmlValidator.validate(
                        XML_FILE,
                        XSD_FILE
                );


        if (valid) {

            System.out.println(
                    "XML успішно пройшов валідацію!"
            );

        } else {

            System.out.println(
                    "XML містить помилки!"
            );

            return;
        }


        // =========================================
        // 2. DOM
        // =========================================

        System.out.println();
        System.out.println(
                "2. DOM parser:"
        );


        List<Device> domDevices =
                DomParser.parse(XML_FILE);


        for (Device device : domDevices) {

            System.out.println(device);
        }


        // =========================================
        // 3. SAX
        // =========================================

        System.out.println();
        System.out.println(
                "3. SAX parser:"
        );


        List<Device> saxDevices =
                SaxParser.parse(XML_FILE);


        for (Device device : saxDevices) {

            System.out.println(device);
        }


        // =========================================
        // 4. StAX
        // =========================================

        System.out.println();
        System.out.println(
                "4. StAX parser:"
        );


        List<Device> staxDevices =
                StaxParser.parse(XML_FILE);


        for (Device device : staxDevices) {

            System.out.println(device);
        }


        // =========================================
        // 5. SORTING
        // =========================================

        System.out.println();
        System.out.println(
                "5. Сортування за ціною:"
        );


        Collections.sort(
                domDevices,
                new DeviceComparator()
        );


        for (Device device : domDevices) {

            System.out.println(
                    device.getName()
                            + " - "
                            + device.getPrice()
                            + " грн"
            );
        }


        // =========================================
        // 6. XSLT TRANSFORMATION
        // =========================================

        System.out.println();
        System.out.println(
                "6. XSLT перетворення:"
        );


        transformXml();


        System.out.println();
        System.out.println(
                "Результат збережено у:"
        );

        System.out.println(
                RESULT_FILE
        );
    }


    private static void transformXml() {

        try {

            TransformerFactory factory =
                    TransformerFactory.newInstance();


            Source xsl =
                    new StreamSource(
                            new File(XSL_FILE)
                    );


            Transformer transformer =
                    factory.newTransformer(xsl);


            Source xml =
                    new StreamSource(
                            new File(XML_FILE)
                    );


            Result result =
                    new StreamResult(
                            new File(RESULT_FILE)
                    );


            transformer.transform(
                    xml,
                    result
            );


        } catch (Exception e) {

            System.out.println(
                    "Помилка XSLT перетворення:"
            );

            e.printStackTrace();
        }
    }
}