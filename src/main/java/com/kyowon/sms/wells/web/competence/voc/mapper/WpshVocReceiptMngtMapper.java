package com.kyowon.sms.wells.web.competence.voc.mapper;


import com.kyowon.sms.wells.web.competence.voc.dto.WpshVocReceiptMngtDto.SearchDtlRes;
import com.kyowon.sms.wells.web.competence.voc.dto.WpshVocReceiptMngtDto.SearchReq;
import com.kyowon.sms.wells.web.competence.voc.dto.WpshVocReceiptMngtDto.SearchRes;
import com.kyowon.sms.wells.web.competence.voc.dvo.WpshVocReceiptMngtDvo;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;


@Mapper
public interface WpshVocReceiptMngtMapper {

    PagingResult<SearchRes> selectVocReceiptMngtPages(
        SearchReq dto,
        PageInfo pageInfo
    );

    List<SearchRes> selectVocReceiptMngtPages(
        SearchReq dto
    );

    SearchDtlRes selectVocReceiptDtl(
        WpshVocReceiptMngtDvo dvo
    );

    String selectVocRcpId(
    );

    String selectVocRlyId(
    );

    int saveVocReceipt(
        WpshVocReceiptMngtDvo dvo
    );

    int saveVocReceiptRcp(
        WpshVocReceiptMngtDvo dvo
    );

    int saveVocReceiptProcs(
        WpshVocReceiptMngtDvo dvo
    );

    int saveVocReceiptEvl(
        WpshVocReceiptMngtDvo dvo
    );

    int saveVocReceiptRly(
        WpshVocReceiptMngtDvo dvo
    );
}
