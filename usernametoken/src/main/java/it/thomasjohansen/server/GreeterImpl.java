package it.thomasjohansen.server;

import java.util.logging.Logger;
import it.thomasjohansen.hello_world_soap_http.Greeter;
import javax.jws.WebService;

@WebService(
        name = "Greeter",
        serviceName = "SOAPService",
        targetNamespace = "http://thomasjohansen.it/hello_world_soap_http"
)
public class GreeterImpl implements Greeter {

    public String greetMe(String me) {
        return "Hello " + me;
    }
}
