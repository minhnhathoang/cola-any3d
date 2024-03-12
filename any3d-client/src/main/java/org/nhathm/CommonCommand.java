package org.nhathm;

import com.alibaba.cola.dto.Command;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommonCommand extends Command {
    private String operator;
    private boolean needsOperator;
}