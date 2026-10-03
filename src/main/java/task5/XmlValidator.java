package task5;

import javax.xml.XMLConstants;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import java.io.File;

public class XmlValidator {

    public static boolean validate(
            String xmlPath,
            String xsdPath) {

        try {

            SchemaFactory factory =
                    SchemaFactory.newInstance(
                            XMLConstants.W3C_XML_SCHEMA_NS_URI
                    );


            Schema schema =
                    factory.newSchema(
                            new File(xsdPath)
                    );


            schema.newValidator().validate(
                    new StreamSource(
                            new File(xmlPath)
                    )
            );


            return true;

        } catch (Exception e) {

            System.out.println(
                    "XML не пройшов валідацію:"
            );

            System.out.println(
                    e.getMessage()
            );

            return false;
        }
    }
}