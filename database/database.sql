-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `mydb` DEFAULT CHARACTER SET utf8 ;
USE `mydb` ;

-- -----------------------------------------------------
-- Table `mydb`.`user`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mydb`.`user` ;

CREATE TABLE IF NOT EXISTS `mydb`.`user` (
  `idUser` INT NOT NULL AUTO_INCREMENT,
  `password` VARCHAR(45) NULL,
  `admin` TINYINT NULL DEFAULT 0,
  `username` VARCHAR(45) NULL,
  `mail` VARCHAR(70) NULL,
  `loyalty_point` INT NULL DEFAULT 0,
  `address` VARCHAR(200) NULL,
  `is_validate` TINYINT NULL DEFAULT 0,
  `first_name` VARCHAR(45) NULL,
  `last_name` VARCHAR(45) NULL,
  PRIMARY KEY (`idUser`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`category`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mydb`.`category` ;

CREATE TABLE IF NOT EXISTS `mydb`.`category` (
  `idcategory` INT NOT NULL,
  `name` VARCHAR(45) NULL,
  PRIMARY KEY (`idcategory`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`product`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mydb`.`product` ;

CREATE TABLE IF NOT EXISTS `mydb`.`product` (
  `idProduct` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NULL,
  `description` VARCHAR(200) NULL,
  `unit_price` FLOAT NULL,
  `listed` TINYINT NULL,
  `idCategory` INT NULL,
  `sexe` VARCHAR(45) NULL,
  `stock` INT NULL,
  `size` VARCHAR(45) NULL,
  PRIMARY KEY (`idProduct`),
  INDEX `category_idx` (`idCategory` ASC) VISIBLE,
  CONSTRAINT `category`
    FOREIGN KEY (`idCategory`)
    REFERENCES `mydb`.`category` (`idcategory`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`command`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mydb`.`command` ;

CREATE TABLE IF NOT EXISTS `mydb`.`command` (
  `idCommand` INT NOT NULL AUTO_INCREMENT,
  `idUser` INT NULL,
  `date` DATE NULL,
  PRIMARY KEY (`idCommand`),
  INDEX `user_idx` (`idUser` ASC) VISIBLE,
  CONSTRAINT `user`
    FOREIGN KEY (`idUser`)
    REFERENCES `mydb`.`user` (`idUser`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`command_line`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mydb`.`command_line` ;

CREATE TABLE IF NOT EXISTS `mydb`.`command_line` (
  `idCommand` INT NOT NULL,
  `line_number` INT NOT NULL,
  `idProduct` INT NOT NULL,
  `quantity` INT NULL,
  `line_price` INT NULL,
  PRIMARY KEY (`idCommand`, `line_number`),
  INDEX `product_idx` (`idProduct` ASC) VISIBLE,
  CONSTRAINT `command`
    FOREIGN KEY (`idCommand`)
    REFERENCES `mydb`.`command` (`idCommand`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `product`
    FOREIGN KEY (`idProduct`)
    REFERENCES `mydb`.`product` (`idProduct`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
