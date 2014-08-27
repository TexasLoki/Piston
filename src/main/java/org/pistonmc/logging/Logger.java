package org.pistonmc.logging;

import in.parapengu.craftbot.bot.ChatColor;
import in.parapengu.craftbot.util.ClassUtils;

import java.text.SimpleDateFormat;
import java.util.Date;

import static in.parapengu.craftbot.logging.LogLevel.*;

public class Logger {

    private String name;
    private LogLevel level;

    private boolean debug;
    private SimpleDateFormat format;
    private Logger parent;

    protected Logger(String name) {
        this.name = name;
    }

    protected Logger(String name, LogLevel level) {
        this(name);
        this.level = level;
    }

    public String getName() {
        return name;
    }

    public String getPrefix() {
        return (parent != null ? parent.getPrefix() : "") + (name != null ? "[" + name + "] " : "");
    }

    public LogLevel getLevel() {
        return level;
    }

    public Logger setLevel(LogLevel level) {
        this.level = level;
        return this;
    }

    public boolean isDebug() {
        return debug || (parent != null && parent.isDebug());
    }

    public Logger setDebug(boolean debug) {
        this.debug = debug;
        return this;
    }

    public SimpleDateFormat getFormat() {
        return format;
    }

    public Logger setFormat(SimpleDateFormat format) {
        this.format = format;
        return this;
    }

    public Logger getParent() {
        return parent;
    }

    public Logger setParent(Logger parent) {
        this.parent = parent;
        while(format == null && parent != null) {
            if(parent.getFormat() != null) {
                format = parent.getFormat();
                break;
            }

            parent = parent.getParent();
        }

        return this;
    }

    private Logger print(String message) {
        System.out.println(message);
        return this;
    }

    public Logger log(LogLevel level, Object pre, Object... messages) {
        if(level == DEBUG && !isDebug()) {
            return this;
        }

        for(Object message : messages) {
            String prefix = "";
            if(format != null || level != null) {
                String formatPre = format != null ? format.format(new Date()) : "";
                String levelPre = level != null ? level.getColor().toConsole() + level.name() + ChatColor.RESET.toConsole() : null;
                prefix = "[" + formatPre + (formatPre != null && levelPre != null ? " " : "") + levelPre + "] ";
            }

            String msg = message.toString();
            if(message instanceof Exception) {
                Exception ex = (Exception) message;
                if(isDebug()) {
                    ex.printStackTrace();
                    continue;
                }

                msg = ex.getMessage() != null ? ex.getMessage() : ex.getClass().getSimpleName();
            }

            msg = ChatColor.getConsoleString(msg);
            if(pre != null) {
                msg = pre.toString() + msg;
            }

            String string = prefix + getPrefix() + msg + ChatColor.RESET.toConsole();
            print(string);
        }
        return this;
    }

    public Logger log(Object... messages) {
        log(level, null, messages);
        return this;
    }

    public Logger debug(Object... messages) {
        log(DEBUG, null, messages);
        return this;
    }

    public Logger info(Object... messages) {
        log(INFO, null, messages);
        return this;
    }

    public Logger warning(Object... messages) {
        log(WARNING, null, messages);
        return this;
    }

    public Logger severe(Object... messages) {
        log(SEVERE, null, messages);
        return this;
    }

    public Logger log(String prefix, Exception... exceptions) {
        log(SEVERE, prefix, exceptions);
        return this;
    }

    public Logger log(Exception... exceptions) {
        log(SEVERE, "", exceptions);
        return this;
    }

    @Override
    public String toString() {
        return ClassUtils.build(getClass(), this, true);
    }

}
