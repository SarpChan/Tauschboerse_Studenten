package de.hsrm.mi.swtpro.backend.controller.rest;

import de.hsrm.mi.swtpro.backend.controller.exceptions.SwapOfferNotFoundException;
import de.hsrm.mi.swtpro.backend.model.SwapOffer;
import de.hsrm.mi.swtpro.backend.service.messagebroker.MessageSender;
import de.hsrm.mi.swtpro.backend.service.repository.SwapOfferRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.net.URISyntaxException;

/**
 * Basic CRUD Controller for insert, update, read and delete functionality at the model.
 */
@RestController
@RequestMapping("/rest")
public class SwapOfferCrudController {

    @Autowired
    SwapOfferRepository swapOfferRepository;

    @Autowired
    MessageSender messageSender;

    /**
     * Insert a SwapOffer object into the Model
     *
     * @param swapOffer recieves a SwapOffer class via POST request
     * @return SwapOffer object
     * @throws URISyntaxException
     */
    @PostMapping(path = "/swapOffer/create", consumes = "application/json", produces = MediaType.APPLICATION_JSON_VALUE)
    public SwapOffer createSwapOffer(@RequestBody SwapOffer swapOffer) throws URISyntaxException {
        swapOfferRepository.save(swapOffer);
        messageSender.sendSwapOfferMessage(swapOffer, "add");
        return swapOffer;
    }

    /**
     * Update a SwapOffer object into the Model
     * @param swapOffer recieves a SwapOffer class via POST request
     * @return SwapOffer object
     * @throws SwapOfferNotFoundException
     */
    @PostMapping(path = "/swapOffer/update", consumes = "application/json", produces = MediaType.APPLICATION_JSON_VALUE)
    public SwapOffer updateSwapOffer(@RequestBody SwapOffer swapOffer) throws SwapOfferNotFoundException {
        return swapOfferRepository.save(swapOffer);

    }

    /**
     * Find a SwapOffer object from the Model
     * @param swapOfferId request id
     * @return SwapOffer object
     * @throws SwapOfferNotFoundException
     */
    @GetMapping(path = "/swapOffer/read/{swapOfferId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public SwapOffer findSwapOffer(@PathVariable long swapOfferId) throws SwapOfferNotFoundException {
        if (swapOfferRepository.findById(swapOfferId).isPresent()) {
            return swapOfferRepository.findById(swapOfferId).get();
        } else {
            throw new SwapOfferNotFoundException("SwapOffer not found");
        }
    }

    /**
     * Remove a SwapOffer object from the Model
     * @param swapOfferId Path variable with id for deletion
     * @return SwapOffer object or
     * @throws SwapOfferNotFoundException
     */
    @DeleteMapping(path = "/swapOffer/delete/{swapOfferId}", consumes = "application/json")
    public void deleteSwapOffer(@PathVariable long swapOfferId) throws SwapOfferNotFoundException {
        if (swapOfferRepository.findById(swapOfferId).isPresent()) {
            messageSender.sendSwapOfferMessage(swapOfferRepository.getOne(swapOfferId), "delete");
            swapOfferRepository.deleteById(swapOfferId);  
        } else {
            throw new SwapOfferNotFoundException("SwapOffer not found");
        }
    }

}
