
package de.hsrm.mi.swtpro.backend.model;

import java.sql.Timestamp;

public class MessageBrokerMessage{
    private long timestamp;
    private String message;

    public MessageBrokerMessage(String courseTitle, char fromGroupChar, char toGroupChar){
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
    }

    public MessageBrokerMessage(String courseTitle){
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