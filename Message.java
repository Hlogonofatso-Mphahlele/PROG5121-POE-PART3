
package chatapp;

import java.util.Random;
import java.io.FileWriter;
import java.io.IOException;

public class Message {
        
    private String messageID;
    private String recipient;
    private String messageText;
    private String messageHash;
    
    private static int messagesCount = 0;
    
    public Message(String recipient, String messageText) {
        this.messageID = generateMessageID();
        this.recipient = recipient;
        this.messageText = messageText;
        this.messageHash = createMessageHash();
        
    }
    
    
    private String generateMessageID() {
        Random rd = new Random();
        long id = 1000000000L + (long)(rd.nextDouble() * 9000000000L);
        return String.format("%10d", id);
    }
    
    public boolean checkMessageID() {
        return messageID.length() <= 10;
    }
    
    public String checkRecipientCell() {
        if(recipient.startsWith("+27") && recipient.length() == 12){
            return "Cellphone number stored successfully";
        }
        return "Cell phone number incorrectly formatted or doesnt conatin the international code";
    }
    
    public String validateMessageLength() {
        if (messageText.length() <= 250){
            return "Message ready to send";
        } else {
            int excess = messageText.length() - 250;
            return "Message exceeds 250 charecters by " + excess + " chrarecters, please reduce the text size";
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
                messagesCount++;              
                return "Message successfully send.";
            case 2:
                return "Press 0 to delete the message.";
            case 3:
                storeMessageToJSON();
                return "Message successfully stored.";
            default:
                return "Invalid option";
        }
    }
       
   
    
    public static int returnTotalMessages() {
        return messagesCount;
    }
    
    public void storeMessageToJSON() {
        try(FileWriter writer = new FileWriter("message.json", true)) {
            
           writer.write("Message ID: " + messageID + "\n");
           writer.write("Message Hash: " + messageHash + "\n");
           writer.write("Recipient Cell: " + recipient + "\n");
           writer.write("Message: " + messageText + "\n\n");
           
        } catch(IOException e) {
            System.out.println("Error witing to JSON file");
        }
       
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
    

