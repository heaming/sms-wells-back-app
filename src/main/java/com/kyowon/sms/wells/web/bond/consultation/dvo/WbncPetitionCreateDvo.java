package com.kyowon.sms.wells.web.bond.consultation.dvo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class WbncPetitionCreateDvo {
    private String bndBizDvCd; /*채권업무구분코드*/
    private String bndLwsBilDvCd; /*채권소송청구구분코드*/
    private String bndBilPpsDvCd; /*채권청구취지구분코드*/
    private String istmYn; /*할부여부*/
    private String dscYn; /*할인여부*/
    private String cntrDtlStatCd; /*계약상세상태코드*/

}
