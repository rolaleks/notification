package ru.geekbrains.parser.cian.utils;

import org.jsoup.nodes.Element;
import ru.geekbrains.entity.Address;

import java.math.BigDecimal;

public interface DataExtractor {

    Address getAddress(Element articleTag);

    Byte getQuantity(Element articleTag);

    Short getQuadrature(Element articleTag);

    String getPeriod(Element articleTag);

    BigDecimal getPrice(Element articleTag);

    String getTitle(Element articleTag);

    String getDescription(Element articleTag);

    String getLink(Element articleTag);
}
