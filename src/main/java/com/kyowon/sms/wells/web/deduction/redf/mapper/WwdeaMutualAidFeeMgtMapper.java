package com.kyowon.sms.wells.web.deduction.redf.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.kyowon.sms.wells.web.deduction.redf.dto.WwdeaMutualAidFeeMgtDto.SearchMutualAidFeeReq;
import com.kyowon.sms.wells.web.deduction.redf.dto.WwdeaMutualAidFeeMgtDto.SearchMutualAidFeeRes;
import com.kyowon.sms.wells.web.deduction.redf.dto.WwdeaMutualAidFeeMgtDto.SearchMutualAidFeeSubRes;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;

@Mapper
public interface WwdeaMutualAidFeeMgtMapper {
    /*상조 되물림생성 조회- 개인*/
    PagingResult<SearchMutualAidFeeRes> selectMutalAids(SearchMutualAidFeeReq dto, PageInfo pageInfo);

    /*상조 되물림생성 조회 엑셀다운로드-개인*/
    List<SearchMutualAidFeeRes> selectMutalAids(SearchMutualAidFeeReq dto);

    /*상조 되물림생성 조회-조직*/
    PagingResult<SearchMutualAidFeeSubRes> selectMutalAidsSub(SearchMutualAidFeeReq dto, PageInfo pageInfo);

    /*상조 되물림생성 조회 엑셀다운로드-조직*/
    List<SearchMutualAidFeeSubRes> selectMutalAidsSub(SearchMutualAidFeeReq dto);

}
