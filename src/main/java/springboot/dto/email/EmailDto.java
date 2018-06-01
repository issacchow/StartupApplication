package springboot.dto.email;

import springboot.dal.emailMyISAM.EmailMyISAMEntity;

public class EmailDto extends EmailMyISAMEntity {

    private String otherColumn;

    public String getOtherColumn() {
        return otherColumn;
    }

    public void setOtherColumn(String otherColumn) {
        this.otherColumn = otherColumn;
    }
}
