package pl.meklad.ipezput2k20.exceprtion;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * Create by dev on 06.11.2020
 */
@Setter
@Getter
public class ExceptionResp {
    private Date respDate;
    private String respMessage;

    public ExceptionResp() {
        super();
    }

    public ExceptionResp(Date date, String message) {
        super();
        this.respDate = date;
        this.respMessage = message;
    }


}
