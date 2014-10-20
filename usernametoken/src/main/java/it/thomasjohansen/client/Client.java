package it.thomasjohansen.client;

import it.thomasjohansen.hello_world_soap_http.Greeter;
import it.thomasjohansen.hello_world_soap_http.SOAPService;
import org.apache.cxf.Bus;
import org.apache.cxf.BusFactory;
import org.apache.cxf.bus.spring.SpringBusFactory;

import javax.xml.namespace.QName;
import java.io.File;
import java.net.URL;

public class Client {
    private static final QName SERVICE_NAME
            = new QName("http://thomasjohansen.it/hello_world_soap_http", "SOAPService");

    private static final QName PORT_NAME =
            new QName("http://thomasjohansen.it/hello_world_soap_http", "SoapPort");


    private Client() {
    }

    public static void main(String args[]) throws Exception {

        if (args.length < 2) {
            System.out.println("please specify wsdl and configuration file");
            System.exit(1);
        }

        URL wsdlURL;
        File wsdlFile = new File(args[0]);
        if (wsdlFile.exists()) {
            wsdlURL = wsdlFile.toURI().toURL();
        } else {
            wsdlURL = new URL(args[0]);
        }

        SpringBusFactory bf = new SpringBusFactory();
        URL busURL;
        File busFile = new File(args[1]);
        if (busFile.exists()) {
            busURL = busFile.toURI().toURL();
        } else {
            busURL = new URL(args[1]);
        }

        Bus bus = bf.createBus(busFile.toString());
        BusFactory.setDefaultBus(bus);

        SOAPService ss = new SOAPService(wsdlURL, SERVICE_NAME);
        Greeter port = ss.getPort(PORT_NAME, Greeter.class);

        try {
            String resp = port.greetMe(System.getProperty("user.name"));
            System.out.println(resp);
        } catch (Exception e) {
            System.out.println("Invocation failed with the following: " + e.getCause());
            System.out.println();
        }
        System.exit(0);
    }

}
