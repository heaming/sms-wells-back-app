package com.kyowon.sms.wells.web.service.stock.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import static com.kyowon.sms.wells.web.service.stock.dto.WsnaMovementStoreDto.*;

@Mapper
public interface WsnaMovementStoreMapper {

    /**
    * 이동입고현황 조회
    *
    * @param dto : {
    *            stStrDt : 입고시작일자
    *            edStrDt : 입고종료일자
    *            strTpCd : 입고유형코드
    *            ostrWareDvCd : 출고창고구분코드
    *            ostrWareNoD : 출고창고디테일번호
    *            ostrWareNoM : 출고창고마스터번호
    *            }
    *
    * @return 조회결과
    */
    List<SearchRes> selectMovementStores(SearchReq dto);
}
