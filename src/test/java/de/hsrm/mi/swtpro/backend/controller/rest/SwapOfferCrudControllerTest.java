package de.hsrm.mi.swtpro.backend.controller.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import de.hsrm.mi.swtpro.backend.model.Group;
import de.hsrm.mi.swtpro.backend.model.SwapOffer;
import de.hsrm.mi.swtpro.backend.service.repository.SwapOfferRepository;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.Matchers.is;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@AutoConfigureMockMvc
@RunWith(SpringRunner.class)
public class SwapOfferCrudControllerTest {

    @Autowired
    private MockMvc mockMvc;
    
    @Autowired
    SwapOfferRepository swapOfferRepository;

    @Before
    public void setUp(){
        Group fromGroup = Group.builder()
                .id(1L)
                .groupChar('C');

        Group toGroup = Group.builder()
                .id(2L)
                .groupChar('X');

        SwapOffer swapOffer = SwapOffer.builder()
                .id(33L)
                .date("12.03.1999")
                .student("Ein Student")
                .fromGroup(fromGroup)
                .toGroup(toGroup)
                .build();

        swapOfferRepository.save(swapOffer);
    }

    @Test
    public void whenCreateSwapOffer_thenSaveSwapOffer() throws Exception{
        Group fromGroup = Group.builder()
                .id(1L)
                .groupChar('A');

        Group toGroup = Group.builder()
                .id(2L)
                .groupChar('B');

        SwapOffer swapOffer = SwapOffer.builder()
                .id(22L)
                .date("31.12.2019")
                .student("Dude")
                .fromGroup(fromGroup)
                .toGroup(toGroup)
                .build();

        mockMvc.perform(MockMvcRequestBuilders
                .post("/swapOffer/create")
                .content(asJsonString(swapOffer))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.swapOffer/create").exists());

        assertThat(swapOfferRepository.findByFromGroup(fromGroup), hasProperty("id",is(22L)));
        assertThat(swapOfferRepository.findByToGroup(toGroup), hasProperty("id",is(22L)));
    }

    @Test
    public void whenReadSwapOffer_thenReturnSwapOffer() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/swapOffer/read","33")
                .accept(MediaType.APPLICATION_JSON)).andDo(print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.swapOfferId").value(33));
        
        assertThat(swapOfferRepository.findById(33L), hasProperty("fromGroup",is('C')));
        assertThat(swapOfferRepository.findById(33L), hasProperty("fromGroup",is('X')));
    }

    @Test
    public void whenUpdateSwapOffer_thenChangeSwapOffer() throws Exception {
        Group fromGroup = Group.builder()
                .id(1L)
                .groupChar('Y');

        Group toGroup = Group.builder()
                .id(2L)
                .groupChar('Z');

        SwapOffer swapOffer = SwapOffer.builder()
                .id(33L)
                .date("29.10.1985")
                .student("Kwama Botschafter")
                .fromGroup(fromGroup)
                .toGroup(toGroup)
                .build();

        mockMvc.perform(MockMvcRequestBuilders
                .post("/swapOffer/update")
                .content(asJsonString(swapOffer))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.swapOffer/update").exists());

        assertThat(swapOfferRepository.findByFromGroup(fromGroup), hasProperty("id",is(33L)));
        assertThat(swapOfferRepository.findByToGroup(toGroup), hasProperty("id",is(33L)));
    }

    @Test
    public void whenDeleteSwapOffer_thenRemoveSwapOffer() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/swapOffer/delete","33")
                .accept(MediaType.APPLICATION_JSON)).andDo(print())
                .andExpect(status().isOk());
        
        assertThat(swapOfferRepository.findById(33), is(null)); //TODO: geht das mit is(null)?
    }

    private static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}