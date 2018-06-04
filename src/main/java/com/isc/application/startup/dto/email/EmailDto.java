package com.isc.application.startup.dto.email;

import com.isc.application.startup.dal.emailMyISAM.EmailMyISAMEntity;

public class EmailDto extends EmailMyISAMEntity {

    private String otherColumn;

    public String getOtherColumn() {
        return otherColumn;
    }

    public void setOtherColumn(String otherColumn) {
        this.otherColumn = otherColumn;
    }
}
