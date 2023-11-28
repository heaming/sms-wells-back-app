package com.kyowon.sms.wells.web.closing.performance.dto;

import io.swagger.annotations.ApiModel;

public class WdccPressurePumpDto {

    @ApiModel(value = "WdccPressurePumpDto-SearchConfirmManagementReq")
    public record SearchConfirmManagementReq(
        String fstVstFshDtFrom, /*작업일자star*/
        String fstVstFshDtTo, /*작업일자*/
        String cnfmYn, /*확정여부*/
        String itmPdCd, /*제품코드*/
        String svBizDclsfCd, /*업무유형*/
        String svBizHclsfCd /*사용/회수*/
    ) {}

    @ApiModel(value = "WdccPressurePumpDto-SearchConfirmManagementRes")
    public record SearchConfirmManagementRes(
        String cnfmYn, /*확정여부*/
        String errMsg, /*오류상세*/
        String cntrNo, /*계약번호*/
        String cntrSn, /*계약일련번호*/
        String cstKnm, /*고객명*/
        String filtSellTpCd, /*고객유형*/
        String adnSvSn, /*일련번호*/
        String itmPdCd, /*제품코드*/
        String basePdCd, /*제품코드*/
        String pdAbbrNm, /*제품명*/
        String pdNm, /*가압펌프자재*/
        String ogNm, /*센터*/
        String wrkTypNm, /*업무유형*/
        String gubn, /*사용/회수*/
        String lctamt, /*제품가격*/
        String svMcn, /*서비스기간*/
        String dutyUseMcn, /*의무기간*/
        String fnlVstFshDt, /*작업일자*/
        String rcpdt, /*접수일자*/
        String istDt, /*설치일자*/
        String reqdDt, /*철거일자*/
        String rsgDt, /*해약일자*/
        String fnlItmGdCd, /*등급*/
        String useQty, /*수량*/
        String chagGbNm, /*구분*/
        String prtnrKnm, /*엔지니어*/
        String tCnt, /*누적재고*/
        String fstRgstDtm, /*등록일자*/
        String fstRgstUsrId, /*등록자*/
        String fnlMdfcDtm, /*수정일자*/
        String fnlMdfcUsrId, /*수정자*/
        String adnSvStrtYm, /*부가서비스시작년월*/
        String istDuedt, /*설치예정일자*/
        String svBizDclsfCd, /*업무유형*/
        String pdctPdCd /*제품상품코드*/

    ) {}

    @ApiModel(value = "WdccPressurePumpDto-SearchSalesBaseReq")
    public record SearchSalesBaseReq(
        String slYm /*작업일자star*/
    ) {}

    @ApiModel(value = "WdccPressurePumpDto-SearchSalesBaseRes")
    public record SearchSalesBaseRes(
        String cntrNo, /*계약번호*/
        String cntrSn, /*계약일련번호*/
        String pdNm, /* 상품명*/
        String adnSvCsAmt, /*매출금액*/
        String istDt, /* 설치일자*/
        String reqdDt, /* 철거일자 */
        String mpyBsdt, /* 이체일자*/
        String dpTpCd, /* 이체구분*/
        String copnDvCd, /* 개인/법인*/
        String alncmpCd, /* 상조제휴*/
        String alncStatTpCd, /* 제휴상태*/
        String lcpgub, /* 선납여부*/
        String lcam36, /* 선수금*/
        String lcmon1, /* 렌탈기간*/
        String lcamt1, /* 렌탈료*/
        String lcram1 /* 렌탈할인*/
    ) {}

    @ApiModel("WdccPressurePumpDto-SaveReq")
    public record SaveReq(
        String cntrNo, /* 계약번호*/
        String cntrSn, /* 계약일련번호*/
        String itmPdCd, /* 제품상품코드*/
        String lctamt, /* 제품가격*/
        String adnSvStrtYm, /* 부가서비스시작년월*/
        String rcpdt, /* 접수일자*/
        String istDuedt, /* 설치예정일자*/
        String istDt, /* 설치일자*/
        String fnlVstFshDt, /* 작업일자*/
        String svBizHclsfCd, /* 사용/회수*/
        String pdctPdCd /* 제품상품코드*/

    ) {}

    @ApiModel("WdccPressurePumpDto-RemoveReq")
    public record RemoveReq(
        String cntrNo, /*계약번호*/
        String adnSvSn /*부가서비스일련번호*/
    ) {}

}
