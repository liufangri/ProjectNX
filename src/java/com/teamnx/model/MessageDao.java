/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.teamnx.model;

/**
 *
 * @author Y400
 */
public interface MessageDao {

    public boolean insert(Message message);

    public boolean delete(String id);

    public boolean setReaded(String messageId);

    public boolean setUnread(String messageId);

    public boolean getAllMessage(String receiverId);

    public boolean getAllUnreadMessage(String receiverId);

    public boolean delete(Message message);
}
