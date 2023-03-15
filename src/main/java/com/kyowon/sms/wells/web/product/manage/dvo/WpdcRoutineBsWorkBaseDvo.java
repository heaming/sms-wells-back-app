package com.kyowon.sms.wells.web.product.manage.dvo;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class WpdcRoutineBsWorkBaseDvo {
    private String svPdCd; /* 서비스상품코드 */
    private String pdctPdCd; /* 제품상품코드 */
    private Long dtlSn; /* 상세일련번호 */
    private String svBizDclsfCd; /* 서비스업무세분류코드 */
    private String vstDvCd; /* 방문구분코드 */
    private String filtChngLvCd; /* 필터교체단계코드 */
    private String partPdCd; /* 부품상품코드 */
    private Long partUseQty; /* 부품사용수량 */
    private Long svPrdMmN; /* 서비스주기월수 */
    private Long svStrtmmN; /* 서비스시작월수 */
    private Long svTms; /* 서비스횟수 */
    private Long totStplMcn; /* 총약정개월수 */
    private String excdMmVal; /* 제외월값 */
    private String istMm; /* 설치월 */
    private String strtWkYVal; /* 시작작업년도값 */
    private String wkMm; /* 작업월 */
    private String dtaDlYn; /* 데이터삭제여부 */
}
