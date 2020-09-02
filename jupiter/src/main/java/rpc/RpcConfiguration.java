package rpc;


import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONObject;
import java.util.HashSet;
import java.util.Set;
import entity.Item;
public class RpcConfiguration {
	public static void writeJsonArray(HttpServletResponse response,JSONArray array) throws IOException{
		PrintWriter writer = response.getWriter();
		writer.print(array);
	}
	
	public static void writeJsonObject(HttpServletResponse response,JSONObject obj) throws IOException {
		PrintWriter writer = response.getWriter();
		writer.print(obj);
		
	}
	
	public static Item parseFavoriteItem(JSONObject favoriteItem) {
		Set<String> keywords = new HashSet<>();
		JSONArray array = favoriteItem.getJSONArray("keywords");
		for (int i = 0; i < array.length(); ++i) {
			keywords.add(array.getString(i));
		}
		return Item.builder()
				.itemId(favoriteItem.getString("item_id"))
				.name(favoriteItem.getString("name"))
				.address(favoriteItem.getString("address"))
				.url(favoriteItem.getString("url"))
				.imageUrl(favoriteItem.getString("image_url"))
		        .keywords(keywords)
		        .build();
	}


}
