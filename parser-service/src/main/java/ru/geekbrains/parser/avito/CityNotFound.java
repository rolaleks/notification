package ru.geekbrains.parser.avito;

public class CityNotFound extends RuntimeException {

    public CityNotFound(String error){
        super(error);
    }
}
