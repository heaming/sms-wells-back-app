package com.kyowon.sms.wells.web.product.manage.dvo;

import lombok.Getter;
import lombok.Setter;

/**
 * <pre>
 * 서비스 상품 - 생활맞춤필터관리 DVO
 * </pre>
 *
 * @author jintae.choi
 * @since 2023-10-10
 */
@Setter
@Getter
public class WpdcLifeCustomFilterBaseDvo {
    private String svPdCd; /* 서비스상품코드 */
    private String pdctPdCd; /* 제품상품코드 */
    private String partPdCd; /* 부품상품코드 */
    private Long dtlSn; /* 상세일련번호 */
    private String vstDvCd; /* 방문구분코드 */
    private String prdMmVal; /* 주기월값 */
    private String chPdctPdCd; /* 변경제품상품코드 */
    private String filtCmuCdv; /* 필터공용코드값 */
    private String filtCmuNm; /* 필터공용명 */
    private String filtCmuEpl; /* 필터공용설명 */
    private String dtaDlYn; /* 데이터삭제여부 */
}
