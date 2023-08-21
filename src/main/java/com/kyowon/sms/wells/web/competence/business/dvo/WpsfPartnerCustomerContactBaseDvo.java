package com.kyowon.sms.wells.web.competence.business.dvo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WpsfPartnerCustomerContactBaseDvo {
    private String ogTpCd; /* 조직유형코드 */
    private String prtnrNo; /* 파트너번호 */
    private Integer ctplcSn; /* 연락처일련번호 */
    private String fnm; /* 성명 */
    private String cralLocaraTno; /* 휴대지역전화번호 */
    private String mexnoEncr; /* 휴대전화국번호암호화 */
    private String cralIdvTno; /* 휴대개별전화번호 */
    private String zip; /* 우편번호 */
    private String basAdr; /* 기본주소 */
    private String dtlAdr; /* 상세주소 */
    private String ctplcImgFileId; /* 연락처이미지파일ID */
    private String dtaDlYn; /* 데이터삭제여부 */
    private String fileNm;
    private String realFpath;
    private String fileUid;
}
