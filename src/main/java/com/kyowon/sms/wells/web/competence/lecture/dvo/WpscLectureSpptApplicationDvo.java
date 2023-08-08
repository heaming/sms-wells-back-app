package com.kyowon.sms.wells.web.competence.lecture.dvo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WpscLectureSpptApplicationDvo {
    private String lectrSpptAplcId;      /* 강의지원신청ID */
    private String lectrSpptOgTpCd;      /* 강의지원조직유형코드 */
    private String lectrYm;              /* 강의년월 */
    private Integer lectrTCnt;           /* 강의차수 */
    private Integer lectrSpptLectCd;     /* 강의지원강사코드 */
    private Integer lectrSpptLectrCd;    /* 강의지원강의코드 */
    private String lectrDt;              /* 강의일자 */
    private String ogId;                 /* 조직ID */
    private String bldCd;                /* 빌딩코드 */
    private String lectrAplcUsrId;       /* 강의신청사용자ID */
    private String dtaDlYn;              /* 데이터삭제여부 */
    private String rowState;
}
