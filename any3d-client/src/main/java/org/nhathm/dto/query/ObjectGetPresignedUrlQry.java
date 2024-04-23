package org.nhathm.dto.query;

import lombok.Data;


@Data
public class ObjectGetPresignedUrlQry {

    private String fileName;

    private String method;
}
