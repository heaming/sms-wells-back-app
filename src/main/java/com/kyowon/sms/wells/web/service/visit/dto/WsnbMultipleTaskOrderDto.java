package com.kyowon.sms.wells.web.service.visit.dto;

import io.swagger.annotations.ApiModel;

/**
 * <pre>
 * W-SV-S-0012 다건 작업오더, 정보변경 처리
 * </pre>
 *
 * @see PR_KIWI_WRK_CREATE_V2M
 * @author yeonghwa.cheon
 * @since 2023.02.09
 */
public class WsnbMultipleTaskOrderDto {

    @ApiModel(value = "WsnbMultipleTaskOrderDto-SaveReq")
    public record SaveReq(
        /* TODO: 파라미터로 받아오는 값에 따라 로직 변경 필요 */
        String inChnlDvCd, /* 입력채널구분코드 */
        String svBizHclsfCd, /* 서비스업무대분류코드 */
        String rcpdt, /* 접수일자 */
        String asIstOjNo, /* AS설치대상번호 */
        String mrtStatCd, /* 자료상태코드 */
        String svBizDclsfCd, /* 서비스업무세분류코드 */
        /* apld_dt, apld_tm 미정. */
        String urgtYn, /* 긴급여부 */
        String vstRqdt, /* 방문요청일자 */
        String vstAkHh, /* 방문요청시간 */
        String smsFwYn, /* SMS발송여부 */
        String svEtAmt, /* 서비스예상금액 */
        String dpDvCd, /* 입금구분코드 */
        String cnslTpHclsfCd, /* 상담유형대분류코드 */
        String cnslTpMclsfCd, /* 상담유형중분류코드 */
        String cnslTpLclsfCd, /* 상담유형소분류코드 */
        String cnslDtlpTpCd, /* 상담세부유형코드 */
        String cnslMoCn, /* 상담메모내용 */
        /*p_etc_1 미정*/
        String svCnrOgId, /* 서비스센터조직ID */
        String pdGdCd, /* 상품등급코드 */
        /*p_ac216_etc_1, p_mk_co 미정*/
        String cntrNo, /* 계약번호 */
        String cntrSn, /* 계약일련번호 */
        String cntrRcpChnlTpCd,
        String sellTpCd,
        String sellPrtnrNo,
        String cralLocaraTnoPtnr, /* 파트넌전화번호1 */
        String mexnoEncrPtnr, /* 파트넌전화번호2 */
        String cralIdvTnoPtnr, /* 파트너전화번호3 */
        String dgr1HgrDgPrtnrNo, /* 지점장id */
        String cralLocaraTnoCh, /* 지점장번호1 */
        String mexnoEncrCh, /* 지점장번호2 */
        String cralIdvTnoCh, /* 지점장번호3 */
        String copnDvCd,
        String rcgvpKnm, /* 고객한글명 */
        String locaraTno, /* 고객전화번호1 */
        String exnoEncr, /* 고객전화번호2 */
        String idvTno, /* 고객전화번호3 */
        String newAdrZip, /* 고객우편번호 */
        String rnadr, /* 고객도로명주소 */
        String rdadr, /* 고객도로명상세주소 */
        String adrId, /* 고객주소ID */
        String adrDvCd, /* 주소구분코드 */
        String basePdCd, /* 상품코드 */
        String pdCd, /* 상품코드??(sale_cd) */
        String pdGdCd201, /* P_DEG 변환. 상품등급코드 201이랑 211둘다받아오는데,??? */
        String pdprpVal01, /* P_USGE */
        String sellTpCd201, /* P_SALE_TYP  */
        String svPrd, /* 서비스주기 */
        String stplPtrm,

        String compYn, /* 보상여부 (하위 값들 이전화면에서 주는 파라미터값에따라 명칭 및 로직 변경) */
        String mchnClnOjYn, /* 기기변경여부 */
        String cntrNoB, /* 보상계약번호 */
        String custKnm, /* 고객명 */
        String saleCd, /* ST112_SALE_CD */
        String partList /* TODO : partList 확인 필요 */
    ) {}

}
