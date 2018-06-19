package codecheck;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class App {
	public static void main(String[] args) {

		HttpURLConnection connection = null;

		if (args == null) {
			System.out.println("Error!");
		} else if (args[0] == null) {
			System.out.println("Error!");
		} else {
			try {
				//URL 設定
				URL url = new URL("http://challenge-server.code-check.io/");

				connection = (HttpURLConnection) url.openConnection();
				connection.setRequestProperty("p", args[0]);
				connection.setRequestMethod("GET");

				//結果がHTTP200であれば結果出力
				int resultHttpStatusCode = connection.getResponseCode();
				if (resultHttpStatusCode == HttpURLConnection.HTTP_OK) {
					try (InputStreamReader isr = new InputStreamReader(connection.getInputStream(),
							StandardCharsets.UTF_8);
							BufferedReader reader = new BufferedReader(isr)) {
						String resultString;
						while ((resultString = reader.readLine()) != null) {
							System.out.println(resultString);
						}
					}
				} else if (resultHttpStatusCode == HttpURLConnection.HTTP_BAD_REQUEST) {
					System.out.println("BAD REQUEST!");
				} else {

				}
				System.out.println("ERROR! HTTPSTATUS:" + resultHttpStatusCode);

			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				if (connection != null) {
					connection.disconnect();
				}
			}

		}

	}

}
