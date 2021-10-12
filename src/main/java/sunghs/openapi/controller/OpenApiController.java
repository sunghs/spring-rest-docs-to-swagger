package sunghs.openapi.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sunghs.openapi.model.RequestDto;
import sunghs.openapi.model.ResponseDto;
import sunghs.openapi.service.OpenApiService;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class OpenApiController {

    private final OpenApiService openApiService;

    @PostMapping("/post")
    public ResponseDto run(@RequestBody final RequestDto requestDto) {
        return openApiService.run(requestDto);
    }
}
