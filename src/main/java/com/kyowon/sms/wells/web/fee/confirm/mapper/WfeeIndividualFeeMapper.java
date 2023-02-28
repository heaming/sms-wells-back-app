package com.kyowon.sms.wells.web.fee.confirm.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.kyowon.sms.wells.web.fee.confirm.dto.WfeeIndividualFeeDto;

/**
 * <pre>
 * 수수료 개인 상세
 * </pre>
 *
 * @author gs.piit56
 * @since 2023.02.01
 */
@Mapper
public interface WfeeIndividualFeeMapper {
    List<WfeeIndividualFeeDto.SearchRes> selectIndividualPerformanceDetails(
        WfeeIndividualFeeDto.SearchReq dto
    );

    WfeeIndividualFeeDto.FindHmstInformationRes selectHmstInformation(
        WfeeIndividualFeeDto.SearchHmstReq dto
    );

    List<WfeeIndividualFeeDto.SearchHmstEtcRes> selectHmstEtcInformations(
        WfeeIndividualFeeDto.SearchHmstReq dto
    );

    List<WfeeIndividualFeeDto.SearchHmstFeeRes> selectHmstFeeInformations(
        WfeeIndividualFeeDto.SearchHmstReq dto
    );

    WfeeIndividualFeeDto.FindHmstDeductionRes selectHmstDeduction(
        WfeeIndividualFeeDto.SearchHmstReq dto
    );

    List<WfeeIndividualFeeDto.SearchHmstPnpyamRes> selectHmstPnpyamInformations(
        WfeeIndividualFeeDto.SearchHmstReq dto
    );

    WfeeIndividualFeeDto.FindPlarInformationRes selectPlarInformation(
        WfeeIndividualFeeDto.SearchPlarReq dto
    );

    List<WfeeIndividualFeeDto.SearchPlarEtcRes> selectPlarEtcInformations(
        WfeeIndividualFeeDto.SearchPlarReq dto
    );

    List<WfeeIndividualFeeDto.SearchPlarFeeRes> selectPlarFeeInformations(
        WfeeIndividualFeeDto.SearchPlarReq dto
    );

    WfeeIndividualFeeDto.FindPlarDeductionRes selectPlarDeduction(
        WfeeIndividualFeeDto.SearchPlarReq dto
    );

    List<WfeeIndividualFeeDto.SearchPlarPnpyamRes> selectPlarPnpyamInformations(
        WfeeIndividualFeeDto.SearchPlarReq dto
    );

    WfeeIndividualFeeDto.FindMngerInformationRes selectMngerInformation(
        WfeeIndividualFeeDto.SearchMngerReq dto
    );

    List<WfeeIndividualFeeDto.SearchMngerEtcRes> selectMngerEtcInformations(
        WfeeIndividualFeeDto.SearchMngerReq dto
    );

    List<WfeeIndividualFeeDto.SearchMngerFeeRes> selectMngerFeeInformations(
        WfeeIndividualFeeDto.SearchMngerReq dto
    );

    WfeeIndividualFeeDto.FindMngerDeductionRes selectMngerDeduction(
        WfeeIndividualFeeDto.SearchMngerReq dto
    );

    List<WfeeIndividualFeeDto.SearchMngerPnpyamRes> selectMngerPnpyamInformations(
        WfeeIndividualFeeDto.SearchMngerReq dto
    );
}
