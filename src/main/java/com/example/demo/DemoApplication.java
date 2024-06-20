package com.example.demo;

import com.example.demo.filters.FirstLetterIsABCDE;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;

public class DemoApplication {
    public static void main(String[] args) throws IOException {

        // Po otrzymaniu słowa z serwera, należy pamiętać to słowo oraz moment jego otrzymania aż do końca działania programu.
        // Liczbę wszystkich otrzymanych słów należy zapisywać w etykiecie wordCountLabel.
//        Tworzny jest WordContainer, który am za zadanie przechować te wszystkie słowa. Obiekt tej klasy tworzony jest w
//        mainie i przekazywany do ConnectionThread, aby tam zapisywać słowa otrzymane z serwera.

        WordContainer wordContainer = new WordContainer(new FirstLetterIsABCDE());
        //Połączenie się z serverem. Parametry połączenia to adres - localhost i port - 5000 zapisane w kodzie
        ConnectionThread connectionThread = new ConnectionThread("localhost", 5000, wordContainer);
        connectionThread.start();
        //




    }

}