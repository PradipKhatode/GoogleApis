package resources;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.config.RestAssuredConfig;
import io.restassured.config.SSLConfig;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

import java.io.*;
import java.util.Properties;

public class Utils {
    static private RequestSpecification req;

    public RequestSpecification requestSpecification() throws IOException {

        if (req==null) // for call the requestSpecification once for multiple runs as well.
        {
            PrintStream log = new PrintStream(new FileOutputStream("logging.txt"));
            // Disable SSL validation
            RestAssured.config = RestAssuredConfig.config().sslConfig(SSLConfig.sslConfig().relaxedHTTPSValidation());
            // Using Spec builder for Request and response
            req = new RequestSpecBuilder().
                    setBaseUri(getGlobalValue("baseUrl"))
                    .addQueryParam("key", "qaclick123")
                    .addFilter(RequestLoggingFilter.logRequestTo(log)) // Logging the logs of Request
                    .addFilter(ResponseLoggingFilter.logResponseTo(log)) //Logging the Logs of Response
                    .setContentType(ContentType.JSON).build();
            return req;
        }
        return req;
    }

    public static String getGlobalValue(String key) throws IOException {
        Properties prop =new Properties();
        FileInputStream fis= new FileInputStream("D:\\RestAPI\\GoogleApiTest\\GoogleApis\\src\\test\\java\\resources\\global.properties");
        prop.load(fis);
        return  prop.getProperty(key);

    }
}
