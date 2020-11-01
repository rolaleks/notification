package ru.geekbrains.parser.cian.temp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import ru.geekbrains.parser.cian.CianParser;

@Component
public class Runner implements CommandLineRunner {

    private CianParser cianParser;

    @Autowired
    public void setCianParser(CianParser cianParser) {
        this.cianParser = cianParser;
    }

    @Override
    public void run(String... args) throws Exception {
        cianParser.start("Россия", "Севастополь");
    }
}
