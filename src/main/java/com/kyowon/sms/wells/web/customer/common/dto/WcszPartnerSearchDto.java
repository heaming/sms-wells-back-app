package com.kyowon.sms.wells.web.customer.common.dto;

import org.apache.commons.lang.StringUtils;

import com.sds.sflex.common.utils.DbEncUtil;

import io.swagger.annotations.ApiModel;

public class WcszPartnerSearchDto {

    @ApiModel(value = "WcszPartnerSearchDto-SearchBizOrganizationsByHgrOgCdReq")
    public record SearchBizOrganizationsByHgrOgCdReq(
        String baseYm, /* 기준연월 */
        String hgrOgId, /* 상위조직ID */
        String ogLevlDvCd, /* 조직레벨구분코드(1:사업단,2:총괄단,3:센터,4:지국) */
        String dgr1LevlOgCd, /* 조직레벨구분코드(1:사업단코드) */
        String dgr2LevlOgCd, /* 조직레벨구분코드(2:총괄단코드) */
        String dgr3LevlOgCd, /* 조직레벨구분코드(3:센터코드) */
        String ogTpCd /* E02 특화변수 */
    ) {}

    @ApiModel(value = "WcszPartnerSearchDto-SearchBizOrganizationsByHgrOgCdRes")
    public record SearchBizOrganizationsByHgrOgCdRes(
        String ogId, /* 조직 ID */
        String ogCd, /* 조직 코드 */
        String ogNm, /* 조직명 */
        String ogAbbrNm, /* 조직약어명 */
        String hooPrtnrNo, /* 조직장 사번 */
        String hooPrtnrNm, /* 조직장 명 */
        String dgr1LevlOgCd, /* 사업단 코드 */
        String dgr2LevlOgCd, /* 총괄단 코드 */
        String dgr3LevlOgCd /* 센터 코드 */
    ) {}

    @ApiModel(value = "WcszPartnerSearchDto-SearchPartnersByOgCdReq")
    public record SearchPartnersByOgCdReq(
        String ogTpCd, /* 조직유형코드 */
        String ogCd, /* 조직코드 */
        String ogId /* 조직ID */
    ) {}

    @ApiModel(value = "WcszPartnerSearchDto-SearchPartnersByOgCdRes")
    public record SearchPartnersByOgCdRes(
        String ogId, /* EP 조직 ID */
        String ogTpCd, /* EP 조직 구분 코드 */
        String ogCd, /* EP 조직 코드 */
        String ogNm, /* EP 조직 명 */
        String pstnDvCd, /* EP 직급코드 */
        String pstnNm, /* 직급 명 */
        String epNo, /* EP 사번 */
        String epNm, /* EP 명 */
        String epNmNo, /* ep 명 +  ep 사번 */
        String epMpNo1, /* EP휴대폰번호1 */
        String epMpNo2, /* EP휴대폰번호2 */
        String epMpNo3, /* EP휴대폰번호3 */
        String epMpNo,
        String bdvOgId, /* 사업단 조직 ID  */
        String bdvOgCd, /* 사업단 조직 코드 */
        String bdvOgNm, /* 사업단 */
        String bdvPrtnrNo, /* 사업단장사번 */
        String bdvPrtnrNm, /* 사업단장명 */
        String gnrdvOgId, /* 총괄단 조직 ID  */
        String gnrdvOgCd, /* 총괄단 조직 코드 */
        String gnrdvOgNm, /* 총괄단 */
        String gnrdvPrtnrNo, /* 총괄단장사번 */
        String gnrdvPrtnrNm, /* 총괄단장명 */
        String cnrOgId, /* 센터 조직 ID */
        String cnrOgCd, /* 센터 조직 코드 */
        String cnrOgNm, /* 센터명 */
        String cnrNo, /* 센터장사번 */
        String cnrNm, /* 센터장명 */
        String cnrOfficeTelNo1, /* 센타전화번호1 */
        String cnrOfficeTelNo2, /* 센타전화번호2 */
        String cnrOfficeTelNo3, /* 센타전화번호3 */
        String cnrMpNo1, /* 센터장휴대폰번호1 */
        String cnrMpNo2, /* 센터장휴대폰번호2 */
        String cnrMpNo3, /* 센터장휴대폰번호3 */
        String dtrcOgCd, /* 지국 조직 코드 */
        String dtrcOgNm, /* 지국명 */
        String dtrcNd, /* 지국장 사번 */
        String dtrcNm, /* 지국장 명 */
        String dtrcMpNo1, /* 지국장 휴대폰번호2 */
        String dtrcMpNo2, /* 지국장 휴대폰번호3 */
        String dtrcMpNo3 /* 지국장 휴대폰번호3 */
    ) {
        public SearchPartnersByOgCdRes {
            epMpNo2 = StringUtils.isNotEmpty(epMpNo2) ? DbEncUtil.dec(epMpNo2) : epMpNo2;
            cnrOfficeTelNo2 = StringUtils.isNotEmpty(cnrOfficeTelNo2) ? DbEncUtil.dec(cnrOfficeTelNo2)
                : cnrOfficeTelNo2;
            cnrMpNo2 = StringUtils.isNotEmpty(cnrMpNo2) ? DbEncUtil.dec(cnrMpNo2) : cnrMpNo2;
            dtrcMpNo2 = StringUtils.isNotEmpty(dtrcMpNo2) ? DbEncUtil.dec(dtrcMpNo2) : dtrcMpNo2;

            if (StringUtils.isNotEmpty(epMpNo1) && StringUtils.isNotEmpty(epMpNo2) && StringUtils.isNotEmpty(epMpNo3)) {
                epMpNo = epMpNo1 + "-" + epMpNo2 + "-" + epMpNo3;
            }
        }

    }

    @ApiModel(value = "WcszPartnerSearchDto-SearchDtrcPartnersByHgrOgCdRes")
    public record SearchDtrcPartnersByHgrOgCdRes(
        String dtrcOgId, /* 지국장 조직 ID */
        String dtrcOgTpCd, /* 지국장 조직 구분 코드 */
        String dtrcOgCd, /* 지국장 조직 코드 */
        String dtrcOgNm, /* 지국장 조직 명 */
        String dtrcPrtnrNo, /* 지국장 사번 */
        String dtrcPrtnrKnm, /* 지국장 이름 */
        String dtrcPrtnrNmNo, /* 파트너명 : 파트너 한글명(파트너번호) */
        String dtrcPstnDvCd, /* 지국장 직급코드 */
        String dtrcPstnNm, /* 조직장 직급 명 */
        String dtrcMpNo1, /* 지국장 휴대폰번호1 */
        String dtrcMpNo2, /* 지국장 휴대폰번호2 */
        String dtrcMpNo3, /* 지국장 휴대폰번호3 */
        String bdvOgId, /* 사업단 조직 ID  */
        String bdvOgCd, /* 사업단 조직 코드 */
        String bdvOgNm, /* 사업단 */
        String bdvPrtnrNo, /* 사업단장사번 */
        String bdvPrtnrNm, /* 사업단장명 */
        String gnrdvOgId, /* 총괄단 조직 ID  */
        String gnrdvOgCd, /* 총괄단 조직 코드 */
        String gnrdvOgNm, /* 총괄단 */
        String gnrdvPrtnrNo, /* 총괄단장사번 */
        String gnrdvPrtnrNm, /* 총괄단장명 */
        String cnrOgId, /* 센터 조직 ID */
        String cnrOgCd, /* 센터 조직 코드 */
        String cnrOgNm, /* 센터명 */
        String cnrNo, /* 센터장사번 */
        String cnrNm /* 센터장명 */
    ) {}

    @ApiModel(value = "WcszPartnerSearchDto-SearchPartnersByHgrOgCdRes")
    public record SearchPartnersByHgrOgCdRes(
        String ogId, /* ep 조직 id */
        String ogTpCd, /* ep 조직 구분 코드 */
        String ogCd, /* ep 조직 코드 */
        String ogNm, /* ep 조직 명 */
        String pstnDvCd, /* ep 직급코드 */
        String pstnNm, /* 직급 명 */
        String epNo, /* ep 사번 */
        String epNm, /* ep 명 */
        String epMpNo1, /* ep휴대폰번호1 */
        String epMpNo2, /* ep휴대폰번호2 */
        String epMpNo3, /* ep휴대폰번호3 */
        String bdvOgId, /* 사업단 조직 iD  */
        String bdvOgCd, /* 사업단 조직 코드 */
        String bdvOgNm, /* 사업단 */
        String bdvPrtnrNo, /* 사업단장사번 */
        String bdvPrtnrNm, /* 사업단장명 */
        String gnrdvOgId, /* 총괄단 조직 ID  */
        String gnrdvOgCd, /* 총괄단 조직 코드 */
        String gnrdvOgNm, /* 총괄단 */
        String gnrdvPrtnrNo, /* 총괄단장사번 */
        String gnrdvPrtnrNm, /* 총괄단장명 */
        String cnrOgId, /* 센터 조직 id */
        String cnrOgCd, /* 센터 조직 코드 */
        String cnrOgNm, /* 센터명 */
        String cnrNo, /* 센터장사번 */
        String cnrNm, /* 센터장명 */
        String cnrOfficeTelNo1, /* 센타전화번호1 */
        String cnrOfficeTelNo2, /* 센타전화번호2 */
        String cnrOfficeTelNo3, /* 센타전화번호3 */
        String cnrMpNo1, /* 센터장휴대폰번호1 */
        String cnrMpNo2, /* 센터장휴대폰번호2 */
        String cnrMpNo3, /* 센터장휴대폰번호3 */
        String dtrcOgId, /* 지국 조직 iD */
        String dtrcOgCd, /* 지국 조직 코드 */
        String dtrcOgNm, /* 지국명 */
        String dtrcNd, //* 지국장 사번 */
        String dtrcNm, //* 지국장 명 */
        String dtrcMpNo1, /* 지국장 휴대폰번호1 */
        String dtrcMpNo2, /* 지국장 휴대폰번호2 */
        String dtrcMpNo3, /* 지국장 휴대폰번호3 */
        String searchText /* 파트너 text */
    ) {
        public SearchPartnersByHgrOgCdRes {}
    }

    //////
    @ApiModel(value = "WcszPartnerSearchDto-SearchPartnerByPkReq")
    public record SearchPartnerByPkReq(
        String ogTpCd, /* 기준연월 */
        String prtnrNo /* 상위조직ID */
    ) {}

    @ApiModel(value = "WcszPartnerSearchDto-SearchPartnerByPkRes")
    public record SearchPartnerByPkRes(
        String ogId, /* ep 조직 id */
        String ogTpCd, /* ep 조직 구분 코드 */
        String ogCd, /* ep 조직 코드 */
        String ogNm, /* ep 조직 명 */
        String pstnDvCd, /* ep 직급코드 */
        String pstnNm, /* 직급 명 */
        String epNo, /* ep 사번 */
        String epNm, /* ep 명 */
        String epNmNo, /* ep 명 +  ep 사번 */
        String epMpNo1, /* ep휴대폰번호1 */
        String epMpNo2, /* ep휴대폰번호2 */
        String epMpNo3, /* ep휴대폰번호3 */
        String epOfficeTelNo1, /* EP전화번호1 */
        String epOfficeTelNo2, /* EP전화번호2 */
        String epOfficeTelNo3, /* EP전화번호3 */
        String bdvOgId, /* 사업단 조직 ID  */
        String bdvOgCd, /* 사업단 조직 코드 */
        String bdvOgNm, /* 사업단 */
        String bdvPrtnrNo, /* 사업단장사번 */
        String bdvPrtnrNm, /* 사업단장명 */
        String gnrdvOgId, /* 총괄단 조직 ID  */
        String gnrdvOgCd, /* 총괄단 조직 코드 */
        String gnrdvOgNm, /* 총괄단 */
        String gnrdvPrtnrNo, /* 총괄단장사번 */
        String gnrdvPrtnrNm, /* 총괄단장명 */
        String cnrOgId, /* 센터 조직 ID */
        String cnrOgCd, /* 센터 조직 코드 */
        String cnrOgNm, /* 센터명 */
        String cnrNo, /* 센터장사번 */
        String cnrNm, /* 센터장명 */
        String cnrOfficeTelNo1, /* 센타전화번호1 */
        String cnrOfficeTelNo2, /* 센타전화번호2 */
        String cnrOfficeTelNo3, /* 센타전화번호3 */
        String cnrMpNo1, /* 센터장휴대폰번호1 */
        String cnrMpNo2, /* 센터장휴대폰번호2 */
        String cnrMpNo3, /* 센터장휴대폰번호3 */
        String dtrcOgId, /* 지국 조직 ID */
        String dtrcOgCd, /* 지국 조직 코드 */
        String dtrcOgNm, /* 지국명 */
        String dtrcNd, /* 지국장 사번 */
        String dtrcNm, /* 지국장 명 */
        String dtrcNmNo, /* 지국장 명 + 지국장 사번 */
        String dtrcMpNo1, /* 지국장 휴대폰번호1 */
        String dtrcMpNo2, /* 지국장 휴대폰번호2 */
        String dtrcMpNo3 /* 지국장 휴대폰번호3 */
    ) {}

    @ApiModel(value = "WcszPartnerSearchDto-SearchHgrOgCdForBizSpptRes")
    public record SearchHgrOgCdForBizSpptRes(
        String ogTpCd, /* 조직 구분 코드 */
        String bdvOgId, /* 사업단 조직 ID  */
        String bdvOgCd, /* 사업단 조직 코드 */
        String bdvOgNm, /* 사업단 */
        String gnrdvOgId, /* 총괄단 조직 ID  */
        String gnrdvOgCd, /* 총괄단 조직 코드 */
        String gnrdvOgNm, /* 총괄단 */
        String prtnrNo /* 파트너번호 */
    ) {}

    @ApiModel(value = "WcszPartnerSearchDto-SearchCenterOgCdForBizSpptRes")
    public record SearchCenterOgCdForBizSpptRes(
        String cnrOgId, /* 센터 조직 ID */
        String cnrOgCd, /* 센터 조직 코드 */
        String cnrOgNm /* 센터명 */
    ) {}
}
