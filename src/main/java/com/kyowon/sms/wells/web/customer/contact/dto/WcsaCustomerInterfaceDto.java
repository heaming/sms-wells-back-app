package com.kyowon.sms.wells.web.customer.contact.dto;

import org.apache.commons.lang.StringUtils;

import com.sds.sflex.common.utils.DbEncUtil;

import io.swagger.annotations.ApiModel;

public class WcsaCustomerInterfaceDto {

    /**
     * 고객번호 기준으로 고객정보를 조회 (서비스) Search Request Dto
     * @param cstNo 고객번호
     */
    @ApiModel(value = "WcsaCustomerInterfaceDto-SearchCustomerInfoReq")
    public record SearchCustomerInfoReq(
        String cstNo /* 고객번호 */
    ) {}

    /**
     * 고객번호 기준으로 고객정보를 조회 (IF정보 : EAI_WCUI1011_WELLS 고객정보 조회) Search Result Dto
     * @param cstNo 고객번호
     * @param itgCstNo 통합고객번호
     * @param cikVal ci키값
     * @param sfkVal 세이프키값
     * @param copnDvCd 법인격구분코드
     * @param cstInflwPhDvCd 고객유입경로코드
     * @param cstKnm 고객한글명
     * @param lnfDvCd 내외국인구분코드
     * @param bryyMmdd 생년월일
     * @param sexDvCd 성별구분코드
     * @param emadr 이메일주소
     * @param bzrno 사업자등록번호
     * @param crpSpmtDrmNm 법인점포명
     * @param locaraTno 지역전화번호
     * @param exno 전화국번호
     * @param idvTno 개별전화번호
     * @param cralLocaraTno 휴대지역전화번호
     * @param mexno 휴대전화국번호
     * @param cralIdvTno 휴대개별전화번호
     * @param adrId 주소ID
     * @param zip 우편번호
     * @param basAdr 기본주소
     * @param dtlAdr 상세주소
     * @param prtnrNo 파트너번호
     * @param sapEmpno sap사원번호
     * @param fmlDscOjYn 가족할인대상여부
     * @param hsCtfYn 본인인증여부
     * @param empYn 직원여부
     * @param cstGdCd 고객등급코드
     * @param nusdRsonCd 미사용사유코드
     * @param dtaDlYn 데이터삭제여부
     * @param fstRgstDtm 최초등록일시
     * @param fnlMdfcDtm 최종수정일시
     * @param rsCd 결과코드
     * @param rsMsg 결과메시지
     */
    @ApiModel(value = "WcsaCustomerInterfaceDto-SearchCustomerRes")
    public record SearchCustomerRes(
        String cstNo, /* 고객번호*/
        String itgCstNo, /* 통합고객번호 */
        String cikVal, /* ci키값 */
        String sfkVal, /* 세이프키값 */
        String copnDvCd, /* 법인격구분코드 */
        String cstInflwPhDvCd, /* 고객유입경로코드 */
        String cstKnm, /* 고객한글명 */
        String lnfDvCd, /* 내외국인구분코드 */
        String bryyMmdd, /* 생년월일 */
        String sexDvCd, /* 성별구분코드 */
        String emadr, /*이메일주소*/
        String bzrno, /*사업자등록번호*/
        String crpSpmtDrmNm, /* 법인점포명 */
        String locaraTno, /* 지역전화번호 */
        String exno, /*전화국번호*/
        String idvTno, /* 개별전화번호 */
        String cralLocaraTno, /* 휴대지역전화번호 */
        String mexno, /*휴대전화국번호*/
        String cralIdvTno, /* 휴대개별전화번호 */
        String adrId, /* 주소ID */
        String zip, /*우편번호*/
        String basAdr, /* 기본주소 */
        String dtlAdr, /* 상세주소 */
        String prtnrNo, /* 파트너번호 */
        String sapEmpno, /* sap사원번호 */
        String fmlDscOjYn, /* 가족할인대상여부 */
        String hsCtfYn, /* 본인인증여부 */
        String empYn, /* 직원여부 */
        String cstGdCd, /* 고객등급코드 */
        String nusdRsonCd, /* 미사용사유코드 */
        String dtaDlYn, /* 데이터삭제여부 */
        String fstRgstDtm, /* 최초등록일시 */
        String fnlMdfcDtm, /* 최종수정일시 */
        String rsCd, /* 결과코드 */
        String rsMsg /* 결과메시지 */

    ) {
        public SearchCustomerRes {
            exno = StringUtils.isNotEmpty(exno) ? DbEncUtil.dec(exno) : exno;
            mexno = StringUtils.isNotEmpty(mexno) ? DbEncUtil.dec(mexno) : mexno;
        }
    }

}
