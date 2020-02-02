
package de.hsrm.mi.swtpro.backend.model;

import java.sql.Timestamp;

public class MessageBrokerPersonalMessage{
    private long timestamp;
    private String message;
    private long userid;

    public MessageBrokerPersonalMessage(String courseTitle, char fromGroupChar, char toGroupChar, long userid){
        this.timestamp = new Timestamp(System.currentTimeMillis()).getTime();
        StringBuilder sb = new StringBuilder();
        sb.append("Du hast erfolgreich von der Gruppe ")
        .append(courseTitle)
        .append(" ")
        .append(fromGroupChar)
        .append(" zu ")
        .append(toGroupChar)
        .append(" getauscht.");
        this.message = sb.toString();
        this.userid = userid;
    }

    public long getTimestamp(){
        return this.timestamp;
    }

    public String getMessage(){
        return this.message;
    }

    public long getUserid(){
        return this.userid;
    }


}