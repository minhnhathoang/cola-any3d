CREATE TABLE users
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

CREATE TABLE user_profiles
(
    user_id bigint(20)  NOT NULL,
    name    varchar(70) NOT NULL,
    avatar  varchar(100) DEFAULT NULL,
    address varchar(100) DEFAULT NULL,
    phone   varchar(20)  DEFAULT NULL,

    CONSTRAINT user_profiles_FK FOREIGN KEY (user_id) REFERENCES user (id)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8;


CREATE TABLE projects
(
    id               bigint(20)  NOT NULL,
    user_id          bigint(20)  NOT NULL,
    name             varchar(70) NOT NULL,
    metadata         json      DEFAULT NULL,
    created_at       timestamp DEFAULT current_timestamp(),
    last_modified_at timestamp DEFAULT NULL,

    PRIMARY KEY (id),
    CONSTRAINT projects_FK FOREIGN KEY (user_id) REFERENCES user (id)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

CREATE TABLE contents
(
    id               bigint(20) NOT NULL,
    project_id       bigint(20) NOT NULL,
    image_target_id  bigint(20) DEFAULT NULL,
    metadata         json       DEFAULT NULL,
    created_at       timestamp  DEFAULT current_timestamp(),
    last_modified_at timestamp  DEFAULT NULL,

    PRIMARY KEY (id),
    CONSTRAINT contents_FK1 FOREIGN KEY (project_id) REFERENCES project (id),
    CONSTRAINT contents_FK2 FOREIGN KEY (hologram_id) REFERENCES holograms (id)

) ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

CREATE TABLE holograms
(
    id         bigint(20)  NOT NULL,
    content_id bigint(20)  NOT NULL,
    name       varchar(70) NOT NULL,
    type       varchar(20) NOT NULL,

    PRIMARY KEY (id)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

CREATE TABLE image_targets
(
    id         bigint(20)  NOT NULL,
    project_id bigint(20)  NOT NULL,
    name       varchar(70) NOT NULL,
    type       varchar(20) NOT NULL,
    ar_sdk     varchar(20) NOT NULL,

    PRIMARY KEY (id),
    CONSTRAINT targets_FK FOREIGN KEY (project_id) REFERENCES project (id)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8;
