package com.kyowon.sms.wells.web.contract.ordermgmt.dvo;

import com.sds.sflex.system.config.annotation.DBEncField;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WctaPifDestructionProcsDvo {
    private String cntrNo;
    @DBEncField
    private String space;
}
