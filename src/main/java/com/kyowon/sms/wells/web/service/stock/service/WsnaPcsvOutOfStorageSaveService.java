package com.kyowon.sms.wells.web.service.stock.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kyowon.sms.wells.web.service.stock.dvo.WsnaPcsvOutOfStorageSaveDvo;
import com.kyowon.sms.wells.web.service.stock.mapper.WsnaPcsvOutOfStorageSaveMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class WsnaPcsvOutOfStorageSaveService {

    private final WsnaPcsvOutOfStorageSaveMapper mapper;

    @Transactional
    public void savePcsvOutOfStorage(WsnaPcsvOutOfStorageSaveDvo dvo) {

        if ("1112".equals(dvo.getSvBizDclsfCd())) {
            //정상출고
            mapper.insertPcsvOutOfStorage(dvo); // 작업결과 IU
            mapper.insertPcsvOutOfStorageRvPy(dvo); //사용내역 + 수불처리

            //TODO KSS 마스터 예정일자, 매출일자, 설치일자 업데이트
            //TODO KSS 마스터 변경정보 인서트

        } else if ("1113".equals(dvo.getSvBizDclsfCd())) {
            //재배송출고
            mapper.insertPcsvOutOfStorageRshp(dvo); // 작업결과 IU(재배송)
        }

    }
}
