package com.kyowon.sms.wells.web.contract.risk.service;

import static com.kyowon.sms.wells.web.contract.risk.dto.WcteSalesLimitDto.*;

import java.util.Iterator;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kyowon.sms.wells.web.contract.risk.converter.WcteSalesLimitConverter;
import com.kyowon.sms.wells.web.contract.risk.dvo.WcteSellLmOjIzDvo;
import com.kyowon.sms.wells.web.contract.risk.mapper.WcteSalesLimitMapper;
import com.sds.sflex.system.config.constant.CommConst;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;
import com.sds.sflex.system.config.exception.BizException;
import com.sds.sflex.system.config.validation.BizAssert;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class WcteSalesLimitService {

    private final WcteSalesLimitMapper mapper;
    private final WcteSalesLimitConverter converter;

    public PagingResult<SearchEntrpJLmOjRes> getEntrepreneurJoinLmOjssPages(
        SearchEntrpJLmOjReq dto, PageInfo pageInfo
    ) {
        return mapper.selectEntrepreneurJoinLmOjss(dto, pageInfo);
    }

    public List<SearchEntrpJLmOjRes> getEntrepreneurJoinLmOjssExcelDownload(SearchEntrpJLmOjReq dto) {
        return mapper.selectEntrepreneurJoinLmOjss(dto);
    }

    @Transactional
    public int saveEntrepreneurJoinLmOjss(List<SaveEntrpJLmOjReq> dtos) {
        int processCount = 0;
        Iterator<SaveEntrpJLmOjReq> iterator = dtos.iterator();

        while (iterator.hasNext()) {
            SaveEntrpJLmOjReq dto = iterator.next();
            WcteSellLmOjIzDvo dvo = converter.mapSaveEntrpJLmOjReqToDvo(dto);
            String sellLmDv = dvo.getSellLmDv();
            String sellLmRlsDtm = dvo.getSellLmRlsDtm(); //해제일자
            String sellLmOcDtm = dvo.getSellLmOcDtm(); //발생일자
            String[] param = {dvo.getRownum()};

            processCount += switch (dvo.getRowState()) {
                case CommConst.ROW_STATE_UPDATED -> {
                    if ("3".equals(sellLmDv))
                        BizAssert.isTrue(sellLmRlsDtm.length() < 1, "MSG_ALT_RLS_DT_ERR", param);

                    if ("4".equals(sellLmDv))
                        BizAssert.isFalse(sellLmRlsDtm.isEmpty(), "MSG_ALT_BAD_RLS_ERR", param);

                    String sellLmBzrno = mapper.selectEntrepreneurJoinLmOjssCheck(dvo.getSellLmId());
                    BizAssert.isTrue(dvo.getSellLmBzrno().equals(sellLmBzrno), "MSG_ALT_LM_BZRNO_ERR");

                    mapper.updateEntrepreneurJoinLmOjss(dvo);
                    int result = mapper.insertEntrepreneurJoinLmOjss(dvo);
                    BizAssert.isTrue(result == 1, "MSG_ALT_SVE_ERR");

                    yield result;
                }
                case CommConst.ROW_STATE_CREATED -> {
                    if ("3".equals(sellLmDv))
                        BizAssert.isTrue(sellLmRlsDtm.length() < 1, "MSG_ALT_RLS_DT_ERR", param);

                    BizAssert.isTrue(sellLmOcDtm.length() == 8, "MSG_ALT_BAD_OC_DT_ERR");

                    int result = mapper.insertEntrepreneurJoinLmOjss(dvo);
                    BizAssert.isTrue(result == 1, "MSG_ALT_SVE_ERR");

                    yield result;
                }
                default -> throw new BizException("MSG_ALT_UNHANDLE_ROWSTATE");
            };
        }
        return processCount;
    }

    @Transactional
    public int removeEntrepreneurJoinLmOjss(String[] sellLmIds) {
        int processCount = 0;

        for (String sellLmId : sellLmIds) {
            int result = mapper.deleteEntrepreneurJoinLmOjss(sellLmId);

            BizAssert.isTrue(result == 1, "MSG_ALT_SVE_ERR");
            processCount += result;
        }
        return processCount;
    }
}
