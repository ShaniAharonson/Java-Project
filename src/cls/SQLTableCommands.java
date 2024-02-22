package cls;

public class SQLTableCommands {
    // create tables codes by SQL
    public static final String CREATE_COMPANY_TABLE = "" +
            "CREATE TABLE IF NOT EXISTS `javaproject`.`companies` (" +
            "  `id` INT NOT NULL AUTO_INCREMENT," +
            "  `name` VARCHAR(25) NOT NULL," +
            "  `email` VARCHAR(45) NOT NULL," +
            "  `password` VARCHAR(10) NULL," +
            "  PRIMARY KEY (`id`)," +
            "  UNIQUE INDEX `email_UNIQUE` (`email` ASC) VISIBLE);";

    public static final String CREATE_CUSTOMERS_TABLE = "" +
            "CREATE TABLE IF NOT EXISTS `javaproject`.`customers` (" +
            "  `id` INT NOT NULL AUTO_INCREMENT," +
            "  `First_Name` VARCHAR(15) NOT NULL," +
            "  `Last_Name` VARCHAR(15) NOT NULL," +
            "  `email` VARCHAR(45) NOT NULL," +
            "  `password` VARCHAR(10) NULL," +
            "  UNIQUE INDEX `email_UNIQUE` (`email` ASC) VISIBLE," +
            "  UNIQUE INDEX `password_UNIQUE` (`password` ASC) VISIBLE," +
            "  PRIMARY KEY (`id`));";

    public static final String CREATE_CATEGORIES_TABLE = "" +
            "CREATE TABLE IF NOT EXISTS `javaproject`.`categories` (" +
            "  `id` INT NOT NULL AUTO_INCREMENT," +
            "  `name` VARCHAR(25) NOT NULL," +
            "  PRIMARY KEY (`id`)," +
            "  UNIQUE INDEX `name_UNIQUE` (`name` ASC) VISIBLE);";

    public static final String CREATE_COUPONS_TABLE = "" +
            "CREATE TABLE IF NOT EXISTS `javaproject`.`coupons` (" +
            "  `id` INT NOT NULL AUTO_INCREMENT," +
            "  `company_id` INT NOT NULL," +
            "  `category_id` INT NOT NULL," +
            "  `title` VARCHAR(30) NOT NULL," +
            "  `description` VARCHAR(45) NULL," +
            "  `start_date` DATE NOT NULL," +
            "  `end_date` DATE NOT NULL," +
            "  `amount` INT NULL," +
            "  `price` DOUBLE NULL," +
            "  `image` VARCHAR(45) NULL,"+
            "  PRIMARY KEY (`id`)," +

            "  UNIQUE INDEX `description_UNIQUE` (`description` ASC) VISIBLE);";

    public static final String CREATE_CUSTOMERS_VS_COUPONS_TABLE = "" +
            "CREATE TABLE IF NOT EXISTS `javaproject`.`customers_vs_coupons` (" +
            "  `customers_id` INT NOT NULL," +
            "  `coupon_id` INT NOT NULL," +
            "  PRIMARY KEY (`customers_id`, `coupon_id`));";

    public static final String TESTTABLE = "CREATE TABLE IF NOT EXISTS `javaproject`.`new_table` (" +
            "  `id` INT NOT NULL AUTO_INCREMENT," +
            "  `name` VARCHAR(45) NOT NULL," +
            "  `email` VARCHAR(45) NOT NULL," +
            "  UNIQUE INDEX `name_UNIQUE` (`name` ASC) VISIBLE," +
            "  UNIQUE INDEX `email_UNIQUE` (`email` ASC) VISIBLE," +
            "  PRIMARY KEY (`id`))";
}
