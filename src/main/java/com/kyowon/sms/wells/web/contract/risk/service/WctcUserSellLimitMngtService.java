package com.kyowon.sms.wells.web.contract.risk.service;

import java.util.Iterator;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kyowon.sms.wells.web.contract.risk.converter.WctcUserSellLimitMngtConverter;
import com.kyowon.sms.wells.web.contract.risk.dto.WctcUserSellLimitMngtDto.SaveReq;
import com.kyowon.sms.wells.web.contract.risk.dto.WctcUserSellLimitMngtDto.SearchReq;
import com.kyowon.sms.wells.web.contract.risk.dto.WctcUserSellLimitMngtDto.SearchRes;
import com.kyowon.sms.wells.web.contract.risk.dvo.WctcUserSellLimitMngtDvo;
import com.kyowon.sms.wells.web.contract.risk.mapper.WctcUserSellLimitMngtMapper;
import com.sds.sflex.system.config.constant.CommConst;
import com.sds.sflex.system.config.exception.BizException;
import com.sds.sflex.system.config.validation.BizAssert;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class WctcUserSellLimitMngtService {
    private final WctcUserSellLimitMngtMapper mapper;
    private final WctcUserSellLimitMngtConverter converter;

    public List<SearchRes> getSellLimitList(SearchReq dto) {
        return mapper.selectSellLimitList(dto);
    }

    @Transactional
    public int saveSellBase(List<SaveReq> dtos) {
        int processCount = 0;
        Iterator<SaveReq> iterator = dtos.iterator();

        while (iterator.hasNext()) {
            SaveReq dto = iterator.next();
            WctcUserSellLimitMngtDvo userSellLimitMngt = converter.mapSaveReqToWctcUserSellLimitMngtDvo(dto);
            processCount += switch (dto.rowState()) {
                case CommConst.ROW_STATE_CREATED -> {
                    String cdCheck = mapper.selecBaseCdCheck(dto);
                    BizAssert.isTrue(cdCheck != "0", "MSG_ALT_DUPLICATE_EXISTS");
                    int result = mapper.insertSellBaseBas(userSellLimitMngt);
                    if (StringUtils.isNotEmpty(userSellLimitMngt.getVlEndDtm())) {
                        userSellLimitMngt.setVlEndDtm("99991231");
                    }

                    if (StringUtils.isNotEmpty(dto.sellBaseChnl())) {
                        userSellLimitMngt.setSellBaseCd("11");
                        userSellLimitMngt.setSellBaseChval(dto.sellBaseChnl());
                        mapper.insertSellBaseDtl(userSellLimitMngt);
                    }
                    if (StringUtils.isNotEmpty(dto.deptCd())) {
                        userSellLimitMngt.setSellBaseCd("12");
                        userSellLimitMngt.setSellBaseChval(dto.deptCd());
                        mapper.insertSellBaseDtl(userSellLimitMngt);
                    }
                    if (StringUtils.isNotEmpty(dto.sellBaseUsr())) {
                        userSellLimitMngt.setSellBaseCd("13");
                        userSellLimitMngt.setSellBaseChval(dto.sellBaseUsr());
                        mapper.insertSellBaseDtl(userSellLimitMngt);
                    }
                    if (StringUtils.isNotEmpty(dto.copnDvCd())) {
                        userSellLimitMngt.setSellBaseCd("31");
                        userSellLimitMngt.setSellBaseChval(dto.copnDvCd());
                        mapper.insertSellBaseDtl(userSellLimitMngt);
                    }
                    if (StringUtils.isNotEmpty(dto.zip())) {
                        userSellLimitMngt.setSellBaseCd("32");
                        userSellLimitMngt.setSellBaseChval(dto.zip());
                        mapper.insertSellBaseDtl(userSellLimitMngt);
                    }
                    if (StringUtils.isNotEmpty(dto.pdCd())) {
                        userSellLimitMngt.setSellBaseCd("25");
                        userSellLimitMngt.setSellBaseChval(dto.pdCd());
                        mapper.insertSellBaseDtl(userSellLimitMngt);
                    }
                    if (StringUtils.isNotEmpty(dto.pdMclsfId())) {
                        userSellLimitMngt.setSellBaseCd("22");
                        userSellLimitMngt.setSellBaseChval(dto.pdMclsfId());
                        mapper.insertSellBaseDtl(userSellLimitMngt);
                    }
                    if (StringUtils.isNotEmpty(dto.pdLclsfId())) {
                        userSellLimitMngt.setSellBaseCd("23");
                        userSellLimitMngt.setSellBaseChval(dto.pdLclsfId());
                        mapper.insertSellBaseDtl(userSellLimitMngt);
                    }
                    if (StringUtils.isNotEmpty(dto.sellBasePrd())) {
                        userSellLimitMngt.setSellBaseCd("27");
                        userSellLimitMngt.setSellBaseChval(dto.sellBasePrd());
                        mapper.insertSellBaseDtl(userSellLimitMngt);
                    }
                    if (StringUtils.isNotEmpty(dto.sellBaseSellTp())) {
                        userSellLimitMngt.setSellBaseCd("28");
                        userSellLimitMngt.setSellBaseChval(dto.sellBaseSellTp());
                        mapper.insertSellBaseDtl(userSellLimitMngt);
                    }
                    BizAssert.isTrue(result == 1, "MSG_ALT_SVE_ERR");
                    yield result;
                }
                case CommConst.ROW_STATE_UPDATED -> {
                    int result = mapper.updateSellBaseBas(userSellLimitMngt);
                    if (StringUtils.isNotEmpty(dto.sellBaseChnl())) {
                        userSellLimitMngt.setSellBaseCd("11");
                        userSellLimitMngt.setSellBaseChval(dto.sellBaseChnl());
                        mapper.updateSellBaseDtl(userSellLimitMngt);
                    }
                    if (StringUtils.isNotEmpty(dto.deptCd())) {
                        userSellLimitMngt.setSellBaseCd("12");
                        userSellLimitMngt.setSellBaseChval(dto.deptCd());
                        mapper.updateSellBaseDtl(userSellLimitMngt);
                    }
                    if (StringUtils.isNotEmpty(dto.sellBaseUsr())) {
                        userSellLimitMngt.setSellBaseCd("13");
                        userSellLimitMngt.setSellBaseChval(dto.sellBaseUsr());
                        mapper.updateSellBaseDtl(userSellLimitMngt);
                    }
                    if (StringUtils.isNotEmpty(dto.copnDvCd())) {
                        userSellLimitMngt.setSellBaseCd("31");
                        userSellLimitMngt.setSellBaseChval(dto.copnDvCd());
                        mapper.updateSellBaseDtl(userSellLimitMngt);
                    }
                    if (StringUtils.isNotEmpty(dto.zip())) {
                        userSellLimitMngt.setSellBaseCd("32");
                        userSellLimitMngt.setSellBaseChval(dto.zip());
                        mapper.updateSellBaseDtl(userSellLimitMngt);
                    }
                    if (StringUtils.isNotEmpty(dto.pdCd())) {
                        userSellLimitMngt.setSellBaseCd("25");
                        userSellLimitMngt.setSellBaseChval(dto.pdCd());
                        mapper.updateSellBaseDtl(userSellLimitMngt);
                    }
                    if (StringUtils.isNotEmpty(dto.pdMclsfId())) {
                        userSellLimitMngt.setSellBaseCd("22");
                        userSellLimitMngt.setSellBaseChval(dto.pdMclsfId());
                        mapper.updateSellBaseDtl(userSellLimitMngt);
                    }
                    if (StringUtils.isNotEmpty(dto.pdLclsfId())) {
                        userSellLimitMngt.setSellBaseCd("23");
                        userSellLimitMngt.setSellBaseChval(dto.pdLclsfId());
                        mapper.updateSellBaseDtl(userSellLimitMngt);
                    }
                    if (StringUtils.isNotEmpty(dto.sellBasePrd())) {
                        userSellLimitMngt.setSellBaseCd("27");
                        userSellLimitMngt.setSellBaseChval(dto.sellBasePrd());
                        mapper.updateSellBaseDtl(userSellLimitMngt);
                    }
                    if (StringUtils.isNotEmpty(dto.sellBaseSellTp())) {
                        userSellLimitMngt.setSellBaseCd("28");
                        userSellLimitMngt.setSellBaseChval(dto.sellBaseSellTp());
                        mapper.updateSellBaseDtl(userSellLimitMngt);
                    }
                    BizAssert.isTrue(result == 1, "MSG_ALT_SVE_ERR");
                    yield result;
                }
                default -> throw new BizException("MSG_ALT_UNHANDLE_ROWSTATE");
            };
        }

        return processCount;
    }

    @Transactional
    public int removeSellBase(List<String> sellBaseIds) {
        int processCount = 0;
        int result = 0;
        for (Iterator<String> iterator = sellBaseIds.iterator(); iterator.hasNext(); processCount += result) {
            String sellBaseId = iterator.next();
            mapper.removeSellBaseBas(sellBaseId);
            result = mapper.removeSellBaseDtl(sellBaseId);
        }
        return processCount;
    }

}
