package sunghs.openapi;

import com.epages.restdocs.apispec.MockMvcRestDocumentationWrapper;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.restdocs.RestDocumentationContextProvider;
import org.springframework.restdocs.RestDocumentationExtension;
import org.springframework.restdocs.mockmvc.MockMvcRestDocumentation;
import org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders;
import org.springframework.restdocs.payload.JsonFieldType;
import org.springframework.restdocs.payload.PayloadDocumentation;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestConstructor;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import sunghs.openapi.model.RequestDto;

import java.nio.charset.StandardCharsets;

@Slf4j
@SpringBootTest
@ActiveProfiles("local")
@RequiredArgsConstructor
@ExtendWith(RestDocumentationExtension.class)
@TestConstructor(autowireMode = TestConstructor.AutowireMode.ALL)
class OpenApiTests {

    private final ObjectMapper objectMapper;

    private MockMvc mockMvc;

    @BeforeEach
    void setUp(WebApplicationContext webApplicationContext, RestDocumentationContextProvider contextProvider) {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext)
                .apply(MockMvcRestDocumentation.documentationConfiguration(contextProvider))
                .build();
    }

    @Test
    @DisplayName("post 문서화 테스트")
    void postTest() throws Exception {
        // given
        RequestDto requestDto = new RequestDto();
        requestDto.setId(5L);
        requestDto.setName("test");

        String request = objectMapper.writeValueAsString(requestDto);

        // when & then
        mockMvc.perform(RestDocumentationRequestBuilders.post("/api/post")
                .content(request)
                .characterEncoding(StandardCharsets.UTF_8.name())
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcRestDocumentationWrapper.document("post", "설명부분", "요약 노출부분",
                        PayloadDocumentation.requestFields(
                                PayloadDocumentation.fieldWithPath("id").type(JsonFieldType.NUMBER).description("아이디"),
                                PayloadDocumentation.fieldWithPath("name").type(JsonFieldType.STRING).description("이름")
                        ),
                        PayloadDocumentation.responseFields(
                                PayloadDocumentation.fieldWithPath("result").type(JsonFieldType.STRING).description("결과"),
                                PayloadDocumentation.fieldWithPath("code").type(JsonFieldType.NUMBER).description("결과코드")
                        )));
    }
}
