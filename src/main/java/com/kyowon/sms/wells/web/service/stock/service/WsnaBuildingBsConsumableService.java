package com.kyowon.sms.wells.web.service.stock.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;

import com.kyowon.sms.wells.web.service.stock.converter.WsnaBuildingBsConsumableConverter;
import com.kyowon.sms.wells.web.service.stock.dto.WsnaBuildingBsConsumableDto.*;
import com.kyowon.sms.wells.web.service.stock.dvo.WsnaBuildingBsConsumableDvo;
import com.kyowon.sms.wells.web.service.stock.mapper.WsnaBuildingBsConsumableMapper;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class WsnaBuildingBsConsumableService {
    private final WsnaBuildingBsConsumableMapper mapper;
    private final WsnaBuildingBsConsumableConverter converter;

    public List<SearchRes> getBuildingBsConsumables(SearchReq dto) {
        return null;
    }

    public PagingResult<SearchRes> getBuildingBsConsumablePages(SearchReq dto, PageInfo pageInfo) {
        // 빌딩정보 조회
        PagingResult<WsnaBuildingBsConsumableDvo> bldInfos = mapper.selectBuildings(dto, pageInfo);
        List<WsnaBuildingBsConsumableDvo> bldAndItemsInfos = new ArrayList<>();
        Iterator<WsnaBuildingBsConsumableDvo> it = bldInfos.iterator();

        while (it.hasNext()) {
            WsnaBuildingBsConsumableDvo bftBldInfo = it.next();
            WsnaBuildingBsConsumableDvo aftBldInfo = new WsnaBuildingBsConsumableDvo();
            List<WsnaBuildingBsConsumableDvo> itemInfos = new ArrayList<>();

            aftBldInfo = bftBldInfo;

            List<String> fxnItemQtys = new ArrayList<>();
            List<String> aplcItemQtys = new ArrayList<>();

            // 빌딩 별 기등록 품목 수량 조회
            itemInfos = mapper.selectItemQtys(dto.mngtYm(), bftBldInfo.getBldCd());

            if (CollectionUtils.isEmpty(itemInfos)) {
                // 빌딩 별 미등록 품목 계산 수량 조회
                itemInfos = mapper.selectItemFirstQtys(dto.mngtYm(), bftBldInfo.getStrWareNo());

                for (WsnaBuildingBsConsumableDvo itemInfo : itemInfos) {
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
                for (WsnaBuildingBsConsumableDvo itemInfo : itemInfos) {
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

    public FindTmlmRes getBuildingBsConsumableAplcClose(String mngtYm) {
        FindTmlmRes res = mapper.selectBuildingBsConsumableAplcClose(mngtYm);

        if (ObjectUtils.isEmpty(res)) {
            res = mapper.selectBuildingBsConsumableAplcFirstClose(mngtYm);
        }

        return res;
    }

    @Transactional
    public int createBuildingBsConsumableAplcClose(CreateTmlmReq dto) {
        WsnaBuildingBsConsumableDvo dvo = converter.mapCreateTmlmReqToCsmbDblv(dto);

        return mapper.mergeBuildingBsConsumableAplcClose(dvo);
    }

    public List<SearchBldRes> getBuildingList(String mngtYm) {
        return mapper.selectBuildingList(mngtYm);
    }

    @Transactional
    public int createBuildingBsConsumables(List<CreateReq> dtos) {
        return mapper.mergeBuildingBsConsumables(dtos);
    }
}
