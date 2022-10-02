package com.sf.ui.service;

import com.sf.ui.entity.WeatherHistory;
import com.sf.ui.remote.CompareXlsxFeignService;
import com.sf.ui.remote.dto.CompareXlsxServiceRequestDto;
import com.sf.ui.remote.dto.CompareXlsxServiceResponseDto;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Slf4j
@Service
@RequiredArgsConstructor
public class RemoteService {

    private final CompareXlsxFeignService compareXlsxFeignService;
    private final WeatherService weatherService;

    public CompareXlsxServiceResponseDto startCallParse(@NonNull MultipartFile oneCFile,
                                                        @NonNull MultipartFile pepsiFile,
                                                        @NonNull Integer oneCNamePosition,
                                                        @NonNull Integer oneCPlusPosition,
                                                        @NonNull Integer oneCMinusPosition,
                                                        @NonNull Integer pepsiNamePosition,
                                                        @NonNull Integer pepsiCountPosition) {
        try {
            log.info("start call remote method to parse file");
            CompareXlsxServiceResponseDto result = compareXlsxFeignService.getCompare(CompareXlsxServiceRequestDto
                    .builder()
                    .oneCFile(Base64.encodeBase64String(oneCFile.getBytes()))
                    .oneCNamePosition(oneCNamePosition)
                    .oneCPlusPosition(oneCPlusPosition)
                    .oneCMinusPosition(oneCMinusPosition)
                    .pepsiFileBase64(Base64.encodeBase64String(pepsiFile.getBytes()))
                    .pepsiNamePosition(pepsiNamePosition)
                    .pepsiCountPosition(pepsiCountPosition)
                    .build());
            log.info("remote method successful return result");
            return result;
        } catch (Exception e) {
            log.error("something go wrong");
            e.printStackTrace();
            throw new IllegalArgumentException();
        }
    }

    public WeatherHistory getWeather() {
        try {
            log.info("start call remote method to get weather");
            WeatherHistory result = weatherService.getWeather();
            log.info("remote method successful return result");
            return result;
        } catch (Exception e) {
            log.error("something go wrong");
            e.printStackTrace();
            throw new IllegalArgumentException();
        }
    }
}
