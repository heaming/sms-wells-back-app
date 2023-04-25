package com.kyowon.sms.wells.web.service.stock.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;

import com.kyowon.sms.wells.web.service.stock.converter.WsnaNewManagerBsConsumableConverter;
import com.kyowon.sms.wells.web.service.stock.dto.WsnaBuildingBsConsumableDto.SearchBldRes;
import com.kyowon.sms.wells.web.service.stock.dto.WsnaNewManagerBsConsumableDto.*;
import com.kyowon.sms.wells.web.service.stock.dvo.WsnaNewManagerBsConsumableDvo;
import com.kyowon.sms.wells.web.service.stock.mapper.WsnaBuildingBsConsumableMapper;
import com.kyowon.sms.wells.web.service.stock.mapper.WsnaNewManagerBsConsumableMapper;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class WsnaNewManagerBsConsumableService {
    private final WsnaNewManagerBsConsumableMapper mapper;

    private final WsnaBuildingBsConsumableMapper bldMapper;
    private final WsnaNewManagerBsConsumableConverter converter;

    public List<SearchItmRes> getItems(String mngtYm) {
        return mapper.selectItems(mngtYm);
    }

    public List<SearchBldRes> selectBuildings(String mngtYm) {
        return bldMapper.selectBuildingList(mngtYm);
    }

    public List<SearchRes> getNewManagerBsConsumables(SearchReq dto) {
        return null;
    }

    public PagingResult<SearchRes> getNewManagerBsConsumablePages(SearchReq dto, PageInfo pageInfo) {
        PagingResult<WsnaNewManagerBsConsumableDvo> bldInfos = mapper.selectBuildings(dto, pageInfo);
        List<WsnaNewManagerBsConsumableDvo> bldAndItemsInfos = new ArrayList<>();
        Iterator<WsnaNewManagerBsConsumableDvo> it = bldInfos.iterator();

        while (it.hasNext()) {
            WsnaNewManagerBsConsumableDvo bfBldInfo = it.next();
            WsnaNewManagerBsConsumableDvo aftBldInfo = new WsnaNewManagerBsConsumableDvo();
            List<WsnaNewManagerBsConsumableDvo> itemInfos = new ArrayList<>();

            aftBldInfo = bfBldInfo;

            List<String> fxnItemQtys = new ArrayList<>();
            List<String> aplcItemQtys = new ArrayList<>();

            // 매니저 별 기등록 품목 수량 조회
            itemInfos = mapper.selectItemQtys(dto.mngtYm(), bfBldInfo.getPrtnrNo());

            if (CollectionUtils.isEmpty(itemInfos)) {
                // 매니저 별 미등록 품목 계산 수량 조회
                itemInfos = mapper.selectItemFirstQtys(dto.mngtYm(), bfBldInfo.getPrtnrNo());

                for (WsnaNewManagerBsConsumableDvo itemInfo : itemInfos) {
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
                for (WsnaNewManagerBsConsumableDvo itemInfo : itemInfos) {
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

    public FindTmlmRes getNewManagerBsConsumableAplcClose(String mngtYm) {
        FindTmlmRes res = mapper.selectNewManagerBsConsumableAplcClose(mngtYm);

        if (ObjectUtils.isEmpty(res)) {
            res = mapper.selectNewManagerBsConsumableAplcFirstClose(mngtYm);
        }

        return res;
    }

    @Transactional
    public int createBuildingBsConsumableAplcClose(CreateTmlmReq dto) {
        WsnaNewManagerBsConsumableDvo dvo = converter.mapCreateTmlmReqToNewManagerBsConsumable(dto);

        return mapper.mergeNewManagerBsConsumableAplcClose(dvo);
    }

    @Transactional
    public int createNewManagerBsConsumables(List<CreateReq> dtos) {
        List<WsnaNewManagerBsConsumableDvo> dvos = converter.mapCreateReqToNewManagerBsConsumable(dtos);

        return mapper.mergeNewManagerBsConsumables(dvos);
    }
}
