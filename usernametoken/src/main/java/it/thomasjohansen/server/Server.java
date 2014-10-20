package it.thomasjohansen.server;

import org.apache.cxf.Bus;
import org.apache.cxf.BusFactory;
import org.apache.cxf.bus.spring.SpringBusFactory;

import java.io.File;
import java.net.URL;

public class Server {

    private Server(URL busURL) throws Exception {
        SpringBusFactory bf = new SpringBusFactory();
        Bus bus = bf.createBus(busURL.toString());
        BusFactory.setDefaultBus(bus);
    }

    public static void main(String args[]) throws Exception {
        if (args.length == 0) {
            System.out.println("please specify configuration file");
            System.exit(1);
        }
        URL busURL;
        File busFile = new File(args[0]);
        if (busFile.exists()) {
            busURL = busFile.toURI().toURL();
        } else {
            busURL = new URL(args[0]);
        }
        new Server(busURL);
        System.out.println("Server is running (Ctrl-C to stop)...");
    }

}
