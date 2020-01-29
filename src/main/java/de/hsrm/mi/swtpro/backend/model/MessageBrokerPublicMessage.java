package de.hsrm.mi.swtpro.backend.model;

import java.sql.Timestamp;

public class MessageBrokerPublicMessage{
    private long timestamp;
    private String action;
    private String data;


    public MessageBrokerPublicMessage(String action, String data){
        this.timestamp = new Timestamp(System.currentTimeMillis()).getTime();
        this.action = action;
        this.data = data;
    }

	public long getTimestamp(){
        return this.timestamp;
    }

    public String getAction(){
        return this.action;
    }
    
    public String getData(){
        return this.data;
    }
    
}