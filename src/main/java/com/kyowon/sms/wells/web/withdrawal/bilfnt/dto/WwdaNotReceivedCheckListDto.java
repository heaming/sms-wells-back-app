package com.kyowon.sms.wells.web.withdrawal.bilfnt.dto;

import com.sds.sflex.common.utils.DbEncUtil;
import com.sds.sflex.common.utils.StringUtil;
import io.swagger.annotations.ApiModel;

public class WwdaNotReceivedCheckListDto {

    @ApiModel("WwdaNotReceivedCheckListDto-SearchAftnBilNrcvListReq")
    public record SearchAftnBilNrcvListReq(
        String bilDt,
        String fntDvCd
    ) {}

    @ApiModel("WwdaNotReceivedCheckListDto-SearchAftnBilNrcvListRes")
    public record SearchAftnBilNrcvListRes(
        String autoFntClsf,
        String bnkCd,
        String bnkNm,
        String ct
    ) {}

    @ApiModel("WwdaNotReceivedCheckListDto-SearchAftnDpNcrtCheckListReq")
    public record SearchAftnDpNcrtCheckListReq(
        String bilDt,
        String fntDvCd
    ) {}

    @ApiModel("WwdaNotReceivedCheckListDto-SearchAftnDpNcrtCheckListRes")
    public record SearchAftnDpNcrtCheckListRes(
        // 자동이체 입금 미생성 체크 목록
        String aftnClsf,
        String cntrNo,
        String cntrSn,
        String bilDt,
        String sellTpCd,
        String dpAmt
    ) {}

    @ApiModel("WwdaNotReceivedCheckListDto-SearchAftnSlPerfCheckInqrRes")
    public record SearchAftnSlPerfCheckInqrRes(
        // 매출실적 입금 전용 누락건
        String sellTpNm,
        String baseYm,
        String cntrNo,
        String cntrSn,
        String cntr,
        String rentalNmn,
        String thmIntamDpAmt,
        String thmIstmRfndAmt,
        String rveAmt,
        String dpMesCd
    ) {
        public SearchAftnSlPerfCheckInqrRes {
            if (!StringUtil.isEmpty(cntrNo) && !StringUtil.isEmpty(cntrSn)) {
                cntr = cntrNo + "-" + cntrSn;
            }
        }
    }

    @ApiModel("WwdaNotReceivedCheckListDto-SearchResultBundleErrorRes")
    public record SearchResultBundleErrorRes(
        // 묶음 출금 오등록
        String mstrSellTpCd,
        String dgCntrNo,
        String mstrDpTpCd,
        String subSellTpCd,
        String subodCntrNo,
        String subDpTpCd,
        String errTyp
    ) {}

}
