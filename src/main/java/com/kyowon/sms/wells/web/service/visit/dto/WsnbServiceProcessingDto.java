package com.kyowon.sms.wells.web.service.visit.dto;

import io.swagger.annotations.ApiModel;

/**
 * <pre>
 * W-SV-U-0054M01 서비스처리 내역
 * </pre>
 *
 * @author hyewon.kim
 * @since 2023.03.20
 */
public class WsnbServiceProcessingDto {

    @ApiModel("WsnbServiceProcessingIzDto-FindProductRes")
    public record FindProductRes(
        String codeId, // 코드ID(상품코드)
        String codeName // 코드명(상품명)
    ) {}

    @ApiModel("WsnbServiceProcessingIzDto-SearchReq")
    public record SearchReq(
        String serviceType, // 서비스유형
        String ogId, // 서비스센터
        String prtnrNo, // 엔지니어
        String refriType, // 유무상구분
        String pdGrpCd, // 상품그룹코드
        String pdCd, // 상품코드
        String svBizDclsfCd, // 업무유형(서비스업무세분류코드)
        String inquiryBase, // 조회기준
        String baseDateFrom, // 기준일자From
        String baseDateTo, // 기준일자To
        String wkPrgsStatCd, // 작업상태
        String installBase // 설치기준
    ) {}

}
