package dev.sxmurxy.discord.webhook;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public final class DiscordMessage {

	String username;
	String avatar_url;
	String content;
	Boolean tts;
	List<DiscordEmbed> embeds;
	transient List<File> files; // transient - excluding files from json created from the object. They will added separately.

	private DiscordMessage() {}

	public static DiscordMessage.Builder builder() {
		return new Builder();
	}

	public static class Builder {

		private final DiscordMessage message = new DiscordMessage();

		/**
		 * Overrides the Discord BOT name.
		 */
		public Builder withUsername(String username) {
			message.username = username;
			return this;
		}
		
		/**
		 * Overrides the Discord BOT avatar.
		 */
		public Builder withAvatar(String avatar_url) {
			message.avatar_url = avatar_url;
			return this;
		}
		
		/**
		 * Just the content of the message.
		 */
		public Builder withContent(String content) {
			message.content = content;
			return this;
		}

		public Builder withTextToSpeech(boolean tts) {
			message.tts = tts;
			return this;
		}
		
		public Builder addEmbed(DiscordEmbed embed) {
			return addEmbeds(embed);
		}

		public Builder addEmbeds(DiscordEmbed... embeds) {
			if (message.embeds == null)
				message.embeds = new ArrayList<>(3);
			
			Collections.addAll(message.embeds, embeds);
			return this;
		}
		
		public Builder addFile(File file) {
			return addFiles(file);
		}

		public Builder addFiles(File... files) {
			if (message.files == null)
				message.files = new ArrayList<>(3);
			
			Collections.addAll(message.files, files);
			return this;
		}

		public DiscordMessage build() {
			return message;
		}

	}

}
