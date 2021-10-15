package sunghs.openapi.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import sunghs.openapi.model.RequestDto;
import sunghs.openapi.model.ResponseDto;
import sunghs.openapi.service.OpenApiService;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class OpenApiController {

    private final OpenApiService openApiService;

    @PostMapping("/post")
    public ResponseDto post(@RequestBody final RequestDto requestDto) {
        return openApiService.run(requestDto);
    }

    @GetMapping("/get")
    public ResponseDto get(final RequestDto requestDto) {
        return openApiService.run(requestDto);
    }
}
