package com.sf.compare.parse;

import com.sf.compare.dto.CompareField;
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
public class ParsePepsiFile {

    @SneakyThrows
    public Set<CompareField> parse(InputStream inputStream, Integer pepsiNamePosition, Integer pepsiCountPosition) {
        Map<String, CompareField> result = new HashMap<>();

        try (XSSFWorkbook workbook = new XSSFWorkbook(inputStream)) {
            XSSFSheet sheet = workbook.getSheetAt(0);

            for (Row row : sheet) {
                Cell cellName = row.getCell(pepsiNamePosition - 1);
                Cell cellCount = row.getCell(pepsiCountPosition - 1);
                try {
                    String name = parseName(cellName.getStringCellValue());
                    if (name.isEmpty()) continue;
                    CompareField compareField = CompareField
                            .builder()
                            .code(cellName.getStringCellValue())
                            .count(cellCount.getNumericCellValue())
                            .build();
                    result.merge(name, compareField, (first, second) ->
                            CompareField
                                    .builder()
                                    .code(first.getCode())
                                    .count(first.getCount() + second.getCount())
                                    .build());
                } catch (Exception e) {
                    log.warn("String with paran dont pars {}, {}", cellName, cellCount);
                }
            }
        }
        return new HashSet<>(result.values());
    }

    private String parseName(String stringCellValue) {
        return stringCellValue.replaceAll("[a-zA-Zа-яА-Я]*", "").replaceAll("_\\d{2}\\.\\d{2}_", "");
    }
}
