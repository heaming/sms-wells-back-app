package com.kyowon.sms.wells.web.service.visit.dvo;

import com.sds.sflex.system.config.annotation.DBDecField;
import com.sds.sflex.system.config.annotation.DBEncField;
import lombok.Getter;
import lombok.Setter;

/**
 * <pre>
 * W-SV-S-0053 고객센터에서 접수한 아웃소싱 업체 여러건 AS건에 대해 알림톡 발송
 * </pre>
 *
 * @author yeonghwa.cheon
 * @since 2023.02.01
 */
@Setter
@Getter
public class WsnbBusinessAsBiztalkDvo {

    String svBizHclsfCd; /* 서비스업무대분류코드 */
    String rcpdt; /* 접수일자 */
    String asIstOjNo; /* as설치대상번호 */
    String cstSvAsnNo; /* 고객서비스배정번호 */
    String cntrNo; /* 계약번호 */
    String cntrSn; /* 계약일련번호 */
    String svBizDclsfCd; /* 서비스업무세분류코드 */

    String cstKnm; /* 계약자명 */
    String pdNm; /* 제품명 */
    String vstDate; /* 방문일자 */
    String vstTime; /* 방문시간 */
    String vstTypeNm; /* 방문유형 */
    String cralLocaraTno; /* 휴대지역전화번호 */
    @DBEncField
    @DBDecField
    String mexnoEncr; /* 휴대전화국번호암호화 */
    String cralIdvTno; /* 휴대개별전화번호 */

}
