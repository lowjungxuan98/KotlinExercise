# Kotlin Backend

## Table of Contents

- [About](#about)
- [Getting Started](#getting_started)
- [Configuration](#Configuration)

## About <a name = "about"></a>

This backend can be perform normal RESTful API or REALTIME

## Getting Started <a name = "getting_started"></a>

Install required package
```
npm i
```

Setup the Database
```
USE kotlin_exercise; # selected the database that want to use
CREATE TABLE IF NOT EXISTS `student_tables` (
    `id` INTEGER NOT NULL auto_increment , 
    `first_name` VARCHAR(255), 
    `last_name` VARCHAR(255), 
    `roll_no` INTEGER, 
    `createdAt` DATETIME NOT NULL, 
    `updatedAt` DATETIME NOT NULL, PRIMARY KEY (`id`)
) ENGINE=InnoDB;
```

## Configuration <a name = "Configuration"></a>

Perform reset the tables just change the config in `./app/config/index.js`
```
db.sequelize.sync({force:true}) # true = will reset, false = will not reset
```

Perform the auto generate the model
```
sequelize-auto -o "./auto" -d kotlin_exercise -h 127.0.0.1 -u root -p 3306 -x admin -e mysql
```