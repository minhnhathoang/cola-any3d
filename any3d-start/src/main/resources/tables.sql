CREATE TABLE user
(
    id               varchar(36)  NOT NULL,
    username         varchar(20)  NOT NULL,
    hashed_password  varchar(70) DEFAULT NULL,
    email            varchar(100) NOT NULL,
    created_at       timestamp   DEFAULT current_timestamp(),
    last_modified_at timestamp   DEFAULT NULL,

    CONSTRAINT PRIMARY KEY PK_user (id),
    UNIQUE KEY AK1_user (username),
    UNIQUE KEY AK2_user (email)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8;


CREATE TABLE user_profile
(
    id      varchar(36) NOT NULL,
    user_id varchar(36) NOT NULL,
    name    varchar(70)  DEFAULT NULL,
    avatar  varchar(100) DEFAULT NULL,
    address varchar(100) DEFAULT NULL,
    phone   varchar(20)  DEFAULT NULL,

    UNIQUE KEY AK1_user_profile (user_id),
    CONSTRAINT FK1_user_profile_user FOREIGN KEY (user_id) REFERENCES user (id)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8;


CREATE TABLE project
(
    id               varchar(36)  NOT NULL,
    owner_id         varchar(36)  NOT NULL,
    name             varchar(70)  NOT NULL,
    description      varchar(255) NOT NULL,
    metadata         json      DEFAULT NULL,
    created_at       timestamp DEFAULT current_timestamp(),
    last_modified_at timestamp DEFAULT NULL,
    deleted          tinyint(1) NOT NULL DEFAULT 0,

    PRIMARY KEY (id),
    CONSTRAINT FK1_project_user FOREIGN KEY (owner_id) REFERENCES user (id)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8;


CREATE TABLE content
(
    id               varchar(36) NOT NULL,
    project_id       varchar(36) NOT NULL,
    name             varchar(70) NOT NULL,
    metadata         json      DEFAULT NULL,
    created_at       timestamp DEFAULT current_timestamp(),
    last_modified_at timestamp DEFAULT NULL,
    deleted          tinyint(1) NOT NULL DEFAULT 0,

    PRIMARY KEY (id),
    CONSTRAINT FK1_content_project FOREIGN KEY (project_id) REFERENCES project (id) ON DELETE CASCADE

) ENGINE = InnoDB
  DEFAULT CHARSET = utf8;


CREATE TABLE hologram
(
    id              varchar(36) NOT NULL,
    content_id      varchar(36) NOT NULL,
    filename        varchar(70) NOT NULL,

    PRIMARY KEY (id),
    CONSTRAINT FK1_hologram_content FOREIGN KEY (content_id) REFERENCES content (id) ON DELETE CASCADE,
    UNIQUE KEY AK1_hologram (content_id)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8;


CREATE TABLE image_target
(
    id              varchar(36) NOT NULL,
    content_id      varchar(36) NOT NULL,
    filename        varchar(70) NOT NULL,
    ar_sdk_type     varchar(20) NOT NULL,
    additional_data json DEFAULT NULL,

    PRIMARY KEY (id),
    CONSTRAINT FK1_image_target_content FOREIGN KEY (content_id) REFERENCES content (id) ON DELETE CASCADE
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8;


CREATE TABLE vuforia_key
(
    id         varchar(36)  NOT NULL,
    project_id varchar(36)  NOT NULL,
    access_key varchar(100) NOT NULL,
    secret_key varchar(100) NOT NULL,

    PRIMARY KEY (id),
    CONSTRAINT FK1_vuforia_key_project FOREIGN KEY (project_id) REFERENCES project (id) ON DELETE CASCADE,
    UNIQUE KEY AK1_vuforia_key (project_id)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8;
