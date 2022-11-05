var DataTypes = require("sequelize").DataTypes;
var _student_tables = require("./student_tables");
var _tutorials = require("./tutorials");

function initModels(sequelize) {
  var student_tables = _student_tables(sequelize, DataTypes);
  var tutorials = _tutorials(sequelize, DataTypes);


  return {
    student_tables,
    tutorials,
  };
}
module.exports = initModels;
module.exports.initModels = initModels;
module.exports.default = initModels;
