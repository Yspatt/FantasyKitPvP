package fantasynetwork.fantasykitpvp.utils;

public class TalkTime{
    private String message;
    private long time;
    public TalkTime(String message,long time){
        this.time = time;
        this.message = message;
    }
    public long getTime() {
        return time;
    }

    public String getMessage() {
        return message;
    }
}