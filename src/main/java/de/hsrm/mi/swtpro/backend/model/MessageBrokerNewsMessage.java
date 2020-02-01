
package de.hsrm.mi.swtpro.backend.model;

import java.sql.Timestamp;

public class MessageBrokerNewsMessage{
    private long timestamp;
    private String message;

    public MessageBrokerNewsMessage(String courseTitle){
        this.timestamp = new Timestamp(System.currentTimeMillis()).getTime();
        StringBuilder sb = new StringBuilder();
        sb.append("Das Modul ")
        .append(courseTitle)
        .append(" wurde verändert");
        this.message = sb.toString();
    }

    public long getTimestamp(){
        return this.timestamp;
    }

    public String getMessage(){
        return this.message;
    }


}