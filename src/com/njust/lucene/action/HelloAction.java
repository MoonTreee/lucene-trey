package com.njust.lucene.action;

/**
 * Created by Trey Wang on 2018/4/27.
 */
import java.text.DateFormat;
import java.util.Date;

import com.opensymphony.xwork2.ActionSupport;

public class HelloAction extends ActionSupport {
    private String message;

    public String getMessage() {
        return message;
    }

    @Override
    public String execute() {
        message = " Hello World, Now is " + DateFormat.getInstance().format( new Date());
        return SUCCESS;
    }
}