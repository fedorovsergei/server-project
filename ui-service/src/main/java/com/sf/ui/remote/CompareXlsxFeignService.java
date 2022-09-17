package com.sf.ui.remote;

import com.sf.ui.remote.dto.CompareXlsxServiceRequestDto;
import com.sf.ui.remote.dto.CompareXlsxServiceResponseDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient(name = "compare-xlsx-service", url = "#{applicationProperties.compareXlsxServiceUrl}", path = "/")
public interface CompareXlsxFeignService {

    @PostMapping("/compare")
    CompareXlsxServiceResponseDto getCompare(@RequestBody CompareXlsxServiceRequestDto requestDto);
}
