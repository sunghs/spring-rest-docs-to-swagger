package sunghs.openapi.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import sunghs.openapi.model.RequestDto;
import sunghs.openapi.model.ResponseDto;

@Slf4j
@Service
@RequiredArgsConstructor
public class OpenApiService {

    public ResponseDto run(final RequestDto requestDto) {
        log.info("request : {}", requestDto.toString());
        return new ResponseDto("OK", 200);
    }
}
