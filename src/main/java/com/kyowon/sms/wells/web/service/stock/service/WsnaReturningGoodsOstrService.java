package com.kyowon.sms.wells.web.service.stock.service;

import java.util.List;

import com.sds.sflex.system.config.validation.BizAssert;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kyowon.sms.wells.web.service.stock.converter.WsnaReturningGoodsOstrConverter;
import com.kyowon.sms.wells.web.service.stock.dto.WsnaReturningGoodsOstrDto.*;
import com.kyowon.sms.wells.web.service.stock.dvo.WsnaReturningGoodsDvo;
import com.kyowon.sms.wells.web.service.stock.mapper.WsnaReturningGoodsOstrMapper;
import com.sds.sflex.common.utils.StringUtil;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * <pre>
 * W-SV-U-0001M01 반품출고 등록
 * </pre>
 *
 * @author hyewon.kim 김혜원
 * @since 2023.02.14
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class WsnaReturningGoodsOstrService {

    final String RETURN_INSIDE = "261"; // 반품출고(내부)
    final String RETURN_OUTSIDE = "262"; // 반품출고(외부)
    final String WARE_DV_CD_LOGISTICS_CENTER = "1"; // 창고구분코드 = 물류센터

    private final WsnaReturningGoodsOstrMapper mapper;

    private final WsnaReturningGoodsOstrConverter converter;

    public List<SearchWarehouseRes> getWareHouses(SearchWarehouseReq dto) {
        return this.mapper.selectWareHouses(dto);
    }

    public SearchRes getReturningGoodsOstrs(String itmOstrNo) {
        ItemOutOfStorage itemOutOfStorage = this.mapper.selectItemOutOfStorage(itmOstrNo);
        List<ReturningGoods> returningGoods = this.converter
            .mapAllReturningGoodsDvoToReturningGoods(this.mapper.selectReturningGoodsOstrs(itmOstrNo));

        return new SearchRes(itemOutOfStorage, returningGoods);
    }

    public Boolean isClosed(String itmOstrNo) {
        String sellReceiptDate = this.mapper.selectIsClosedByPk(itmOstrNo);
        return StringUtil.isNotEmpty(sellReceiptDate);
    }

    @Transactional
    public int saveReturningGoodsOstrs(List<SaveReq> dtos) throws Exception {
        int processCount = 0;
        int serialNumber = 0;

        for (SaveReq dto : dtos) {
            serialNumber += 1;
            WsnaReturningGoodsDvo dvo = this.converter.mapSaveReqToReturningGoodsDvo(dto);

            dvo.setOstrSn(String.valueOf(serialNumber));
            dvo.setStrSn(String.valueOf(serialNumber));

            // 품목출고내역 insert
            int result = this.mapper.insertItemForwardingHistory(dvo);

            if (isReturnToLogistics(dvo.getOstrTpCd(), dvo.getStrWareDvCd())) {
                // TODO: 반품(내부)이고 입고 창고가 물류센터인 경우 - 반품요청 중계 테이블 Insert
                // TODO: 품목재고내역관리 서비스(W-SV-S-0087)의 품목재고내역 등록 메소드(saveItemStockIzRgsts)를 호출 - 반품출고
            } else if (isReturning(dvo.getOstrTpCd())) {
                // 반품(내부/외부)이고 입고창고가 물류센터가 아닌 경우 - 품목입고내역 insert
                result = this.mapper.insertItemReceivingHistory(dvo);
                // TODO: 품목재고내역 등록 메소드(saveItemStockIzRgsts)를 호출 - 반품출고
                // TODO: 품목재고내역 이동 메소드(saveItemStockIzMmts)를 호출 - 재고이동
                // TODO: 품목재고내역 등록 메소드(saveItemStockIzRgsts)를 호출 - 입고
            } else {
                // TODO: 품목재고내역 등록 메소드(saveItemStockIzRgsts)를 호출 - 폐기출고
            }

            BizAssert.isTrue(result == 1, "MSG_ALT_SVE_ERR");
            processCount += result;
        }

        return processCount;
    }

    @Transactional
    public int removeReturningGoodsOstrs(List<RemoveReq> dtos) throws Exception {
        int processCount = 0;

        for (RemoveReq dto : dtos) {
            WsnaReturningGoodsDvo dvo = this.converter.mapRemoveReqToReturningGoodsDvo(dto);

            // 반품출고(내부/외부)인 경우
            if (isReturning(dvo.getOstrTpCd())) {
                // 입고창고가 물류센터인 경우
                if (WARE_DV_CD_LOGISTICS_CENTER.equals(dvo.getStrWareDvCd())) {
                    // TODO: 물류센터에 출고 요청 취소(삭제) Interface 를 위한 중계테이블에 Insert

                } else {
                    // TODO: 품목재고내역 삭제 메소드(saveItemStockIzDls)를 호출 - 입고창고의 입고재고수량 삭제
                    // TODO: 품목재고내역 이동 메소드(saveItemStockIzMmts)를 호출 - 입고창고의 이동재고수량 삭제
                    this.mapper.deleteItemReceivingHistory(dvo); // 품목입고내역삭제
                }
            }

            // TODO: 품목재고내역 삭제 메소드(saveItemStockIzDls)를 호출 - 출고창고의 출고재고수량 복원

            int result = this.mapper.deleteItemForwardingHistory(dvo); // 품목출고내역삭제

            BizAssert.isTrue(result == 1, "MSG_ALT_DEL_ERR");
            processCount += result;
        }

        return processCount;
    }

    public Boolean isReturning(String ostrTpCd) {
        return RETURN_INSIDE.equals(ostrTpCd) || RETURN_OUTSIDE.equals(ostrTpCd);
    }

    public Boolean isReturnToLogistics(String ostrTpCd, String strWareDvCd) {
        return RETURN_INSIDE.equals(ostrTpCd) && WARE_DV_CD_LOGISTICS_CENTER.equals(strWareDvCd);
    }

}
