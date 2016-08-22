package cyla.juan.icab.uitls;

/**
 * Created by chen on 16-4-21.
 */
public class MessageCall  {

   private static MessageCall messageCall;



    public static MessageCall getInstance() {
        if (messageCall == null) {
            messageCall = new MessageCall();
        }
        return messageCall;
    }



}
