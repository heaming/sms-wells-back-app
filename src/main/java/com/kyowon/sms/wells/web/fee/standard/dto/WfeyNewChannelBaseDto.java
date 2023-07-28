package com.kyowon.sms.wells.web.fee.standard.dto;

public class WfeyNewChannelBaseDto {

    public record SearchNewChannelBaseReq(
        String col1,
        String col2,
        String col3,
        String col4,
        String col5,
        String col6

    ) {}

    public record SearchNewChannelBaseRes(
        String col1

    ) {}

    public record SaveNewChannelBaseReq(
        String rowState,
        String col1
    ) {}
}
