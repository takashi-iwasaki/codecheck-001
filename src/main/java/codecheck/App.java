package codecheck;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import net.arnx.jsonic.JSON;

public class App {

	public static void main(String[] args) {

		if (args == null) {
			System.out.println("Error!");
		} else if (args[0] == null) {
			System.out.println("Error!");
		} else {
			try {
				//UTF8でやり取り
				Charset charset = StandardCharsets.UTF_8;
				//inputをencoding
				String encodedParam = URLEncoder.encode(args[0], "UTF-8");

				//getなのでURLにパラメータ付与
				HttpClient httpclient = HttpClients.createDefault();
				HttpGet request = new HttpGet("http://challenge-server.code-check.io/api/hash?q=" + encodedParam);

				HttpResponse response = null;

				//アクセス
				response = httpclient.execute(request);

				int status = response.getStatusLine().getStatusCode();
				//System.out.println("HTTPステータス:" + status);
				//HTTPステータス:200

				if (status == HttpStatus.SC_OK) {
					String responseData = EntityUtils.toString(response.getEntity(), charset);

					TestApiDto user = JSON.decode(responseData, TestApiDto.class);
					System.out.println(user.getHash());

				}
			} catch (ClientProtocolException e) {
				e.printStackTrace();
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} finally {

			}
		}

	}

	//JSON分解用の内部クラス
	class TestApiDto {
		String q;

		public String getQ() {
			return q;
		}

		public void setQ(String q) {
			this.q = q;
		}

		String hash;

		public String getHash() {
			return hash;
		}

		public void setHash(String hash) {
			this.hash = hash;
		}

	}

}
