package com.kyowon.sms.wells.web.service.stock.service;

import static com.kyowon.sms.wells.web.service.stock.dto.WsnaOutOfStorageByTaskTypeDto.SearchReq;

import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.kyowon.sms.wells.web.service.stock.dto.WsnaOutOfStorageByTaskTypeDto.WareField;
import com.kyowon.sms.wells.web.service.stock.dvo.WsnaOutOfStorageByTaskTypeConvertDvo;
import com.kyowon.sms.wells.web.service.stock.mapper.WsnaOutOfStorageByTaskTypeMapper;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;

import lombok.RequiredArgsConstructor;

/**
 * <pre>
 *  K-W-SV-U-0253M01 품목별 자재출고현황
 * </pre>
 *
 * @author heymi.cho
 * @since 2023.07.26
 */
@Service
@RequiredArgsConstructor
public class WsnaOutOfStorageByTaskTypeService {

    private final WsnaOutOfStorageByTaskTypeMapper mapper;

    public List<WareField> getWareFields(SearchReq dto) {
        return this.mapper.selectWareField(dto);
    }

    public WsnaOutOfStorageByTaskTypeConvertDvo converPivotOutOfStorageByTaskType(SearchReq dto) {
        WsnaOutOfStorageByTaskTypeConvertDvo dvo = new WsnaOutOfStorageByTaskTypeConvertDvo();
        List<WareField> wareFields = this.mapper.selectWareField(dto);

        String wareByUseQty = "";
        String wareByPdctUprcSum = "";

        // 표시유형-수량
        if (dto.dispTypeCd().equals("1")) {
            wareByUseQty = wareFields.stream()
                .map(item -> "SUM(DECODE(HGR_WARE_NO,'" + item.wareNo() + "',USE_QTY)) TYP_" + item.wareNo())
                .collect(Collectors.joining(","));
            dvo.setWareByUseQty(wareByUseQty);
        } else if (dto.dispTypeCd().equals("2")) { // 금액
            wareByPdctUprcSum = wareFields.stream()
                .map(item -> "SUM(DECODE(HGR_WARE_NO,'" + item.wareNo() + "',PDCT_UPRC_SUM)) TYP_" + item.wareNo())
                .collect(Collectors.joining(","));
            dvo.setWareByPdctUprcSum(wareByPdctUprcSum);
        }

        //request dto 매핑
        dvo.setStOstrDt(dto.stOstrDt());
        dvo.setEdOstrDt(dto.edOstrDt());
        dvo.setCstDvCd(dto.cstDvCd());
        dvo.setStocTypeCd(dto.stocTypeCd());
        dvo.setTaskTypeCd(dto.taskTypeCd());
        dvo.setDispTypeCd(dto.dispTypeCd());
        dvo.setItmKndCd(dto.itmKndCd());
        dvo.setItmPdCd(dto.itmPdCd());
        dvo.setItmPdCdFrom(dto.itmPdCdFrom());
        dvo.setItmPdCdTo(dto.itmPdCdTo());
        dvo.setPdGdCd(dto.pdGdCd());
        dvo.setUseSel(dto.useSel());
        dvo.setItmGrpCd(dto.itmGrpCd());
        dvo.setSvMatGrpCd(dto.svMatGrpCd());

        return dvo;
    }

    public List<HashMap<String, Object>> getOutOfStorageByTaskType(WsnaOutOfStorageByTaskTypeConvertDvo dvo) {
        return mapper.selectOutOfStorageByTaskType(dvo);
    }

    /**
     * 품목별 자재출고현황 - 조회
     *
     * @param dto : { stOstrDt : 검색시작일, edOstrDt : 검색종료일, cstDvCd : 고객구분, stocTypeCd: 재고유형, taskTypeCd : 업무유형, dispTypeCd : 조회기준, itmKndCd : 품목구분, itmPdCd : 품목코드, pdGdCd : 등급코드, useSel : 사용유무  }
     * @return 조회결과
     */
    public PagingResult<HashMap<String, Object>> getOutOfStorageByTaskType(SearchReq dto, PageInfo pageInfo) {
        WsnaOutOfStorageByTaskTypeConvertDvo dvo = this.converPivotOutOfStorageByTaskType(dto);
        List<HashMap<String, Object>> list = this.getOutOfStorageByTaskType(dvo);
        PagingResult<HashMap<String, Object>> result = new PagingResult<>();
        result.setList(list);
        result.setPageInfo(pageInfo);

        return result;
    }

    /**
     * 품목별 자재출고현황 - 조회
     *
     * @param dto : { stOstrDt : 검색시작일, edOstrDt : 검색종료일, cstDvCd : 고객구분, stocTypeCd: 재고유형, taskTypeCd : 업무유형, dispTypeCd : 조회기준, itmKndCd : 품목구분, itmPdCd : 품목코드, pdGdCd : 등급코드, useSel : 사용유무  }
     * @return 조회결과
     */
    public List<HashMap<String, Object>> getOutOfStorageByTaskType(SearchReq dto) {
        WsnaOutOfStorageByTaskTypeConvertDvo dvo = this.converPivotOutOfStorageByTaskType(dto);
        List<HashMap<String, Object>> list = this.getOutOfStorageByTaskType(dvo);

        return list;

    }

}
