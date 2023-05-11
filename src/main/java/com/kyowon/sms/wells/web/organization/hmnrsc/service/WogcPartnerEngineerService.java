package com.kyowon.sms.wells.web.organization.hmnrsc.service;

import com.kyowon.sms.common.web.organization.attachment.dvo.ZogeSeizureMgtDvo;
import com.kyowon.sms.common.web.organization.mprcntr.dto.ZogbRecruitingMgtDto;
import com.kyowon.sms.common.web.organization.mprcntr.dvo.ZogbRecruitingMgtDvo;
import com.kyowon.sms.wells.web.organization.hmnrsc.converter.WogcPartnerEngineerConverter;
import com.kyowon.sms.wells.web.organization.hmnrsc.dto.WogcPartnerEngineerDto;
import com.kyowon.sms.wells.web.organization.hmnrsc.dvo.WogcPartnerEngineerDvo;
import com.kyowon.sms.wells.web.organization.hmnrsc.mapper.WogcPartnerEngineerMapper;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import com.kyowon.sms.wells.web.organization.hmnrsc.dto.WogcPartnerEngineerDto.SearchEngineerRes;
import com.kyowon.sms.wells.web.organization.hmnrsc.dto.WogcPartnerEngineerDto.SearchEngineerReq;
import com.kyowon.sms.wells.web.organization.hmnrsc.dto.WogcPartnerEngineerDto.FindJoeManagementReq;
import com.kyowon.sms.wells.web.organization.hmnrsc.dto.WogcPartnerEngineerDto.FindJoeManagementRes;
import com.kyowon.sms.wells.web.organization.hmnrsc.dto.WogcPartnerEngineerDto.FindEngineerGradeReq;
import com.kyowon.sms.wells.web.organization.hmnrsc.dto.WogcPartnerEngineerDto.FindEngineerGradeRes;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * <pre>
 *
 * </pre>
 *
 * @author 김동석
 * @since 2023-05-24
 */
@Service
@RequiredArgsConstructor
public class WogcPartnerEngineerService {
    private final WogcPartnerEngineerMapper mapper;
    private final WogcPartnerEngineerConverter wogcPartnerEngineerConverter;

    public PagingResult<SearchEngineerRes> getEngineerAttends(SearchEngineerReq dto, PageInfo pageInfo) {
        return mapper.selectEngineerAttends(dto, pageInfo);
    }

    public List<SearchEngineerRes> getEngineerAttendsForExcelDownload(SearchEngineerReq dto) {
        return mapper.selectEngineerAttends(dto);
    }

    public PagingResult<WogcPartnerEngineerDto.FindJoeManagementRes> getJoeManagementPages(FindJoeManagementReq dto, PageInfo pageInfo) {
        return this.mapper.selectJoeManagements(dto, pageInfo);
    }

    public List<WogcPartnerEngineerDto.FindJoeManagementRes> getJoeManagementForExcelDownload(FindJoeManagementReq dto) {
        List<WogcPartnerEngineerDto.FindJoeManagementRes> result = null;
        List<WogcPartnerEngineerDvo> dvos = this.mapper.selectJoeManagementForExcelDownload(dto);
        for (WogcPartnerEngineerDvo dvo : dvos) {
            String cralLocaraTno = StringUtils.isNotEmpty(dvo.getCralLocaraTno()) ? dvo.getCralLocaraTno() : "";
            String mexnoEncr = StringUtils.isNotEmpty(dvo.getMexnoEncr()) ? dvo.getMexnoEncr() : "";
            String cralIdvTno = StringUtils.isNotEmpty(dvo.getCralIdvTno()) ? dvo.getCralIdvTno() : "";
            dvo.setCralLocaraTno(cralLocaraTno + "-" + mexnoEncr + "-" + cralIdvTno);
        }
        result = this.wogcPartnerEngineerConverter.mapWogcPartnerEngineerDvoToFindJoeManagementRes(dvos);
        return result;
    }

    @Transactional
    public int saveJoeManagement(List<WogcPartnerEngineerDto.SaveJoeManagementReq> dtos) {
        int processCnt = 0;

        for (WogcPartnerEngineerDto.SaveJoeManagementReq dto : dtos) {
            WogcPartnerEngineerDvo dvo = this.wogcPartnerEngineerConverter.mapSaveJoeManagementReqToWogcPartnerEngineerDvo(dto);
            processCnt += this.mapper.insertWkGrpBlgDtl(dvo);
            this.mapper.updatePrtnrGrpCd(dvo);
        }

        return processCnt;
    }

    public PagingResult<WogcPartnerEngineerDto.FindEngineerGradeRes> getEngineerGradePages(FindEngineerGradeReq dto, PageInfo pageInfo) {
        return this.mapper.selectEngineerGrades(dto, pageInfo);
    }

    public List<WogcPartnerEngineerDto.FindEngineerGradeRes> getEngineerGradeForExcelDownload(FindEngineerGradeReq dto) {
        return this.mapper.selectEngineerGrades(dto);
    }

    @Transactional
    public int saveEngineerGrade(List<WogcPartnerEngineerDto.SaveEngineerGradeReq> dtos) {
        int processCnt = 0;
        return processCnt;
    }
}
