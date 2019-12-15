package de.hsrm.mi.swtpro.backend.model.requestModel;

import lombok.Getter;

public class SwapOfferRequest {
    @Getter
    int ownerEnrollmentNumber;
    @Getter
    long fromGroupsID;
    @Getter
    long[] toGroupsID;
}
