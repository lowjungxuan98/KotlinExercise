module.exports = (sequelize, Sequelize) => {
    const Student = sequelize.define("student_table", {
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

    return Student;
};