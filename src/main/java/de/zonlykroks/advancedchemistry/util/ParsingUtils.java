package de.zonlykroks.advancedchemistry.util;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import de.zonlykroks.advancedchemistry.element.Element;

import java.util.List;

public class ParsingUtils {

    public static void parseItems(String json) {
        ObjectMapper objectMapper = new ObjectMapper();

        try {
            List<Element> elements = objectMapper.readValue(json, new TypeReference<>() {});
            elements.forEach(Element::convertAndRegisterToItem);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
