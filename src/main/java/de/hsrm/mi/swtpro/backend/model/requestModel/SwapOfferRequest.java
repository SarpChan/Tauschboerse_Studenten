package de.hsrm.mi.swtpro.backend.model.requestModel;

import lombok.Getter;

public class SwapOfferRequest {
    @Getter
    int userID;
    @Getter
    long fromGroupsID;
    @Getter
    long[] toGroupsID;
}
