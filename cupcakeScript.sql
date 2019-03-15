/*CREATE SCHEMA `Cupcakes` ; */

DROP TABLE IF EXISTS `Cupcakes`.`invoice_has_items`;
DROP TABLE IF EXISTS `Cupcakes`.`invoice`;
DROP TABLE IF EXISTS `Cupcakes`.`user`;
DROP TABLE IF EXISTS `Cupcakes`.`cupcake_Bottoms`;
DROP TABLE IF EXISTS `Cupcakes`.`cupcake_Toppings`;

CREATE TABLE `Cupcakes`.`user` (
  `user_id` INT NOT NULL AUTO_INCREMENT,
  `username` VARCHAR(45) NOT NULL UNIQUE,
  `password` VARCHAR(45) NOT NULL,
  `email` VARCHAR(45) NOT NULL,
  `balance` DOUBLE NULL DEFAULT 0,
   `type` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`user_id`));
  

CREATE TABLE `Cupcakes`.`cupcake_Bottoms` (
  `bottom_id` INT NOT NULL AUTO_INCREMENT,
  `bottom_flavour` VARCHAR(45) NOT NULL,
  `bottom_price` DOUBLE NOT NULL,
  PRIMARY KEY (`bottom_id`));
  
  CREATE TABLE `Cupcakes`.`cupcake_Toppings` (
  `topping_id` INT NOT NULL AUTO_INCREMENT,
  `topping_flavour` VARCHAR(45) NOT NULL,
  `topping_price` DOUBLE NOT NULL,
  PRIMARY KEY (`topping_id`));
  
  CREATE TABLE `Cupcakes`.`invoice` (
  `invoice_id` INT NOT NULL AUTO_INCREMENT,
  `user_id` INT NOT NULL,
  `total_price` DOUBLE NOT NULL,
  `order_date` DATETIME NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`invoice_id`),
  INDEX `user_has_invoice_to_user_fk_idx` (`user_id` ASC) VISIBLE,
  CONSTRAINT `user_has_invoice_to_user_fk`
    FOREIGN KEY (`user_id`)
    REFERENCES `Cupcakes`.`user` (`user_id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE);
    
    CREATE TABLE `Cupcakes`.`invoice_has_items` (
  `invoice_id` INT NOT NULL,
  `topping_id` INT NOT NULL,
  `bottom_id` INT NOT NULL,
  `quantity` INT NOT NULL,
  `price` DOUBLE NOT NULL,
  INDEX `invoice_to_bottom_fk_idx` (`bottom_id` ASC) VISIBLE,
  INDEX `invoice_to_invoice_fk_idx` (`invoice_id` ASC) VISIBLE,
  INDEX `invoice_to_topping_fk_idx` (`topping_id` ASC) VISIBLE,
  CONSTRAINT `invoice_to_bottom_fk`
    FOREIGN KEY (`bottom_id`)
    REFERENCES `Cupcakes`.`cupcake_Bottoms` (`bottom_id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `invoice_to_invoice_fk`
    FOREIGN KEY (`invoice_id`)
    REFERENCES `Cupcakes`.`invoice` (`invoice_id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `invoice_to_topping_fk`
    FOREIGN KEY (`topping_id`)
    REFERENCES `Cupcakes`.`cupcake_Toppings` (`topping_id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE);
