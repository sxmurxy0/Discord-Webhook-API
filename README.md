<h1 align='center'>Discord-Webhook-API:stars:</h1> </br>

Java API tool for easy work with discord webhooks. Send files, embeds or just text messages. </br>
More information and examples are [**here**](https://birdie0.github.io/discord-webhooks-guide/index.html). You can also use discord text formatting in your messages.

## Example message
<img width="264" alt="Снимок экрана 2023-07-05 004509" src="https://github.com/sxmurxy2007/Discord-Webhook-API/assets/46312126/00e033ad-4787-4303-93cc-e9c07432a139">

## Example's code
It doesn't matter in what order you will add components. Discord manages it itself.
```java
    private final WebhookAgent webhook = new WebhookAgent("your.webhook.url");
    
    public void test() {
    	DiscordMessage message = DiscordMessage.builder()
    		.withUsername("CUSTOM USERNAME")
    		.withAvatar("https://foni.club/uploads/posts/2023-01/1673429102_foni-club-p-super-puper-mega-krutie-oboi-48.jpg")
    		.withContent("Your message. **You can** `write anything you` __want__")
    		.addEmbed(DiscordEmbed.builder()
    				.withTitle("Testing testing title!")
    				.withDescription("Description time")
    				.withColor(Color.WHITE)
    				.withAuthor(new AuthorEmbed("sxmurxy", "https://sun6-21.userapi.com/s/v1/if1/dBGCgSOmmhlMRrHKvpRg7-tBe2C61B-SF37DfOxFIlJWolDCE9o0KIsegJXqzaaCX1if5y4k.jpg?size=639x639&quality=96&crop=0,76,639,639&ava=1", "https://vk.com/traplicate"))
    				.withImage(new ImageEmbed("https://yt3.googleusercontent.com/wxbc8Sby-cCy3zjWtFeLr3iO-j8hg_PfaSCiGJ-WioNTxv29EzNF6l07Ce0M4jsxoCE9EC_VXg=s900-c-k-c0x00ffffff-no-rj"))
    				.withThumbnail(new ThumbnailEmbed("https://sun6-21.userapi.com/s/v1/if1/dBGCgSOmmhlMRrHKvpRg7-tBe2C61B-SF37DfOxFIlJWolDCE9o0KIsegJXqzaaCX1if5y4k.jpg?size=639x639&quality=96&crop=0,76,639,639&ava=1"))
    				.withTimestamp(System.currentTimeMillis())
    				.addFields(new FieldEmbed("Field1", "Value1", true),
    						new FieldEmbed("Field2", "Value2", true),
    						new FieldEmbed("Field3", "Value3", true), 
    						new FieldEmbed("Field4", "Value4", true))
    				.withFooter(new FooterEmbed("Footer text right there!", "https://sun6-21.userapi.com/s/v1/if1/dBGCgSOmmhlMRrHKvpRg7-tBe2C61B-SF37DfOxFIlJWolDCE9o0KIsegJXqzaaCX1if5y4k.jpg?size=639x639&quality=96&crop=0,76,639,639&ava=1"))
    				.build())
    		.addFile(new File("C:/Users/%USERNAME%/Downloads/file"))
    		.build();
    	
    	webhook.execute(message);
    }
```
## Dependencies
```gradle
dependencies {
    implementation 'com.google.code.gson:gson:2.10'
    implementation 'org.apache.httpcomponents:httpmime:4.5.14'
}
```
