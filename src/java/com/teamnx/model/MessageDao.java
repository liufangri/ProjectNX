/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.teamnx.model;

import java.util.ArrayList;

/**
 *
 * @author Y400
 */
public interface MessageDao {

    public boolean insert(Message message);

    public boolean delete(String id);

    public boolean setReaded(String messageId);

    public boolean setUnread(String messageId);

    public Message findMessageById(String messageId);

    public ArrayList<Message> getAllMessage(String receiverId);

    public ArrayList<Message> getAllUnreadMessage(String receiverId);

    public boolean delete(Message message);

    public boolean setAllReaded(String receiverId);

}
