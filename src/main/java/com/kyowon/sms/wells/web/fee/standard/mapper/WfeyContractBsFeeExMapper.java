package com.kyowon.sms.wells.web.fee.standard.mapper;

import com.kyowon.sms.wells.web.fee.standard.dto.WfeyContractBsFeeExDto.SearchContractBsFeeExReq;
import com.kyowon.sms.wells.web.fee.standard.dto.WfeyContractBsFeeExDto.SearchContractBsFeeExRes;
import com.kyowon.sms.wells.web.fee.standard.dvo.WfeyContractBsFeeExDvo;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface WfeyContractBsFeeExMapper {
    /* 계약별 BS 수수료 예외 기준정보 조회 */
    List<SearchContractBsFeeExRes> selectContractBsFeeExList(SearchContractBsFeeExReq req);
    PagingResult<SearchContractBsFeeExRes> selectContractBsFeeExList(SearchContractBsFeeExReq req, PageInfo pageInfo);
    /* 기준변경차수 최대값 채번*/
    int selectMaxBaseChTcnt(String cntrNo, int cntrSn);
    /* 정합성체크 */
    int selecDuplicateContractBsFeeEx(WfeyContractBsFeeExDvo req);
    /* 계약별 BS 수수료 예외 기준정보 인서트 */
    int insertContractBsFeeEx(WfeyContractBsFeeExDvo req);
    /* 계약별 BS 수수료 예외 기준정보 업데이트 */
    int updateContractBsFeeEx(WfeyContractBsFeeExDvo req);
    /* 계약별 BS 수수료 예외 기준정보 삭제 */
    int deleteContractBsFeeEx(WfeyContractBsFeeExDvo req);

}
