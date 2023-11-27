package com.kyowon.sms.wells.web.service.stock.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kyowon.sms.wells.web.service.stock.dto.WsnaMdProductReturningGoodsMgtDto.SaveReq;
import com.kyowon.sms.wells.web.service.stock.dto.WsnaMdProductReturningGoodsMgtDto.SearchReq;
import com.kyowon.sms.wells.web.service.stock.dvo.WsnaMdProductReturningGoodsMgtDvo;
import com.kyowon.sms.wells.web.service.stock.mapper.WsnaMdProductReturningGoodsMgtMapper;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class WsnaMdProductReturningGoodsMgtService {
    private final WsnaMdProductReturningGoodsMgtMapper mapper;
    private final WsnaMdProductReturningGoodsSaveService service;

    public List<WsnaMdProductReturningGoodsMgtDvo> getMdProductReturningGoods(
        SearchReq req
    ) {
        return mapper.selectMdProductReturningGoods(req);
    }

    @Transactional
    public int saveMdProductReturningGoods(List<SaveReq> dtos) {
        return service.saveMdProductReturningGoods(dtos);
    }

}
