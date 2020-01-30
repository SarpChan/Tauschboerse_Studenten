package de.hsrm.mi.swtpro.backend.model.requestModel;

import lombok.Getter;

public class SwapOfferRequest {
    @Getter
    long id;
    @Getter
    long fromGroupsID;
    @Getter
    long[] toGroupsID;
}
