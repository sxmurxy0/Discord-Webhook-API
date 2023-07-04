package dev.sxmurxy.discord.webhook;

import java.awt.Color;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;
import java.util.TimeZone;

public final class DiscordEmbed {

	String title;
	String description;
	String url;
	Integer color;
	String timestamp;
	AuthorEmbed author;
	ImageEmbed image;
	ThumbnailEmbed thumbnail;
	FooterEmbed footer;
	List<FieldEmbed> fields;
	
	private DiscordEmbed() {}

	public static DiscordEmbed.Builder builder() {
		return new Builder();
	}

	public static class Builder {

		private final DiscordEmbed embed = new DiscordEmbed();

		public Builder withTitle(String title) {
			embed.title = title;
			return this;
		}
		
		/**
		 * Title will become clickable with this url.
		 */
		public Builder withTitleUrl(String url) {
			embed.url = url;
			return this;
		}

		public Builder withDescription(String description) {
			embed.description = description;
			return this;
		}
		
		/**
		 * Embed will have a colored border on the left.
		 */
		public Builder withColor(Color color) {
			embed.color = (((color.getRed() << 8) + color.getGreen()) << 8) + color.getBlue();
			return this;
		}
		
		/**
		 * Embed will have signature with date.
		 */
		public Builder withTimestamp(Calendar calendar) {
			embed.timestamp = OffsetDateTime.ofInstant(calendar.toInstant(), calendar.getTimeZone().toZoneId()).toString();;
			return this;
		}
		
		/**
		 * Embed will have signature with date.
		 */
		public Builder withTimestamp(long timeInMillis) {
			Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("GMT"));
	        calendar.setTimeInMillis(timeInMillis);
			return withTimestamp(calendar);
		}
		
		public Builder withAuthor(AuthorEmbed author) {
			embed.author = author;
			return this;
		}
		
		public Builder withImage(ImageEmbed image) {
			embed.image = image;
			return this;
		}
		
		public Builder withThumbnail(ThumbnailEmbed thumbnail) {
			embed.thumbnail = thumbnail;
			return this;
		}
		
		public Builder withFooter(FooterEmbed footer) {
			embed.footer = footer;
			return this;
		}
		
		public Builder addField(FieldEmbed field) {
			return addFields(field);
		}
		
		public Builder addFields(FieldEmbed... fields) {
			if (embed.fields == null)
				embed.fields = new ArrayList<>(4);
			
			Collections.addAll(embed.fields, fields);
			return this;
		}
		
		public DiscordEmbed build() {
			return embed;
		}
		
	}

	public static class AuthorEmbed {

		String name;
		String icon_url;
		String url;
		
		public AuthorEmbed(String name, String icon_url) {
			this.name = name;
			this.icon_url = icon_url;
		}
		
		/**
		 * @param url - author name will become clickable with this url.
		 */
		public AuthorEmbed(String name, String icon_url, String url) {
			this(name, icon_url);
			this.url = url;
		}
		
	}
	
	public static class FieldEmbed {
		
		String name;
		String value;
		Boolean inline;
		
		public FieldEmbed(String name, String value) {
			this.name = name;
			this.value = value;
		}
		
		/**
		 * @param inline - controls whether field will be in the next line or in the same. Default is false.
		 */
		public FieldEmbed(String name, String value, Boolean inline) {
			this(name, value);
			this.inline = inline;
		}
		
	}
	
	public static class ImageEmbed {

		String url;
		
		public ImageEmbed(String url) {
			this.url = url;
		}
		
	}
	
	public static class ThumbnailEmbed {

		String url;
		
		public ThumbnailEmbed(String url) {
			this.url = url;
		}
		
	}
	
	public static class FooterEmbed {
		
		String text;
		String icon_url;
		
		public FooterEmbed(String text, String icon_url) {
			this.text = text;
			this.icon_url = icon_url;
		}
		
	}

}
