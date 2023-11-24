package com.kyowon.sms.wells.web.service.stock.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.kyowon.sms.wells.web.service.stock.dto.WsnaInstallationStockPsByDayDto.SearchReq;
import com.kyowon.sms.wells.web.service.stock.dto.WsnaInstallationStockPsByDayDto.SearchResPsWareStr;
import com.kyowon.sms.wells.web.service.stock.dvo.WsnaInstallationStockPsByDayCenterDvo;
import com.kyowon.sms.wells.web.service.stock.dvo.WsnaInstallationStockPsByDayPdDvo;
import com.kyowon.sms.wells.web.service.stock.mapper.WsnaInstallationStockPsByDayMapper;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * <pre>
 * K-W-SV-U-0116M01 일자별 설치재고 현황
 * </pre>
 *
 * @author heymi.cho 조혜미
 * @since 2023.07.11
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class WsnaInstallationStockPsByDayService {
    private final WsnaInstallationStockPsByDayMapper mapper;

    public PagingResult<WsnaInstallationStockPsByDayCenterDvo> getInstallationStockPsByDayCenter(
        SearchReq dto, PageInfo pageInfo
    ) {
        Map<String, SearchResPsWareStr> wareStrMap = mapper.selectPsWareStr(dto).stream()
            .collect(Collectors.toMap(SearchResPsWareStr::itmPdCd, Function.identity()));

        PagingResult<WsnaInstallationStockPsByDayCenterDvo> list = mapper
            .selectInstallationStockPsByDayCenter(dto, pageInfo);
        List<WsnaInstallationStockPsByDayCenterDvo> dvoList = new ArrayList<>();

        for (int i = 0; i < list.getList().size(); i++) {
            WsnaInstallationStockPsByDayCenterDvo item = list.getList().get(i);
            SearchResPsWareStr wareStr = wareStrMap.get(item.getPdCd());
            item.setPajuQty(wareStr.qtyPajuSum());
            //item.setEngQty(wareStr.qtyEngSum());
            //item.setPrvMng(wareStr.qtyCenterSum());
            dvoList.add(item);
        }

        list.setList(dvoList);

        return list;
    }

    public List<WsnaInstallationStockPsByDayCenterDvo> getInstallationStockPsByDayCenter(SearchReq dto) {
        return mapper.selectInstallationStockPsByDayCenter(dto);
    }

    public PagingResult<WsnaInstallationStockPsByDayPdDvo> getInstallationStockPsByDayPd(
        SearchReq dto, PageInfo pageInfo
    ) {

        Map<String, SearchResPsWareStr> wareStrMap = mapper.selectPsWareStr(dto).stream()
            .collect(Collectors.toMap(SearchResPsWareStr::itmPdCd, Function.identity()));

        PagingResult<WsnaInstallationStockPsByDayPdDvo> list = mapper.selectInstallationStockPsByDayPd(dto, pageInfo);
        List<WsnaInstallationStockPsByDayPdDvo> dvoList = new ArrayList<>();

        for (int i = 0; i < list.getList().size(); i++) {
            WsnaInstallationStockPsByDayPdDvo item = list.getList().get(i);
            SearchResPsWareStr wareStr = wareStrMap.get(item.getPdCd());
            item.setCenterQty(wareStr.qtyCenterSum());
            item.setEngQty(wareStr.qtyEngSum());
            item.setPajuQty(wareStr.qtyPajuSum());
            dvoList.add(item);
        }

        list.setList(dvoList);

        return list;
    }

    public List<WsnaInstallationStockPsByDayPdDvo> getInstallationStockPsByDayPd(SearchReq dto) {
        return mapper.selectInstallationStockPsByDayPd(dto);
    }
}
