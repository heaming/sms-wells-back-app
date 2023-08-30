package com.kyowon.sms.wells.web.kakaosync.common.dvo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class KakaoWcsbSyncDefaultDvo {

    private String inPath; /* 가망고객유입경로 */
    private String akdGub; /* 가망고객사업부구분코드 */
    private String infoAgre; /* 동의상태구분코드 */
    private String mrknAgre; /* 동의상태구분코드 */
    private String custWcde; /* 최초등록사용자ID (AKDCDE) */
    private String custUcde; /* 최종수정사용자ID (AKDCDE) */
    private String name; /* 가망고객한글명 */
    private String brdt; /* 생년월일 */
    @JsonProperty("gender")
    private String gndr; /* 성별구분코드 */
    private String custHnO1; /* 휴대지역전화번호 */
    private String custHnO2; /* 휴대전화국번호암호화 */
    private String custHnO3; /* 휴대개별전화번호 */
    @JsonProperty("zipcode")
    private String zpcd; /* 주소ID (ADR_ID로 통합)*/
    @JsonProperty("base_address")
    private String addr1; /* 주소1 (ADR_ID로 통합)*/
    @JsonProperty("detail_address")
    private String addr2; /* 주소2 (ADR_ID로 통합)*/
    @JsonProperty("email")
    private String email; /* 이메일주소 */
    private String frdnsrNo; /* 무료진단유형코드 (000)*/
    private String prensrNo; /* 상위무료진단유형코드 (078) */
    private String custWdsp; /* 최초등록프로그램ID  ('') */
    private String custUdsp; /* 최종수정프로그램ID ('') */
    @JsonProperty("akdcde")
    private String akdcde; /* 파트너번호 */
    private String custGubn; /* 고객구분 */
    private String custSrno; /* 가망고객ID */
    private String kywonKey; /* 교원키 */
    private String k_Coddan; /* 지국장 사번 */
}
