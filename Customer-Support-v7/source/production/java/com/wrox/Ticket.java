package com.wrox;

import java.util.Date;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

public class Ticket
{
    private String customerName;

    private String subject;

    private String body;

    private Date dateCreated;
    
    private Date dateClosed;

    private Map<String, Attachment> attachments = new LinkedHashMap<>();
    
    private int UserID;
    
    private int RefID;
    
    public Date getDateClosed(){
    	return dateClosed;
    }
    
    public void setDateClosed(Date newDateClosed){
    	dateClosed = newDateClosed;
    }
    
    public int getRefID(){
    	return RefID;
    }
    
    public void setRefID(int newRefID){
    	RefID=newRefID;
    }
    
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

    public Date getDateCreated()
    {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated)
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
