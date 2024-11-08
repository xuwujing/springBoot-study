package com.pancm.config;

@Component("demoDefenderStrategy")
public class DemoDefenderStrategy implements DefenderStrategy{
    @Override
    public String category() {
        return "name";
    }
    
    @Override
    public int retainPrefixCount() {
        return 1;
    }
    
    @Override
    public int retainSuffixCount() {
        return 1;
    }
    
    @Override
    public char replaceChar() {
        return '$';
    }
}