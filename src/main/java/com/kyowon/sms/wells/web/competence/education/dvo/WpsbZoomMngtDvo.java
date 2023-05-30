package com.kyowon.sms.wells.web.competence.education.dvo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WpsbZoomMngtDvo {
    private String svEducMnalId; /* 서비스교육매뉴얼ID */
    private String hgrSvEducMnalId; /* 상위서비스교육매뉴얼ID */
    private String svEducMnalNm; /* 서비스교육매뉴얼명 */
    private Integer inqrLvTcnt; /* 조회단계차수 */
    private Long expsrOdr; /* 노출순서 */
    private String svEducCtgNm; /* 서비스교육카테고리명 */
    private String svEducMnalCn; /* 서비스교육매뉴얼내용 */
    private String rsbDvCd; /* 직책구분코드 */
    private String dtaDlYn; /* 데이터삭제여부 */
}
