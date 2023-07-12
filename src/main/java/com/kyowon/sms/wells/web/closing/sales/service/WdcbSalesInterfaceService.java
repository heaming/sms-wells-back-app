package com.kyowon.sms.wells.web.closing.sales.service;

import com.kyowon.sms.wells.web.closing.sales.dto.WdcbSalesInterfaceDto.SearchAllianceContractRes;
import com.kyowon.sms.wells.web.closing.sales.dto.WdcbSalesInterfaceDto.SearchByContractReq;
import com.kyowon.sms.wells.web.closing.sales.mapper.WdcbSalesInterfaceMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * <pre>
 * Sales Interface Service
 * </pre>
 *
 * @author  seungeon.jang
 * @since 2023.07.12
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class WdcbSalesInterfaceService {

    private final WdcbSalesInterfaceMapper mapper;

    public SearchAllianceContractRes getAllianceContract(final SearchByContractReq req) {
        return mapper.selectAllianceContract(req);
    }

}
