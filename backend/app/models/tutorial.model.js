module.exports = (sequelize, Sequelize) => {
    const Tutorial = sequelize.define("student_tables", {
      first_name: {
        type: Sequelize.STRING
      },
      last_name: {
        type: Sequelize.STRING
      },
      roll_no: {
        type: Sequelize.INTEGER
      }
    });
  
    return Tutorial;
  };