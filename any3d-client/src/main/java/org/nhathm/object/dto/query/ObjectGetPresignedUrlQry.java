package org.nhathm.object.dto.query;

import lombok.Data;

/**
 * @author <a href="mailto:nhathm.uet@outlook.com">nhathm</a>
 */
@Data
public class ObjectGetPresignedUrlQry {

    private String fileName;

    private String method;
}
