package com.sf.ui.service;

import com.sf.ui.remote.CompareXlsxFeignService;
import com.sf.ui.remote.WeatherFeignService;
import com.sf.ui.remote.dto.CompareXlsxServiceRequestDto;
import com.sf.ui.remote.dto.CompareXlsxServiceResponseDto;
import com.sf.ui.remote.dto.WeatherServiceResponseDto;
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
    private final WeatherFeignService weatherFeignService;

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

    public WeatherServiceResponseDto getWeather() {
        try {
            log.info("start call remote method to get weather");
            WeatherServiceResponseDto result = weatherFeignService.getWeather();
            log.info("remote method successful return result");
            return result;
        } catch (Exception e) {
            log.error("something go wrong");
            e.printStackTrace();
            throw new IllegalArgumentException();
        }
    }
}
