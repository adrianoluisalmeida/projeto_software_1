-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Table `user`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `user` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `email` VARCHAR(255) NOT NULL,
  `password` CHAR(35) NOT NULL,
  `score` DECIMAL(8,2) NOT NULL DEFAULT 0.0,
  `admin` TINYINT(1) NULL DEFAULT 0,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `person`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `person` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `type` INT NOT NULL,
  `name` VARCHAR(255) NOT NULL,
  `cpf` CHAR(11) NOT NULL,
  `identity` CHAR(10) NULL,
  `born_date` DATE NULL,
  `prim_phone` CHAR(11) NOT NULL,
  `sec_phone` CHAR(11) NULL,
  `bio` TEXT NULL,
  `photo` CHAR(11) NULL,
  `obs` TEXT NULL,
  `street` VARCHAR(255) NULL,
  `number` INT NULL,
  `neib` VARCHAR(255) NULL,
  `postal_code` CHAR(8) NULL,
  `compl` VARCHAR(255) NULL,
  `city` VARCHAR(255) NULL,
  `state` CHAR(45) NULL,
  `lat` DOUBLE NULL,
  `long` DOUBLE NULL,
  `user_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_person_user1_idx` (`user_id` ASC),
  CONSTRAINT `fk_person_user1`
    FOREIGN KEY (`user_id`)
    REFERENCES `user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `entity`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `entity` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(255) NOT NULL,
  `cnpj` CHAR(14) NOT NULL,
  `description` TEXT NULL,
  `fund_date` DATE NULL,
  `prim_phone` CHAR(11) NOT NULL,
  `sec_phone` CHAR(11) NULL,
  `logo` CHAR(11) NULL,
  `obs` TEXT NULL,
  `street` VARCHAR(255) NULL,
  `number` INT NULL,
  `neib` VARCHAR(255) NULL,
  `postal_code` CHAR(8) NULL,
  `compl` VARCHAR(255) NULL,
  `city` VARCHAR(255) NULL,
  `state` CHAR(2) NULL,
  `lat` DOUBLE NULL,
  `lng` DOUBLE NULL,
  `user_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_entity_user1_idx` (`user_id` ASC),
  CONSTRAINT `fk_entity_user1`
    FOREIGN KEY (`user_id`)
    REFERENCES `user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `help_solicitation`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `help_solicitation` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `sol_date_time` DATETIME NOT NULL,
  `fin_date_time` DATETIME NULL,
  `lat` DOUBLE NOT NULL,
  `lng` DOUBLE NOT NULL,
  `help_status` INT NOT NULL,
  `help_type` INT NOT NULL,
  `calimant_score` INT NULL,
  `helper_score` INT NULL,
  `person_id` INT NULL,
  `entity_id` INT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_help_solicitation_person1_idx` (`person_id` ASC),
  INDEX `fk_help_solicitation_entity1_idx` (`entity_id` ASC),
  CONSTRAINT `fk_help_solicitation_person1`
    FOREIGN KEY (`person_id`)
    REFERENCES `person` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_help_solicitation_entity1`
    FOREIGN KEY (`entity_id`)
    REFERENCES `entity` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `feedback`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `feedback` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `description` VARCHAR(255) NOT NULL,
  `note` TEXT NOT NULL,
  `date_time` DATETIME NOT NULL,
  `help_solicitation_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_feedback_help_solicitation_idx` (`help_solicitation_id` ASC),
  CONSTRAINT `fk_feedback_help_solicitation`
    FOREIGN KEY (`help_solicitation_id`)
    REFERENCES `help_solicitation` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `help_offer`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `help_offer` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `help_solicitation_id` INT NOT NULL,
  `person_id` INT NULL,
  `entity_id` INT NULL,
  `status` INT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_help_offer_help_solicitation1_idx` (`help_solicitation_id` ASC),
  INDEX `fk_help_offer_person1_idx` (`person_id` ASC),
  INDEX `fk_help_offer_entity1_idx` (`entity_id` ASC),
  CONSTRAINT `fk_help_offer_help_solicitation1`
    FOREIGN KEY (`help_solicitation_id`)
    REFERENCES `help_solicitation` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_help_offer_person1`
    FOREIGN KEY (`person_id`)
    REFERENCES `person` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_help_offer_entity1`
    FOREIGN KEY (`entity_id`)
    REFERENCES `entity` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
