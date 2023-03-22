package com.kyowon.sms.wells.web.service.visit.dto;

import io.swagger.annotations.ApiModel;

/**
 * <pre>
 * W-SV-I-0020 Wells홈페이지 홈카페 캡슐 패키지 변경을 위한 배송 정보 리스트
 * </pre>
 *
 * @author yeonghwa.cheon
 * @since 2023.03.20
 */
public class WsnbCapsulepkgChSppinfDto {

    @ApiModel(value = "WsnbCapsulepkgChSppinfDto-SearchReq")
    public record SearchReq(
        String CNTR_NO, /* 계약번호 */
        String CNTR_SN, /* 계약일련번호 */
        String SPP_DUEDT /* 배송예정일자 */
    ) {}

    @ApiModel(value = "WsnbCapsulepkgChSppinfDto-SearchRes")
    public record SearchRes(
        String SPP_DUEDT, /* 배송예정일자 */
        String PD_CD, /* 상품코드 */
        String AFCH_PD_CD, /* 변경후상품코드 */
        String FILT_PD_CD, /* 필터상품코드 */
        String SELL_AMT, /* 판매금액 */
        String SPP_PRGS_STAT_CD, /* 배송진행상태코드 */
        String IST_ADDR /* 설치주소 */
    ) {}

}
