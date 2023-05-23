package com.kyowon.sms.wells.web.contract.ordermgmt.dvo;

import java.math.BigDecimal;
import java.util.List;

import lombok.Builder;
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
    @Builder
    public static class PdClsfDvo {
        String pdClsfId;
        String pdClsfNm;
        String pdClsfCd;
    }

    @Getter
    @Setter
    public static class PdDvo {
        String pdClsf;
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
        String sellTpNm;
        String sellTpDtlCd;
        String sellTpDtlNm;
        String pdTpCd;
        String pdTpDtlCd;
        String basePdChoYn;
        String crncyDvCd;
        String spaySellPsbYn;
        String vatTpCd;
        String vatTpNm;
        String pdctUprcUseYn;
        String fgptYn;
        String filtEnddt;
        String otscPdYn;
        String rfbshYn;
        String istPcsvTpCd;
        String mpactYn;
        String istBzsCd;
        String stocMngtOjYn;
        String pcsvBzsCd;
        String svAlncBzsCd;
        String ordPrmitYm;
        String bzsSppYn;
        String bfPdCd;
        String cntrPtrmDvCd;
        String dutyUsePtrmDvCd;
        String rglrSppCntrDvCd;
        String rglrSppDutyPtrmDvCd;
        String rglrSppPrmMcn;
        String rglrSppBilDvCd;
        String rglrSppConsTpcd;
        String rglrSppPdctConsQty;
        String pdChoLmYn;
        String pdChoQty;
        String laboEuYn;
        String chdvcPrmitYn;
        String fgptTpYn;
        String asRcpPrmitYn;
        String frisuAsYn;
        String rntf;
        String ackmtPerfRt;
        String ackmtCt;
        String feeAmt;
        String hdCndtCd;
        String rdsYn;
        String redfYn;
        String anaFactVal1;
        String anaFactVal2;
        String pvdaYn;
        String hcrMshTpCd;
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
