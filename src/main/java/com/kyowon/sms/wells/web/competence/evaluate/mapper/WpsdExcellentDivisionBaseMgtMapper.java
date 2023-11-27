package com.kyowon.sms.wells.web.competence.evaluate.mapper;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.kyowon.sms.wells.web.competence.evaluate.dto.WpsdExcellentDivisionBaseMgtDto.*;
import com.kyowon.sms.wells.web.competence.evaluate.dvo.WpsdElvBaseDvo;
import com.kyowon.sms.wells.web.competence.evaluate.dvo.WpsdElvDetailDvo;
import com.kyowon.sms.wells.web.competence.evaluate.dvo.WpsdExcellentDivisionDeadlineDvo;
import com.kyowon.sms.wells.web.competence.evaluate.dvo.WpsdPdBaseDvo;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;

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

    int selectEvaluationBase(WpsdElvBaseDvo dvo);

    int updateEvaluationBase(WpsdElvBaseDvo dvo);

    int insertEvaluationBase(WpsdElvBaseDvo dvo);

    int removeEvaluationResponsibility(WpsdElvBaseDvo dvo);

    int insertEvaluationResponsibility(WpsdElvBaseDvo dvo);

    PagingResult<EvlDetailSearchRes> selectEvaluationDetailPages(EvlSearchReq req, PageInfo pageInfo);

    List<HashMap<String, Object>> selectTargetList(WpsdElvDetailDvo dvo);

    int insertEvaluationDetail(@Param("param") WpsdElvDetailDvo dvo, @Param("target") List<HashMap<String, Object>> target);

    List<EvlArticlesSearchRes> selectEvaluationArticales(EvlSearchReq req);

    PagingResult<TrgSearchRes> selectTargetBaseMgtPages(TrgSearchReq req, PageInfo pageInfo);

    int updateTargetBase(WpsdElvDetailDvo dvo);

    int deleteEvaluationDetail(WpsdElvDetailDvo dvo);

    int selectExcellentDivisionDeadlineCount(WpsdExcellentDivisionDeadlineDvo dvo);

    int updateExcellentDivisionDeadline(WpsdExcellentDivisionDeadlineDvo dvo);

    int insertExcellentDivisionDeadline(WpsdExcellentDivisionDeadlineDvo dvo);

    DeadlineSearchRes selectExcellentDivisionDeadline(DeadlineSearchReq req);

}
