package com.kyowon.sms.wells.web.service.stock.service;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.jdbc.UncategorizedSQLException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kyowon.sms.wells.web.service.stock.converter.WsnaPcsvReturningGoodsMgtConverter;
import com.kyowon.sms.wells.web.service.stock.dto.WsnaPcsvReturningGoodsMgtDto;
import com.kyowon.sms.wells.web.service.stock.dto.WsnaPcsvReturningGoodsMgtDto.SaveReq;
import com.kyowon.sms.wells.web.service.stock.dto.WsnaPcsvReturningGoodsMgtDto.SearchReq;
import com.kyowon.sms.wells.web.service.stock.dto.WsnaPcsvReturningGoodsMgtDto.SearchRes;
import com.kyowon.sms.wells.web.service.stock.dvo.WsnaPcsvReturningGoodsDvo;
import com.kyowon.sms.wells.web.service.stock.mapper.WsnaPcsvReturningGoodsMgtMapper;
import com.sds.sflex.system.config.exception.BizException;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class WsnaPcsvReturningGoodsMgtService {
    private final WsnaPcsvReturningGoodsMgtMapper mapper;
    private final WsnaPcsvReturningGoodsMgtConverter converter;
    private final WsnaPcsvReturningGoodsSaveService service;

    public List<SearchRes> getPcsvReturningGoods(
        SearchReq req
    ) {
        return mapper.selectPcsvReturningGoods(req);
    }

    public List<SearchRes> getPcsvReturningGoodsExcelDownload(SearchReq dto) {
        return mapper.selectPcsvReturningGoods(dto);
    }

    public List<WsnaPcsvReturningGoodsMgtDto.FindLogisticsCentersRes> getPcsvLogisticsCenters() {
        return mapper.selectPcsvLogisticsCenters();
    }

    public List<WsnaPcsvReturningGoodsMgtDto.FindProductsRes> getPcsvProducts(
        WsnaPcsvReturningGoodsMgtDto.FindProductsReq dto
    ) {
        return mapper.selectPcsvProducts(dto);
    }

    @Transactional
    public int savePcsvReturningGoods(List<SaveReq> dtos) {
        int processCount = 0;

        try {
            for (SaveReq dto : dtos) {
                WsnaPcsvReturningGoodsDvo dvo = converter.mapSaveReqToPcsvReturningGoodsDvo(dto);

                service.savePcsvReturningGoods(dvo);

                processCount += 1;
            }
        } catch (Exception e) {
            if (e instanceof UncategorizedSQLException) {
                int errorCode = ((UncategorizedSQLException)e).getSQLException().getErrorCode();
                if (errorCode == 20003) {
                    String message = ((UncategorizedSQLException)e).getSQLException().getMessage();

                    if (StringUtils.isNotEmpty(message) && message.indexOf("[") > -1 && message.indexOf("]") > -1) {
                        int start = message.indexOf("[") + 1;
                        int end = message.indexOf("]");
                        message = message.substring(start, end);
                    }
                    throw new BizException(message);
                }
            }
        }

        return processCount;
    }

}
