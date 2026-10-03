package task5;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamReader;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;

public class StaxParser {

    public static List<Device> parse(String filePath) {

        List<Device> devices =
                new ArrayList<>();

        try {

            XMLInputFactory factory =
                    XMLInputFactory.newInstance();

            XMLStreamReader reader =
                    factory.createXMLStreamReader(
                            new FileInputStream(filePath)
                    );


            Device currentDevice = null;

            List<String> ports =
                    new ArrayList<>();


            while (reader.hasNext()) {

                int event =
                        reader.next();


                if (event ==
                        XMLStreamConstants.START_ELEMENT) {

                    String name =
                            reader.getLocalName();


                    if (name.equals("Component")) {

                        currentDevice =
                                new Device();

                        ports.clear();
                    }


                    else if (currentDevice != null) {

                        switch (name) {

                            case "Name":

                                currentDevice.setName(
                                        reader.getElementText()
                                );

                                break;


                            case "Origin":

                                currentDevice.setOrigin(
                                        reader.getElementText()
                                );

                                break;


                            case "Price":

                                currentDevice.setPrice(
                                        Double.parseDouble(
                                                reader.getElementText()
                                        )
                                );

                                break;


                            case "Peripheral":

                                currentDevice.setPeripheral(
                                        Boolean.parseBoolean(
                                                reader.getElementText()
                                        )
                                );

                                break;


                            case "PowerConsumption":

                                currentDevice.setPowerConsumption(
                                        Integer.parseInt(
                                                reader.getElementText()
                                        )
                                );

                                break;


                            case "Cooler":

                                currentDevice.setCooler(
                                        Boolean.parseBoolean(
                                                reader.getElementText()
                                        )
                                );

                                break;


                            case "Group":

                                currentDevice.setGroup(
                                        reader.getElementText()
                                );

                                break;


                            case "Port":

                                ports.add(
                                        reader.getElementText()
                                );

                                break;


                            case "Critical":

                                currentDevice.setCritical(
                                        Boolean.parseBoolean(
                                                reader.getElementText()
                                        )
                                );

                                break;
                        }
                    }
                }


                else if (
                        event ==
                                XMLStreamConstants.END_ELEMENT
                ) {

                    String name =
                            reader.getLocalName();


                    if (name.equals("Component")
                            && currentDevice != null) {

                        currentDevice.setPorts(
                                new ArrayList<>(ports)
                        );

                        devices.add(
                                currentDevice
                        );

                        currentDevice = null;
                    }
                }
            }


            reader.close();

        } catch (Exception e) {

            System.out.println(
                    "Помилка StAX-парсингу:"
            );

            e.printStackTrace();
        }

        return devices;
    }
}