package com.kyowon.sms.wells.web.fee.standard.mapper;

import com.kyowon.sms.wells.web.fee.standard.dto.WfeyEngineerAllowanceDto.*;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface WfeyHomeMasterCalculationMapper {

    /**
     * 홈마스터 급지수수료(W030012) 후처리
     *
     * @param baseYm 기준년월
     * @param ogTpCd 조직유형코드
     * @param feeCd 수수료코드
     * @param feeTcntDvCd 수수료차수구분코드
     * @return 처리개수
     */
    Integer updateRglvlFeePostProcess(String baseYm, String ogTpCd, String feeCd, String feeTcntDvCd);

    /**
     * 홈마스터 서비스현장수수료(W030007) 후처리
     *
     * @param baseYm 기준년월
     * @param ogTpCd 조직유형코드
     * @param feeCd 수수료코드
     * @param feeTcntDvCd 수수료차수구분코드
     * @return 처리개수
     */
    Integer updateSvSiteFeePostProcess(String baseYm, String ogTpCd, String feeCd, String feeTcntDvCd);
}
