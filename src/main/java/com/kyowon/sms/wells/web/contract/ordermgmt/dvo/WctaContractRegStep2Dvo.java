package com.kyowon.sms.wells.web.contract.ordermgmt.dvo;

import java.math.BigDecimal;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WctaContractRegStep2Dvo {
    String cntrNo;
    WctaContractBasDvo bas;
    List<WctaContractDtlDvo> dtls;
    List<PdClsfDvo> pdClsf;
    List<PdDvo> products;

    @Getter
    @Setter
    public static class PdClsfDvo {
        String pdClsfId;
        String pdClsfNm;
        String pdClsfCd;
    }

    @Getter
    @Setter
    public static class PdDvo {
        String mclsfVal;
        String lclsfVal;
        String pdCd;
        String pdNm;
        String pdHclsfId;
        String pdMclsfId;
        String pdLclsfId;
        String pdDclsfId;
        String pdAbbrNm;
        String cstBasePdAbbrNm;
        String pdEplCn;
        String sellTpCd;
        String sellTpDtlCd;
        String pdTpCd;
        String pdTpDtlCd;
        String basePdChoYn;
        String crncyDvCd;
        String spaySellPsbYn;
        String vatTpCd;
        String pdctUprcUseYn;
        String fgptYn;
        String lrnnAgeUnitCd;
        String minLrnnAge;
        String maxLrnnAge;
        String sellChnlId;
        String alrpyDvCd;
        String pdWgt;
        String prrCntrPdOjYn;
        String rcpBaseStrtdt;
        String rcpBaseEnddt;
        String cnfmBaseStrtdt;
        String cnfmBaseEnddt;
        String lrnnLvGrpDvCd;
        String smtChgYn;
        String chdvcPrmitYn;
        String islndIncmdcYn;
        String istmMcn;
        String smtMlgUseYn;
        String sppDvCd;
        String misuDvCd;
        String pdChip1;
        String pdChip2;
        String pdQty;
        List<PdDetailDvo> suscMms;
        List<PdDetailDvo> lrnnLvs;
        List<PdDetailDvo> strtLvs;
    }

    @Getter
    @Setter
    public static class PdDetailDvo {
        String codeId;
        String codeName;
    }

    @Getter
    @Setter
    public static class PdAmtDvo {
        Long fnlAmt;
        Long vat;
        Long ackmtPerfAmt;
        BigDecimal ackmtPerfRt;
        Long cvtPerfAmt;
        String pdPrcFnlDtlId;
        String fxamFxrtDvCd;
        Integer verSn;
        Long ctrVal;
        String pdPrcId;
    }

    @Getter
    @Setter
    public static class PdSvcDvo {
        String pdNm;
        String pdCd;
        String pdPrpVal11;
        String pdPrpVal12;
        String pdRelId;
        String basePdCd;
        String ojPdCd;
        String pdRelTpCd;
    }
}
