
package chatapp;

import java.util.Scanner;

public class Chatapp {

       
    static int index = 0;
    
    public static void main(String[] args) {
        Scanner kb = new Scanner(System.in);
        
        System.out.println("Register Your Account");
        
        System.out.print("Enter your first name: ");
        String firstName = kb.nextLine();
        
        System.out.print("Enter your last name: ");
        String lastName = kb.nextLine();
        
        System.out.print("Enter your username: ");
        String username = kb.nextLine();
        
        System.out.print("Enter your password: ");
        String password = kb.nextLine();
        
        System.out.print("Enter your cellphone number: ");
        String cellPhoneNumber = kb.nextLine();
        
        Login user = new Login(username, password, cellPhoneNumber);
        
        String registrationResult = user.registerUser();
        System.out.println(registrationResult);
        
        if (registrationResult.equals("User registered successfully")) {
            System.out.println("Enter Details To Login");
            
            System.out.print("Enter your username: ");
            String loginUsername = kb.nextLine();
            
            System.out.print("Enter ur password: ");
            String loginPassword = kb.nextLine();
            
            boolean LoginSuccess = user.loginUser(loginUsername, loginPassword);
            
            System.out.println(user.returnLoginStatus(LoginSuccess, firstName, lastName));          
          
        if(LoginSuccess) {
            System.out.println("Welcome To QuickChat");
            
            System.out.print("How many messages would you like to send?: ");
            int numberOfMessages = kb.nextInt();
            kb.nextLine();
            
            String[] messageIDs = new String[numberOfMessages];
            String[] messageHashs = new String[numberOfMessages];
            String[] recipientCells = new String[numberOfMessages];
            String[] messages = new String[numberOfMessages];
            String[] messageStatuses = new String[numberOfMessages];
            
            int count = 0;
            while (true) {
                System.out.println("Menu:");
                System.out.println("1) Send Message");
                System.out.println("2) Show Message Log");
                System.out.println("3) Search For A Message");
                System.out.println("4) Show The Longest Message Details");
                System.out.println("5) Delete Message");
                System.out.println("6) Quit");
                
                System.out.print("Choose an option from the menu: ");
                int menuOption = kb.nextInt();
                kb.nextLine();
                
                switch (menuOption) {
                    case 1:
                        if(count < numberOfMessages) {
                            System.out.print("Enter recipent cell number: ");
                            String recipient = kb.nextLine();
                            
                            System.out.print("Enter message: ");
                            String messageText = kb.nextLine();
                            
                            Message ms = new Message(recipient, messageText);
                            
                            System.out.println(ms.checkRecipientCell());
                            System.out.println(ms.validateMessageLength());
                            
                            System.out.println("Choose an action to do from the following:");
                            System.out.println("1) Send Message");
                            System.out.println("2) Disregard Message");
                            System.out.println("3) Store Message");
                            System.out.print("What would u like to do: ");
                            
                            
                            int optionChoice = kb.nextInt();
                            kb.nextLine();
                            
                            messageIDs[count] = ms.getMessageID();
                            messageHashs[count] = ms.getMessageHash();
                            recipientCells[count] = ms.getRecipientCell();
                            messages[count] = ms.getMessageText();
                            
                            if (optionChoice == 1) {
                                messageStatuses[count] = "Sent";
                                System.out.println(ms.sendMessages(1));
                                messageIDs[index] = ms.getMessageID();
                                messageHashs[index] = ms.getMessageHash();
                                recipientCells[index] = ms.getRecipientCell();
                                messages[index] = ms.getMessageText();
                                messageStatuses[index] = "Sent";
                                index++;
                                
                                System.out.println("\n" + ms.printMessageDetails());
                                
                            } else if (optionChoice == 2){
                                messageStatuses[count] = "Disregarded";
                                System.out.println(ms.sendMessages(2));
                                messageIDs[index] = ms.getMessageID();
                                messageHashs[index] = ms.getMessageHash();
                                recipientCells[index] = ms.getRecipientCell();
                                messages[index] = ms.getMessageText();
                                messageStatuses[index] = "Disregarded";
                                index++;
                               
                            }else if(optionChoice == 3) {
                                messageStatuses[count] = "Stored";
                                System.out.println(ms.sendMessages(3));
                                messageIDs[index] = ms.getMessageID();
                                messageHashs[index] = ms.getMessageHash();
                                recipientCells[index] = ms.getRecipientCell();
                                messages[index] = ms.getMessageText();
                                messageStatuses[index] = "Stored";
                                index++;
                            } else {
                                System.out.println("Inavlid option.");
                            }
                            count++;                            
                        } else {
                            System.out.println("Youve reached the maximum number of messages you can send");
                        }
                        break;
                        
                    case 2:
                        boolean found = false;
                        for (int i = 0; i< count; i++) {
                           if(messageStatuses[i].equals("Sent") || messageStatuses[i].equals("Disregarded") || messageStatuses[i].equals("Stored")) {
                               System.out.println("\nMessage ID: " + messageIDs[i]);
                               System.out.println("Message Hash: " + messageHashs[i]);
                               System.out.println("Recipient Cell: " + recipientCells[i]);
                               System.out.println("Message Status: " + messageStatuses[i]);
                               found = true;
                           } 
                        }
                        
                        if(!found) {
                            System.out.println("No messages found in the log.");
                        }
                        break;
                        
                    case 3:
                        System.out.print("Enter Message ID, Message Hash or Recipient Cell: ");
                        String searchValue = kb.nextLine();
                        boolean messageFound = false;
                        for (int i = 0; i < count; i++){
                            if(!messageStatuses[i].equals("Deleted")) {
                                if (messageIDs[i].equals(searchValue) || messageHashs[i].equals(searchValue) || recipientCells[i].equals(searchValue)) {
                                    System.out.println("Message ID: "+messageIDs[i]);
                                    System.out.println("Message Hash: " +messageHashs[i]);
                                    System.out.println("Recipient Cell: " + recipientCells[i]);
                                    System.out.println("Message: " + messages[i]);
                                    System.out.println("Message Status: " + messageStatuses[i]);
                                    messageFound = true;
                                }
                            }
                        }
                        
                        if(!messageFound) {
                            System.out.println("Message not foud.");
                        }
                        break;
                        
                    case 4:
                        int longestMessageChars = 0;
                        int longestIndxs = -1;
                        
                        for(int i = 0; i < index; i++) {
                            if(messages[i] != null && messages[i].length() > longestMessageChars) {
                                longestMessageChars = messages[i].length();
                                longestIndxs = i;
                            }
                        }
                        
                        if (longestIndxs != -1) {
                            System.out.println("Longest Message Recipient Cell: "+ recipientCells[longestIndxs]);
                            System.out.println("Message Length: "+ longestMessageChars+ " charecters");
                        }
                        break;
                        
                        
                    case 5:
                                               
                        System.out.print("Enter a message ID to delete: ");
                        String deleteMessageID = kb.nextLine();
                        boolean deleteMessage = false;
                        
                        for (int i = 0; i < count; i++) {
                            if(messageIDs[i].equals(deleteMessageID)) {
                                messageStatuses[i] = "Deleted";
                                System.out.println("Message deleted successfully.");
                                
                                deleteMessage = true;
                                break;
                            }
                        }
                        
                        if (!deleteMessage) {
                            System.out.println("Message not found.");
                        }
                        break;
                        
                    case 6:
                        System.out.println("\nTotal messages sent: " + Message.returnTotalNumberOfMessages());
                        
                        System.out.println("Thank u for using QuickChat");
                        
                        
                        kb.close();
                        return;
                        
                    default: 
                        System.out.println("Invalid option choosen");
                }
            }
        }
            
        }
        kb.close();
     
    }    
    
}
