package de.lukas.systemplugin.utils;

import java.net.HttpURLConnection;
import java.net.URL;
import java.io.OutputStream;
import java.io.IOException;

public class DiscordWebhook {
    public static void main(String[] args) {
        String webhookUrl = "https://discord.com/api/webhooks/WEBHOOK_ID/WEBHOOK_TOKEN";
        String message = "Hello from Java!";
        sendWebhook(webhookUrl, message);
    }

    public static void sendWebhook(String webhookUrl, String message) {
        try {
            URL url = new URL(webhookUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setDoOutput(true);

            String jsonMessage = "{\"content\":\"" + message + "\"}";
            OutputStream outputStream = connection.getOutputStream();
            outputStream.write(jsonMessage.getBytes());
            outputStream.flush();
            outputStream.close();

            int responseCode = connection.getResponseCode();
            System.out.println("Response Code : " + responseCode);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}