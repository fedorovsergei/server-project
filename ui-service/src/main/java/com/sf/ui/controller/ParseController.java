package com.sf.ui.controller;

import com.sf.ui.remote.dto.CompareXlsxServiceResponseDto;
import com.sf.ui.service.RemoteService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Slf4j
@Controller
@RequestMapping("/parse")
@RequiredArgsConstructor
public class ParseController {

    private final RemoteService remoteService;

    @Operation
    @GetMapping("/get")
    public String parseFile() {
        log.info("[API] start call GET method /parse/get");
        return "startCompareFile";
    }

    @Operation
    @PostMapping("/get")
    public String parseFile(@RequestParam("oneCFile") MultipartFile oneCFile,
                            @RequestParam("pepsiFile") MultipartFile pepsiFile,
                            @RequestParam("oneCNamePosition") Integer oneCNamePosition,
                            @RequestParam("oneCPlusPosition") Integer oneCPlusPosition,
                            @RequestParam("oneCMinusPosition") Integer oneCMinusPosition,
                            @RequestParam("pepsiNamePosition") Integer pepsiNamePosition,
                            @RequestParam("pepsiCountPosition") Integer pepsiCountPosition,
                            Model model) {
        log.info("[API] start call POST method /parse/get");
        try {
            CompareXlsxServiceResponseDto result =
                    remoteService.startCallParse(oneCFile, pepsiFile, oneCNamePosition, oneCPlusPosition, oneCMinusPosition, pepsiNamePosition, pepsiCountPosition);
            if (result != null) {
                model.addAttribute("resultCompare", result);
                return "resultCompareFile";
            }
        } catch (Exception e) {
            log.error("exception in ui service");
        }
        return "error";
    }
}
