package codecheck;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

import org.apache.http.entity.StringEntity;
import org.apache.http.client.fluent.Request;
import org.apache.http.client.fluent.Response;

public class App {
	public static void main(String[] args) {

		Response response = null;
        try {
            response =
                Request
                .Get("http://challenge-server.code-check.io/")
                .execute();


        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (response != null) {
                response.discardContent();
            }
        }

        System.out.println("===== HTTP GET End =====");
    }
	}
}
