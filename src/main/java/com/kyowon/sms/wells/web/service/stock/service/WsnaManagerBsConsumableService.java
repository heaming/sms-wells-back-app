package com.kyowon.sms.wells.web.service.stock.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;

import com.kyowon.sms.wells.web.service.stock.converter.WsnaManagerBsConsumableConverter;
import com.kyowon.sms.wells.web.service.stock.dto.WsnaBuildingBsConsumableDto;
import com.kyowon.sms.wells.web.service.stock.dto.WsnaManagerBsConsumableDto.*;
import com.kyowon.sms.wells.web.service.stock.dvo.WsnaManagerBsConsumableDvo;
import com.kyowon.sms.wells.web.service.stock.mapper.WsnaBuildingBsConsumableMapper;
import com.kyowon.sms.wells.web.service.stock.mapper.WsnaManagerBsConsumableMapper;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class WsnaManagerBsConsumableService {
    private final WsnaManagerBsConsumableMapper mapper;
    private final WsnaBuildingBsConsumableMapper bldMapper;
    private final WsnaManagerBsConsumableConverter converter;

    public List<SearchItmRes> selectItems(String mngtYm) {
        return mapper.selectItems(mngtYm);
    }

    public List<WsnaBuildingBsConsumableDto.SearchBldRes> selectBuildings(String mngtYm) {
        return bldMapper.selectBuildingList(mngtYm);
    }

    public PagingResult<SearchRes> getManagerBsConsumablePages(SearchReq dto, PageInfo pageInfo) {
        PagingResult<WsnaManagerBsConsumableDvo> bldInfos = mapper.selectBuildings(dto, pageInfo);
        List<WsnaManagerBsConsumableDvo> bldAndItemsInfos = new ArrayList<>();
        Iterator<WsnaManagerBsConsumableDvo> it = bldInfos.iterator();

        while (it.hasNext()) {
            WsnaManagerBsConsumableDvo bfBldInfo = it.next();
            WsnaManagerBsConsumableDvo aftBldInfo = new WsnaManagerBsConsumableDvo();
            List<WsnaManagerBsConsumableDvo> itemInfos = new ArrayList<>();

            aftBldInfo = bfBldInfo;

            List<String> fxnItemQtys = new ArrayList<>();
            List<String> aplcItemQtys = new ArrayList<>();

            // 매니저 별 기등록 품목 수량 조회
            itemInfos = mapper.selectItemQtys(dto.mngtYm(), bfBldInfo.getPrtnrNo());

            if (CollectionUtils.isEmpty(itemInfos)) {
                // 매니저 별 미등록 품목 계산 수량 조회
                itemInfos = mapper.selectItemFirstQtys(dto.mngtYm(), bfBldInfo.getPrtnrNo());

                if (CollectionUtils.isEmpty(itemInfos)) {
                    for (WsnaManagerBsConsumableDvo itemInfo : itemInfos) {
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
                }
            } else {
                for (WsnaManagerBsConsumableDvo itemInfo : itemInfos) {
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

    public FindTmlmRes getManagerBsConsumableAplcClose(String mngtYm) {
        FindTmlmRes res = mapper.selectManagerBsConsumableAplcClose(mngtYm);

        if (ObjectUtils.isEmpty(res)) {
            res = mapper.selectManagerBsConsumableAplcFirstClose(mngtYm);
        }

        return res;
    }

    @Transactional
    public int createBuildingBsConsumableAplcClose(CreateTmlmReq dto) {
        WsnaManagerBsConsumableDvo dvo = converter.mapCreateTmlmReqToNewManagerBsConsumable(dto);

        return mapper.mergeManagerBsConsumableAplcClose(dvo);
    }

    @Transactional
    public int createManagerBsConsumables(List<CreateReq> dtos) {
        List<WsnaManagerBsConsumableDvo> dvos = converter.mapCreateReqToNewManagerBsConsumable(dtos);

        return mapper.mergeManagerBsConsumables(dvos);
    }
}
