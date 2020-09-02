package external;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;

public class myGitHubClient {
	private static String URL_TEMPLATE = "https://jobs.github.com/positions.json?description=%s&lat=%s&long=%s";
	private static String DEFAULT_KEYWORD = "developer";
	public JSONArray search(Double lat, Double lon, String keyWord) {
		if(lat==0||lon==null) {
			JSONArray  array = new JSONArray(new JSONObject().put("Lacking info","no result"));
			return array;
		}
		else {
			if(keyWord==null) {
				keyWord = DEFAULT_KEYWORD;
			}
			
			try {
				keyWord=URLEncoder.encode(keyWord,"UTF-8");
			}
			catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
		
		}
		//Process the given parameter
		String url = String.format(URL_TEMPLATE, keyWord,lat,lon);
		CloseableHttpClient httpClient = HttpClients.createDefault();
		
		ResponseHandler<JSONArray> responseHandler = new ResponseHandler<JSONArray> () {
			@Override
			public JSONArray handleResponse(final HttpResponse response) throws ClientProtocolException, IOException {
				
				int status= response.getStatusLine().getStatusCode();
				if(status!=200) {
					return new JSONArray();
					
				}
				HttpEntity entity = response.getEntity();
				if(entity==null) return new JSONArray();
				String responseBody = EntityUtils.toString(entity);
				JSONArray array = new JSONArray(responseBody);
				return array;
				}
		 };
		 try {
			 return httpClient.execute(new HttpGet(url),responseHandler);
		 }
		 catch(ClientProtocolException e) {
			 e.printStackTrace();
		 }
		 catch(IOException e) {
			 e.printStackTrace();
		 }
		 return new JSONArray();
	}
}
