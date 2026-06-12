
package chatapp;

import java.util.Random;
import java.io.FileWriter;
import org.json.JSONObject;
import java.io.IOException;

public class Message {
        
    private String messageID;
    private String recipient;
    private String messageText;
    private String messageHash;
    
    private static int messagesCount = 0;
    private static int totalMessages = 0;
    
    private static String[] messageIDs = new String[500];
    private static String[] messageHashes = new String[500];
    private static String[] recipientCells = new String[500];
    private static String[] messages = new String[500];
    private static String[] messageStatuses = new String[500];
    
    public Message(String recipient, String messageText) {
        this.messageID = generateMessageID();
        this.recipient = recipient;
        this.messageText = messageText;
        this.messageHash = createMessageHash();
        
    }
    
    
    private String generateMessageID() {
        Random rd = new Random();
        long id = 1000000000L + (long)(rd.nextDouble() * 9000000000L);
        return String.format("%010d", id);
    }
    
    public boolean checkMessageID() {
        return messageID.length() <= 10;
    }
    
    public String checkRecipientCell() {
        if(recipient.startsWith("+27") && recipient.length() == 12){
            return "Cell phone number stored successfully";
        }
        return "Cell phone number incorrectly formatted or doesn't contain the international code";
    }
    
    public String validateMessageLength() {
        if (messageText.length() <= 250){
            return "Message ready to send";
        } else {
            int excess = messageText.length() - 250;
            return "Message exceeds 250 charecters by " + excess + " charecters, please reduce the text size";
        }
        
    } 
    
    public String createMessageHash() {
        String[] words = messageText.trim().split(" ");
        String firstWord = words[0];
        String lastWord = words[words.length - 1];
        return messageID.substring(0, 2) + ":" + messagesCount+ ":" + (firstWord + lastWord).toUpperCase();
    }
    
    public String sendMessages(int optionChoice){
        switch(optionChoice){
            case 1:
                messageIDs[messagesCount] = messageID;
                messageHashes[messagesCount] = messageHash;
                recipientCells[messagesCount] = recipient;
                messages[messagesCount] = messageText;
                messageStatuses[messagesCount] = "Sent";
                
                messagesCount++;
                return "Message Sent Successfully.";
                
            case 2:
                return "Press 0 to delete the message.";
                
            case 3:
                messageIDs[messagesCount] = messageID;
                messageHashes[messagesCount] = messageHash;
                recipientCells[messagesCount] = recipient;
                messages[messagesCount] = messageText;
                messageStatuses[messagesCount] = "Stored";
                
                storeMessageToJSON();
                messagesCount++;
                return "Message successfully stored";
                
            default: 
                return "Invalid option";
            
              
                
        }
    }
    
    
    public void storeMessageToJSON() {
        try {
            FileWriter wrt = new FileWriter("messages.json", true);
            wrt.write("\n");
            wrt.write("Message ID: " + messageID);
            wrt.write("Message Hash: " + messageHash);
            wrt.write("Recipient Cell: " + recipient);
            wrt.write("Message: " + messageText);
            wrt.close();
         
        } catch (IOException e) {
            
            System.out.println("Error while writing to the JSON file.");
        }
        
       
    }
    
    
    public static void showMessageLog() {
        boolean found = false;
        for(int i = 0; i< messagesCount; i++){
            if(messageStatuses[i].equals("Sent") || messageStatuses[i].equals("Stored")) {
                found = true;
                
                System.out.println("\nMessage ID: " + messageIDs[i]);
                System.out.println("Message Hash: " + messageHashes[i]);
                System.out.println("Recipient Cell: " + recipientCells[i]);
                System.out.println("Message Status: " + messageStatuses);
            }
        }
        
        if (!found) {
            System.out.println("No messages found in the message log");
        }
    }
    
    public static void searchMessages(String searchMsg) {
        for(int i = 0; i< messagesCount; i++) {
            if (messageStatuses[i].equals("Deleted")) {continue;}
        if(messageIDs[i].equals(searchMsg) ||messageHashes[i].equals(searchMsg)){
            System.out.println("\nMessage found");
            System.out.println("Message ID: " + messageIDs[i]);
            System.out.println("Message Hash: " + messageHashes[i]);
            System.out.println("Recipient Cell: " + recipientCells[i]);
            System.out.println("Message Status: "+ messageStatuses[i]);
          return;
        }
        }
        System.out.println("Message not found");
    }
    public static void showLongestMessageReport() {
        if(messagesCount == 0) {
            System.out.println("No messages avilable");
            return;
        }
        
        int longestMsg = 0;
        for (int i =0; i < messagesCount; i++) {
            if(messages[i].length() > messages[longestMsg].length()) {
                longestMsg =i;
            }
        }
        
        System.out.println("Longest Message Recipient Cell: " + recipientCells[longestMsg]);
        System.out.println("Message Length: " + messages[longestMsg] + " charecters");
    
    }
    
    
    public static void deleteMessage(String msgIDToDeleteMsg) {
        for(int i = 0; i<messagesCount; i++) {
            if(messageIDs[i].equals(msgIDToDeleteMsg)){
                messageStatuses[i] = "Deleted";
                System.out.println("Message deleted successfully");
                return;
            }
        }
        System.out.println("Message not found");
    }
    
    
    public static int returnTotalNumberOfMessages() {
        return totalMessages;
    }
    public String printMessageDetails() {
        return "Message ID: " + messageID  +  "\nMessage Hash: "  +  messageHash  +  "\nRecipient Cell: "  +  recipient  +  "\nMessage: "+messageText; 
    }
    
    public String getMessageID(){
        return messageID;
    }
    public String getMessageHash() {
        return messageHash;
    }
    public String getRecipientCell() {
        return recipient;
    }
    public String getMessageText(){
        return messageText;
    }
}
    

