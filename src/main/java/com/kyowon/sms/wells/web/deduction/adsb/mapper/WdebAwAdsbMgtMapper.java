package com.kyowon.sms.wells.web.deduction.adsb.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.kyowon.sms.wells.web.deduction.adsb.dto.WdebAwAdsbMgtDto.CreateReq;
import com.kyowon.sms.wells.web.deduction.adsb.dvo.WdebAwAdsbMgtDvo;

@Mapper
public interface WdebAwAdsbMgtMapper {

    /* 재지급 대상 생성 전, 확정여부 확인 validation */
    int selectAdsbObjecConfirmCheck(CreateReq dto);

    /* 재지급 대상 생성 전, 임시저장 데이터 삭제 */
    int deleteAdsbObjectTemp(WdebAwAdsbMgtDvo dvo);

    /* 재지급 대상 확인 */
    int selectAdsbObjectCreates(WdebAwAdsbMgtDvo dvo);

    /* 상조 재지급 대상 생성 */
    int updateLifeAdsbObjectWells(WdebAwAdsbMgtDvo dvo);

    /* 재지급 대상 생성  */
    int insertAdsbObjectCreates(WdebAwAdsbMgtDvo dvo);
}
