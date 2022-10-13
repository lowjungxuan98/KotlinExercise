module.exports = {
    HOST: "localhost",
    USER: "root",
    PASSWORD: "gnn12345",
    DB: "kotlin_exercise",
    dialect: "mysql",
    pool: {
        max: 5,
        min: 0,
        acquire: 30000,
        idle: 10000
    }
};