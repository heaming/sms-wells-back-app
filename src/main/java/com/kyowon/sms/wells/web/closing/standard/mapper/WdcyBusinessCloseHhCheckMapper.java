package com.kyowon.sms.wells.web.closing.standard.mapper;

import java.util.HashMap;

import org.apache.ibatis.annotations.Mapper;

import com.kyowon.sms.wells.web.closing.standard.dvo.WdcyBusinessCloseHhCheckDvo;

/**
 * <pre>
 * 영업마감통제 서비스 맵퍼
 * </pre>
 *
 * @author gugyeongu
 * @since 2023-02-03
 */
@Mapper
public interface WdcyBusinessCloseHhCheckMapper {
    /**
     * 검색 조건에 맞는 영업마감 시간 정보를 가지고 온다
     * @param searchParam 검색 조건(key: clDt(일자), clPsicNo(담당자번호), clBizTpCd(마감업무유형코드))
     * @return EBusinessCloseHhCheckDvo 검색 결과
     */
    WdcyBusinessCloseHhCheckDvo selectBusinessCloseHh(HashMap<String, String> searchParam);
}
