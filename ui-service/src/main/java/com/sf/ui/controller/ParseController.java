package com.sf.ui.controller;

import com.sf.ui.remote.CompareXlsxFeignService;
import com.sf.ui.remote.dto.CompareXlsxServiceRequestDto;
import com.sf.ui.remote.dto.CompareXlsxServiceResponseDto;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.codec.binary.Base64;
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

    private final CompareXlsxFeignService compareXlsxFeignService;

    @GetMapping("/get")
    @SneakyThrows
    public String getWeather() {
        return "compare";
    }


    @PostMapping("/get")
    @SneakyThrows
    public String getWeather(@RequestParam("oneCFile") MultipartFile oneCFile,
                             @RequestParam("pepsiFile") MultipartFile pepsiFile,
                             @RequestParam("oneCNamePosition") Integer oneCNamePosition,
                             @RequestParam("oneCPlusPosition") Integer oneCPlusPosition,
                             @RequestParam("oneCMinusPosition") Integer oneCMinusPosition,
                             @RequestParam("pepsiNamePosition") Integer pepsiNamePosition,
                             @RequestParam("pepsiCountPosition") Integer pepsiCountPosition,
                             Model model) {
        System.out.println(oneCFile.getName());
        System.out.println(pepsiFile.getName());
        CompareXlsxServiceResponseDto resultCompare = CompareXlsxServiceResponseDto.builder().build();

        resultCompare = compareXlsxFeignService.getCompare(CompareXlsxServiceRequestDto
                .builder()
                .oneCFile(Base64.encodeBase64String(oneCFile.getBytes()))
                .oneCNamePosition(oneCNamePosition)
                .oneCPlusPosition(oneCPlusPosition)
                .oneCMinusPosition(oneCMinusPosition)
                .pepsiFileBase64(Base64.encodeBase64String(pepsiFile.getBytes()))
                .pepsiNamePosition(pepsiNamePosition)
                .pepsiCountPosition(pepsiCountPosition)
                .build());
        System.out.println(resultCompare);
        model.addAttribute("resultCompare", resultCompare);
        return "resultCompare";
    }
}
