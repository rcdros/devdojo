package br.com.devdojo.javaclient;

import java.util.Base64;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by ricardors on 08/08/2018.
 */
public class JavaClientTest {
    public static void main(String[] args) {
        HttpURLConnection connection = null;
        BufferedReader reader = null;
        try
        {
            String basicEncoding =  encodingUsernamePassword("Ricardo", "123456");
            System.out.println(basicEncoding);

            URL url = new URL("http://localhost:8080/v1/protected/students/5");
            connection = (HttpURLConnection)url.openConnection();

            connection.setRequestMethod("GET");
            connection.addRequestProperty("Authorization", "Basic " + basicEncoding);
            reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));

            StringBuilder sb = new StringBuilder();
            String line;

            while((line = reader.readLine()) != null) {
                sb.append(line);
            }

            System.out.println(sb);

        }catch (Exception e){
            e.printStackTrace();
        }
        finally {
            if(reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if(connection != null ){
                connection.disconnect();
            }
        }
    }

    private static String encodingUsernamePassword(String username, String password) {
        String userPassword = username +":" + password;
        return new String(Base64.getEncoder().encodeToString(userPassword.getBytes()));
    }
}

