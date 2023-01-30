package com.kyowon.sms.wells.web.contract.common.dto;

import org.apache.commons.lang.StringUtils;

import io.swagger.annotations.ApiModel;

public class WctzAddressDto {
    // *********************************************************
    // Result Dto
    // *********************************************************
    // 계약공통주소 Search Result Dto
    /**
     * 주소 조회 후 주소 기본 데이터를 반환하는 DTO
     * @param adrId 주소ID
     * @param rnadr 도로명주소
     * @param rdadr 도로명상세주소
     * @param ltnAdr 지번주소
     * @param ltnDtlAdr 지번상세주소
     * @param clngAdrId 정제주소ID
     * @param rnadrMngtNo 도로명주소관리번호
     * @param newAdrZip 신주소우편번호
     * @param oldAdrZip 구주소우편번호
     * @param struMngtNo 건물관리번호
     * @param rcgvpKnm (설치)고객명
     * @param isNewAdr 도로명주소 존재여부
     */
    @ApiModel("WctzAddressDto-SearchRes")
    public record SearchAdrRes(
        String adrId,
        String adr,
        String dtlAdr,
        String adrZip,
        String rnadr,
        String rdadr,
        String ltnAdr,
        String ltnDtlAdr,
        String clngAdrId,
        String rnadrMngtNo,
        String newAdrZip,
        String oldAdrZip,
        String struMngtNo,
        String rcgvpKnm,
        boolean isNewAdr
    ) {
        public SearchAdrRes {
            isNewAdr = StringUtils.isNotEmpty(rnadr());
            adr = isNewAdr ? rnadr : ltnAdr;
            dtlAdr = isNewAdr ? rdadr : ltnDtlAdr;
            adrZip = isNewAdr ? newAdrZip : oldAdrZip;
        }
    }
}
