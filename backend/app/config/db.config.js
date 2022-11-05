module.exports = {
  username: "root",
  password: "admin",
  database: "kotlin_exercise",
  host: "127.0.0.1",
  dialect: "mysql",
  pool: {
    max: 5,
    min: 0,
    acquire: 30000,
    idle: 10000
  }
};