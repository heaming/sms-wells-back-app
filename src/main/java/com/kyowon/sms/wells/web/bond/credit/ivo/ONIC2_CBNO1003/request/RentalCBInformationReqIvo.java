package com.kyowon.sms.wells.web.bond.credit.ivo.ONIC2_CBNO1003.request;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

/**
 * <pre>
 * ONIC2_CBNO1003 CB렌탈정보 조회 요청 Request Interface Dvo
 * </pre>
 *
 * @author gilyong.han
 * @since 2023-10-18
 * */

@Getter
@Setter
public class RentalCBInformationReqIvo {
    @JsonProperty("COMM")
    private CommReq commReq;
    @JsonProperty("DATA")
    private DataReq dataReq;
    @JsonProperty("ROWDATA_1")
    private RowData1Req rowData1Req;
    @JsonProperty("ROWDATA_4")
    private RowData4Req rowData4Req;
    @JsonProperty("ROWDATA_5")
    private RowData5Req rowData5Req;
    @JsonProperty("SUMMARYITEM_6")
    private SummaryItem6Req summaryItem6Req;
    @JsonProperty("SUMMARYITEM_6_REPAET")
    private List<SummaryItem6RepaetItemReq> summaryItem6RepaetItemReqs;
    @JsonProperty("SUMMARYITEM_7")
    private SummaryItem7Req summaryItem7Req;
    @JsonProperty("SUMMARYITEM_7_REPAET")
    private List<SummaryItem7RepaetItemReq> summaryItem7RepaetItemReqs;

}
