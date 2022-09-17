package com.fedorov.demo.parse;

import com.fedorov.demo.dto.CompareField;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Component;

import java.io.InputStream;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@Component
@Slf4j
public class ParseOneCFile {

    @SneakyThrows
    public Set<CompareField> parse(InputStream inputStream, Integer namePosition, Integer oneCPlusPosition, Integer oneCMinusPosition) {
        Map<String, CompareField> result = new HashMap<>();

        try (XSSFWorkbook workbook = new XSSFWorkbook(inputStream)) {
            XSSFSheet sheet = workbook.getSheetAt(0);

            for (Row row : sheet) {
                Cell cellName = row.getCell(namePosition - 1);
                Cell cellCountPlus = row.getCell(oneCPlusPosition - 1);
                Cell cellCountMinus = row.getCell(oneCMinusPosition - 1);
                try {
                    String name = parseName(cellName.getStringCellValue());
                    if (name.isEmpty()) continue;
                    double count = (cellCountPlus == null || cellCountPlus.getNumericCellValue() == 0.0) ? cellCountMinus.getNumericCellValue() * -1 : cellCountPlus.getNumericCellValue();
                    CompareField compareField = CompareField
                            .builder()
                            .code(name)
                            .count(count)
                            .build();
                    result.merge(name, compareField, (first, second) ->
                            CompareField
                                    .builder()
                                    .code(first.getCode())
                                    .count(first.getCount() + second.getCount())
                                    .build());
                } catch (Exception e) {
                    log.warn("String with paran dont pars {}, {}, {}", cellName, cellCountPlus, cellCountMinus);
                }

            }
        }
        return new HashSet<>(result.values());
    }

    private String parseName(String stringCellValue) {
        String[] a = stringCellValue.split(" ");
        for (String s : a) {
            if (s.contains("/")) {
                return s.replaceAll("[a-zA-Zа-яА-Я]*", "").replaceAll("_\\d{2}\\.\\d{2}_", "");
            }
        }
        return "";
    }
}
