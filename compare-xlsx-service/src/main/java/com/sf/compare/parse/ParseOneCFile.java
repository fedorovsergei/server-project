package com.sf.compare.parse;

import com.sf.compare.dto.CompareField;
import lombok.RequiredArgsConstructor;
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

@Slf4j
@Component
@RequiredArgsConstructor
public class ParseOneCFile {

    private final Utils utils;

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
                    String name = utils.parseName(cellName.getStringCellValue());
                    if (name.isEmpty()) continue;
                    double count = (cellCountPlus == null || cellCountPlus.getNumericCellValue() == 0.0)
                            ? cellCountMinus.getNumericCellValue() * -1 : cellCountPlus.getNumericCellValue();

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
}
