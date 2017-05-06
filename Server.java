import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.util.ArrayList;
import java.util.Collection;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;


public class Server {
	static String sharedResponse = "";
    static boolean gotMessageFlag = false;
	public static void main(String[] args) throws Exception	
	{
		
			HttpServer server = HttpServer.create(new InetSocketAddress(8000),0);
					// create a context to get the request to display the results
			server.createContext("/displayresults", new DisplayHandler());
	
			        // create a context to get the request for the POST
			server.createContext("/sendresults",new PostHandler());
			server.setExecutor(null); // creates a default executor
	
			        // get it going
			System.out.println("Starting Server...");
			server.start();
	}
	static void createHTML(){
   	 String url = "";
    }
	static class DisplayHandler implements HttpHandler {
        public void handle(HttpExchange t) throws IOException {

            /*
             * HTML is generated here. 
             */

            String style = "";
            BufferedReader br = new BufferedReader(new FileReader("style.css"));
            while(br.ready())
            {
                style += br.readLine();
            }
            br.close();

            //<div class="floating-box">
            String response = "<!DOCTYPE html><html><head><style>" + style + "</style></head>";
            //create a table
            response += "\n<table>\n<tr>\n<th>Number:<br></th>\n<th>Time:<br></th>\n";//<th>Bib Number:<br></th>\n<th>Time (in ms):<br></th>\n"; 

            Gson g = new Gson();
            // set up the header
            System.out.println(response);
            try {
                if (!sharedResponse.isEmpty()) {
                    //System.out.println(response);
                    // Convert sharedResponse from Json here
                    ArrayList<Athlete> fromJson = g.fromJson(sharedResponse,
                            new TypeToken<Collection<Athlete>>() {
                            }.getType());
                    //Collections.sort((List<T>) fromJson);

                    for (Athlete r : fromJson) {
                        response += "<tr>\n<td>" + (r.getName()).split(" ")[0] + "</td>\n<td>" + (r.getTime()).split(" ")[1] + "</td>\n</tr>\n"; //+ "</td>\n<td>" + r.getBib() + "</td>\n<td>" + r.getTime() + "</td>\n</tr>\n";
                    }
                }
            } catch (JsonSyntaxException e) {
                e.printStackTrace();
            }
            System.out.println(response);
            // write out the response
            response += "\n</table>\n</html>";
            t.sendResponseHeaders(200, response.length());
            OutputStream os = t.getResponseBody();
            os.write(response.getBytes());
            os.close();
        }
    }
	static class PostHandler implements HttpHandler {
        public void handle(HttpExchange transmission) throws IOException {

            //  shared data that is used with other handlers
            sharedResponse = "";

            // set up a stream to read the body of the request
            InputStream inputStr = transmission.getRequestBody();

            // set up a stream to write out the body of the response
            OutputStream outputStream = transmission.getResponseBody();

            // string to hold the result of reading in the request
            StringBuilder sb = new StringBuilder();

            // read the characters from the request byte by byte and build up the sharedResponse
            int nextChar = inputStr.read();
            while (nextChar > -1) {
                sb=sb.append((char)nextChar);
                nextChar=inputStr.read();
            }

            // create our response String to use in other handler
            sharedResponse = sharedResponse+sb.toString();

            // respond to the POST with ROGER
            String postResponse = "Racer Received ";

            System.out.println("response: " + sharedResponse);

            // assume that stuff works all the time
            transmission.sendResponseHeaders(300, postResponse.length());

            // write it and return it
            outputStream.write(postResponse.getBytes());

            outputStream.close();
        }
    }
		
}
