package com.kyowon.sms.wells.web.service.common.dvo;

import static com.sds.sflex.common.docs.dto.AttachFileDto.AttachFile;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

/**
 *
 * <pre>
 * K-W-SV-U-0304M01 상품별 에러코드 관리
 * </pre>
 *
 * @author 37758 이재훈
 * @since 2023.07.03
 */
@Setter
@Getter
public class WsnyErrorCodeMgtDvo {
    public WsnyErrorCodeMgtDvo() {}

    public WsnyErrorCodeMgtDvo(String pdGrpCd, String pdCd) {
        this.pdGrpCd = pdGrpCd;
        this.pdCd = pdCd;
    }

    String pdGrpCd;
    String pdCd;
    String pdNm;
    String errCn;
    String errDvCn;
    String errCausCn;
    String errImageYn;
    String errImageUId;
    String errImageDocId;
    List<AttachFile> attachFiles;
    String flag;
}
