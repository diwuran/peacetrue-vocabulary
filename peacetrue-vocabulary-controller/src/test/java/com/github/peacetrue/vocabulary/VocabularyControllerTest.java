package com.github.peacetrue.vocabulary;

import com.github.peacetrue.spring.util.BeanUtils;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.util.Map;
import java.util.stream.Collectors;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * @author xiayx
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = TestControllerVocabularyAutoConfiguration.class)
@AutoConfigureMockMvc
@ActiveProfiles("vocabulary-controller-test")
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class VocabularyControllerTest {

    public static String basePath = "/vocabularys" ;

    @Autowired
    private MockMvc mockMvc;

    private static MultiValueMap<String, String> to(Object object) {
        LinkedMultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        Map<String, Object> map = BeanUtils.map(object);
        params.setAll(map.entrySet().stream().filter(entry -> entry.getValue() != null).collect(Collectors.toMap(Map.Entry::getKey, entry -> String.valueOf(entry.getValue()))));
        return params;
    }

    @Test
    public void test_01_query() throws Exception {
        this.mockMvc.perform(
                MockMvcRequestBuilders.get(basePath + "?page=0")
                        .accept(MediaType.APPLICATION_JSON)
        )
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$.content.size()").value(1));
    }

    @Test
    public void test_010_query() throws Exception {
        this.mockMvc.perform(
                MockMvcRequestBuilders.get(basePath)
                        .accept(MediaType.APPLICATION_JSON)
        )
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$.size()").value(1));
    }

    @Test
    public void test_02_get() throws Exception {
        this.mockMvc.perform(
                MockMvcRequestBuilders.get(basePath + "/1")
                        .accept(MediaType.APPLICATION_JSON)
        )
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$").exists());
    }

    @Test
    public void test_03_add() throws Exception {
        this.mockMvc.perform(
                MockMvcRequestBuilders.post(basePath + "")
                        .params(to(VocabularyServiceImplTest.RECORD_ADD))
                        .accept(MediaType.APPLICATION_JSON)
        )
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$.id").value(2));
    }

    @Test
    public void test_04_modify() throws Exception {
        VocabularyModify modify = new VocabularyModify();
        modify.setId(VocabularyServiceImplTest.INIT_ID);
        this.mockMvc.perform(
                MockMvcRequestBuilders.put(basePath)
                        .params(to(modify))
                        .accept(MediaType.APPLICATION_JSON)
        )
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$").value(1));
    }

    @Test
    public void test_05_delete() throws Exception {
        this.mockMvc.perform(
                MockMvcRequestBuilders.delete(basePath + "/1")
                        .accept(MediaType.APPLICATION_JSON)
        )
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$").value(1));
    }
}