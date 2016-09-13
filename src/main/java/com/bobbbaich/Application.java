package com.bobbbaich;

import com.bobbbaich.utils.Console;

/**
 * Created by Bohdan Pohotilyi on 13.09.2016.
 */
public class Application {

    public static final String DATASOURCE = "jdbc:h2:~/reviews.db;INIT=RUNSCRIPT from 'classpath:db/init.sql'";

    public static void main(String[] args) throws Exception{
        Console.readToStart();
    }
}

