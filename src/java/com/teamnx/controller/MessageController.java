/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.teamnx.controller;

import com.teamnx.model.MessageDaoImpl;

/**
 *
 * @author Y400
 */
public class MessageController {

    private MessageDaoImpl mdi;

    public void setMdi(MessageDaoImpl mdi) {
	this.mdi = mdi;
    }

}
