-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
-- -----------------------------------------------------
-- Schema hospitaldb
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema hospitaldb
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `hospitaldb` DEFAULT CHARACTER SET latin1 ;
USE `hospitaldb` ;

-- -----------------------------------------------------
-- Table `hospitaldb`.`doctor`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `hospitaldb`.`doctor` (
  `Doctor_id` INT(11) NOT NULL AUTO_INCREMENT,
  `Name` VARCHAR(100) NOT NULL,
  `Email` VARCHAR(100) NULL DEFAULT NULL,
  `Password` VARCHAR(100) NOT NULL,
  `Birthdate` VARCHAR(100) NOT NULL,
  `Specialization` VARCHAR(100) NOT NULL,
  `Department` VARCHAR(100) NULL DEFAULT NULL,
  `WorkHour` VARCHAR(100) NULL DEFAULT NULL,
  PRIMARY KEY (`Doctor_id`))
ENGINE = InnoDB
AUTO_INCREMENT = 27
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `hospitaldb`.`patient`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `hospitaldb`.`patient` (
  `Patient_id` INT(11) NOT NULL AUTO_INCREMENT,
  `Name` VARCHAR(100) NOT NULL,
  `Email` VARCHAR(100) NULL DEFAULT NULL,
  `Password` VARCHAR(100) NOT NULL,
  `Birthdate` VARCHAR(100) NOT NULL,
  `Department` VARCHAR(100) NULL DEFAULT NULL,
  `Specialization` VARCHAR(100) NULL DEFAULT NULL,
  PRIMARY KEY (`Patient_id`))
ENGINE = InnoDB
AUTO_INCREMENT = 29
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `hospitaldb`.`visits`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `hospitaldb`.`visits` (
  `Patient_Patient_id` INT(11) NOT NULL,
  `Doctor_Doctor_id` INT(11) NOT NULL,
  `AppointmentDate` VARCHAR(100) NOT NULL,
  PRIMARY KEY (`Patient_Patient_id`, `Doctor_Doctor_id`),
  INDEX `fk_Patient_has_Doctor_Doctor1_idx` (`Doctor_Doctor_id` ASC),
  INDEX `fk_Patient_has_Doctor_Patient_idx` (`Patient_Patient_id` ASC),
  CONSTRAINT `fk_Patient_has_Doctor_Doctor1`
    FOREIGN KEY (`Doctor_Doctor_id`)
    REFERENCES `hospitaldb`.`doctor` (`Doctor_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Patient_has_Doctor_Patient`
    FOREIGN KEY (`Patient_Patient_id`)
    REFERENCES `hospitaldb`.`patient` (`Patient_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
