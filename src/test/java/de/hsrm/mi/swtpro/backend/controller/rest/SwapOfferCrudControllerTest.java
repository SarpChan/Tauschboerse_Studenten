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
import de.hsrm.mi.swtpro.backend.model.Student;
import de.hsrm.mi.swtpro.backend.model.SwapOffer;
import de.hsrm.mi.swtpro.backend.service.repository.SwapOfferRepository;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.Matchers.is;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.sql.Timestamp;

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
                .groupChar('C')
                .build();

        Group toGroup = Group.builder()
                .id(2L)
                .groupChar('X')
                .build();

        Student student = Student.builder()
                .enrolementNumber(1001338)
                .mail("fischiges_gewoell@klabusterbaerenbande.org")
                .build();

        SwapOffer swapOffer = SwapOffer.builder()
                .id(33L)
                .date(new Timestamp(454454121))
                .student(student)
                .fromGroup(fromGroup)
                .toGroup(toGroup)
                .build();

        swapOfferRepository.save(swapOffer);
    }

    @Test
    public void whenCreateSwapOffer_thenSaveSwapOffer() throws Exception{
        Group fromGroup = Group.builder()
                .id(1L)
                .groupChar('A')
                .build();;

        Group toGroup = Group.builder()
                .id(2L)
                .groupChar('B')
                .build();;

        Student student = Student.builder()
                .enrolementNumber(1001339)
                .mail("dude@carpet.co.nz")
                .build();

        SwapOffer swapOffer = SwapOffer.builder()
                .id(22L)
                .date(new Timestamp(455454121))
                .student(student)
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
                .groupChar('Y')
                .build();;

        Group toGroup = Group.builder()
                .id(2L)
                .groupChar('Z')
                .build();;

        Student student = Student.builder()
                .enrolementNumber(1001337)
                .mail("kwama.botschafter@schwibbl.com")
                .build();

        SwapOffer swapOffer = SwapOffer.builder()
                .id(33L)
                .date(new Timestamp(454454121))
                .student(student)
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
        assertThat(swapOfferRepository.findByToGroup(toGroup), hasProperty("id",is(34L)));
    }

    @Test
    public void whenDeleteSwapOffer_thenRemoveSwapOffer() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/swapOffer/delete","33")
                .accept(MediaType.APPLICATION_JSON)).andDo(print())
                .andExpect(status().isOk());
        
        assertThat(swapOfferRepository.findById(33), is(null));
    }

    private static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}