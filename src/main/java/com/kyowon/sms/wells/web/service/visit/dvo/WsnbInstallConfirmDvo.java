package com.kyowon.sms.wells.web.service.visit.dvo;

import com.sds.sflex.system.config.annotation.DBDecField;
import com.sds.sflex.system.config.annotation.DBEncField;

import lombok.Getter;
import lombok.Setter;

/**
 * <pre>
 * W-SV-S-0054 설치 확인서 알림톡 발송
 * </pre>
 *
 * @author yeonghwa.cheon
 * @since 2023.01.13
 */
@Setter
@Getter
public class WsnbInstallConfirmDvo {

    String cntrNo; /* 계약번호 */
    String cstKnm; /* 계약자명 */
    String itemGrpNm; /* 제품그룹명 */
    String pdNm; /* 제품명 */
    String vstFshDt; /* 방문완료일자 */
    String cralLocaraTno; /* 휴대지역전화번호 */
    @DBEncField
    @DBDecField
    String mexnoEncr; /* 휴대전화국번호암호화 (휴대폰 번호 insert 하는지 확인필요) */
    String cralIdvTno; /* 휴대개별전화번호 */

}
