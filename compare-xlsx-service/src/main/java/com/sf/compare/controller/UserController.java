package com.sf.compare.controller;

import com.sf.compare.dto.RequestDto;
import com.sf.compare.dto.ResponseDto;
import com.sf.compare.service.ParseService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
public class UserController {

    private final ParseService parseService;

    @PostMapping("/compare")
    public ResponseDto getCompare(@RequestBody RequestDto requestDto) {
        log.info("[API] start compere files");
        ResponseDto result = parseService.getParse(requestDto);
        log.info("return {}", result);
        return result;
    }
}
