package utils;

public class Message extends Object{
    private String date;
    private String time;
    private String sender;
    private String text;


    public Message(String date, String time, String sender, String text) {
        this.date = date;
        this.time = time;
        this.sender = sender;
        this.text = text;
    }

    public String concat(){
        return date + "|" + time + "|" + sender + "|" + text;
    }
}
