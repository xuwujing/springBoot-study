package com.pancm.config;

@Component
 public class PluginOne implements LogbackMessageDefender {
 
     public static final String HANDLE_BY_PLUGIN = "PluginOneAbc";
     
     @Override
     public boolean support(ILoggingEvent event) {
         return event.getMDCPropertyMap().containsKey(HANDLE_BY_PLUGIN);
     }
     
     @Override
     public void desensitize(ILoggingEvent event, String message, StringBuilder buffer) {
         buffer.append("[O_O] ").append(event.getFormattedMessage()).append(" [O_O]");
     }
 }