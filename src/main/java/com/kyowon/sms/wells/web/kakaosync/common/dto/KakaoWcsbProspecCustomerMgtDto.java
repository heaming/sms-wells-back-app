package com.kyowon.sms.wells.web.kakaosync.common.dto;

import com.kyowon.sms.wells.web.kakaosync.zcommon.kakaosyncutils.AES256Util;
import com.sds.sflex.common.utils.DbEncUtil;
import com.sds.sflex.common.utils.StringUtil;
import io.swagger.annotations.ApiModel;
import org.apache.commons.lang.StringUtils;

import java.io.UnsupportedEncodingException;
import java.security.GeneralSecurityException;

public class KakaoWcsbProspecCustomerMgtDto {

    @ApiModel(value = "KakaoEcsbProspecCustomerMgtDto-TbSsopPspcCstBas")
    public record TbSsopPspcCstBas(
        String pspcCstId, /* 가망고객ID */
        String pspcCstInflwDt, /* 가망고객유입일자 */
        // @NotBlank
        String pspcCstKnm, /* 가망고객한글명 */
        String pspcCstEnm, /* 가망고객영문명 */
        // @NotBlank
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
        // @NotBlank
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

    // 교사정보
    @ApiModel(value = "KakaoEcsbProspecCustomerMgtDto-TbSsopPspcCstDdlvHist")
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

    // 카카오 싱크 맵핑 데이터 저장
    @ApiModel(value = "KakaoEcsbProspecCustomerMgtDto-SaveReq")
    public record SaveReq(
            String nickname, /* 프로필정보-닉네임 */
            String email, /* 이메일 */
            String gender, /* 성별구분코드 */
            String birthyear, /* 출생년도 */
            String birthday, /* 생일 */
            String phone_number, /* 전화번호 */
            String zipcode, /* 우편번호 */
            String base_address, /* 기본주소 (ADR_ID로 통합)*/
            String detail_address, /* 상세주소 (ADR_ID로 통합)*/
            String employee_id, /* 추천인 사번 */
            String marketing_yn, /* 마케팅 목적 처리 동의서 */
            String akdcde, /* 파트너번호 */
            String channel_yn, /* 외부채널추가여부 */
            String auid /* 외부연계식별값 */
    ) {
        public SaveReq {

            final String key = "KYOWN-biztalk_12";
            try {
                AES256Util aes256 = new AES256Util(key);
                nickname = aes256.decrypt(StringUtil.nvl(nickname));            //프로필정보-닉네임
                email = aes256.decrypt(StringUtil.nvl(email));                  //이메일
                gender = aes256.decrypt(StringUtil.nvl(gender));                //성별
                birthyear = aes256.decrypt(StringUtil.nvl(birthyear));          //출생년도
                birthday = aes256.decrypt(StringUtil.nvl(birthday));            //생일
                phone_number = aes256.decrypt(StringUtil.nvl(phone_number));    //휴대전화번호
                zipcode = aes256.decrypt(StringUtil.nvl(zipcode));              //우편번호
                base_address = aes256.decrypt(StringUtil.nvl(base_address));    //기본주소
                detail_address = aes256.decrypt(StringUtil.nvl(detail_address)); //상세주소
                channel_yn = aes256.decrypt(StringUtil.nvl(channel_yn));        //카카오톡 채널 추가 상태 및 내역
                auid = aes256.decrypt(StringUtil.nvl(auid));                    //AUID
                marketing_yn = aes256.decrypt(StringUtil.nvl(marketing_yn));    //마케팅 목적 처리 동의서
                employee_id = aes256.decrypt(StringUtil.nvl(employee_id));      //추천인 사번
                akdcde = StringUtil.nvl(akdcde);                                //파트너 번호
            } catch (UnsupportedEncodingException e) {
                throw new RuntimeException(e);
            } catch (GeneralSecurityException e) {
                throw new RuntimeException(e);
            }
        }
    }

}
