package com.kyowon.sms.wells.web.service.stock.service;

import java.util.*;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.kyowon.sms.wells.web.service.stock.dto.WsnaInstallationStockPsByDayDto.SearchReq;
import com.kyowon.sms.wells.web.service.stock.dvo.WsnaInstallationStockPsByDayCenterDvo;
import com.kyowon.sms.wells.web.service.stock.dvo.WsnaInstallationStockPsByDayCenterValueDvo;
import com.kyowon.sms.wells.web.service.stock.dvo.WsnaInstallationStockPsByDayPdDvo;
import com.kyowon.sms.wells.web.service.stock.dvo.WsnaInstallationStockPsByDayPdValueDvo;
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
        List<WsnaInstallationStockPsByDayCenterValueDvo> queryResult = mapper
            .selectInstallationStockPsByDayCenter(dto);
        Map<String, WsnaInstallationStockPsByDayCenterDvo> map = new HashMap<>();

        for (int i = 0; i < queryResult.size(); i++) {
            WsnaInstallationStockPsByDayCenterValueDvo item = queryResult.get(i);

            WsnaInstallationStockPsByDayCenterDvo dvo = map
                .getOrDefault(item.getOgCd(), new WsnaInstallationStockPsByDayCenterDvo());

            int rn = item.getRn();

            switch (rn) {
                case 1:
                    dvo.setStockdate1(item.getDateQty());
                    dvo.setInstalldate1(item.getAsnQty());
                    break;
                case 2:
                    dvo.setStockdate2(item.getDateQty());
                    dvo.setInstalldate2(item.getAsnQty());
                    break;
                case 3:
                    dvo.setStockdate3(item.getDateQty());
                    dvo.setInstalldate3(item.getAsnQty());
                    break;
                case 4:
                    dvo.setStockdate4(item.getDateQty());
                    dvo.setInstalldate4(item.getAsnQty());
                    break;
                case 5:
                    dvo.setStockdate5(item.getDateQty());
                    dvo.setInstalldate5(item.getAsnQty());
                    break;
                case 6:
                    dvo.setStockdate6(item.getDateQty());
                    dvo.setInstalldate6(item.getAsnQty());
                    break;
                case 7:
                    dvo.setStockdate7(item.getDateQty());
                    dvo.setInstalldate7(item.getAsnQty());
                    break;
                case 8:
                    dvo.setStockdate8(item.getDateQty());
                    dvo.setInstalldate8(item.getAsnQty());
                    break;
                case 9:
                    dvo.setStockdate9(item.getDateQty());
                    dvo.setInstalldate9(item.getAsnQty());
                    break;
                case 10:
                    dvo.setStockdate10(item.getDateQty());
                    dvo.setInstalldate10(item.getAsnQty());
                    break;
                case 11:
                    dvo.setStockdate11(item.getDateQty());
                    dvo.setInstalldate11(item.getAsnQty());
                    break;
                case 12:
                    dvo.setStockdate12(item.getDateQty());
                    dvo.setInstalldate12(item.getAsnQty());
                    break;
                case 13:
                    dvo.setStockdate13(item.getDateQty());
                    dvo.setInstalldate13(item.getAsnQty());
                    break;
                case 14:
                    dvo.setStockdate14(item.getDateQty());
                    dvo.setInstalldate14(item.getAsnQty());
                    break;
                case 15:
                    dvo.setStockdate15(item.getDateQty());
                    dvo.setInstalldate15(item.getAsnQty());
                    break;
                case 16:
                    dvo.setStockdate16(item.getDateQty());
                    dvo.setInstalldate16(item.getAsnQty());

                    dvo.setOgCd(item.getOgCd());
                    dvo.setOgNm(item.getOgNm());
                    dvo.setPdCd(item.getPdCd());
                    dvo.setSapMatCd(item.getSapMatCd());
                    dvo.setPdNm(item.getPdNm());
                    dvo.setSumQtyCenter(item.getSumQtyCenter());
                    dvo.setSumQtyEng(item.getSumQtyEng());
                    dvo.setSumQtyTot(item.getSumQtyTot());
                    //                    dvo.setAsnDate(item.getAsnDate());
                    dvo.setAggAsnCnt(item.getAggAsnCnt());
                    //                    dvo.setDateQty(item.getDateQty());
                    dvo.setRn(item.getRn());
                    break;
                default:
                    dvo.setOgCd(item.getOgCd());
                    dvo.setOgNm(item.getOgNm());
                    dvo.setPdCd(item.getPdCd());
                    dvo.setSapMatCd(item.getSapMatCd());
                    dvo.setPdNm(item.getPdNm());
                    dvo.setSumQtyCenter(item.getSumQtyCenter());
                    dvo.setSumQtyEng(item.getSumQtyEng());
                    dvo.setSumQtyTot(item.getSumQtyTot());
                    //                    dvo.setAsnDate(item.getAsnDate());
                    dvo.setAggAsnCnt(item.getAggAsnCnt());
                    //                    dvo.setDateQty(item.getDateQty());
                    dvo.setRn(item.getRn());
                    break;
            }

            map.put(item.getOgCd(), dvo);
        }
        List<WsnaInstallationStockPsByDayCenterDvo> sortList = new ArrayList<>(map.values());

        sortList.sort(
            Comparator.comparing(WsnaInstallationStockPsByDayCenterDvo::getOgCd)
            //                .thenComparing(WsnaInstallationStockPsByDayCenterDvo::getPdCd)
            //                .thenComparing(WsnaInstallationStockPsByDayCenterDvo::getAsnDate)
        );
        pageInfo.setTotalCount(Long.valueOf(sortList.size()));
        pageInfo.setPageSize(sortList.size());

        PagingResult<WsnaInstallationStockPsByDayCenterDvo> result = new PagingResult<>(sortList, pageInfo);

        return result;
    }

    public List<WsnaInstallationStockPsByDayCenterDvo> getInstallationStockPsByDayCenter(SearchReq dto) {
        List<WsnaInstallationStockPsByDayCenterValueDvo> queryResult = mapper
            .selectInstallationStockPsByDayCenter(dto);
        Map<String, WsnaInstallationStockPsByDayCenterDvo> map = new HashMap<>();

        for (int i = 0; i < queryResult.size(); i++) {

            WsnaInstallationStockPsByDayCenterValueDvo item = queryResult.get(i);

            WsnaInstallationStockPsByDayCenterDvo dvo = map
                .getOrDefault(item.getOgCd(), new WsnaInstallationStockPsByDayCenterDvo());

            int rn = item.getRn();

            switch (rn) {
                case 1:
                    dvo.setStockdate1(item.getDateQty());
                    dvo.setInstalldate1(item.getAsnQty());
                    break;
                case 2:
                    dvo.setStockdate2(item.getDateQty());
                    dvo.setInstalldate2(item.getAsnQty());
                    break;
                case 3:
                    dvo.setStockdate3(item.getDateQty());
                    dvo.setInstalldate3(item.getAsnQty());
                    break;
                case 4:
                    dvo.setStockdate4(item.getDateQty());
                    dvo.setInstalldate4(item.getAsnQty());
                    break;
                case 5:
                    dvo.setStockdate5(item.getDateQty());
                    dvo.setInstalldate5(item.getAsnQty());
                    break;
                case 6:
                    dvo.setStockdate6(item.getDateQty());
                    dvo.setInstalldate6(item.getAsnQty());
                    break;
                case 7:
                    dvo.setStockdate7(item.getDateQty());
                    dvo.setInstalldate7(item.getAsnQty());
                    break;
                case 8:
                    dvo.setStockdate8(item.getDateQty());
                    dvo.setInstalldate8(item.getAsnQty());
                    break;
                case 9:
                    dvo.setStockdate9(item.getDateQty());
                    dvo.setInstalldate9(item.getAsnQty());
                    break;
                case 10:
                    dvo.setStockdate10(item.getDateQty());
                    dvo.setInstalldate10(item.getAsnQty());
                    break;
                case 11:
                    dvo.setStockdate11(item.getDateQty());
                    dvo.setInstalldate11(item.getAsnQty());
                    break;
                case 12:
                    dvo.setStockdate12(item.getDateQty());
                    dvo.setInstalldate12(item.getAsnQty());
                    break;
                case 13:
                    dvo.setStockdate13(item.getDateQty());
                    dvo.setInstalldate13(item.getAsnQty());
                    break;
                case 14:
                    dvo.setStockdate14(item.getDateQty());
                    dvo.setInstalldate14(item.getAsnQty());
                    break;
                case 15:
                    dvo.setStockdate15(item.getDateQty());
                    dvo.setInstalldate15(item.getAsnQty());
                    break;
                case 16:
                    dvo.setStockdate16(item.getDateQty());
                    dvo.setInstalldate16(item.getAsnQty());

                    dvo.setOgCd(item.getOgCd());
                    dvo.setOgNm(item.getOgNm());
                    dvo.setPdCd(item.getPdCd());
                    dvo.setSapMatCd(item.getSapMatCd());
                    dvo.setPdNm(item.getPdNm());
                    dvo.setSumQtyCenter(item.getSumQtyCenter());
                    dvo.setSumQtyEng(item.getSumQtyEng());
                    dvo.setSumQtyTot(item.getSumQtyTot());
                    //                    dvo.setAsnDate(item.getAsnDate());
                    dvo.setAggAsnCnt(item.getAggAsnCnt());
                    //                    dvo.setDateQty(item.getDateQty());
                    dvo.setRn(item.getRn());
                    break;
                default:
                    dvo.setOgCd(item.getOgCd());
                    dvo.setOgNm(item.getOgNm());
                    dvo.setPdCd(item.getPdCd());
                    dvo.setSapMatCd(item.getSapMatCd());
                    dvo.setPdNm(item.getPdNm());
                    dvo.setSumQtyCenter(item.getSumQtyCenter());
                    dvo.setSumQtyEng(item.getSumQtyEng());
                    dvo.setSumQtyTot(item.getSumQtyTot());
                    //                    dvo.setAsnDate(item.getAsnDate());
                    dvo.setAggAsnCnt(item.getAggAsnCnt());
                    //                    dvo.setDateQty(item.getDateQty());
                    dvo.setRn(item.getRn());
                    break;
            }
            map.put(item.getOgCd(), dvo);
        }

        List<WsnaInstallationStockPsByDayCenterDvo> sortList = new ArrayList<>(map.values());

        sortList.sort(
            Comparator.comparing(WsnaInstallationStockPsByDayCenterDvo::getOgCd)
            //                .thenComparing(WsnaInstallationStockPsByDayCenterDvo::getPdCd)
            //                .thenComparing(WsnaInstallationStockPsByDayCenterDvo::getAsnDate)
        );

        return sortList;
    }

    public PagingResult<WsnaInstallationStockPsByDayPdDvo> getInstallationStockPsByDayPd(
        SearchReq dto, PageInfo pageInfo
    ) {
        List<WsnaInstallationStockPsByDayPdValueDvo> queryResult = mapper
            .selectInstallationStockPsByDayPd(dto);

        Map<String, WsnaInstallationStockPsByDayPdDvo> map = new HashMap<>();

        //        List<WsnaInstallationStockPsByDayPdDvo> sortList = new ArrayList<>();

        for (int i = 0; i < queryResult.size(); i++) {
            WsnaInstallationStockPsByDayPdValueDvo item = queryResult.get(i);

            WsnaInstallationStockPsByDayPdDvo dvo = map
                .getOrDefault(item.getPdCd(), new WsnaInstallationStockPsByDayPdDvo());

            int rn = item.getRn();

            switch (rn) {
                case 1:
                    dvo.setStockdate1(item.getDateQty());
                    dvo.setInstalldate1(item.getAsnQty());
                    break;
                case 2:
                    dvo.setStockdate2(item.getDateQty());
                    dvo.setInstalldate2(item.getAsnQty());
                    break;
                case 3:
                    dvo.setStockdate3(item.getDateQty());
                    dvo.setInstalldate3(item.getAsnQty());
                    break;
                case 4:
                    dvo.setStockdate4(item.getDateQty());
                    dvo.setInstalldate4(item.getAsnQty());
                    break;
                case 5:
                    dvo.setStockdate5(item.getDateQty());
                    dvo.setInstalldate5(item.getAsnQty());
                    break;
                case 6:
                    dvo.setStockdate6(item.getDateQty());
                    dvo.setInstalldate6(item.getAsnQty());
                    break;
                case 7:
                    dvo.setStockdate7(item.getDateQty());
                    dvo.setInstalldate7(item.getAsnQty());
                    break;
                case 8:
                    dvo.setStockdate8(item.getDateQty());
                    dvo.setInstalldate8(item.getAsnQty());
                    break;
                case 9:
                    dvo.setStockdate9(item.getDateQty());
                    dvo.setInstalldate9(item.getAsnQty());
                    break;
                case 10:
                    dvo.setStockdate10(item.getDateQty());
                    dvo.setInstalldate10(item.getAsnQty());
                    break;
                case 11:
                    dvo.setStockdate11(item.getDateQty());
                    dvo.setInstalldate11(item.getAsnQty());
                    break;
                case 12:
                    dvo.setStockdate12(item.getDateQty());
                    dvo.setInstalldate12(item.getAsnQty());
                    break;
                case 13:
                    dvo.setStockdate13(item.getDateQty());
                    dvo.setInstalldate13(item.getAsnQty());
                    break;
                case 14:
                    dvo.setStockdate14(item.getDateQty());
                    dvo.setInstalldate14(item.getAsnQty());
                    break;
                case 15:
                    dvo.setStockdate15(item.getDateQty());
                    dvo.setInstalldate15(item.getAsnQty());
                    break;
                case 16:
                    dvo.setStockdate16(item.getDateQty());
                    dvo.setInstalldate16(item.getAsnQty());

                    dvo.setOgCd(item.getOgCd());
                    dvo.setOgNm(item.getOgNm());
                    dvo.setPdCd(item.getPdCd());
                    dvo.setSapMatCd(item.getSapMatCd());
                    dvo.setPdNm(item.getPdNm());
                    dvo.setSumQtyCenter(item.getSumQtyCenter());
                    dvo.setSumQtyEng(item.getSumQtyEng());
                    dvo.setSumQtyTot(item.getSumQtyTot());
                    //                    dvo.setAsnDate(item.getAsnDate());
                    dvo.setAggAsnCnt(item.getAggAsnCnt());
                    //                    dvo.setDateQty(item.getDateQty());
                    dvo.setRn(item.getRn());
                    dvo.setLgstQty(item.getLgstQty());

                    //                    sortList.add(dvo);
                    //                    log.debug(dvo.toString());
                    break;
                default:
                    dvo.setOgCd(item.getOgCd());
                    dvo.setOgNm(item.getOgNm());
                    dvo.setPdCd(item.getPdCd());
                    dvo.setSapMatCd(item.getSapMatCd());
                    dvo.setPdNm(item.getPdNm());
                    dvo.setSumQtyCenter(item.getSumQtyCenter());
                    dvo.setSumQtyEng(item.getSumQtyEng());
                    dvo.setSumQtyTot(item.getSumQtyTot());
                    //                    dvo.setAsnDate(item.getAsnDate());
                    dvo.setAggAsnCnt(item.getAggAsnCnt());
                    dvo.setDateQty(item.getDateQty());
                    dvo.setRn(item.getRn());
                    dvo.setLgstQty(item.getLgstQty());

                    //                                        sortList.add(dvo);
                    break;
            }
            map.put(item.getPdCd(), dvo);
        }

        List<WsnaInstallationStockPsByDayPdDvo> list = new ArrayList(map.values());
        List<WsnaInstallationStockPsByDayPdDvo> sortList = list.stream().filter(item -> item.getAggAsnCnt() > 0)
            .collect(Collectors.toList());
        //        for (Map.Entry<String, WsnaInstallationStockPsByDayPdDvo> entry : map.entrySet()) {
        //            if (entry.getValue().getAggAsnCnt() > 0)
        //                sortList.add(entry.getValue());
        //        }

        log.info(sortList.toString());

        sortList.sort(
            Comparator.comparing(WsnaInstallationStockPsByDayPdDvo::getPdCd)
            //                .thenComparing(WsnaInstallationStockPsByDayPdDvo::getOgCd)
            //                .thenComparing(WsnaInstallationStockPsByDayCenterDvo::getAsnDate)
        );

        pageInfo.setTotalCount(Long.valueOf(sortList.size()));
        pageInfo.setPageSize(sortList.size());

        PagingResult<WsnaInstallationStockPsByDayPdDvo> result = new PagingResult<>(sortList, pageInfo);

        return result;
    }

    public List<WsnaInstallationStockPsByDayPdDvo> getInstallationStockPsByDayPd(SearchReq dto) {
        List<WsnaInstallationStockPsByDayPdValueDvo> queryResult = mapper
            .selectInstallationStockPsByDayPd(dto);
        Map<String, WsnaInstallationStockPsByDayPdDvo> map = new HashMap<>();

        for (int i = 0; i < queryResult.size(); i++) {
            WsnaInstallationStockPsByDayPdValueDvo item = queryResult.get(i);
            WsnaInstallationStockPsByDayPdDvo dvo = map
                .getOrDefault(item.getPdCd(), new WsnaInstallationStockPsByDayPdDvo());

            int rn = item.getRn();

            switch (rn) {
                case 1:
                    dvo.setStockdate1(item.getDateQty());
                    dvo.setInstalldate1(item.getAsnQty());
                    break;
                case 2:
                    dvo.setStockdate2(item.getDateQty());
                    dvo.setInstalldate2(item.getAsnQty());
                    break;
                case 3:
                    dvo.setStockdate3(item.getDateQty());
                    dvo.setInstalldate3(item.getAsnQty());
                    break;
                case 4:
                    dvo.setStockdate4(item.getDateQty());
                    dvo.setInstalldate4(item.getAsnQty());
                    break;
                case 5:
                    dvo.setStockdate5(item.getDateQty());
                    dvo.setInstalldate5(item.getAsnQty());
                    break;
                case 6:
                    dvo.setStockdate6(item.getDateQty());
                    dvo.setInstalldate6(item.getAsnQty());
                    break;
                case 7:
                    dvo.setStockdate7(item.getDateQty());
                    dvo.setInstalldate7(item.getAsnQty());
                    break;
                case 8:
                    dvo.setStockdate8(item.getDateQty());
                    dvo.setInstalldate8(item.getAsnQty());
                    break;
                case 9:
                    dvo.setStockdate9(item.getDateQty());
                    dvo.setInstalldate9(item.getAsnQty());
                    break;
                case 10:
                    dvo.setStockdate10(item.getDateQty());
                    dvo.setInstalldate10(item.getAsnQty());
                    break;
                case 11:
                    dvo.setStockdate11(item.getDateQty());
                    dvo.setInstalldate11(item.getAsnQty());
                    break;
                case 12:
                    dvo.setStockdate12(item.getDateQty());
                    dvo.setInstalldate12(item.getAsnQty());
                    break;
                case 13:
                    dvo.setStockdate13(item.getDateQty());
                    dvo.setInstalldate13(item.getAsnQty());
                    break;
                case 14:
                    dvo.setStockdate14(item.getDateQty());
                    dvo.setInstalldate14(item.getAsnQty());
                    break;
                case 15:
                    dvo.setStockdate15(item.getDateQty());
                    dvo.setInstalldate15(item.getAsnQty());
                    break;
                case 16:
                    dvo.setStockdate16(item.getDateQty());
                    dvo.setInstalldate16(item.getAsnQty());

                    dvo.setOgCd(item.getOgCd());
                    dvo.setOgNm(item.getOgNm());
                    dvo.setPdCd(item.getPdCd());
                    dvo.setSapMatCd(item.getSapMatCd());
                    dvo.setPdNm(item.getPdNm());
                    dvo.setSumQtyCenter(item.getSumQtyCenter());
                    dvo.setSumQtyEng(item.getSumQtyEng());
                    dvo.setSumQtyTot(item.getSumQtyTot());
                    //                    dvo.setAsnDate(item.getAsnDate());
                    dvo.setAggAsnCnt(item.getAggAsnCnt());
                    //                    dvo.setDateQty(item.getDateQty());
                    dvo.setRn(item.getRn());
                    dvo.setLgstQty(item.getLgstQty());
                    break;
                default:
                    dvo.setOgCd(item.getOgCd());
                    dvo.setOgNm(item.getOgNm());
                    dvo.setPdCd(item.getPdCd());
                    dvo.setSapMatCd(item.getSapMatCd());
                    dvo.setPdNm(item.getPdNm());
                    dvo.setSumQtyCenter(item.getSumQtyCenter());
                    dvo.setSumQtyEng(item.getSumQtyEng());
                    dvo.setSumQtyTot(item.getSumQtyTot());
                    //                    dvo.setAsnDate(item.getAsnDate());
                    dvo.setAggAsnCnt(item.getAggAsnCnt());
                    //                    dvo.setDateQty(item.getDateQty());
                    dvo.setRn(item.getRn());
                    dvo.setLgstQty(item.getLgstQty());
                    break;
            }
            map.put(item.getOgCd(), dvo);
        }

        List<WsnaInstallationStockPsByDayPdDvo> list = new ArrayList(map.values());
        //        for (Map.Entry<String, WsnaInstallationStockPsByDayPdDvo> entry : map.entrySet()) {
        //            if (entry.getValue().getAggAsnCnt() > 0)
        //                sortList.add(entry.getValue());
        //        }
        List<WsnaInstallationStockPsByDayPdDvo> sortList = list.stream().filter(item -> item.getAggAsnCnt() > 0)
            .collect(Collectors.toList());
        log.info(sortList.toString());
        sortList.sort(
            Comparator.comparing(WsnaInstallationStockPsByDayPdDvo::getPdCd)
            //                .thenComparing(WsnaInstallationStockPsByDayPdDvo::getPdCd)
            //                .thenComparing(WsnaInstallationStockPsByDayCenterDvo::getAsnDate)
        );

        return sortList;
    }
}
