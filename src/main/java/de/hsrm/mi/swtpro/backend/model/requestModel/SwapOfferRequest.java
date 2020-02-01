package de.hsrm.mi.swtpro.backend.model.requestModel;

import lombok.Getter;

/**
 * A SwapOfferRequest contains the ids of the two groups that are part of a swapOffer
 */
public class SwapOfferRequest {
    @Getter
    long fromGroupsID;
    @Getter
    long[] toGroupsID;
}
