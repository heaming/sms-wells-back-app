package com.kyowon.sms.wells.web.competence.business.dvo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WpsfActivityGoodsApplicationBaseDvo {
    private String ogTpCd; /* 조직유형코드 */
    private String aplcPsbStrtD; /* 신청가능시작일 */
    private String aplcPsbEndD; /* 신청가능종료일 */
    private String rtngdPsbStrtD; /* 반품가능시작일 */
    private String rtngdPsbEndD; /* 반품가능종료일 */
    private String rtngdShrnEmadr; /* 반품공유이메일주소 */
    private String confArtcCn; /* 확인사항내용 */
    private String dtaDlYn; /* 데이터삭제여부 */

}
