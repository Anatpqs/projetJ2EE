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
CREATE TABLE IF NOT EXISTS `mydb`.`user` (
  `idUser` INT NOT NULL AUTO_INCREMENT,
  `login` VARCHAR(45) NULL,
  `password` VARCHAR(45) NULL,
  `admin` TINYINT NULL,
  `username` VARCHAR(45) NULL,
  `mail` VARCHAR(70) NULL,
  `loyalty_point` INT NULL,
  `address` VARCHAR(200) NULL,
  `is_validate` TINYINT NULL,
  PRIMARY KEY (`idUser`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`product`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`product` (
  `idProduct` INT NOT NULL AUTO_INCREMENT,
  `stock` INT NULL,
  `name` VARCHAR(45) NULL,
  `description` VARCHAR(200) NULL,
  `unit_price` INT NULL,
  `listed` TINYINT NULL,
  `category` VARCHAR(45) NULL,
  `size` VARCHAR(45) NULL,
  PRIMARY KEY (`idProduct`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`command`
-- -----------------------------------------------------
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
CREATE TABLE IF NOT EXISTS `mydb`.`command_line` (
  `idCommand` INT NOT NULL,
  `idProduct` INT NOT NULL,
  `quantity` INT NULL,
  `line_price` INT NULL,
  `line_number` INT NOT NULL,
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
