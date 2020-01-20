package com.example.library.controller;

import com.example.library.converter.request.AuthorRequestDtoConverter;
import com.example.library.converter.response.AuthorFullResponseDtoConverter;
import com.example.library.converter.response.AuthorMinimalResponseDtoConverter;
import com.example.library.service.AuthorService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.Collections;

import static com.example.library.controller.util.AuthorControllerTestUtil.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AuthorControllerTest {

    private static final String SINGLE_AUTHOR_URL = "/library/author/{id}";
    private static final String AUTHORS_URL = "/library/author";

    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext context;

    @MockBean
    private AuthorFullResponseDtoConverter authorFullResponseDtoConverter;

    @MockBean
    private AuthorRequestDtoConverter authorRequestDtoConverter;

    @MockBean
    private AuthorMinimalResponseDtoConverter authorMinimalResponseDtoConverter;

    @MockBean
    private AuthorService authorServiceMock;

    @Before
    public void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
    }

    public static String asJsonString(final Object object) {
        try {
            return new ObjectMapper().writeValueAsString(object);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void getAllAuthors() throws Exception {
        Mockito.when(authorMinimalResponseDtoConverter.toDto(authorDbAdd)).thenReturn(authorMinimalResponseDto);

        Mockito.when(authorServiceMock.getAllAuthors()).thenReturn(Collections.singletonList(authorDbAdd));

        mockMvc.perform(get(AUTHORS_URL).accept(MediaType.APPLICATION_JSON))
               .andDo(print()).andExpect(status().isOk())
               .andExpect(jsonPath("$.*").isArray())
               .andExpect(jsonPath("$[0].id").value(authorMinimalResponseDto.getId()))
               .andExpect(jsonPath("$[0].name").value(authorMinimalResponseDto.getName()));

        Mockito.verify(authorServiceMock, Mockito.times(1)).getAllAuthors();
        Mockito.verifyNoMoreInteractions(authorServiceMock);
    }

    @Test
    public void getAuthor() throws Exception {
        Mockito.when(authorFullResponseDtoConverter.toDto(authorDbAdd)).thenReturn(authorFullResponseDto);

        Mockito.when(authorServiceMock.getOneAuthor(AUTHOR_ID)).thenReturn(authorDbAdd);

        mockMvc.perform(get(SINGLE_AUTHOR_URL, AUTHOR_ID).accept(MediaType.APPLICATION_JSON))
               .andDo(print())
               .andExpect(status().isOk())
               .andExpect(jsonPath("$.id").value(authorFullResponseDto.getId()))
               .andExpect(jsonPath("$.name").value(authorFullResponseDto.getName()))
               .andExpect(jsonPath("$.books").exists())
               .andExpect(jsonPath("$.books").isArray());
    }

    @Test
    public void addAuthor() throws Exception {
        Mockito.when(authorMinimalResponseDtoConverter.toDto(authorDbAdd)).thenReturn(authorMinimalResponseDto);

        Mockito.when(authorRequestDtoConverter.fromDto(authorRequestDtoAdd)).thenReturn(authorPreAdd);

        Mockito.when(authorServiceMock.addAuthor(authorPreAdd)).thenReturn(authorDbAdd);

        mockMvc.perform(
                post(AUTHORS_URL).content(asJsonString(authorRequestDtoAdd)).contentType(MediaType.APPLICATION_JSON)
                                 .accept(MediaType.APPLICATION_JSON))
               .andDo(print()).andExpect(status().isOk())
               .andExpect(jsonPath("$.name").value(authorMinimalResponseDto.getName()))
               .andExpect(jsonPath("$.id").value(authorMinimalResponseDto.getId()));
    }

    @Test
    public void deleteAuthor() throws Exception {
        Mockito.when(authorMinimalResponseDtoConverter.toDto(authorDbAdd)).thenReturn(authorMinimalResponseDto);

        Mockito.when(authorServiceMock.deleteAuthor(AUTHOR_ID)).thenReturn(authorDbAdd);

        mockMvc.perform(delete(SINGLE_AUTHOR_URL, AUTHOR_ID).accept(MediaType.APPLICATION_JSON))
               .andDo(print())
               .andExpect(status().isOk())
               .andExpect(jsonPath("$.name").value(authorMinimalResponseDto.getName()))
               .andExpect(jsonPath("$.id").value(authorMinimalResponseDto.getId()));
    }

    @Test
    public void updateAuthor() throws Exception {
        Mockito.when(authorMinimalResponseDtoConverter.toDto(authorDbUpdate)).thenReturn(authorResponseDtoUpdate);

        Mockito.when(authorRequestDtoConverter.fromDto(authorRequestDtoUpdate)).thenReturn(authorPreUpdate);

        Mockito.when(authorServiceMock.updateAuthor(AUTHOR_ID, authorPreUpdate)).thenReturn(authorDbUpdate);

        mockMvc.perform(put(SINGLE_AUTHOR_URL, AUTHOR_ID).content(asJsonString(authorRequestDtoUpdate))
                                                         .contentType(MediaType.APPLICATION_JSON)
                                                         .accept(MediaType.APPLICATION_JSON))
               .andDo(print())
               .andExpect(status().isOk())
               .andExpect(jsonPath("$.name").value(authorResponseDtoUpdate.getName()))
               .andExpect(jsonPath("$.id").value(authorResponseDtoUpdate.getId()));
    }
}