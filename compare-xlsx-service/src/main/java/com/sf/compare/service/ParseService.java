package com.sf.compare.service;

import com.sf.compare.dto.RequestDto;
import com.sf.compare.dto.ResponseDto;
import com.sf.compare.dto.CompareField;
import com.sf.compare.parse.ParseOneCFile;
import com.sf.compare.parse.ParsePepsiFile;
import com.google.common.collect.Sets;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.binary.Base64;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.HashSet;
import java.util.Set;

@Slf4j
@Service
@RequiredArgsConstructor
public class ParseService {

    private final ParsePepsiFile parsePepsiFile;
    private final ParseOneCFile parseOneCFile;

    public ResponseDto getParse(@NotNull RequestDto requestDto) {
        try {
            Set<CompareField> compareOneCFile = parseOneCFile.parse(parseBase64ToFile(requestDto.getOneCFile()), requestDto.getOneCNamePosition(), requestDto.getOneCPlusPosition(), requestDto.getOneCMinusPosition());
            Set<CompareField> comparePepsiFile = parsePepsiFile.parse(parseBase64ToFile(requestDto.getPepsiFileBase64()), requestDto.getPepsiNamePosition(), requestDto.getPepsiCountPosition());

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

    @SneakyThrows
    private InputStream parseBase64ToFile(String base64) {
        byte[] data = Base64.decodeBase64(base64);
        return new ByteArrayInputStream(data);
    }
}