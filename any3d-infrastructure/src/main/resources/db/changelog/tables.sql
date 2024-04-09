CREATE TABLE user
(
    id               bigint(20)   NOT NULL,
    username         varchar(20)  NOT NULL,
    hashed_password  varchar(70) DEFAULT NULL,
    email            varchar(100) NOT NULL,
    created_at       timestamp   DEFAULT current_timestamp(),
    last_modified_at timestamp   DEFAULT NULL,

    CONSTRAINT PRIMARY KEY (id),
    UNIQUE KEY users_AK1 (username),
    UNIQUE KEY users_AK2 (email)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

CREATE TABLE user_profile
(
    user_id bigint(20)  NOT NULL,
    name    varchar(70) NOT NULL,
    avatar  varchar(100) DEFAULT NULL,
    address varchar(100) DEFAULT NULL,
    phone   varchar(20)  DEFAULT NULL,

    CONSTRAINT user_profile_FK FOREIGN KEY (user_id) REFERENCES user (id)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8;


CREATE TABLE project
(
    id               bigint(20)  NOT NULL,
    user_id          bigint(20)  NOT NULL,
    name             varchar(70) NOT NULL,
    metadata         json      DEFAULT NULL,
    created_at       timestamp DEFAULT current_timestamp(),
    last_modified_at timestamp DEFAULT NULL,

    PRIMARY KEY (id),
    CONSTRAINT project_FK FOREIGN KEY (user_id) REFERENCES user (id)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

CREATE TABLE content
(
    id               bigint(20) NOT NULL,
    project_id       bigint(20) NOT NULL,
    metadata         json      DEFAULT NULL,
    created_at       timestamp DEFAULT current_timestamp(),
    last_modified_at timestamp DEFAULT NULL,

    PRIMARY KEY (id),
    CONSTRAINT content_FK1 FOREIGN KEY (project_id) REFERENCES project (id)

) ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

CREATE TABLE hologram
(
    id         bigint(20)  NOT NULL,
    content_id bigint(20)  NOT NULL,
    fileName   varchar(70) NOT NULL,
    type       varchar(20) NOT NULL,

    PRIMARY KEY (id),
    CONSTRAINT hologram_FK FOREIGN KEY (content_id) REFERENCES content (id)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

CREATE TABLE image_target
(
    id         bigint(20)  NOT NULL,
    content_id bigint(20)  NOT NULL,
    name       varchar(70) NOT NULL,
    type       varchar(20) NOT NULL,
    ar_sdk     varchar(20) NOT NULL,

    PRIMARY KEY (id),
    CONSTRAINT image_target_FK FOREIGN KEY (content_id) REFERENCES content (id)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8;


### ACL tables
CREATE TABLE acl_sid
(
    id        bigint unsigned NOT NULL AUTO_INCREMENT,
    principal tinyint(1)      NOT NULL,
    sid       varchar(100)    NOT NULL,

    PRIMARY KEY (id),
    UNIQUE KEY acl_sid_UK1 (sid, principal)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

CREATE TABLE acl_class
(
    id   bigint unsigned NOT NULL AUTO_INCREMENT,
    name varchar(100)    NOT NULL,

    PRIMARY KEY (id),
    UNIQUE KEY acl_class_UK1 (name)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

CREATE TABLE acl_object_identity
(
    id                 bigint signed NOT NULL AUTO_INCREMENT,
    object_id_class    bigint(20)    NOT NULL,
    object_id_identity bigint(20)    NOT NULL,
    owner_sid          bigint(20) DEFAULT NULL,
    entries_inheriting tinyint(1) DEFAULT NULL,

    PRIMARY KEY (id),
    UNIQUE KEY acl_object_identity_UK1 (object_id_class, object_id_identity),
    CONSTRAINT acl_object_identity_FK1 FOREIGN KEY (object_id_class) REFERENCES acl_class (id),
    CONSTRAINT acl_object_identity_FK2 FOREIGN KEY (owner_sid) REFERENCES acl_sid (id)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

CREATE TABLE acl_entry
(
    id                  bigint(20) NOT NULL,
    acl_object_identity bigint(20) NOT NULL,
    ace_order           int(11)    NOT NULL,
    sid                 bigint(20) NOT NULL,
    mask                int(11)    NOT NULL,
    granting            tinyint(1) NOT NULL,
    audit_success       tinyint(1) NOT NULL,
    audit_failure       tinyint(1) NOT NULL,

    PRIMARY KEY (id),
    CONSTRAINT acl_entry_FK1 FOREIGN KEY (acl_object_identity) REFERENCES acl_object_identity (id),
    CONSTRAINT acl_entry_FK2 FOREIGN KEY (sid) REFERENCES acl_sid (id)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8;