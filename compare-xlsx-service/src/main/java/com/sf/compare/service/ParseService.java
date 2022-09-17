package com.sf.compare.service;

import com.google.common.collect.Sets;
import com.sf.compare.dto.CompareField;
import com.sf.compare.dto.RequestDto;
import com.sf.compare.dto.ResponseDto;
import com.sf.compare.parse.ParseOneCFile;
import com.sf.compare.parse.ParsePepsiFile;
import com.sf.compare.parse.Utils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;

@Slf4j
@Service
@RequiredArgsConstructor
public class ParseService {

    private final ParsePepsiFile parsePepsiFile;
    private final ParseOneCFile parseOneCFile;
    private final Utils utils;

    public ResponseDto getParse(@NotNull RequestDto requestDto) {
        log.info("start compare two files");
        try {
            Set<CompareField> compareOneCFile = parseOneCFile.parse(utils
                    .parseBase64ToFile(requestDto.getOneCFile()), requestDto.getOneCNamePosition(), requestDto.getOneCPlusPosition(), requestDto.getOneCMinusPosition());

            Set<CompareField> comparePepsiFile = parsePepsiFile.parse(utils
                    .parseBase64ToFile(requestDto.getPepsiFileBase64()), requestDto.getPepsiNamePosition(), requestDto.getPepsiCountPosition());

            return buildResult(compareOneCFile, comparePepsiFile);
        } catch (Throwable e) {
            log.error("Something go wrong");
            e.printStackTrace();
            throw new IllegalArgumentException();
        }
    }

    private ResponseDto buildResult(Set<CompareField> compareOneCFile, Set<CompareField> comparePepsiFile) {
        Set<CompareField> resultCompare = new HashSet<>();
        resultCompare.addAll(Sets.difference(comparePepsiFile, compareOneCFile));
        resultCompare.addAll(Sets.difference(compareOneCFile, comparePepsiFile));

        ResponseDto result = ResponseDto
                .builder()
                .oneCFile(compareOneCFile)
                .pepsiFile(comparePepsiFile)
                .compare(resultCompare)
                .build();
        log.info("in 1c file find {} strings", compareOneCFile.size());
        log.info("in pepsi file find {} strings", comparePepsiFile.size());
        return result;
    }

}