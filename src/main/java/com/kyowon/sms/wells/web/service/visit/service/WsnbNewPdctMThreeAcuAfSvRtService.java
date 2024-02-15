package com.kyowon.sms.wells.web.service.visit.service;

import com.kyowon.sms.wells.web.service.visit.dto.WsnbNewPdctMThreeAcuAfSvRtDto.*;
import com.kyowon.sms.wells.web.service.visit.mapper.WsnbNewPdctMThreeAcuAfSvRtMapper;
import com.sds.sflex.common.utils.StringUtil;
import com.sds.sflex.system.config.constant.CommConst;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;
import com.sds.sflex.system.config.exception.BizException;
import com.sds.sflex.system.config.validation.BizAssert;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class WsnbNewPdctMThreeAcuAfSvRtService {
    private final WsnbNewPdctMThreeAcuAfSvRtMapper mapper;

    public List<SearchRes> getNewPdctMThreeAcuAfSvRtList(SearchReq dto){
        return mapper.selectNewPdctMThreeAcuAfSvRts(dto);
    }

    public PagingResult<PdListRes> getPdResultPages(PdListReq dto, PageInfo pageInfo){
        PagingResult<PdListRes> dtos = this.mapper.selectPdResults(dto, pageInfo);
        return dtos;
    }

    /**
     * M+3 출시일 등록 내역 삭제
     *
     * @param pdCds
     * @return
     */
    @Transactional
    public int removeNewPdctMThreeAcuAfSvRtInfos(List<String> pdCds){
        int processCount = 0;
        for(int i = 0; i < pdCds.size(); i++){
            int result = mapper.deleteNewPdctMThreeAcuAfSvRtInfo(pdCds.get(i));
            BizAssert.isTrue(result == 1, "MSG_ALT_SVE_ERR");
            processCount += result;
        }

        return processCount;
    }

    @Transactional
    public int saveNewPdctMThreeAcuAfSvRtInfos(List<SaveReq> dtos){
        int processCount = 0;
        for(SaveReq dto : dtos){
            int result = 0;

            switch (dto.rowState()){
                case CommConst.ROW_STATE_CREATED -> {
                    if(StringUtil.isEmpty(dto.otscDvCd())){
                        throw new BizException("MSG_ALT_EMPTY_REQUIRED_VAL");
                    }
                    int existCnt = mapper.selectDuplicationPdCdCnt(dto);
                    BizAssert.isFalse(existCnt > 0, "MSG_ALT_ALREADY_RGST_INFO");
                    result += mapper.insertNewPdctMThreeAcuAfSvRtInfo(dto);
                }
                case CommConst.ROW_STATE_UPDATED -> {
                    result += mapper.updateNewPdctMThreeAcuAfSvRtInfo(dto);
                }
            }

            processCount += result;
        }

        return processCount;
    }

    public List<PdDtlListRes> getPdDtlList(){
        return mapper.selectPdDtls();
    }
}
