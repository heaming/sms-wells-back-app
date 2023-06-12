package com.kyowon.sms.wells.web.service.stock.mapper;

import static com.kyowon.sms.wells.web.service.stock.dto.WsnaMovementStoreDto.*;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.kyowon.sms.wells.web.service.stock.dvo.WsnaMovementStoreConfirmDvo;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;

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

    /**
    * 이동입고현황 조회
    *
    * @param dto : {
    *            stStrDt : 입고시작일자
    *            edStrDt : 입고종료일자
    *            strTpCd : 입고유형코드
    *            }
    *
    * @return 조회결과
    */
    PagingResult<MovementRes> selectMoveMentStrIzs(SearchReq dto, PageInfo pageInfo);

    List<MovementRes> selectMoveMentStrIzs(SearchReq dto);

    PagingResult<MovementOstrMngtRes> selectMovementStoresReg(MovementOstrMngtReq dto, PageInfo pageInfo);

    List<MovementOstrMngtRes> selectMovementStoresReg(MovementOstrMngtReq dto);

    int saveStrConfirm(WsnaMovementStoreConfirmDvo dvo);

    int saveOstrConfirm(WsnaMovementStoreConfirmDvo dvo);

    int saveItemQtyConfirm(WsnaMovementStoreConfirmDvo dvo);

    int countStrWareMonthlyClosed(warehouseMonthlyReq dto);
}
