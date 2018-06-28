package com.optimusprime.springrecipeapp.controllers;

import com.optimusprime.springrecipeapp.commands.RecipeCommand;
import com.optimusprime.springrecipeapp.services.ImageService;
import com.optimusprime.springrecipeapp.services.RecipeService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.Model;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class ImageControllerTest {

    ImageController imageController;

    @Mock
    ImageService imageService;

    @Mock
    RecipeService recipeService;

    @Mock
    Model model;

    MockMvc mockMvc;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        imageController = new ImageController(recipeService, imageService);
        mockMvc = MockMvcBuilders.standaloneSetup(imageController).build();
    }

    @Test
    public void showUploadForm() throws Exception {
        //given
        RecipeCommand recipeCommand = new RecipeCommand();
        recipeCommand.setId(1L);

        when(recipeService.findCommandById(anyLong())).thenReturn(recipeCommand);

        //when
        mockMvc.perform(get("/recipe/1/image"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.model().attributeExists("recipe"));

        verify(recipeService, times(1)).findCommandById(anyLong());

    }

    @Test
    public void testHandleImagePost() throws Exception {
        MockMultipartFile mockMultipartFile =
                new MockMultipartFile("imagefile", "testing.txt", "text/plain",
                        "Optimus Prime".getBytes());
        mockMvc.perform(multipart("/recipe/1/image").file(mockMultipartFile))
                .andExpect(status().is3xxRedirection())
                .andExpect(header().string("Location","/recipe/1/show"));

        verify(imageService,times(1)).saveImageFile(anyLong(),any());

    }

    @Test
    public void testRenderImageFromDB() throws Exception {
        //given
        RecipeCommand recipeCommand= new RecipeCommand();
        recipeCommand.setId(1L);

        String s = "fake image test";
        Byte[] byteBoxed =  new Byte[s.getBytes().length];

        int i = 0;

        for(byte primByte: s.getBytes()){
            byteBoxed[i++] = primByte;
        }

        recipeCommand.setImage(byteBoxed);

        when(recipeService.findCommandById(anyLong())).thenReturn(recipeCommand);

        //when
        MockHttpServletResponse response = mockMvc.perform(get("/recipe/1/recipeimage"))
                .andExpect(status().isOk())
                .andReturn().getResponse();

        byte[] responseByte =  response.getContentAsByteArray();

        assertEquals(s.getBytes().length,responseByte.length);
    }
}