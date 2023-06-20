
package com.kyowon.sms.wells.web.product.standard.service;

import java.util.List;

import org.eclipse.jetty.util.StringUtil;
import org.springframework.stereotype.Service;

import com.kyowon.sms.common.web.product.zcommon.constants.PdProductConst;
import com.kyowon.sms.wells.web.product.standard.converter.WpdyWellsAllianceMgtConverter;
import com.kyowon.sms.wells.web.product.standard.dto.WpdyWellsAllianceMgtDto;
import com.kyowon.sms.wells.web.product.standard.dvo.WpdyAllianceBaseDvo;
import com.kyowon.sms.wells.web.product.standard.mapper.WpdyWellsAllianceMgtMapper;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class WpdyWellsAllianceMgtService {

    private final WpdyWellsAllianceMgtConverter converter;
    private final WpdyWellsAllianceMgtMapper mapper;

    public List<WpdyWellsAllianceMgtDto.SearchRes> getWellsAlliances(WpdyWellsAllianceMgtDto.SearchReq dto) {
        return mapper.selectWellsAlliances(dto);
    }

    public PagingResult<WpdyWellsAllianceMgtDto.SearchRes> getWellsAlliancePages(WpdyWellsAllianceMgtDto.SearchReq dto, PageInfo pageInfo) {
        return mapper.selectWellsAlliancePages(dto, pageInfo);
    }

    public int saveWellsAlliances(WpdyWellsAllianceMgtDto.SaveReq dto) throws Exception {
        int cnt = 0;
        List<WpdyAllianceBaseDvo> bases = converter.mapAllAllianceBaseDtoToAllianceBaseDvo(dto.bases());
        for (WpdyAllianceBaseDvo base : bases) {
            if (StringUtil.isEmpty(base.getPdAlncmpBaseId())) {
                base.setPdAlncmpBaseId(mapper.selectWellsAllianceId());
            }
            cnt += mapper.mergeWellsAllianceBase(base);
        }
        return cnt;
    }

    public int removeWellsAlliances(List<WpdyWellsAllianceMgtDto.AllianceBase> dtos) {
        int cnt = 0;
        List<WpdyAllianceBaseDvo> bases = converter.mapAllAllianceBaseDtoToAllianceBaseDvo(dtos);
        for (WpdyAllianceBaseDvo base : bases) {
            cnt += mapper.deleteWellsAllianceBase(base);
        }
        return cnt;
    }

    public String checkDuplication(List<WpdyWellsAllianceMgtDto.AllianceBase> dtos) {
        List<WpdyAllianceBaseDvo> bases = converter.mapAllAllianceBaseDtoToAllianceBaseDvo(dtos);
        String duplicationKey = null;
        for (WpdyAllianceBaseDvo base : bases) {
            duplicationKey = mapper.selectWellsAllianceDuplication(base);
            if (StringUtil.isNotBlank(duplicationKey)) {
                break;
            }
        }
        return duplicationKey;
    }

    public String checkValidation(List<WpdyWellsAllianceMgtDto.AllianceBase> dtos) {
        List<WpdyAllianceBaseDvo> bases = converter.mapAllAllianceBaseDtoToAllianceBaseDvo(dtos);
        String validationIssueKey = null;
        String validResult = null;
        for (WpdyAllianceBaseDvo base : bases) {
            validResult = mapper.selectWellsAllianceValidation(base);
            if (StringUtil.isBlank(validResult)) {
                validationIssueKey = base.getPdCd() + PdProductConst.COMMA + StringUtil.nonNull(base.getSvPdCd()) + PdProductConst.COMMA + StringUtil.nonNull(base.getStplPrdCd());
                break;
            }
        }
        return validationIssueKey;
    }

}
