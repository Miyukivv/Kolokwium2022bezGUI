package com.example.demo;

import com.example.demo.filters.WordFilter;
import org.springframework.cglib.core.Local;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

//        // Po otrzymaniu słowa z serwera, należy pamiętać to słowo oraz moment jego otrzymania aż do końca działania programu.
//        // Liczbę wszystkich otrzymanych słów należy zapisywać w etykiecie wordCountLabel.

public class WordContainer {
    private ArrayList<Word> wordList = new ArrayList<>();
    private ArrayList<Word> wordsNotAdded = new ArrayList<>();
    private int wordCountLabel = 0;

    private WordFilter wordFilter;



    public WordContainer(WordFilter wordFilter) {
        this.wordFilter = wordFilter;
    }

    public void addWord(String wordString){
        if(!wordFilter.isWordMatching(wordString)){
            wordsNotAdded.add(new Word(wordString, LocalDateTime.now()));
            return;
        }
        Word word = new Word(wordString, LocalDateTime.now());
        wordList.add(word);
        //Wpisy w liście wordList powinny być posortowane alfabetycznie (zgodnie z ASCII, ignorując polskie znaki) według słów,
        // które zawierają, na przykład wpis “13:00:00 alfa” powinien znaleźć się przed “12:00:00 beta”.

        wordList.sort((w1, w2) -> w1.getWordString().compareTo(w2.getWordString()));
        //
        wordCountLabel = wordList.size(); //lub wordCountLabel++ (takie samo działanie)
    }

    //Słowa otrzymane z serwera powinny być filtrowane na bieżąco - dodawane do listy wordList wyłącznie, jeżeli spełniają założenia filtra.
    // Należy je jednak zapamiętywać, aby wyświetlić je w razie usunięcia lub zmiany filtra.


    @Override
    public String toString() {
        String result = "";
        for(Word w : wordList){
            result += w.getWordString() + " " + w.getTime() + "\n";
        }
        return result;
    }

    public ArrayList<Word> getWords() {
        return wordList;
    }

    public int getWordCountLabel() {
        return wordCountLabel;
    }

    public String getWordsNotAddedString() {
        String output = "";
        for(Word w : wordsNotAdded){
            output += w.getWordString() + " " + w.getTime() + "\n";
        }
        return output;
    }

    class Word {
        private String wordString;
        private LocalDateTime time;

        public Word(String wordString, LocalDateTime time) {
            this.wordString = wordString;
            this.time = time;
        }

        public String getWordString() {
            return wordString;
        }

        public LocalDateTime getTime() {
            return time;
        }
    }
}
