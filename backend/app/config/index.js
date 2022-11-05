const dbConfig = require('./db.config.js');
const { Sequelize, DataTypes, QueryTypes } = require('sequelize');

const sequelize = new Sequelize(
    dbConfig.database,
    dbConfig.username,
    dbConfig.password,
    {
        host: dbConfig.host,
        dialect: dbConfig.dialect,
        dialectOptions: {
            decimalNumbers: true,
            // useUTC: false,
            // timezone: '+08:00',
            dateStrings: true,
            typeCast: function (field, next) { // for reading from database
                if (field.type === 'DATETIME') {
                    return field.string()
                }
                return next()
            },
        },
        operatorsAliases: 0,
        define: {
            freezeTableName: true,
            underscored: false,
            timestamps: false
        },
        pool: {
            max: dbConfig.pool.max,
            min: dbConfig.pool.min,
            acquire: dbConfig.pool.acquire,
            idle: dbConfig.pool.idle
        },
        // useUTC: false,
        // timezone: '+08:00'
    }
)

sequelize.authenticate()
    .then(() => {
        console.log('connected..')
    })
    .catch(err => {
        console.log('Error' + err)
    })


module.exports = {
    sequelize,
    DataTypes
}