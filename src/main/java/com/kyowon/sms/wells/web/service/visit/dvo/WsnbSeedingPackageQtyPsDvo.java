package com.kyowon.sms.wells.web.service.visit.dvo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WsnbSeedingPackageQtyPsDvo {
    String sdingPkgGrpCd;
    String sdingPkgGrpCdNm;
    String vstDt;
    Integer installUpload;
    Integer asUpload;
    Integer bsUpload;
    Integer totalUpload;
    Integer expInstall;
    Integer expAs;
    Integer expBs;
    Integer installCnt;
    Integer asCnt;
    Integer bsCnt;
    Integer useQty;
}
