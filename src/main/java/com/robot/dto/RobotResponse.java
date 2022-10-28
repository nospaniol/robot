package com.robot.dto;

import lombok.Data;
import java.io.Serializable;


@Data
public class RobotResponse implements Serializable {

   public String title = "";
    public String message = "";
    public String detail = "";
    public Object data= null;

}
