package mandu.galbi.rediscache;




import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

import com.fasterxml.jackson.databind.ObjectMapper;
import mandu.galbi.rediscache.common.RestDocsConfiguration;
import mandu.galbi.rediscache.domain.Item;
import mandu.galbi.rediscache.service.ItemService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.restdocs.payload.JsonFieldType;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.restdocs.headers.HeaderDocumentation.*;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.restdocs.snippet.Attributes.key;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@WebMvcTest(ItemController.class)
@Import(RestDocsConfiguration.class) // 테스트 설정 import
@AutoConfigureRestDocs // 알아서 설정해준다~!
public class ItemControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ItemService itemService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    @DisplayName("특정 ID값의 Item 불러오기")
    void Item_By_ID_Get_test() throws Exception {
        given(itemService.getItemForId(anyString())).willReturn(new Item("abc", "test"));
        this.mockMvc.perform(get("/item/abc").accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andDo(document("item-get-by-id",
                        requestHeaders( // 요청 헤더
                                headerWithName(HttpHeaders.ACCEPT).description(MediaType.APPLICATION_JSON_VALUE)
                        ),
                        responseHeaders( // 응답 헤더
                                headerWithName(HttpHeaders.CONTENT_TYPE).description(MediaType.APPLICATION_JSON_VALUE)
                        ),
                        responseFields( // 응답 필드
                                fieldWithPath("id").description("Id of item"),
                                fieldWithPath("description").description("description of item")
                        ))
                )
                .andExpect(jsonPath("$.id").value(containsString("abc")));

    }

}
