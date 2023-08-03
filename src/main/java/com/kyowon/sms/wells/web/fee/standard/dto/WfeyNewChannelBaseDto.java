package com.kyowon.sms.wells.web.fee.standard.dto;

import com.sds.sflex.system.config.masking.MaskRequired;
import com.sds.sflex.system.config.masking.MaskingType;

public class WfeyNewChannelBaseDto {

    public record SearchNewChannelBaseReq(
        String apyStrtdt,
        String pdCd,
        String sellTpCd,
        String svPrd,
        String pmotCd,
        String feecDvCd

    ) {}

    public record SearchNewChannelBaseRes(
        Long mtrSn,              /* 자료일련번호 */
        String sellTpCd,         /* 판매유형코드 */
        String pdCd,             /* 상품코드 */
        String pdNm,
        String uswyTpCd,         /* 용도유형코드 */
        String sellDscDvCd,      /* 판매할인구분코드 */
        String sellDscrCd,       /* 판매할인율코드 */
        String pmotCd,           /* 프로모션코드 */
        String feecDvCd,         /* 수수료채널구분코드 */
        String sppDvCd,          /* 배송구분코드 */
        Long svPrd,              /* 서비스주기 */
        String hcrDvCd1,          /* 홈케어구분코드 */
        String hcrDvCd2,          /* 홈케어구분코드 */
        String hgrPdCd,          /* 상위상품코드 */
        String apyStrtdt,        /* 적용시작일자 */
        Long totStplMcn,         /* 총약정개월수 */
        Long chSn,               /* 변경일련번호 */
        String apyEnddt,         /* 적용종료일자 */
        String feeFxamYn,        /* 수수료정액여부 */
        Long sellFee,            /* 판매수수료 */
        String dtaDlYn,          /* 데이터삭제여부 */
        String fstRgstDtm,     /* 최초등록일시 */
        @MaskRequired(type = MaskingType.NAME)
        String fstRgstUsrId,  /* 최초등록사용자ID */
        String fstRgstPrgId,  /* 최초등록프로그램ID */
        String fstRgstDeptId, /* 최초등록부서ID */
        String fnlMdfcDtm,     /* 최종수정일시 */
        @MaskRequired(type = MaskingType.NAME)
        String fnlMdfcUsrId,  /* 최종수정사용자ID */
        String fnlMdfcPrgId,  /* 최종수정프로그램ID */
        String fnlMdfcDeptId /* 최종수정부서ID */

    ) {}

    public record SaveNewChannelBaseReq(
        String rowState,
        Long mtrSn,              /* 자료일련번호 */
        String sellTpCd,         /* 판매유형코드 */
        String pdCd,             /* 상품코드 */
        String uswyTpCd,         /* 용도유형코드 */
        String sellDscDvCd,      /* 판매할인구분코드 */
        String sellDscrCd,       /* 판매할인율코드 */
        String pmotCd,           /* 프로모션코드 */
        String feecDvCd,         /* 수수료채널구분코드 */
        String sppDvCd,          /* 배송구분코드 */
        Long svPrd,              /* 서비스주기 */
        String hcrDvCd1,          /* 홈케어구분코드 */
        String hcrDvCd2,          /* 홈케어구분코드 */
        String hgrPdCd,          /* 상위상품코드 */
        String apyStrtdt,        /* 적용시작일자 */
        Long totStplMcn,         /* 총약정개월수 */
        Long chSn,               /* 변경일련번호 */
        String apyEnddt,         /* 적용종료일자 */
        String feeFxamYn,        /* 수수료정액여부 */
        Long sellFee,            /* 판매수수료 */
        String dtaDlYn          /* 데이터삭제여부 */
    ) {}
}
