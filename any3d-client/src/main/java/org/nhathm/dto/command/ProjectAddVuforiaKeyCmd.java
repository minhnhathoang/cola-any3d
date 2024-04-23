package org.nhathm.dto.command;

import lombok.Data;

/**
 * @author nhathm
 */
@Data
public class ProjectAddVuforiaKeyCmd {

    private String projectId;

    private String serverAccessKey;

    private String serverSecretKey;
}
