package com.sf.compare.parse;

import lombok.SneakyThrows;
import org.apache.commons.codec.binary.Base64;
import org.springframework.stereotype.Component;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

@Component
public class Utils {

    @SneakyThrows
    public InputStream parseBase64ToFile(String base64) {
        byte[] data = Base64.decodeBase64(base64);
        return new ByteArrayInputStream(data);
    }

    public String parseName(String stringCellValue) {
        return stringCellValue.replaceAll("[a-zA-Zа-яА-Я]*", "").replaceAll("_\\d{2}\\.\\d{2}_", "");
    }

    public String splitAndParse(String stringCellValue) {
        String[] a = stringCellValue.split(" ");
        for (String s : a) {
            if (s.contains("/")) {
                return parseName(s.trim());
            }
        }
        return "";
    }
}
