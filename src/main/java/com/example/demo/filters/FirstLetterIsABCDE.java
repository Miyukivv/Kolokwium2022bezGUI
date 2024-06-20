package com.example.demo.filters;

public class FirstLetterIsABCDE implements WordFilter{
    @Override
    public boolean isWordMatching(String word) {
        if(word.length() > 0){
            char firstLetter = word.charAt(0);
            return Character.toUpperCase(firstLetter) == 'A'
                    || Character.toUpperCase(firstLetter) == 'B'
                    || Character.toUpperCase(firstLetter) == 'C'
                    || Character.toUpperCase(firstLetter) == 'D'
                    || Character.toUpperCase(firstLetter) == 'E';
        }

        return false;
    }
}
