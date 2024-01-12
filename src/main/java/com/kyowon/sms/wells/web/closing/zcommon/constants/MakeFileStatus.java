package com.kyowon.sms.wells.web.closing.zcommon.constants;

import lombok.Getter;

@Getter
public enum MakeFileStatus {
    PROCESSING("0", "P"), // 진행 중
    DONE("1", "D") //완료
    ;

    private final String code;
    private final String name;

    MakeFileStatus(String code, String name) {
        this.code = code;
        this.name = name;
    }

    public String getCode() {
        return code;
    }
    public String getName() {
        return name;
    }
}
