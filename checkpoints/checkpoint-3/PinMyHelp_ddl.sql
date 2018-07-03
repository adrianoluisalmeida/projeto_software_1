DROP DATABASE IF EXISTS pinmyhelp;
CREATE DATABASE IF NOT EXISTS pinmyhelp;
USE pinmyhelp;

# DROP TABLE IF EXISTS `user`
CREATE TABLE IF NOT EXISTS `user` (
    user_id           INT          AUTO_INCREMENT,
    email             VARCHAR(255) NOT NULL,
    `password`        VARCHAR(255) NOT NULL,
    is_admin          BOOLEAN      DEFAULT FALSE,
    PRIMARY KEY       (user_id)
) ENGINE = InnoDB;

# DROP TABLE IF EXISTS person
CREATE TABLE IF NOT EXISTS person (
    person_id           INT,
    person_type         ENUM ('Claimant', 'Voluntary') NOT NULL,
    person_name         VARCHAR(255) NOT NULL,
    cpf                 CHAR(11)     NOT NULL,
    rg                  CHAR(10)     NULL,
    born_date           DATE         NOT NULL,
    person_first_phone  CHAR(11)     NOT NULL,
    person_second_phone CHAR(11)     NULL,
    profile_picture     CHAR(17)     NULL,
    biography           TEXT         NULL,
    person_score        DECIMAL(8,2) NOT NULL DEFAULT 0.0,
    person_notes        TEXT         NULL,
    # INIT ADDRESS INFO - PREFIX: p_
    p_postal_code       CHAR(8)      NULL, 
    p_uf                CHAR(2)      NULL,
    p_city              VARCHAR(255) NULL,
    p_neighborhood      VARCHAR(255) NULL,
    p_street            VARCHAR(255) NULL,
    p_number            INT          NULL,
    p_complement        VARCHAR(255) NULL,
    p_latitude          DOUBLE       NULL, # it helps in the use of google maps api 
    p_longitude         DOUBLE       NULL, # it helps in the use of google maps api
    # END ADDRESS INFO 
    PRIMARY KEY         (person_id),
    FOREIGN KEY         (person_id)  REFERENCES `user` (user_id)
) ENGINE = InnoDB;

# DROP TABLE IF EXISTS entity
CREATE TABLE IF NOT EXISTS entity (
    entity_id           INT,
    entity_name         VARCHAR(255) NOT NULL,
    cnpj                CHAR(14)     NOT NULL,
    foundation_date     DATE         NOT NULL,
    entity_first_phone  CHAR(11)     NOT NULL,
    entity_second_phone CHAR(11)     NULL,
    logo                CHAR(17)     NULL,
    description         TEXT         NULL,
    entity_score        DECIMAL(8,2) NOT NULL DEFAULT 0.0,
    entity_notes        TEXT         NULL,
    # INIT ADDRESS INFO - PREFIX: e_
    e_postal_code       CHAR(8)      NULL, 
    e_uf                CHAR(2)      NULL,
    e_city              VARCHAR(255) NULL,
    e_neighborhood      VARCHAR(255) NULL,
    e_street            VARCHAR(255) NULL,
    e_number            INT          NULL,
    e_complement        VARCHAR(255) NULL,
    e_latitude          DOUBLE       NULL, # it helps in the use of google maps api 
    e_longitude         DOUBLE       NULL, # it helps in the use of google maps api
    # END ADDRESS INFO 
    PRIMARY KEY         (entity_id),
    FOREIGN KEY         (entity_id)  REFERENCES `user` (user_id)
) ENGINE = InnoDB;

# DROP TABLE IF EXISTS help_solicitation
CREATE TABLE IF NOT EXISTS help_solicitation (
    solicitation_id          INT            AUTO_INCREMENT,
    solicitation_status      INT            NOT NULL DEFAULT 1,
    solicitation_type        INT            NOT NULL,
    solicitation_description TEXT           NOT NULL,
    solicitation_created     TIMESTAMP      NOT NULL DEFAULT NOW(),
    solicitation_updated     TIMESTAMP      NULL     ON UPDATE NOW(),
    start_date               DATETIME       NOT NULL,
    end_date                 DATETIME       NULL,
    # INIT ADDRESS INFO - PREFIX: s_
    s_postal_code            CHAR(8)        NULL,   
    s_uf                     CHAR(2)        NULL,
    s_city                   VARCHAR(255)   NULL,
    s_neighborhood           VARCHAR(255)   NULL,
    s_street                 VARCHAR(255)   NULL,
    s_number                 INT            NULL,
    s_latitude               DOUBLE         NULL,     # allows real-time geolocation based solicitation
    s_longitude              DOUBLE         NULL,     # allows real-time geolocation based solicitation
    # END ADDRESS INFO 
    claimant_id              INT            NULL,     # can be Entity or Person with type = 'Claimant'
    PRIMARY KEY          (solicitation_id),
    FOREIGN KEY          (claimant_id)  REFERENCES `user` (user_id)
) ENGINE = InnoDB;

# DROP TABLE IF EXISTS help_offer
CREATE TABLE IF NOT EXISTS help_offer (
    offer_id            INT               AUTO_INCREMENT,
    offer_status        INT               NOT NULL, # TODO: change type to ENUM 
    offer_observation	TEXT 		  NULL, 
    offer_created       TIMESTAMP         NOT NULL DEFAULT NOW(),
    offer_updated       TIMESTAMP         NULL     ON UPDATE NOW(),
    solicitation_id     INT               NOT NULL,
    voluntary_id        INT               NULL,     # can be Entity or Person with type = 'Voluntary'
    PRIMARY KEY         (offer_id),
    FOREIGN KEY         (solicitation_id) REFERENCES help_solicitation (solicitation_id),
    FOREIGN KEY         (voluntary_id)    REFERENCES `user` (user_id)
) ENGINE = InnoDB;

# DROP TABLE IF EXISTS feedback
CREATE TABLE IF NOT EXISTS feedback (
    feedback_id         INT               AUTO_INCREMENT,
    feedback_created    TIMESTAMP         NOT NULL DEFAULT NOW(),
    feedback_updated    TIMESTAMP         NULL     ON UPDATE NOW(),
    rating              INT               NOT NULL,
    comments            TEXT              NULL,
    sender_id           INT               NOT NULL,
    solicitation_id     INT               NULL,
    offer_id            INT               NULL,
    PRIMARY KEY         (feedback_id),
    FOREIGN KEY         (sender_id)       REFERENCES `user` (user_id), 
    FOREIGN KEY         (solicitation_id) REFERENCES help_solicitation (solicitation_id),
    FOREIGN KEY         (offer_id)        REFERENCES help_offer (offer_id)
) ENGINE = InnoDB;
