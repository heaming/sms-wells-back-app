package com.kyowon.sms.wells.web.competence.evaluate.mapper;

import com.kyowon.sms.wells.web.competence.evaluate.dto.WpsdExcellentDivisionBaseMgtDto.*;
import com.kyowon.sms.wells.web.competence.evaluate.dvo.WpsdElvBaseDvo;
import com.kyowon.sms.wells.web.competence.evaluate.dvo.WpsdElvDetailDvo;
import com.kyowon.sms.wells.web.competence.evaluate.dvo.WpsdPdBaseDvo;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface WpsdExcellentDivisionBaseMgtMapper {

    PagingResult<PdSearchRes> selectProductBaseMgtPages(
        PdSearchReq dto,
        PageInfo pageInfo
    );

    List<PdSearchRes> selectProductBaseMgtPages(
        PdSearchReq dto
    );

    int insertProductBase(WpsdPdBaseDvo dvo);

    int updateProductBase(WpsdPdBaseDvo dvo);

    int removeProductBase(WpsdPdBaseDvo dvo);

    PagingResult<EvlSearchRes> selectEvaluationBaseMgtPages(EvlSearchReq req, PageInfo pageInfo);

    int updateEvaluationBase(WpsdElvBaseDvo dvo);

    int insertEvaluationBase(WpsdElvBaseDvo dvo);

    int removeEvaluationResponsibility(WpsdElvBaseDvo dvo);

    int insertEvaluationResponsibility(WpsdElvBaseDvo dvo);

    PagingResult<EvlDetailSearchRes> selectEvaluationDetailPages(EvlSearchReq req, PageInfo pageInfo);

    int updateEvaluationDetail(WpsdElvDetailDvo dvo);

    List<EvlArticlesSearchRes> selectEvaluationArticales(EvlSearchReq req);

    PagingResult<TrgSearchRes> selectTargetBaseMgtPages(TrgSearchReq req, PageInfo pageInfo);


    int updateTargetBase(WpsdElvDetailDvo dvo);


}
