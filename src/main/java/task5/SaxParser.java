package task5;

import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;
import org.xml.sax.SAXException;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.util.ArrayList;
import java.util.List;

public class SaxParser {

    public static List<Device> parse(String filePath) {

        List<Device> devices = new ArrayList<>();

        try {

            SAXParserFactory factory =
                    SAXParserFactory.newInstance();

            SAXParser parser =
                    factory.newSAXParser();


            DeviceHandler handler =
                    new DeviceHandler();


            parser.parse(
                    filePath,
                    handler
            );


            devices = handler.getDevices();

        } catch (Exception e) {

            System.out.println(
                    "Помилка SAX-парсингу:"
            );

            e.printStackTrace();
        }

        return devices;
    }


    private static class DeviceHandler
            extends DefaultHandler {

        private final List<Device> devices =
                new ArrayList<>();

        private Device currentDevice;

        private String currentElement;

        private StringBuilder text =
                new StringBuilder();


        private final List<String> ports =
                new ArrayList<>();


        @Override
        public void startElement(
                String uri,
                String localName,
                String qName,
                Attributes attributes) {

            currentElement = qName;

            text.setLength(0);


            if (qName.equals("Component")) {

                currentDevice =
                        new Device();

                ports.clear();
            }
        }


        @Override
        public void characters(
                char[] ch,
                int start,
                int length) {

            text.append(
                    ch,
                    start,
                    length
            );
        }


        @Override
        public void endElement(
                String uri,
                String localName,
                String qName)
                throws SAXException {

            String value =
                    text.toString().trim();


            if (currentDevice != null) {

                switch (qName) {

                    case "Name":
                        currentDevice.setName(value);
                        break;

                    case "Origin":
                        currentDevice.setOrigin(value);
                        break;

                    case "Price":
                        currentDevice.setPrice(
                                Double.parseDouble(value)
                        );
                        break;

                    case "Peripheral":
                        currentDevice.setPeripheral(
                                Boolean.parseBoolean(value)
                        );
                        break;

                    case "PowerConsumption":
                        currentDevice.setPowerConsumption(
                                Integer.parseInt(value)
                        );
                        break;

                    case "Cooler":
                        currentDevice.setCooler(
                                Boolean.parseBoolean(value)
                        );
                        break;

                    case "Group":
                        currentDevice.setGroup(value);
                        break;

                    case "Port":
                        ports.add(value);
                        break;

                    case "Critical":
                        currentDevice.setCritical(
                                Boolean.parseBoolean(value)
                        );
                        break;

                    case "Component":

                        currentDevice.setPorts(
                                new ArrayList<>(ports)
                        );

                        devices.add(currentDevice);

                        currentDevice = null;

                        break;
                }
            }

            currentElement = "";
        }


        public List<Device> getDevices() {
            return devices;
        }
    }
}