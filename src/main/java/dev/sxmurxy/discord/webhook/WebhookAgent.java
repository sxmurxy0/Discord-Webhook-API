package dev.sxmurxy.discord.webhook;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

import org.apache.http.HttpEntity;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.HttpClients;

import com.google.gson.Gson;

public final class WebhookAgent {
	
	private static final HttpClient HTTP_CLIENT = HttpClients.createDefault();
	private  static final Gson GSON = new Gson();
	private final String url;
	
	public WebhookAgent(String url) {
		this.url = url;
	}
	
	public String getUrl() {
		return url;
	}
	
	public void execute(DiscordMessage message) {
		if (message.content == null && message.files == null && message.embeds == null)
			throw new RuntimeException("Discord message can't contain no information.");
			
		new Thread(() -> {
			HttpPost httpPost = new HttpPost(url);
			HttpEntity entity = null;
			if (message.files != null) {
				MultipartEntityBuilder entityBuilder = MultipartEntityBuilder.create();
				entityBuilder.addPart("payload_json", new StringBody(GSON.toJson(message), ContentType.APPLICATION_JSON));
				for (int i = 0; i < message.files.size(); i++) {
					File file = message.files.get(i);
					if (file.exists() && file.isFile())
						entityBuilder.addBinaryBody("file" + i, file);
					else
						System.err.print("File [" + file.getAbsolutePath() + "] doesn't exist. It is skipped.");
				}
				entity = entityBuilder.build();
			} else {
				try {
					httpPost.addHeader("Content-Type", "application/json");
					entity = new StringEntity(GSON.toJson(message));
				} catch (UnsupportedEncodingException ex) {
					ex.printStackTrace();
					return;
				}
			}
			httpPost.setEntity(entity);
			
			try {
				HTTP_CLIENT.execute(httpPost);
			} catch (IOException ex) { ex.printStackTrace(); } 
			
		}).start();
	}
	
}
