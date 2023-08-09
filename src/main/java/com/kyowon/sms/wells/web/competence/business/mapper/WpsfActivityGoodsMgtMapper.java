package com.kyowon.sms.wells.web.competence.business.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.kyowon.sms.wells.web.competence.business.dto.WpsfActivityGoodsMgtDto;
import com.kyowon.sms.wells.web.competence.business.dvo.*;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;

@Mapper
public interface WpsfActivityGoodsMgtMapper {
    WpsfActivityGoodsMgtDto.SearchRes selectApplicationBase(WpsfActivityGoodsMgtDto.SearchReq dto);

    WpsfActivityGoodsMgtDto.SearchRes selectApplicationBase(WpsfActivityGoodsMgtDto.EditApplicationReq dto);

    int mergeApplicationBase(WpsfActivityGoodsApplicationBaseDvo dvo);

    List<WpsfActivityGoodsMgtDto.SearchAcriGdsBasRes> selectActivityGoodsBase(
        WpsfActivityGoodsMgtDto.SearchReq dto
    );

    int insertActivityGoodsBase(WpsfActivityGoodsBaseDvo dvo);

    int selectMaxActiGdsSn(WpsfActivityGoodsMgtDto.EditReq dto);

    List<WpsfActivityGoodsMgtDto.SearchAcriGdsBasRes> selectMaxActivityGoodsBase(WpsfActivityGoodsMgtDto.SearchReq dto);

    int updateGoodsBase(WpsfActivityGoodsBaseDvo dvo);

    PagingResult<WpsfActivityGoodsMgtDto.SearchStatRes> selectActivityGoodsApplicationIzPages(
        WpsfActivityGoodsMgtDto.SearchReq dto, PageInfo pageInfo
    );

    List<WpsfActivityGoodsMgtDto.SearchStatRes> selectActivityGoodsApplicationIzPages(
        WpsfActivityGoodsMgtDto.SearchReq dto
    );

    List<WpsfActivityGoodsMgtDto.SearchBaseCodeRes> selectGdsBaseCodes(WpsfActivityGoodsMgtDto.SearchReq dto);

    int insertActiGdsAplcIz(WpsfActivityGoodsAplcIzDvo dvo);

    int updateActiGdsAplcIz(WpsfActivityGoodsAplcIzDvo dvo);

    String selectActiGdsAplcId();

    int insertActiGdsAplcStatIz(WpsfActiGdsAplcStatIzDvo sDvo);

    int selectMaxActiGdsAplcSn();

    List<WpsfActivityGoodsMgtDto.SearchCodeRes> selectStddBases(WpsfActivityGoodsMgtDto.SearchReq dto);

    List<WpsfActivityGoodsMgtDto.SearchCodeRes> selectStddDtls(WpsfActivityGoodsMgtDto.SearchReq dto);

    int updateActiGdsAplcStatIz(WpsfActiGdsAplcStatIzDvo dvo);

    int updateActiGdsAplcIzSn(WpsfActivityGoodsAplcIzDvo aDvo);

    int insertActivityGoodsDeductionItemization(WpsfActivityGoodsDeductionItemizationDvo dvo);

    int updateActivityGoodsDeductionItemization(WpsfActivityGoodsDeductionItemizationDvo dvo);

    String selectMaxactiGdsDdtnId();

    int updateActivityGoodsDeductionDtlYn(WpsfActivityGoodsDeductionItemizationDvo dvo);

    List<WpsfActivityGoodsMgtDto.SearchDeductionItemizationRes> selectActivityGoodsDeductionItemization(
        WpsfActivityGoodsMgtDto.SearchReq dto
    );

    int insertActivityGoodsSize(WpsfActivityGoodsSizeDvo dvo);

    int updateActivityGoodsSize(WpsfActivityGoodsSizeDvo dvo);

    String selectMaxActiGdsStddDvId();

    List<WpsfActivityGoodsMgtDto.SearchSizeRes> selectActivityGoodsSize(WpsfActivityGoodsMgtDto.SearchReq dto);

    int updateActivityGoodsSizeDlayn(WpsfActivityGoodsSizeDvo dvo);

    int updateActivityGoodsSizeDetailDlayn(WpsfActivityGoodsSizeDetailDvo dvo);

    int insertActivityGoodsSizeDetail(WpsfActivityGoodsSizeDetailDvo dvo);

    List<WpsfActivityGoodsMgtDto.SearchSizeDetailRes> selectActivityGoodsSizeDetail(
        WpsfActivityGoodsMgtDto.SearchReq dto
    );

    int selectCountactiGdsStddCd(WpsfActivityGoodsSizeDetailDvo dvo);

    int updateActivityGoodsSizeDetail(WpsfActivityGoodsSizeDetailDvo dvo);

    int selectCountactiGdsDdtnId(WpsfActivityGoodsMgtDto.EditApplicationReq dto);

    WpsfActivityGoodsBaseDvo selectGoodsBase(WpsfActivityGoodsBaseDvo bDvo);
}
