# Kotlin Backend

## Table of Contents

- [About](#about)
- [Getting Started](#getting_started)

## About <a name = "about"></a>

Write about 1-2 paragraphs describing the purpose of your project.

## Getting Started <a name = "getting_started"></a>

Perform reset the tables just change the config in `./app/config/index.js`
```
db.sequelize.sync({force:true}) # true = will reset, false = will not reset
```

Perform the auto generate the model
```
sequelize-auto -o "./auto" -d kotlin_exercise -h 127.0.0.1 -u root -p 3306 -x admin -e mysql
```