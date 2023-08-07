package com.kyowon.sms.wells.web.competence.lecture.dvo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WpscLectureSpptAplcPtrmDvo {
    private String lectrSpptOgTpCd;    /* 강의지원조직유형코드 */
    private String lectrYm;            /* 강의년월 */
    private String aplcStrtdt;         /* 신청시작일자 */
    private String aplcStrtHm;         /* 신청시작시분 */
    private String aplcEnddt;          /* 신청종료일자 */
    private String aplcEndHm;          /* 신청종료시분 */
    private String dtaDlYn;            /* 데이터삭제여부 */
}
