package entity;

import org.json.JSONArray;
import org.json.JSONObject;
import java.util.Set;
//public class Item {
//	private String itemId;
//	private String name;
//	private String address;
//	private Set<String> keywords;
//	private String imageUrl;
//	private String url;
//	
//	public Item(String itemId,String name) {
//		this.itemId = itemId;
//		this.name= name;
//		
//	}
//	
//	private Item(ItemBuilder builder) {
//		this.itemId = builder.itemId;
//		this.name = builder.name;
//		this.address = builder.address;
//		this.imageUrl = builder.imageUrl;
//		this.url = builder.url;
//		this.keywords = builder.keywords;
//	}
//
//
//
//	public Item(String itemId,String name,String address)
//	public String getItemId() {
//		return itemId;
//	}
//	public String getName() {
//		return name;
//	}
//	public String getAddress() {
//		return address;
//	}
//	public Set<String> getKeywords() {
//		return keywords;
//	}
//	public String getImageUrl() {
//		return imageUrl;
//	}
//	public String getUrl() {
//		return url;
//	}
//	
//	public static class ItemBuilder {
//		private String itemId;
//		private String name;
//		private String address;
//		private Set<String> keywords;
//		private String imageUrl;
//		private String url;
//		
//		public ItemBuilder() {};
////		public void setItem(String itemId) {
////			this.itemId = itemId;
////		}
//		public void setItemId(String itemId) {
//			this.itemId = itemId;
//		}
//		public void setName(String name) {
//			this.name = name;
//		}
//		public void setAddress(String address) {
//			this.address = address;
//		}
//		public void setKeywords(Set<String> keywords) {
//			this.keywords = keywords;
//		}
//		public void setImageUrl(String imageUrl) {
//			this.imageUrl = imageUrl;
//		}
//		public void setUrl(String url) {
//			this.url = url;
//		}
//		
//		public Item build() {
//			return new Item(this);
//		}
//		
//	}
//	
//
//}

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class Item {
	private String itemId;
	private String name;
	private String address;
	private Set<String> keywords;
	private String imageUrl;
	private String url;
	
	public JSONObject toJSONObject() {
		JSONObject obj = new JSONObject();
		obj.put("item_id", itemId);
		obj.put("name", name);
		obj.put("address", address);
		obj.put("keywords", new JSONArray(keywords));
		obj.put("image_url", imageUrl);
		obj.put("url", url);
		return obj;
	}

}

