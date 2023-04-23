package com.kyowon.sms.wells.web.service.stock.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;

import com.kyowon.sms.wells.web.service.stock.converter.WsnaBldBfsvcCsmbDdlvConverter;
import com.kyowon.sms.wells.web.service.stock.dto.WsnaBldBfsvcCsmbDdlvDto.*;
import com.kyowon.sms.wells.web.service.stock.dvo.WsnaBldBfsvcCsmbDdlvDvo;
import com.kyowon.sms.wells.web.service.stock.mapper.WsnaBldBfsvcCsmbDdlvMapper;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class WsnaBldBfsvcCsmbDdlvService {
    private final WsnaBldBfsvcCsmbDdlvMapper mapper;
    private final WsnaBldBfsvcCsmbDdlvConverter converter;

    public List<SearchRes> getBldCsmbDeliveries(SearchReq dto) {
        return null;
    }

    public PagingResult<SearchRes> getBldCsmbDeliveryPages(SearchReq dto, PageInfo pageInfo) {
        // 빌딩정보 조회
        PagingResult<WsnaBldBfsvcCsmbDdlvDvo> bldInfos = mapper.selectBuildingInfos(dto, pageInfo);
        List<WsnaBldBfsvcCsmbDdlvDvo> bldAndItemsInfos = new ArrayList<>();
        Iterator<WsnaBldBfsvcCsmbDdlvDvo> it = bldInfos.iterator();

        while (it.hasNext()) {
            WsnaBldBfsvcCsmbDdlvDvo bftBldInfo = it.next();
            WsnaBldBfsvcCsmbDdlvDvo aftBldInfo = new WsnaBldBfsvcCsmbDdlvDvo();
            List<WsnaBldBfsvcCsmbDdlvDvo> itemInfos = new ArrayList<>();

            aftBldInfo = bftBldInfo;

            List<String> fxnItemQtys = new ArrayList<>();
            List<String> aplcItemQtys = new ArrayList<>();

            // 빌딩 별 기등록 품목 수량 조회
            itemInfos = mapper.selectItemQtys(dto.mngtYm(), bftBldInfo.getBldCd());

            if (CollectionUtils.isEmpty(itemInfos)) {
                // 빌딩 별 미등록 품목 계산 수량 조회
                itemInfos = mapper.selectItemFirstQtys(dto.mngtYm(), bftBldInfo.getStrWareNo());

                for (WsnaBldBfsvcCsmbDdlvDvo itemInfo : itemInfos) {
                    switch (itemInfo.getBfsvcCsmbDdlvTpCd()) {
                        case "1" -> {
                            fxnItemQtys.add(itemInfo.getFxnDdlvUnitQty());
                        }

                        case "2" -> {
                            aplcItemQtys.add(itemInfo.getAplcDdlvUnitQty());
                        }
                    }
                }

                aftBldInfo.setReqYn(itemInfos.get(0).getReqYn());
                aftBldInfo.setFxnQtys(fxnItemQtys); // 고정품목
                aftBldInfo.setAplcQtys(aplcItemQtys); // 신청품목
                bldAndItemsInfos.add(aftBldInfo);
            } else {
                for (WsnaBldBfsvcCsmbDdlvDvo itemInfo : itemInfos) {
                    switch (itemInfo.getBfsvcCsmbDdlvTpCd()) {
                        case "1" -> {
                            fxnItemQtys.add(itemInfo.getBfsvcCsmbDdlvQty());
                        }

                        case "2" -> {
                            aplcItemQtys.add(itemInfo.getBfsvcCsmbDdlvQty());
                        }
                    }
                }

                aftBldInfo.setReqYn(itemInfos.get(0).getReqYn());
                aftBldInfo.setFxnQtys(fxnItemQtys); // 고정품목
                aftBldInfo.setAplcQtys(aplcItemQtys); // 신청품목
                bldAndItemsInfos.add(aftBldInfo);
            }
        }

        PagingResult<SearchRes> rtnDto = converter.mapDvoToSearchRes(bldAndItemsInfos);
        rtnDto.setPageInfo(bldInfos.getPageInfo());

        return rtnDto;
    }

    public List<SearchItmRes> getItems(String mngtYm) {
        return mapper.selectItems(mngtYm);
    }

    public FindTmlmRes getBldCsmbAplcClose(String mngtYm) {
        FindTmlmRes res = mapper.selectBldCsmbAplcClose(mngtYm);

        if (ObjectUtils.isEmpty(res)) {
            res = mapper.selectBldCsmbAplcFirstClose(mngtYm);
        }

        return res;
    }

    @Transactional
    public int createBldCsmbAplcClose(CreateTmlmReq dto) {
        WsnaBldBfsvcCsmbDdlvDvo dvo = converter.mapCreateTmlmReqToCsmbDblv(dto);

        return mapper.mergeBldCsmbAplcClose(dvo);
    }

    public List<SearchBldRes> selectBuildings(String mngtYm) {
        return mapper.selectBuildings(mngtYm);
    }

    @Transactional
    public int createBldCsmbDeliveries(List<CreateReq> dtos) {
        return mapper.mergeBldCsmbDeliveries(dtos);
    }
}
