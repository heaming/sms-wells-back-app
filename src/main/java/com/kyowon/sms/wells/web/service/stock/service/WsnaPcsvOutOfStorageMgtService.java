package com.kyowon.sms.wells.web.service.stock.service;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.jdbc.UncategorizedSQLException;
import org.springframework.stereotype.Service;

import com.kyowon.sms.wells.web.service.stock.converter.WsnaPcsvOutOfStorageMgtConverter;
import com.kyowon.sms.wells.web.service.stock.dto.WsnaPcsvOutOfStorageMgtDto.*;
import com.kyowon.sms.wells.web.service.stock.dvo.WsnaPcsvOutOfStorageSaveDvo;
import com.kyowon.sms.wells.web.service.stock.mapper.WsnaPcsvOutOfStorageMgtMapper;
import com.sds.sflex.system.config.exception.BizException;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class WsnaPcsvOutOfStorageMgtService {

    private final WsnaPcsvOutOfStorageMgtMapper mapper;

    private final WsnaPcsvOutOfStorageMgtConverter converter;

    private final WsnaPcsvOutOfStorageSaveService service;

    public List<SearchRes> getPcsvOutOfStorages(SearchReq dto) {
        return converter.mapAllDvoToSearchRes(mapper.selectPcsvOutOfStorages(dto));
    }

    public List<SearchRes> getPcsvOutOfStoragesExcelDownload(SearchReq dto) {
        return converter.mapAllDvoToSearchRes(mapper.selectPcsvOutOfStorages(dto));
    }

    public String getPcsvOutOfStorageStockQty(SearchReq dto) {
        return mapper.selectPcsvOutOfStorageStockQty(dto);
    }

    public List<FindLogisticsCentersRes> getPcsvLogisticsCenters() {
        return mapper.selectPcsvLogisticsCenters();
    }

    public List<FindProductsRes> getPcsvProducts(FindProductsReq dto) {
        return mapper.selectPcsvProducts(dto);
    }

    public List<FindIvcPrntSnRes> getPcsvIvcPrntSns(SearchReq dto) {
        return mapper.selectPcsvIvcPrntSns(dto);
    }

    public String getPcsvIvcPrntSn(SearchReq dto) {
        return mapper.selectPcsvIvcPrntSn(dto);
    }

    public int savePcsvOutOfStorage(List<SaveReq> dtos) {
        int processCount = 0;

        try {
            for (SaveReq dto : dtos) {
                WsnaPcsvOutOfStorageSaveDvo dvo = converter.mapSaveReqToPcsvOutOfStorageDvo(dto);

                service.savePcsvOutOfStorage(dvo);

                processCount += 1;
            }
        } catch (Exception e) {
            //TODO 추후 DECLARE 소스 제거시 아래소스도 제거예정
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
            throw e;
        }

        return processCount;
    }

}
