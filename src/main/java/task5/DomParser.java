package task5;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class DomParser {

    public static List<Device> parse(String filePath) {

        List<Device> devices = new ArrayList<>();

        try {

            DocumentBuilderFactory factory =
                    DocumentBuilderFactory.newInstance();

            DocumentBuilder builder =
                    factory.newDocumentBuilder();

            Document document =
                    builder.parse(new File(filePath));

            document.getDocumentElement().normalize();

            NodeList componentList =
                    document.getElementsByTagName("Component");


            for (int i = 0; i < componentList.getLength(); i++) {

                Node node = componentList.item(i);

                if (node.getNodeType() == Node.ELEMENT_NODE) {

                    Element component = (Element) node;

                    Device device = new Device();

                    device.setName(
                            getText(component, "Name")
                    );

                    device.setOrigin(
                            getText(component, "Origin")
                    );

                    device.setPrice(
                            Double.parseDouble(
                                    getText(component, "Price")
                            )
                    );


                    Element type =
                            (Element) component
                                    .getElementsByTagName("Type")
                                    .item(0);


                    device.setPeripheral(
                            Boolean.parseBoolean(
                                    getText(type, "Peripheral")
                            )
                    );

                    device.setPowerConsumption(
                            Integer.parseInt(
                                    getText(type, "PowerConsumption")
                            )
                    );

                    device.setCooler(
                            Boolean.parseBoolean(
                                    getText(type, "Cooler")
                            )
                    );

                    device.setGroup(
                            getText(type, "Group")
                    );


                    NodeList ports =
                            type.getElementsByTagName("Port");

                    List<String> portList =
                            new ArrayList<>();

                    for (int j = 0; j < ports.getLength(); j++) {
                        portList.add(
                                ports.item(j).getTextContent()
                        );
                    }

                    device.setPorts(portList);


                    device.setCritical(
                            Boolean.parseBoolean(
                                    getText(component, "Critical")
                            )
                    );


                    devices.add(device);
                }
            }

        } catch (Exception e) {

            System.out.println(
                    "Помилка DOM-парсингу:"
            );

            e.printStackTrace();
        }

        return devices;
    }


    private static String getText(
            Element element,
            String tagName) {

        return element
                .getElementsByTagName(tagName)
                .item(0)
                .getTextContent()
                .trim();
    }
}