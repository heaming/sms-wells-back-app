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

    // TODO: 수수로 측 서비스 개발 전, 테스트용 UPDATE. 추후 개발 시, 삭제 예정
    //    int updateAdsbObjectTemp(WdebAwAdsbMgtDvo dvo);

    /* 재지급 금액 생성, TODO: 수수료쪽에서 제공 예정 */

    // TODO: 수수로 측 서비스 개발 전, 재지급 중복 체크용 SELECT. 추후 로직 확인 후, 삭제 예정
    //    int selectAdsbDupCheck(CreateReq dto);

}
