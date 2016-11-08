package Services;

import Database.DatabaseClass;
import Model.Message;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

/**
 * Created by ericjohn1 on 11/3/2016.
 */
public class MessageService {

    private Map<Long,Message> messages = DatabaseClass.getMessages();

    public MessageService(){
        messages.put(1L, new Message(1,"Hello", "Eric"));
        messages.put(2L, new Message(2,"Hi", "Eric J"));
        messages.put(3L, new Message(3,"Hey", "Eric Johnson"));
    }

    public List<Message> getAllMessages() {
     return new ArrayList<Message>(messages.values());
    }

    public Message getMessage(long id){
        return messages.get(id);
    }

    public List<Message> getAllMessagesByYear(int year){
        List<Message> messagesForYear = new ArrayList<>();
        Calendar cal = Calendar.getInstance();
        for (Message message : messages.values()){
            cal.setTime(message.getCreated());
            if(cal.get(Calendar.YEAR)== year){
                messagesForYear.add(message);
            }
        }
        return messagesForYear;
    }

    public List<Message> getAllMessagesPagenated(int start, int size){
        ArrayList<Message> list = new ArrayList<Message>(messages.values());
        if (start + size > list.size()) return new ArrayList<Message>();
        return list.subList(start, start + size);
    }

    public Message addMessage(Message message){
        message.setId(messages.size()+1);
        messages.put(message.getId(),message);
        return message;
    }

    public Message updateMessage(Message message){
        if(message.getId() <=0){
            return null;
        }
        messages.put(message.getId(),message);
        return message;
    }

    public Message removeMessage(long id){
        return messages.remove(id);
    }
}
