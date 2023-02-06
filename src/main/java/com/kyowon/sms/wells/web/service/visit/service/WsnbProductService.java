package com.kyowon.sms.wells.web.service.visit.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.kyowon.sms.wells.web.service.visit.dvo.WsnbProductDvo;
import com.kyowon.sms.wells.web.service.visit.mapper.WsnbProductMapper;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * <pre>
 * W-SV-S-0082 큐빅 CC 홈쇼핑건 상담중 상품변경건
 * </pre>
 *
 * @author yeonghwa.cheon
 * @since 2023.02.06
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class WsnbProductService {

    private final WsnbProductMapper mapper;

    public int editProducts() {
        int processCount = 0;

        List<WsnbProductDvo> dvos = mapper.selectProducts();

        for (WsnbProductDvo dvo : dvos) {

            mapper.updateAsInstallationAssign(dvo);

            processCount += mapper.updateAsInstallationObject(dvo);

        }
        return processCount;
    }

}
