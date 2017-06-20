package com.wrox;

import java.time.Instant;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

public class Ticket
{
    private String customerName;

    private String subject;

    private String body;

    private Instant dateCreated;

    private Map<String, Attachment> attachments = new LinkedHashMap<>();
    
    private int UserID;
    
    public int getUserID(){
    	return UserID;
    }
    
    public void setUserID(int newUserID){
    	UserID = newUserID;
    }

    public String getCustomerName()
    {
        return customerName;
    }

    public void setCustomerName(String customerName)
    {
        this.customerName = customerName;
    }

    public String getSubject()
    {
        return subject;
    }

    public void setSubject(String subject)
    {
        this.subject = subject;
    }

    public String getBody()
    {
        return body;
    }

    public void setBody(String body)
    {
        this.body = body;
    }

    public Instant getDateCreated()
    {
        return dateCreated;
    }

    public void setDateCreated(Instant dateCreated)
    {
        this.dateCreated = dateCreated;
    }

    public Attachment getAttachment(String name)
    {
        return this.attachments.get(name);
    }

    public Collection<Attachment> getAttachments()
    {
        return this.attachments.values();
    }

    public void addAttachment(Attachment attachment)
    {
        if(attachment!=null){
        	this.attachments.put(attachment.getName(), attachment);
        	}
    }

    public int getNumberOfAttachments()
    {
        return this.attachments.size();
    }
}
