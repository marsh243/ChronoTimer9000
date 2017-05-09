import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Scanner;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class ResultsServerProxy {
    public void add(ArrayList<Athlete> r) {
        try {
            //System.out.println("in the client");

            // Client will connect to this location
            URL site = new URL("http://localhost:8000/sendresults");
            HttpURLConnection conn = (HttpURLConnection) site.openConnection();

            // now create a POST request
            conn.setRequestMethod("POST");
            conn.setDoOutput(true);
            conn.setDoInput(true);
            DataOutputStream out = new DataOutputStream(conn.getOutputStream());

            // build a string that contains JSON from console
            String content = "";
            content = getJSON(r);

            // write out string to output buffer for message

            out.writeBytes(content);
            out.flush();
            out.close();
            //System.out.println("Done sent to server");

            InputStreamReader inputStr = new InputStreamReader(conn.getInputStream());

            // string to hold the result of reading in the response
            StringBuilder sb = new StringBuilder();

            // read the characters from the request byte by byte and build up
            // the Response
            int nextChar;
            while ((nextChar = inputStr.read()) > -1) {
                sb = sb.append((char) nextChar);
            }

        } catch (Exception e) {
            System.out.print("Connection to server failed.");
        }
    }

    private static String getJSON(ArrayList<Athlete> r) {
        Gson g = new Gson();
        String emps = g.toJson(r);

        return emps;
    }
}