package com.kyowon.sms.wells.web.service.stock.service;

import static com.kyowon.sms.wells.web.service.stock.dto.WsnaMovementStoreDto.*;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kyowon.sms.wells.web.service.stock.converter.WsnaMovementStoreConfirmConverter;
import com.kyowon.sms.wells.web.service.stock.dvo.WsnaMovementStoreConfirmDvo;
import com.kyowon.sms.wells.web.service.stock.mapper.WsnaMovementStoreMapper;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;

import lombok.RequiredArgsConstructor;

/**
 * <pre>
 * W-SV-U-0109M01 이동입고현황
 * </pre>
 *
 * @author songTaeSung
 * @since 2023.02.13
 */
@Service
@RequiredArgsConstructor
public class WsnaMovementStoreService {

    private final WsnaMovementStoreMapper mapper;
    private final WsnaMovementStoreConfirmConverter converter;

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
    public List<SearchRes> getMovementStores(SearchReq dto) {
        return mapper.selectMovementStores(dto);
    }

    /**
     * 이동입고현황 엑셀다운로드
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
    public List<SearchRes> getMovementStoresExcelDownload(SearchReq dto) {
        return mapper.selectMovementStores(dto);
    }

    /**
     * 이동입고관리 조회
     *
     * @param dto : {
     *            stStrDt : 입고시작일자
     *            edStrDt : 입고종료일자
     *            strTpCd : 입고유형코드
     *            pageInfo : 페이징정보
     *            }
     *
     * @return 조회결과
     */
    public PagingResult<MovementRes> getMovementStrIzs(SearchReq dto, PageInfo pageInfo) {
        return mapper.selectMoveMentStrIzs(dto, pageInfo);
    }

    /**
     * 이동입고관리 엑셀다운로드
     *
     * @param dto : {
     *            stStrDt : 입고시작일자
     *            edStrDt : 입고종료일자
     *            strTpCd : 입고유형코드
     *            wareDvCd : 창고구분코드
     *            }
     *
     * @return 조회결과
     */
    public List<MovementRes> getMovementStrIzsExcelDownload(SearchReq dto) {
        return mapper.selectMoveMentStrIzs(dto);
    }

    public PagingResult<MovementOstrMngtRes> getMovementStoresReg(MovementOstrMngtReq dto, PageInfo pageInfo) {
        return mapper.selectMovementStoresReg(dto, pageInfo);
    }

    public List<MovementOstrMngtRes> getMovementStoresReg(MovementOstrMngtReq dto) {
        return mapper.selectMovementStoresReg(dto);
    }

    @Transactional
    public int saveStrMovementConfrim(List<MovementStrSaveReq> list) {
        int cnt = 0;
        for (MovementStrSaveReq dto : list) {
            WsnaMovementStoreConfirmDvo dvo = this.converter.mapDtoToWsnaMovementStoreConfirmDvo(dto);
            cnt += mapper.saveStrConfirm(dvo);
            cnt += mapper.saveOstrConfirm(dvo);
            cnt += mapper.saveItemQtyConfirm(dvo);
        }

        return cnt;
    }

    /**
     * 이관입고 삭제
     * @param dtos
     * @return
     */
    @Transactional
    public int removeMovement(List<MovementStrSaveReq> dtos) {

        int count = 0;

        for (MovementStrSaveReq dto : dtos) {

            WsnaMovementStoreConfirmDvo dvo = this.converter.mapDtoToWsnaMovementStoreConfirmDvo(dto);

            count += mapper.updateItmStrForRemove(dvo);
            count += mapper.updateItmOstrForRemove(dvo);
            count += mapper.updateCstSvItmStocForRemove(dvo);
        }

        return count;
    }

    public int getStrWareMonthlyClosed(warehouseMonthlyReq dto) {
        return mapper.countStrWareMonthlyClosed(dto);
    }
}
