package com.kyowon.sms.wells.web.customer.prospective.dto;

import com.sds.sflex.common.utils.DbEncUtil;
import com.sds.sflex.common.utils.StringUtil;
import io.swagger.annotations.ApiModel;
import lombok.Builder;
import org.apache.commons.lang.StringUtils;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.List;

public class WcsbProspecCustomerMgtDto {

    @ApiModel(value = "WcsbProspecCustomerMgtDto-SearchReq")
    public record SearchReq(
        String applyMethod, /* 신청방법 */
        String hgrFreDgnsTpCd, /* 모집형태 */
        String freDgnsTpCd, /* 모집형태 */
        String startDate, /* 신청일 From */
        String cstType, /* 고객구분 */
        String pspcCstKnm, /* 고객이름 */
        String childNm, /* 자녀이름 */
        String phNo, /* 휴대번호 */
        String endDate /* 신청일 To */
    ) {
        public SearchReq {
            if (StringUtil.isNotEmpty(phNo)) {
                phNo = phNo.replaceAll("-", "");
                phNo = phNo.substring(0, 3) + DbEncUtil.enc(phNo.substring(3, 7)) + phNo.substring(7, 11);
            }
        }
    }

    @ApiModel(value = "WcsbProspecCustomerMgtDto-SearchRes")
    public record SearchRes(
        String pspcCstId, /* 가망고객 id */
        String hgrFreDgnsTpCd, /* 상위 무진 형태 코드 */
        String freDgnsTpCd, /* 무진형태코드 */
        String kIflwpathnm, /* 신청방법 */
        String freDgnsNm, /* 모집형태 */
        //        String cstType, /* 고객구분 - 최근 6개월 실적 존재 여부 확인 */
        String pspcCstKnm, /* 이름 */
        String sexDvCd,
        String sexDvCdNm, /* 성별 */
        String bryyMmdd, /* 생년월일 */
        String cralLocaraTno,
        String mexnoEncr,
        String cralIdvTno, /* 연락처 */
        String child1Nm, /* 자녀 1 이름 */
        String child1Age, /* 자녀 1 나이 */
        String child2Nm, /* 자녀 2 이름 */
        String child2Age, /* 자녀 2 나이 */
        String freeExpnYn, /* 무료체험 여부 */
        String freExpnRcpDtm, /* 무료체험 시작일자 */
        String prtnrNo, /* 교사 사번 */
        String prtnrNm, /* 교사 이름 */
        String newAdrZip, /* 우편번호 */
        String custAdr, /* 주소 */
        String pspcCstRcpCn, /* 메모 내용 */
        String pspcCstInflwPhCd, /* 가망고객유입경로코드 */
        String prPrtnrNwInflwCstYn, /* 홍보파트너신규유입고객여부 */
        String pspcCstFtfYn, /* 가망고객 대면 여부 */
        String pspcCstTpCd, /* 가망고객 유형 코드 */
        String phNo,
        String fstRgstDtm2, /* 신청일 */

        String chnlDvNm, /*  */
        String chnlDtlNm, /*  */
        String chnlDvCd, /*  */
        String chnlDtlCd, /*  */

        String dtrcNm, /*  */
        String dtrcOgNm, /*  */
        String ogAsnStatCd, /*  */
        String ogAsnStatNm, /*  */

        String fstRgstUsrId, /* 최초등록사용자ID */
        String fstRgstUsrNm, /* 최초등록사용자명 */
        String fstRgstDtm, /* 최초등록일시 */
        String fnlMdfcDtm, /* 최종수정일시 */
        String fnlMdfcUsrId, /* 최종수정사용자ID */
        String fnlMdfcUsrNm, /* 최종수정사용자명 */
        String fstRgstDeptId,
        String fnlMdfcDeptId

    ) {
        public SearchRes {
            mexnoEncr = StringUtils.isNotEmpty(mexnoEncr) ? DbEncUtil.dec(mexnoEncr) : mexnoEncr;
            phNo = cralLocaraTno + "-" + DbEncUtil.dec(mexnoEncr) + "-" + cralIdvTno;
        }
    }

    @ApiModel(value = "WcsbProspecCustomerMgtDto-TbSsopPspcCstBas")
    public record TbSsopPspcCstBas(
        String pspcCstId, /* 가망고객ID */
        String pspcCstInflwDt, /* 가망고객유입일자 */
        @NotBlank
        String pspcCstKnm, /* 가망고객한글명 */
        String pspcCstEnm, /* 가망고객영문명 */
        @NotBlank
        String copnDvCd, /* 법인격구분코드 */
        String bzrno, /* 사업자등록번호 */
        String crpSpmtDrmNm, /* 법인추가식별명 */
        String cstNo, /* 고객번호 */
        String idkKndCd, /* 식별키종류코드 */
        String idkVal, /* 식별키값 */
        String idkIsDtm, /* 식별키발급일시 */
        String pspcCstTpCd, /* 가망고객유형코드 */
        String natCd, /* 국가코드 */
        String adrId, /* 주소ID */
        String ctpvNm, /* 시도명 */
        @NotBlank
        String sexDvCd, /* 성별구분코드 */
        String bryyMmdd, /* 생년월일 */
        String cralLocaraTno, /* 휴대지역전화번호 */
        String mexnoEncr, /* 휴대전화국번호암호화 */
        String cralIdvTno, /* 휴대개별전화번호 */
        String locaraTno, /* 지역전화번호 */
        String exnoEncr, /* 전화국번호암호화 */
        String idvTno, /* 개별전화번호 */
        String emadr, /* 이메일주소 */
        String nwInflwCstYn, /* 신규유입고객여부 */
        String aplcDvcDvCd, /* 신청디바이스구분코드 */
        String pspcCstInflwPhCd, /* 가망고객유입경로코드 */
        String freDgnsTpCd, /* 무료진단유형코드 */
        String hgrFreDgnsTpCd, /* 상위무료진단유형코드 */
        String ptlCstDbDvCd, /* 잠재고객DB구분코드 */
        String pspcCstFtfYn, /* 가망고객대면여부 */
        String mgntTpCd, /* 중요도유형코드 */
        String pspcCstRcpCn, /* 가망고객접수내용 */
        String evnNm, /* 이벤트명 */
        String rcmdrPrtnrNo, /* 추천인파트너번호 */
        String ogAsnStatCd, /* 조직배정상태코드 */
        Integer reasnCt, /* 재배정건수 */
        String ichrOgTpCd, /* 담당조직유형코드 */
        String ichrPrtnrNo, /* 담당파트너번호 */
        String prtnrMoCn, /* 파트너메모내용 */
        String prPrtnrNwInflwCstYn, /* 홍보파트너신규유입고객여부 */
        String prPrtnrAsnDtm, /* 홍보파트너배정일시 */
        String otsdChnlSpmtYn, /* 외부채널추가여부 */
        String otsdLkDrmVal, /* 외부연계식별값 */
        String pspcCstDstcBaseDtm, /* 가망고객파기기준일시 */
        String mvsDstcRcvryDvCd, /* 소산파기복구구분코드 */
        String mvsDstcRcvryDtm, /* 소산파기복구일시 */
        String cstType, /* 고객구분 - 최근 6개월 실적 존재 여부 확인 */
        String phNo,
        String pspcCstDivDvCd, /* 잠재고객DB구분코드 */

        String fstRgstUsrId, /* 최초등록사용자ID */
        String fstRgstUsrNm, /* 최초등록사용자명 */
        String fstRgstDtm, /* 최초등록일시 */
        String fnlMdfcDtm, /* 최종수정일시 */
        String fnlMdfcUsrId, /* 최종수정사용자ID */
        String fnlMdfcUsrNm, /* 최종수정사용자명 */
        String fstRgstDeptId,
        String fnlMdfcDeptId,

        String fstRgstUsrEpNo, /* 최초등록사용자사번 */
        String fnlMdfcUsrEpNo, /* 최초수정사용자사번 */
        String fstRgstDeptNm, /* 최초등록부서명 */
        String fnlMdfcDeptNm, /* 최초수정부서명 */

        String newAdrZip, /* 우편번호 */
        String custAdr, /* 주소 */
        String basAdr,
        String dtlAdr,

        // TODO 명확한 게 없어 우선 다 주석처리
        //        String saveGubun, /* I/F 넘어올때 kwlink */
        String freeExpnYn, /* 무료체험 여부 T5*/
        String freExpnRcpDtm, /* 무료체험 신청일자 T5*/
        String freDgnsNm /* 모집형태 */

    ) {
        public TbSsopPspcCstBas {
            //            mexnoEncr = DbEncUtil.dec(mexnoEncr);
            mexnoEncr = StringUtils.isNotEmpty(mexnoEncr) ? DbEncUtil.enc(mexnoEncr) : mexnoEncr;
        }
    }

    @ApiModel(value = "WcsbProspecCustomerMgtDto-TbSsopPspcCstBasRes")
    public record TbSsopPspcCstBasRes(
        String pspcCstId, /* 가망고객ID */
        String pspcCstInflwDt, /* 가망고객유입일자 */
        @NotBlank
        String pspcCstKnm, /* 가망고객한글명 */
        String pspcCstEnm, /* 가망고객영문명 */
        @NotBlank
        String copnDvCd, /* 법인격구분코드 */
        String bzrno, /* 사업자등록번호 */
        String crpSpmtDrmNm, /* 법인추가식별명 */
        String cstNo, /* 고객번호 */
        String idkKndCd, /* 식별키종류코드 */
        String idkVal, /* 식별키값 */
        String idkIsDtm, /* 식별키발급일시 */
        String pspcCstTpCd, /* 가망고객유형코드 */
        String natCd, /* 국가코드 */
        String adrId, /* 주소ID */
        String ctpvNm, /* 시도명 */
        @NotBlank
        String sexDvCd, /* 성별구분코드 */
        String bryyMmdd, /* 생년월일 */
        String cralLocaraTno, /* 휴대지역전화번호 */
        String mexnoEncr, /* 휴대전화국번호암호화 */
        String cralIdvTno, /* 휴대개별전화번호 */
        String locaraTno, /* 지역전화번호 */
        String exnoEncr, /* 전화국번호암호화 */
        String idvTno, /* 개별전화번호 */
        String emadr, /* 이메일주소 */
        String nwInflwCstYn, /* 신규유입고객여부 */
        String aplcDvcDvCd, /* 신청디바이스구분코드 */
        String pspcCstInflwPhCd, /* 가망고객유입경로코드 */
        String freDgnsTpCd, /* 무료진단유형코드 */
        String hgrFreDgnsTpCd, /* 상위무료진단유형코드 */
        String ptlCstDbDvCd, /* 잠재고객DB구분코드 */
        String pspcCstFtfYn, /* 가망고객대면여부 */
        String mgntTpCd, /* 중요도유형코드 */
        String pspcCstRcpCn, /* 가망고객접수내용 */
        String evnNm, /* 이벤트명 */
        String rcmdrPrtnrNo, /* 추천인파트너번호 */
        String ogAsnStatCd, /* 조직배정상태코드 */
        Integer reasnCt, /* 재배정건수 */
        String ichrOgTpCd, /* 담당조직유형코드 */
        String ichrPrtnrNo, /* 담당파트너번호 */
        String prtnrMoCn, /* 파트너메모내용 */
        String prPrtnrNwInflwCstYn, /* 홍보파트너신규유입고객여부 */
        String prPrtnrAsnDtm, /* 홍보파트너배정일시 */
        String otsdChnlSpmtYn, /* 외부채널추가여부 */
        String otsdLkDrmVal, /* 외부연계식별값 */
        String pspcCstDstcBaseDtm, /* 가망고객파기기준일시 */
        String mvsDstcRcvryDvCd, /* 소산파기복구구분코드 */
        String mvsDstcRcvryDtm, /* 소산파기복구일시 */
        String cstType, /* 고객구분 - 최근 6개월 실적 존재 여부 확인 */
        String phNo,
        String pspcCstDivDvCd, /* 잠재고객DB구분코드 */

        String fstRgstUsrId, /* 최초등록사용자ID */
        String fstRgstUsrNm, /* 최초등록사용자명 */
        String fstRgstDtm, /* 최초등록일시 */
        String fnlMdfcDtm, /* 최종수정일시 */
        String fnlMdfcUsrId, /* 최종수정사용자ID */
        String fnlMdfcUsrNm, /* 최종수정사용자명 */
        String fstRgstDeptId,
        String fnlMdfcDeptId,

        String fstRgstUsrEpNo, /* 최초등록사용자사번 */
        String fnlMdfcUsrEpNo, /* 최초수정사용자사번 */
        String fstRgstDeptNm, /* 최초등록부서명 */
        String fnlMdfcDeptNm, /* 최초수정부서명 */

        String newAdrZip, /* 우편번호 */
        String custAdr, /* 주소 */
        String basAdr,
        String dtlAdr,

        String freeExpnYn, /* 무료체험 여부 T5*/
        String freExpnRcpDtm, /* 무료체험 신청일자 T5*/
        String freDgnsNm /* 모집형태 */

    ) {
        public TbSsopPspcCstBasRes {
            phNo = cralLocaraTno + "-" + DbEncUtil.dec(mexnoEncr) + "-" + cralIdvTno;
        }
    }

    // 교사정보
    @ApiModel(value = "WcsbProspecCustomerMgtDto-TbSsopPspcCstDdlvHist")
    public record TbSsopPspcCstDdlvHist(
        String pspcCstId, /* 가망고객ID */
        String pspcCstTpCd, /* 가망고객유형코드 */
        String ogAsnStatCd, /* 조직배정상태코드 */
        String histStrtDtm, /* 이력시작일시 */
        String histEndDtm, /* 이력종료일시 */
        String ogId, /* 조직ID */
        String ogTpCd, /* 조직유형코드 */
        String prtnrNo, /* 파트너번호 */
        String prtnrNm, /* 파트너명 */
        String asnFshDtm /* 배정완료일시 */
    ) {}

    // 자녀정보
    @ApiModel(value = "WcsbProspecCustomerMgtDto-TbSsopPspcCstRlpplDtl")
    public record TbSsopPspcCstRlpplDtl(
        String pspcCstRlpplId, /* 가망고객관계자ID */
        String pspcCstId, /* 가망고객ID */
        String pspcCstRlpplTpCd, /* 가망고객관계자유형코드 */
        String rlpplKnm, /* 관계자한글명 */
        String rlpplSexDvCd, /* 관계자성별구분코드 */
        String rlpplBryyMmdd, /* 관계자생년월일 */
        String rlpplGryCd, /* 관계자학년코드 */
        String rlpplSchNm, /* 관계자학교명 */
        Integer lrnnAge, /* 학습연령 */
        String check
    ) {}

    // 관심사항
    @ApiModel(value = "WcsbProspecCustomerMgtDto-tbSsopPspcCstInrtIz")
    public record tbSsopPspcCstInrtIz(
        String pspcCstId, /* 가망고객ID */
        String inrtArtcCd, /* 관심사항코드 */
        String sellPrpChval, /* 판매속성문자값 */
        BigDecimal sellPrpNuval /* 판매속성숫자값 */
    ) {}

    @ApiModel(value = "WcsbProspecCustomerMgtDto-SaveReq")
    public record SaveReq(
        @NotNull
        WcsbProspecCustomerMgtDto.TbSsopPspcCstBas tbSsopPspcCstBas, // 기본정보
        TbSsopPspcCstDdlvHist tbSsopPspcCstDdlvHist, // 교사정보
        List<TbSsopPspcCstRlpplDtl> tbSsopPspcCstRlpplDtl, // 자녀정보
        List<String> tbSsopPspcCstInrtIz, // 관심상품
        String fnlMdfcDtm
    ) {}

    @ApiModel(value = "WcsbProspecCustomerMgtDto-SearchRecruitTypeReq")
    public record SearchRecruitTypeReq(
        String hgrFreDgnsTpCd, /* 상위 무진코드 */
        String freDgnsFomDvCd

    ) {}

    @ApiModel(value = "WcsbProspecCustomerMgtDto-SearchRecruitTypeRes")
    public record SearchRecruitTypeRes(
        String freDgnsTpCd, /* 무진코드 */
        String hgrFreDgnsTpCd, /* 상위 무진코드 */
        String freDgnsNm, /* 무료진단 명 */
        String freDgnsDtlCn /* 무료진단 상세 */
    ) {}

    @Builder
    @ApiModel(value = "WcsbProspecCustomerMgtDto-ProspecCustomerRes")
    public record ProspecCustomerRes(
        @NotNull
        WcsbProspecCustomerMgtDto.TbSsopPspcCstBasRes tbSsopPspcCstBas, // 기본정보
        TbSsopPspcCstDdlvHist tbSsopPspcCstDdlvHist, // 교사정보
        List<TbSsopPspcCstRlpplDtl> tbSsopPspcCstRlpplDtl, // 자녀정보
        List<String> tbSsopPspcCstInrtIz, // 관심상품
        String fnlMdfcDtm
    ) {}

    @ApiModel(value = "WcsbProspecCustomerMgtDto-AssignPartnerReq")
    public record AssignPartnerReq(
        String pspcCstId, /* 가망고객ID */
        String ogTpCd, /* 조직유형코드 */
        String asIsPrtnrNo, /* 교사 사번 */
        String toBePrtnrNo, /* 교사 사번 */
        String pspcCstTpCd, /* 가망고객 유형 코드 */
        String ogAsnStatCd, /* 배부상태코드 */
        String fnlMdfcDtm /* 최종수정일시 */
    ) {}

    @Builder
    @ApiModel(value = "WcsbProspecCustomerMgtDto-ValidationReq")
    public record ValidationReq(
        @NotNull
        String validationType, /* 유효성체크 유형 */
        String cralLocaraTno, /* 휴대폰 A */
        String mexnoEncr, /* 휴대폰 B */
        String cralIdvTno, /* 휴대폰 C */
        String bzrno, /* 사업자번호 */
        String prtnrNo /* 파트너 */
    ) {
        public ValidationReq {
            if (StringUtils.isNotEmpty(mexnoEncr)) {
                mexnoEncr = DbEncUtil.enc(mexnoEncr);
            }
        }
    }

}
