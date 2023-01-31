package com.kyowon.sms.wells.web.service.visit.dto;

import com.sds.sflex.common.utils.DbEncUtil;
import io.swagger.annotations.ApiModel;

/**
 * <pre>
 * W-SV-S-0080 설치, A/S 해피콜 발송(리뉴얼)
 * </pre>
 *
 * @author yeonghwa.cheon
 * @since 2023.01.26
 */
public class WsnbInstallationHpcallFwDto {

    @ApiModel(value = "WsnbInstallationHpcallFwDto-SearchReq")
    public record SearchReq(
        String cstSvAsnNo, /* 고객서비스배정번호 */
        String wkExcnDt, /* 작업수행일자 */
        String cralLocaraTno, /* 휴대지역전화번호 */
        String mexnoEncr, /* 휴대전화국번호암호화 */
        String cralIdvTno, /* 휴대개별전화번호 */
        String cntrNo /* 계약번호 */
    ) {
        public SearchReq {
            mexnoEncr = DbEncUtil.enc(mexnoEncr); //암호화
        }
    }

}
