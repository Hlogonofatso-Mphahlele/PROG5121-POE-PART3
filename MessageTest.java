
package chatapp;


import org.junit.Test;
import static org.junit.Assert.*;

public class MessageTest {
    
    //tests to check valild and invalid recipient cellphonr number formats
    @Test
    public void testValidRecipientCellFormat() {
        Message ms = new Message("+27718693002", "Hi Mike, can you join us for dinner tonight?");
        assertEquals("Cell phone number successfully captured.", ms.checkRecipientCell());
    }
    @Test
    public void testInvalidRecipientCellFormat(){
        Message ms = new Message("08575975889", "Hi Keegan, did you recieve the payment?");
        assertEquals("Cell phone number incorrectly formatted or doesn't contain the international code. Correct the number and try again.", ms.checkRecipientCell());
    }
    
    //tets for valid and invlid message lengths limits
    @Test
    public void testValidMessageLengthLimit() {
        Message ms = new Message("+27718693002", "Hi Mike, can you join us for dinner tonight?");
        assertEquals("Message ready to send.", ms.validateMessageLength());
    }
    @Test
    public void testInvalidMessageLengthLimit(){
        Message ms = new Message("08575975889", "On the Insert tab, the galleries include items that are designed to coordinate with the overall look of your document. You can use these galleries to insert tables, headers, footers, lists, cover pages, and other document building blocks. When you create pictures, charts, or diagrams, they also coordinate with your current document look.\n" +
"You can easily change the formatting of selected text in the document text by choosing a look for the selected text from the Quick Styles gallery on the Home tab. You can also format text directly by using the other controls on the Home tab. Most controls offer a choice of using the look from the current theme or using a format that you specify directly.\n" +
"To change the overall look of your document, choose new Theme elements on the Page Layout tab. To change the looks available in the Quick Style gallery, use the Change Current Quick Style Set command. Both the Themes gallery and the Quick Styles gallery provide reset commands so that you can always restore the look of your document to the original contained in your current template.");
        assertEquals("Message exceeds 250 charecters by 796 charecters. Please reduce the size", ms.validateMessageLength());
    }       
   
  
    // Message sending options
    @Test
    public void testMessageSendingOption() {
        Message ms = new Message("+27718693002", "Hi Mike, can you join us for dinner tonight?");
        assertEquals("Message successfully sent.", ms.sendMessages(1));
    }
    @Test
    public void testMEssageDisregardingOption(){
        Message ms = new Message("08575975889", "Hi Keegan, did you recieve the payment?");
        assertEquals("Press 0 to delete the message.", ms.sendMessages(2));
    }
    @Test
    public void testStoringMessageOption() {
        Message ms = new Message("+27718693002", "Hi Mike, can you join us for dinner tonight?");
        assertEquals("Message successfully stored", ms.sendMessages(3));
    }
    
    
     @Test
    public void testCreatedMessageID(){
        Message ms  = new Message("+27186930029", "Hi Keegan, did you recieve the payment?");
        assertTrue(ms.checkMessageID());
    }
    
    @Test
    public void testValidMEssageHashFormat() {
        Message ms = new Message("+27186930029" ,"Hi Mike, can you join us for dinner tonight?");
        String msHash = ms.getMessageHash();
        assertTrue(msHash.contains("HITONIGHT"));
    }
    
    @Test
    public void testReturnTotalNumberOfSendMessages() {
        Message ms = new Message("+27286930029", "Hey Keegan, did you recieve the payment?");
        ms.sendMessages(1);
        assertTrue(Message.returnTotalNumberOfMessages() > 0);
    }
}
